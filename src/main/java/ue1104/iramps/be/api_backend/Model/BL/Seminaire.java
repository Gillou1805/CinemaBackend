package ue1104.iramps.be.api_backend.Model.BL;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "seminaire")
public class Seminaire {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seminaire", updatable = false, nullable = false)
    private Long idSeminaire;

    @Column(name = "titre", length = 50, nullable = false)
    private String titre;

    @Column(name = "formateur", length = 50, nullable = false)
    private String formateur;

    // association vers Creneau 
    @OneToOne
    @JoinColumn(name = "id_creneau")
    @JsonBackReference("cren-seminaire")
    private Creneau creneau;

    public Seminaire() {}

    public Long getIdSeminaire() { return idSeminaire; }
    public void setIdSeminaire(Long idSeminaire) { this.idSeminaire = idSeminaire; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getFormateur() { return formateur; }
    public void setFormateur(String formateur) { this.formateur = formateur; }

    public Creneau getCreneau() { return creneau; }
    public void setCreneau(Creneau creneau) { this.creneau = creneau; }
}
