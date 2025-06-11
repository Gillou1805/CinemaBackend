package ue1104.iramps.be.api_backend.Model.BL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_film")
    private Long idFilm;

    @Column(name = "titre")
    private String titre;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "prix_siege_std")
    private Double prixSiegeStd;

    @Column(name = "prix_siege_special")
    private Double prixSiegeSpecial;

    @Column(name = "prix_siege_pmr")
    private Double prixSiegePmr;
    
    @Column(name = "trailer_url")
    private String trailerUrl;

    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonBackReference("film-creneaux")
    private List<Creneau> creneaux;

    // --- Constructeurs ---
    public Film() {}

    public Film(String titre,
                Integer duree,
                String description,
                String image,
                Double prixSiegeStd,
                Double prixSiegeSpecial,
                Double prixSiegePmr) {
        this.titre = titre;
        this.duree = duree;
        this.description = description;
        this.image = image;
        this.prixSiegeStd = prixSiegeStd;
        this.prixSiegeSpecial = prixSiegeSpecial;
        this.prixSiegePmr = prixSiegePmr;
    }

    // --- Getters & setters ---
    public Long getIdFilm() {
        return idFilm;
    }
    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getDuree() {
        return duree;
    }
    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrixSiegeStd() {
        return prixSiegeStd;
    }
    public void setPrixSiegeStd(Double prixSiegeStd) {
        this.prixSiegeStd = prixSiegeStd;
    }

    public Double getPrixSiegeSpecial() {
        return prixSiegeSpecial;
    }
    public void setPrixSiegeSpecial(Double prixSiegeSpecial) {
        this.prixSiegeSpecial = prixSiegeSpecial;
    }

    public Double getPrixSiegePmr() {
        return prixSiegePmr;
    }
    public void setPrixSiegePmr(Double prixSiegePmr) {
        this.prixSiegePmr = prixSiegePmr;
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }
    public void setCreneaux(List<Creneau> creneaux) {
        this.creneaux = creneaux;
    }
    
    public String getTrailerUrl() {
        return trailerUrl;
    }
    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
