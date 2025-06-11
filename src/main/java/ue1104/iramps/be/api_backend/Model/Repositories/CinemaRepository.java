package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ue1104.iramps.be.api_backend.Model.BL.Cinema;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cinema", path = "cinema")
public interface CinemaRepository
        extends JpaRepository<Cinema, Long> {

    List<Cinema> findByNom(String nom);
}
