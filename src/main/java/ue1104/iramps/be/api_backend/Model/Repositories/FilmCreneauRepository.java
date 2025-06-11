// src/main/java/ue1104/iramps/be/api_backend/Model/IntermediateTable/FilmCreneauRepository.java
package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ue1104.iramps.be.api_backend.Model.IntermediateTable.FilmCreneau;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.FilmCreneauId;

import java.util.List;

@Repository
public interface FilmCreneauRepository extends JpaRepository<FilmCreneau, FilmCreneauId> {
    /**
     * Trouve toutes les associations pour un film donné (par identifiant de clé composite)
     */
    List<FilmCreneau> findByIdFilmId(Long filmId);

    /**
     * Trouve toutes les associations pour un créneau donné (par identifiant de clé composite)
     */
    List<FilmCreneau> findByIdCreneauId(Long creneauId);
}
