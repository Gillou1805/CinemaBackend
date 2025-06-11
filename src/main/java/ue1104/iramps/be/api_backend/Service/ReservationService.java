// src/main/java/ue1104/iramps/be/api_backend/Service/ReservationService.java
package ue1104.iramps.be.api_backend.Service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ue1104.iramps.be.api_backend.DTO.ReservationDto;
import ue1104.iramps.be.api_backend.Model.BL.Creneau;
import ue1104.iramps.be.api_backend.Model.BL.Reservation;
import ue1104.iramps.be.api_backend.Model.BL.Siege;
import ue1104.iramps.be.api_backend.Model.BL.Utilisateur;
import ue1104.iramps.be.api_backend.Model.Repositories.CreneauRepository;
import ue1104.iramps.be.api_backend.Model.Repositories.ReservationRepository;
import ue1104.iramps.be.api_backend.Model.Repositories.SiegeRepository;
import ue1104.iramps.be.api_backend.Model.Repositories.UtilisateurRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ReservationService {

    private final ReservationRepository   reservationRepo;
    private final CreneauRepository       creneauRepo;
    private final UtilisateurRepository   userRepo;
    private final SiegeRepository         siegeRepo;    // ← ajouté

    public ReservationService(ReservationRepository reservationRepo,
                              CreneauRepository creneauRepo,
                              UtilisateurRepository userRepo,
                              SiegeRepository siegeRepo) {      // ← modifié
        this.reservationRepo = reservationRepo;
        this.creneauRepo     = creneauRepo;
        this.userRepo        = userRepo;
        this.siegeRepo       = siegeRepo;               // ← initialisation
    }

    /** Liste des réservations pour un créneau */
    public List<Reservation> findByCreneau(Long creneauId) {
        return reservationRepo.findByCreneau_IdCreneau(creneauId);
    }

    /** Création d’une nouvelle réservation + création des Siege associés */
    @Transactional
    public Reservation create(Long creneauId, ReservationDto dto) {
        // --- Récupération creneau + user
        Creneau c = creneauRepo.findById(creneauId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Créneau introuvable"));
        Utilisateur u = userRepo.findById(dto.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilisateur introuvable"));

        // --- Création et sauvegarde de la réservation
        Reservation r = new Reservation();
        r.setCreneau(c);
        r.setUser(u);
        r.setNbSiegeStd(dto.getNbSiegeStd());
        r.setNbSiegeSpecial(dto.getNbSiegeSpecial());
        r.setNbSiegePmr(dto.getNbSiegePmr());

        // prix par défaut 0.0 si null
        r.setPrixSiegeStd(     c.getFilm().getPrixSiegeStd()     != null ? c.getFilm().getPrixSiegeStd()     : 0.0);
        r.setPrixSiegeSpecial( c.getFilm().getPrixSiegeSpecial() != null ? c.getFilm().getPrixSiegeSpecial() : 0.0);
        r.setPrixSiegePmr(     c.getFilm().getPrixSiegePmr()     != null ? c.getFilm().getPrixSiegePmr()     : 0.0);

        Reservation saved = reservationRepo.save(r);

        // --- Création des Siege associés
        for (Long pos : dto.getSeatIds()) {
            Siege s = new Siege();
            s.setPosition(pos.intValue());   // ← requires ce setter
            s.setReservation(saved);
            siegeRepo.save(s);
        }

        return saved;
    }
}
