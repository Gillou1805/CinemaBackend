package ue1104.iramps.be.api_backend.Model.BL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.PersonneCours;

import java.util.Set;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "section.id")
    private Section section;

    @OneToMany(mappedBy = "cours")
    private Set<PersonneCours> personnes;

    public Cours(){}

    public Cours(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }



    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Section getSection() {
        return section;
    }
    public void setSection(Section section) {
        this.section = section;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Set<PersonneCours> getPersonnes() {
        return personnes;
    }
    public void setPersonnes(Set<PersonneCours> personnes) {
        this.personnes = personnes;
    }
}