package ue1104.iramps.be.api_backend.DTO;

public class RegisterRequest {
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;

    // getters et setters sans role



    // getters & setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }

    
}
