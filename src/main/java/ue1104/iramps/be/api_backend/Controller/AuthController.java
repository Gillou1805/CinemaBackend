package ue1104.iramps.be.api_backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ue1104.iramps.be.api_backend.DTO.RegisterRequest;
import ue1104.iramps.be.api_backend.Model.BL.Utilisateur;
import ue1104.iramps.be.api_backend.Model.Repositories.UtilisateurRepository;

// point d’entrée REST dédié à l’inscription et à l’authentification des utilisateurs
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtilisateurRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(UtilisateurRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
   // Empêche l’inscription multiple sur le même email.
    public ResponseEntity<String> register(@RequestBody RegisterRequest dto) {
        if (repo.findByMail(dto.getMail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }
        Utilisateur u = new Utilisateur();
        u.setNom(dto.getNom());
        u.setPrenom(dto.getPrenom());
        u.setMail(dto.getMail());
        u.setRole("USER");  //  forcé ici, pas pris du front
        u.setMdp(encoder.encode(dto.getMdp())); //Stocke les mots de passe de façon sécurisée (hachage BCrypt).
        repo.save(u);
        return ResponseEntity.ok("Inscription réussie");
    }



}
