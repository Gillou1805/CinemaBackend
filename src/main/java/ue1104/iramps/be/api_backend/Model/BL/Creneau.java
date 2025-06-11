package ue1104.iramps.be.api_backend.Model.BL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "creneau")
public class Creneau {

	@Id
    @SequenceGenerator(
        name = "creneau_seq",
        sequenceName = "creneau_id_creneau_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "creneau_seq"
    )
	@Column(name = "id_creneau", updatable = false, nullable = false)
    private Long idCreneau;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_calendrier", referencedColumnName = "id_calendrier"),
        @JoinColumn(name = "id_salle", referencedColumnName = "id_salle")
    })
    @JsonBackReference("calendrier-creneaux")
    private Calendrier calendrier;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "heure_debut", nullable = false)
    private LocalDateTime heureDebut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "heure_fin", nullable = false)
    private LocalDateTime heureFin;

    @Column(name = "type", nullable = false)
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "id_film", nullable = true)
    private Film film;
    
    @OneToOne(mappedBy = "creneau", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cren-seminaire")
    private Seminaire seminaire;

    public Creneau() {}

    public Long getIdCreneau() { return idCreneau; }
    public void setIdCreneau(Long idCreneau) { this.idCreneau = idCreneau; }

    public Calendrier getCalendrier() { return calendrier; }
    public void setCalendrier(Calendrier calendrier) { this.calendrier = calendrier; }

    public LocalDateTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalDateTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalDateTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalDateTime heureFin) { this.heureFin = heureFin; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }
    
    public Seminaire getSeminaire() { return seminaire; }
    public void setSeminaire(Seminaire seminaire) { this.seminaire = seminaire; }
}
