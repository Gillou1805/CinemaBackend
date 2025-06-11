// src/main/java/ue1104/iramps/be/api_backend/Controller/FilmController.java
package ue1104.iramps.be.api_backend.Controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ue1104.iramps.be.api_backend.Model.BL.Creneau;
import ue1104.iramps.be.api_backend.Model.BL.Film;
import ue1104.iramps.be.api_backend.Model.Repositories.FilmRepository;
import ue1104.iramps.be.api_backend.Service.CreneauService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmRepository repo;
    private final CreneauService creneauService;

    public FilmController(FilmRepository repo,
                          CreneauService creneauService) {
        this.repo = repo;
        this.creneauService = creneauService;
    }

    @GetMapping
    public List<Film> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Film one(@PathVariable Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.NOT_FOUND, "Film introuvable"
                   ));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film cree = repo.save(film);
        return ResponseEntity
                .created(URI.create("/api/films/" + cree.getIdFilm()))
                .body(cree);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        Film exist = repo.findById(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film introuvable"));
        exist.setTitre(film.getTitre());
        exist.setDuree(film.getDuree());
        exist.setDescription(film.getDescription());
        exist.setImage(film.getImage());
        exist.setPrixSiegeStd(film.getPrixSiegeStd());
        exist.setPrixSiegeSpecial(film.getPrixSiegeSpecial());
        exist.setPrixSiegePmr(film.getPrixSiegePmr());
        exist.setTrailerUrl(film.getTrailerUrl());
        Film updated = repo.save(exist);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film introuvable");
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * NOUVEL ENDPOINT : renvoie les créneaux de type "film" pour
     * un film donné à la date spécifiée.
     */
    @GetMapping("/{filmId}/creneaux")
    public List<Creneau> getCreneauxPourFilm(
        @PathVariable Long filmId,
        @RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date
    ) {
        return creneauService.getByFilmAndDate(filmId, date);
    }

}
