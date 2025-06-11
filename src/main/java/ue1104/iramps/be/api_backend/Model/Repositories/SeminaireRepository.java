// src/main/java/ue1104/iramps/be/api_backend/Model/Repositories/SeminaireRepository.java
package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ue1104.iramps.be.api_backend.Model.BL.Seminaire;

public interface SeminaireRepository extends JpaRepository<Seminaire, Long> {
    // aucun méthode supplémentaire pour l'instant
}
