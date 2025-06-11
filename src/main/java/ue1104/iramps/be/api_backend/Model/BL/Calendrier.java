package ue1104.iramps.be.api_backend.Model.BL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.CalendrierKey;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "calendrier")
public class Calendrier {

    @EmbeddedId
    private CalendrierKey id;

    @ManyToOne
    @MapsId("idSalle")
    @JoinColumn(name = "id_salle")
    @JsonBackReference("salle-calendriers")
    private Salle salle;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "calendrier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("calendrier-creneaux")
    private Set<Creneau> creneaux;

    // --- Constructeurs ---
    public Calendrier() {}

    // getters & setters
    public CalendrierKey getId() { return id; }
    public void setId(CalendrierKey id) { this.id = id; }
    public Salle getSalle() { return salle; }
    public void setSalle(Salle salle) { this.salle = salle; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Set<Creneau> getCreneaux() { return creneaux; }
    public void setCreneaux(Set<Creneau> creneaux) { this.creneaux = creneaux; }
}
