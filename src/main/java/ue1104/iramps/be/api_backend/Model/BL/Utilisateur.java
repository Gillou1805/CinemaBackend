package ue1104.iramps.be.api_backend.Model.BL;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String mdp;    // mot de passe encodé (BCrypt)

    @Column(nullable = false)
    private String role;   // ex. "USER" ou "ADMIN"

    @Column(nullable = false, unique = true)
    private String mail;   // login unique

    public Utilisateur() {}

    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
}
