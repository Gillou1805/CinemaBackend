package ue1104.iramps.be.api_backend.DTO;

public class LoginRequest {
    private String mail;
    private String mdp;

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }
}
