// src/main/java/ue1104/iramps/be/api_backend/Service/CreneauService.java
package ue1104.iramps.be.api_backend.Service;

import org.springframework.stereotype.Service;
import ue1104.iramps.be.api_backend.Model.BL.Creneau;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.CalendrierKey;
import ue1104.iramps.be.api_backend.Model.Repositories.CreneauRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;  

@Service
public class CreneauService {

    private final CreneauRepository repo;

    public CreneauService(CreneauRepository repo) {
        this.repo = repo;
    }

    /** Récupère tous les créneaux d’un calendrier */
    public List<Creneau> getByCalendrier(CalendrierKey key) {
        return repo.findByCalendrier_Id(key);
    }

    /**
     * Récupère tous les créneaux “film” pour un film donné à une date
     */
    public List<Creneau> getByFilmAndDate(Long filmId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end   = date.atTime(LocalTime.MAX);
        return repo.findCreneauxByFilmAndDate(filmId, start, end);
    }

    /** Sauvegarde (création ou mise à jour) */  
    public Creneau save(Creneau creneau) {
        return repo.save(creneau);
    }

    /** Suppression par ID */
    public void delete(Long id) {
        repo.deleteById(id);
    }

    /**
     * Récupère un seul créneau par son ID,
     * pour exposer film + tarifs côté front
     */
    public Optional<Creneau> findById(Long id) {
        return repo.findById(id);
    }
}
