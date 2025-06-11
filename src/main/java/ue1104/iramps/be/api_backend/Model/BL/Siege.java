package ue1104.iramps.be.api_backend.Model.BL;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "siege")
public class Siege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siege")
    private Long id;

    /** catégorie  */
    @Column(name = "categorie", nullable = true)
    private String categorie;

    /**
     * Le numéro / position du siège dans la salle
     */
    @Column(name = "no_siege", nullable = false)
    private Integer position;

    /** Réservation à laquelle ce siège appartient */
    @ManyToOne(fetch = FetchType.LAZY) //retarde le chargement de la Réservation jusqu’à son premier accès
    @JoinColumn(name = "id_reservation", nullable = false)
    @JsonBackReference
    private Reservation reservation;

    // Getters & setters 

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }

    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
