package ue1104.iramps.be.api_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ue1104.iramps.be.api_backend.DTO.UpdateRoleRequest;          
import ue1104.iramps.be.api_backend.Model.BL.Utilisateur;
import ue1104.iramps.be.api_backend.Model.Repositories.UtilisateurRepository;
import ue1104.iramps.be.api_backend.Service.UtilisateurService;       

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;   

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour le rôle d'un utilisateur
    @PutMapping("/{userId}/role")
    public ResponseEntity<Utilisateur> updateRole(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateRoleRequest payload   //  ici le DTO est désormais reconnu
    ) {
        // payload.getRole() vaut exactement "ADMIN" ou "USER"
        Utilisateur updated = utilisateurService.updateRole(userId, payload.getRole());
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Ajouter un nouvel utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur newUser) {
        Utilisateur savedUser = utilisateurRepository.save(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String mail) {
      return ResponseEntity.ok(utilisateurRepository.existsByMail(mail));
    }
}
