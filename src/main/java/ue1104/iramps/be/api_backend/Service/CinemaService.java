package ue1104.iramps.be.api_backend.Service;

import org.springframework.stereotype.Service;
import ue1104.iramps.be.api_backend.Model.BL.Cinema;
import ue1104.iramps.be.api_backend.Model.Repositories.CinemaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    private final CinemaRepository repo;

    public CinemaService(CinemaRepository repo) {
        this.repo = repo;
    }

    public List<Cinema> findAll() {
        return repo.findAll();
    }

    public Optional<Cinema> findById(Long id) {
        return repo.findById(id);
    }

    public Cinema save(Cinema cinema) {
        return repo.save(cinema);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
