package ue1104.iramps.be.api_backend.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ue1104.iramps.be.api_backend.Model.BL.Cinema;
import ue1104.iramps.be.api_backend.Model.BL.Salle;
import ue1104.iramps.be.api_backend.Model.Repositories.CinemaRepository;
import ue1104.iramps.be.api_backend.Model.Repositories.SalleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    private final SalleRepository repo;
    private final CinemaRepository cinemaRepo;
    private final CalendrierService calendrierService;

    public SalleService(SalleRepository repo,
                        CinemaRepository cinemaRepo,
                        CalendrierService calendrierService) {
        this.repo = repo;
        this.cinemaRepo = cinemaRepo;
        this.calendrierService = calendrierService;
    }

    public List<Salle> findAll() {
        return repo.findAll();
    }

    public Optional<Salle> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Salle save(Salle salle) {
        if (salle.getCinema() == null || salle.getCinema().getIdCinema() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Il faut fournir cinema.idCinema dans le JSON"
            );
        }

        Long cid = salle.getCinema().getIdCinema();

        Cinema cinema = cinemaRepo.findById(cid)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Aucun Cinema trouve pour id = " + cid
            ));

        salle.setCinema(cinema);

        Salle savedSalle = repo.save(salle);

        // Créer le calendrier lié automatiquement s'il n'existe pas
        calendrierService.getCalendrierBySalle(savedSalle.getIdSalle());

        return savedSalle;
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
