package ue1104.iramps.be.api_backend.Model.BL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.PersonneCours;

import java.util.Set;

@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"nom", "prenom"})
}) 
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false,length = 50)
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "status.id")
    private Status status;


    @OneToMany(mappedBy = "personne")
    private Set<PersonneCours> cours;


    public Personne(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    public Personne() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public Set<PersonneCours> getCours() {
        return cours;
    }


    public void setCours(Set<PersonneCours> cours) {
        this.cours = cours;
    }
}