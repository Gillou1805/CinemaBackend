package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ue1104.iramps.be.api_backend.Model.BL.Salle;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "salle", path = "salle")
public interface SalleRepository extends JpaRepository<Salle, Long> {

    List<Salle> findByCapacite(Integer capacite);
    // plus besoin de findByIdSalle ni de CrudRepository explicite
}

