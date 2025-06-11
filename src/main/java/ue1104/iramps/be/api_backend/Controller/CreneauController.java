package ue1104.iramps.be.api_backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ue1104.iramps.be.api_backend.DTO.CreneauDto;
import ue1104.iramps.be.api_backend.Model.BL.Creneau;
import ue1104.iramps.be.api_backend.Model.BL.Calendrier;
import ue1104.iramps.be.api_backend.Model.BL.Film;
import ue1104.iramps.be.api_backend.Model.BL.Seminaire;
import ue1104.iramps.be.api_backend.Model.BL.Salle;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.CalendrierKey;
import ue1104.iramps.be.api_backend.Model.Repositories.FilmRepository;
import ue1104.iramps.be.api_backend.Model.Repositories.SeminaireRepository;
import ue1104.iramps.be.api_backend.Service.CreneauService;

import java.util.List;

@RestController
@RequestMapping("/api/salles/{salleId}/calendrier/{idCalendrier}/creneaux")
public class CreneauController {

    private final CreneauService     service;
    private final FilmRepository     filmRepo;
    private final SeminaireRepository seminaireRepo;

    public CreneauController(CreneauService service,
                             FilmRepository filmRepo,
                             SeminaireRepository seminaireRepo) {
        this.service      = service;
        this.filmRepo     = filmRepo;
        this.seminaireRepo = seminaireRepo;
    }

    //  lister tous les créneaux d’un calendrier 
    @GetMapping
    public List<Creneau> list(
        @PathVariable Long salleId,
        @PathVariable Integer idCalendrier
    ) {
        CalendrierKey key = new CalendrierKey(idCalendrier, salleId);
        return service.getByCalendrier(key);
    }

    // 2) POST : créer un créneau à partir d’un DTO 
    @PostMapping
    public Creneau create(
        @PathVariable Long salleId,
        @PathVariable Integer idCalendrier,
        @RequestBody CreneauDto dto
    ) {
        // 1) rattacher la clé composite
        CalendrierKey key = new CalendrierKey(idCalendrier, salleId);
        Calendrier cal = new Calendrier();
        cal.setId(key);
        Salle salle = new Salle();
        salle.setIdSalle(salleId);
        cal.setSalle(salle);

        // 2) construire l’entité Creneau
        Creneau c = new Creneau();
        c.setCalendrier(cal);
        c.setType(dto.getType());
        c.setHeureDebut(dto.getHeureDebut());
        c.setHeureFin(dto.getHeureFin());

        // 3) si film, récupérer l’entité Film
        if ("film".equalsIgnoreCase(dto.getType())) {
            Long filmId = dto.getFilmId();
            if (filmId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Pour un créneau de type film, 'filmId' est obligatoire");
            }
            Film film = filmRepo.findById(filmId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Film introuvable"));
            c.setFilm(film);
        }

        // 4) si séminaire, créer l’entité Seminaire
        if ("seminaire".equalsIgnoreCase(dto.getType())) {
            String titre    = dto.getSeminaireTitre();
            String formateur = dto.getFormateur();
            if (titre == null || titre.isBlank()
             || formateur == null || formateur.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Pour un créneau de type séminaire, 'seminaireTitre' et 'formateur' sont obligatoires");
            }
            // On ne peut pas encore persister Seminaire car il faut attendre
            // que Creneau ait un ID généré. On persistera juste après la sauvegarde du créneau.
        }

        // 5) enregistrer d’abord le Creneau (sans le seminaire)
        Creneau saved = service.save(c);

        // 6) si c’était un séminaire, on le persiste maintenant
        if ("seminaire".equalsIgnoreCase(dto.getType())) {
            Seminaire s = new Seminaire();
            s.setTitre(dto.getSeminaireTitre());
            s.setFormateur(dto.getFormateur());
            s.setCreneau(saved);
            seminaireRepo.save(s);
        }

        return saved;
    }

    /** 3) PUT : mettre à jour un créneau existant */
    @PutMapping("/{id}")
    public Creneau update(
        @PathVariable Long salleId,
        @PathVariable Integer idCalendrier,
        @PathVariable Long id,
        @RequestBody CreneauDto dto
    ) {
        // 1) on conserve l’ID dans une instance vierge
        Creneau c = new Creneau();
        c.setIdCreneau(id);

        // 2) rattacher calendrier + salle
        CalendrierKey key = new CalendrierKey(idCalendrier, salleId);
        Calendrier cal = new Calendrier();
        cal.setId(key);
        Salle salle = new Salle();
        salle.setIdSalle(salleId);
        cal.setSalle(salle);
        c.setCalendrier(cal);

        // 3) mettre à jour les champs
        c.setType(dto.getType());
        c.setHeureDebut(dto.getHeureDebut());
        c.setHeureFin(dto.getHeureFin());

        // 4) si film, récupérer l’entité Film
        if ("film".equalsIgnoreCase(dto.getType())) {
            Long filmId = dto.getFilmId();
            if (filmId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Pour un créneau de type film, 'filmId' est obligatoire");
            }
            Film film = filmRepo.findById(filmId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Film introuvable"));
            c.setFilm(film);
        }

        // 5) si séminaire, on met à jour (ou on crée) l’entité Seminaire liée
        if ("seminaire".equalsIgnoreCase(dto.getType())) {
            String titre     = dto.getSeminaireTitre();
            String formateur = dto.getFormateur();
            if (titre == null || titre.isBlank()
             || formateur == null || formateur.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Pour un créneau de type séminaire, 'seminaireTitre' et 'formateur' sont obligatoires");
            }
        }

        // 6) enregistrer le Creneau mis à jour
        Creneau updated = service.save(c);

        // 7) si séminaire, traiter l’insertion / mise à jour du Seminaire
        if ("seminaire".equalsIgnoreCase(dto.getType())) {
            // On tente de récupérer un Seminaire existant pour ce créneau
            Seminaire s = seminaireRepo.findById(updated.getIdCreneau())
                              .orElse(new Seminaire());
            s.setTitre(dto.getSeminaireTitre());
            s.setFormateur(dto.getFormateur());
            s.setCreneau(updated);
            seminaireRepo.save(s);
        }

        return updated;
    }

    /** 4) DELETE : supprimer */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public Creneau getOne(
        @PathVariable Long salleId,
        @PathVariable Integer idCalendrier,
        @PathVariable Long id
    ) {
        return service.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
