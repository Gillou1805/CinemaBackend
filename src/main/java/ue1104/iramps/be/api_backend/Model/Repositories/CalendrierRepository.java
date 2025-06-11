package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ue1104.iramps.be.api_backend.Model.BL.Calendrier;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.CalendrierKey;

import java.util.Optional;

public interface CalendrierRepository extends JpaRepository<Calendrier, CalendrierKey> {

    @Query("SELECT c FROM Calendrier c WHERE c.salle.idSalle = :idSalle")
    Optional<Calendrier> findBySalleId(Long idSalle);

    @Query("SELECT MAX(c.id.idCalendrier) FROM Calendrier c")
    Integer findMaxIdCalendrier();
}
	