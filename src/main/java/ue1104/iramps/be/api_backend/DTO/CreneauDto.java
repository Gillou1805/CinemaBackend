package ue1104.iramps.be.api_backend.DTO;

import java.time.LocalDateTime;

//le CreneauDto sert à définir clairement ce qu’on peut envoyer et recevoir pour un créneau, à encapsuler la logique de validation et à découpler le format JSON du modèle de persistence
public class CreneauDto {
    private Long   idCreneau;      // pour la MAJ, ou null à la création
    private Integer idCalendrier;
    private Long     idSalle;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private String    type;        // "film", "reservation", "seminaire", etc.
    private Long      filmId;      // renseigné seulement si type="film"

    // NOUVEAU : données pour séminaire
    private String seminaireTitre; // obligatoire si type="seminaire"
    private String formateur;      // obligatoire si type="seminaire"

    // --- getters & setters ---
    public Long getIdCreneau() { return idCreneau; }
    public void setIdCreneau(Long idCreneau) { this.idCreneau = idCreneau; }

    public Integer getIdCalendrier() { return idCalendrier; }
    public void setIdCalendrier(Integer idCalendrier) { this.idCalendrier = idCalendrier; }

    public Long getIdSalle() { return idSalle; }
    public void setIdSalle(Long idSalle) { this.idSalle = idSalle; }

    public LocalDateTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalDateTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalDateTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalDateTime heureFin) { this.heureFin = heureFin; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getFilmId() { return filmId; }
    public void setFilmId(Long filmId) { this.filmId = filmId; }

    // GET/SET seminaireTitre & formateur
    public String getSeminaireTitre() { return seminaireTitre; }
    public void setSeminaireTitre(String seminaireTitre) { this.seminaireTitre = seminaireTitre; }

    public String getFormateur() { return formateur; }
    public void setFormateur(String formateur) { this.formateur = formateur; }
}
