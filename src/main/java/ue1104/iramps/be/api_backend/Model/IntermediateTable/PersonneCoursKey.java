package ue1104.iramps.be.api_backend.Model.IntermediateTable;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonneCoursKey implements Serializable {

    @Column(name = "personne_id")
    private int personneID;

    @Column(name = "cours_id")
    private int coursID;

    @Column(name =  "date_debut")
    LocalDate dateDebut;

    @Column(name = "date_fin")
    LocalDate dateFin;

    public PersonneCoursKey() {
    }    

    public PersonneCoursKey(int personneID, int coursID) {
        this.personneID = personneID;
        this.coursID = coursID;
    }

    public PersonneCoursKey(int personneID, int coursID, LocalDate dateDebut, LocalDate dateFin) {
        this.personneID = personneID;
        this.coursID = coursID;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void setPersonneID(int personneID) {
        this.personneID = personneID;
    }

    public int getCoursID() {
        return coursID;
    }

    public void setCoursID(int coursID) {
        this.coursID = coursID;
    }

    public int getPersonneID() {
        return personneID;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + personneID;
        result = prime * result + coursID;
        result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
        result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonneCoursKey other = (PersonneCoursKey) obj;
        if (personneID != other.personneID)
            return false;
        if (coursID != other.coursID)
            return false;
        if (dateDebut == null) {
            if (other.dateDebut != null)
                return false;
        } else if (!dateDebut.equals(other.dateDebut))
            return false;
        if (dateFin == null) {
            if (other.dateFin != null)
                return false;
        } else if (!dateFin.equals(other.dateFin))
            return false;
        return true;
    }


}