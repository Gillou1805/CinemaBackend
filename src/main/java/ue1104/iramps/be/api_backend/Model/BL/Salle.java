package ue1104.iramps.be.api_backend.Model.BL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salle")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salle")
    private Long idSalle;

    @Column(name = "capacite")
    private Integer capacite;

    @Column(name = "photo")
    private String photo;

    @Column(name = "nbr_siege_std")
    private Integer nbrSiegeStd;

    @Column(name = "nbr_siege_special")
    private Integer nbrSiegeSpecial;

    @Column(name = "nbr_siege_pmr")
    private Integer nbrSiegePmr;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cinema")
    @JsonBackReference("cinema-salles")
    private Cinema cinema;

    @OneToMany(mappedBy = "salle",
    	      cascade = CascadeType.REMOVE,    //  supprime aussi les calendriers liés
    	      orphanRemoval = true)
    @JsonManagedReference("salle-calendriers")
    private List<Calendrier> calendriers = new ArrayList<>();
    

    


    public Cinema getCinema() { return cinema; }
    public void setCinema(Cinema cinema) { this.cinema = cinema; }


    //  Constructeurs 
    public Salle() {
    }

    public Salle(Integer capacite, String photo, Integer nbrSiegeStd,
                 Integer nbrSiegeSpecial, Integer nbrSiegePmr, String description) {
        this.capacite = capacite;
        this.photo = photo;
        this.nbrSiegeStd = nbrSiegeStd;
        this.nbrSiegeSpecial = nbrSiegeSpecial;
        this.nbrSiegePmr = nbrSiegePmr;
        this.description = description;
    }

    // --- Getters & setters ---
    public Long getIdSalle() { return idSalle; }
    public void setIdSalle(Long idSalle) { this.idSalle = idSalle; }

    public Integer getCapacite() { return capacite; }
    public void setCapacite(Integer capacite) { this.capacite = capacite; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public Integer getNbrSiegeStd() { return nbrSiegeStd; }
    public void setNbrSiegeStd(Integer nbrSiegeStd) { this.nbrSiegeStd = nbrSiegeStd; }

    public Integer getNbrSiegeSpecial() { return nbrSiegeSpecial; }
    public void setNbrSiegeSpecial(Integer nbrSiegeSpecial) { this.nbrSiegeSpecial = nbrSiegeSpecial; }

    public Integer getNbrSiegePmr() { return nbrSiegePmr; }
    public void setNbrSiegePmr(Integer nbrSiegePmr) { this.nbrSiegePmr = nbrSiegePmr; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Calendrier> getCalendriers() { return calendriers; }
    public void setCalendriers(List<Calendrier> calendriers) { this.calendriers = calendriers; }
    
}

