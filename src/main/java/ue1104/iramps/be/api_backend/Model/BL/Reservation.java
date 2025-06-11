package ue1104.iramps.be.api_backend.Model.BL;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creneau", nullable = false)
    @JsonBackReference
    private Creneau creneau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @JsonBackReference
    private Utilisateur user;
    
    @OneToMany(
    	      mappedBy = "reservation",
    	      cascade = CascadeType.ALL,
    	      orphanRemoval = true
    	    )
    @JsonManagedReference 
    	    private List<Siege> sieges = new ArrayList<>();

    @Column(name = "nb_siege_std",     nullable = false) private Integer nbSiegeStd;
    @Column(name = "prix_siege_std",   nullable = false) private Double  prixSiegeStd;
    @Column(name = "nb_siege_special", nullable = false) private Integer nbSiegeSpecial;
    @Column(name = "prix_siege_special", nullable = false) private Double prixSiegeSpecial;
    @Column(name = "nb_siege_pmr",     nullable = false) private Integer nbSiegePmr;
    @Column(name = "prix_siege_pmr",   nullable = false) private Double  prixSiegePmr;

    

    //  Helper pour ajouter un siège 
    public void addSiege(Siege s) {
      sieges.add(s);
      s.setReservation(this);
    }

    // Getters & setters 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Creneau getCreneau() { return creneau; }
    public void setCreneau(Creneau creneau) { this.creneau = creneau; }

    public Utilisateur getUser() { return user; }
    public void setUser(Utilisateur user) { this.user = user; }

    public Integer getNbSiegeStd() { return nbSiegeStd; }
    public void setNbSiegeStd(Integer nb) { this.nbSiegeStd = nb; }

    public Double getPrixSiegeStd() { return prixSiegeStd; }
    public void setPrixSiegeStd(Double p) { this.prixSiegeStd = p; }

    public Integer getNbSiegeSpecial() { return nbSiegeSpecial; }
    public void setNbSiegeSpecial(Integer nb) { this.nbSiegeSpecial = nb; }

    public Double getPrixSiegeSpecial() { return prixSiegeSpecial; }
    public void setPrixSiegeSpecial(Double p) { this.prixSiegeSpecial = p; }

    public Integer getNbSiegePmr() { return nbSiegePmr; }
    public void setNbSiegePmr(Integer nb) { this.nbSiegePmr = nb; }

    public Double getPrixSiegePmr() { return prixSiegePmr; }
    public void setPrixSiegePmr(Double p) { this.prixSiegePmr = p; }

    public List<Siege> getSieges() {
        return sieges;
    }
    public void setSieges(List<Siege> sieges) {
        this.sieges = sieges;
    }
}
