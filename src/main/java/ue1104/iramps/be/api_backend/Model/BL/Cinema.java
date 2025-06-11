package ue1104.iramps.be.api_backend.Model.BL;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cinema")
    private Long idCinema;

    @Column(name = "nom", length = 255, nullable = false)
    private String nom;

    @Column(name = "adresse", columnDefinition = "TEXT")
    private String adresse;

    @OneToMany(mappedBy = "cinema")
    @JsonManagedReference("cinema-salles")
    private List<Salle> salles = new ArrayList<>();

    // --- Constructeurs ---
    public Cinema() {
    }

    public Cinema(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    // --- Getters & setters ---
    public Long getIdCinema() { return idCinema; }
    public void setIdCinema(Long idCinema) { this.idCinema = idCinema; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public List<Salle> getSalles() { return salles; }
    public void setSalles(List<Salle> salles) { this.salles = salles; }
}

