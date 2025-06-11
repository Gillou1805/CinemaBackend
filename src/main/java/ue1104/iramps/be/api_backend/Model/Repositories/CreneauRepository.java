package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ue1104.iramps.be.api_backend.Model.BL.Creneau;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.CalendrierKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CreneauRepository extends JpaRepository<Creneau, Long> {

    List<Creneau> findByCalendrier_Id(CalendrierKey key);

    @Query("""
      SELECT c
        FROM Creneau c
       WHERE c.type = 'film'
         AND c.film.idFilm = :filmId
         AND c.heureDebut BETWEEN :start AND :end
    """)
    List<Creneau> findCreneauxByFilmAndDate(
      @Param("filmId") Long filmId,
      @Param("start")   LocalDateTime start,
      @Param("end")     LocalDateTime end
    );
}
