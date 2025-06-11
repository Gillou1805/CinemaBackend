package ue1104.iramps.be.api_backend.Model.Repositories;

import ue1104.iramps.be.api_backend.Model.BL.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByMail(String mail);
    boolean existsByMail(String mail);

}
