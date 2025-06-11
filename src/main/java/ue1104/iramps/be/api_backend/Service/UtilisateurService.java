package ue1104.iramps.be.api_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ue1104.iramps.be.api_backend.Model.BL.Utilisateur;
import ue1104.iramps.be.api_backend.Model.Repositories.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
   


    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUserById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur createUser(Utilisateur newUser) {
        return utilisateurRepository.save(newUser);
    }

    public Utilisateur updateRole(Long id, String newRole) {
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        if (user.isPresent()) {
            Utilisateur updatedUser = user.get();
            updatedUser.setRole(newRole);
            return utilisateurRepository.save(updatedUser);
        }
        return null;
    }
    
    

    public void deleteUser(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
