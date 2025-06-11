// src/main/java/ue1104/iramps/be/api_backend/Model/Repositories/SiegeRepository.java
package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ue1104.iramps.be.api_backend.Model.BL.Siege;
import java.util.List;

public interface SiegeRepository extends JpaRepository<Siege, Long> {

    // Renvoie tous les Siege pour un créneau donné (via Reservation → Creneau)
    List<Siege> findByReservation_Creneau_IdCreneau(Long creneauId);
}
