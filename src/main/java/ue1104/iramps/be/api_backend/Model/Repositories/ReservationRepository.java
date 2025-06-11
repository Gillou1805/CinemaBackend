// src/main/java/ue1104/iramps/be/api_backend/Model/Repositories/ReservationRepository.java
package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ue1104.iramps.be.api_backend.Model.BL.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCreneau_IdCreneau(Long creneauId);
}
