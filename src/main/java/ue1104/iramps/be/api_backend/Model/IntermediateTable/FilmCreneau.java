package ue1104.iramps.be.api_backend.Model.IntermediateTable;

import jakarta.persistence.*;
import ue1104.iramps.be.api_backend.Model.BL.Film;
import ue1104.iramps.be.api_backend.Model.BL.Creneau;

@Entity
@Table(name = "film_creneau")
public class FilmCreneau {

    @EmbeddedId
    private FilmCreneauId id;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @MapsId("creneauId")
    @JoinColumn(name = "creneau_id")
    private Creneau creneau;

    public FilmCreneau() {}

    public FilmCreneau(Film film, Creneau creneau) {
        this.film = film;
        this.creneau = creneau;
        this.id = new FilmCreneauId(film.getIdFilm(), creneau.getIdCreneau());
    }

    public FilmCreneauId getId() {
        return id;
    }

    public void setId(FilmCreneauId id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}