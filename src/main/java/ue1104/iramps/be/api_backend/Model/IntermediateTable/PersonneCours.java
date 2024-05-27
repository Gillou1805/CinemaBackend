package ue1104.iramps.be.api_backend.Model.IntermediateTable;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import ue1104.iramps.be.api_backend.Model.BL.Cours;
import ue1104.iramps.be.api_backend.Model.BL.Personne;

@Entity
public class PersonneCours {
    @EmbeddedId
    PersonneCoursKey id;

    @ManyToOne
    @MapsId("personneID")
    Personne personne;

    @ManyToOne
    @MapsId("coursID")
    Cours cours;


    public PersonneCours(){}

    public PersonneCours(Personne personne, Cours cours, LocalDate dateDebut, LocalDate dateFin) {
        this.personne = personne;
        this.cours = cours;
        this.id = new PersonneCoursKey(personne.getId(), cours.getId(), dateDebut, dateFin);
    }
    public PersonneCoursKey getId() {
        return id;
    }
    public void setId(PersonneCoursKey id) {
        this.id = id;
    }
    public Personne getPersonne() {
        return personne;
    }
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    public Cours getCours() {
        return cours;
    }
    public void setCours(Cours cours) {
        this.cours = cours;
    }
}
