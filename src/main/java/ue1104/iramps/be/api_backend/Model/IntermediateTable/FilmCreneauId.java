package ue1104.iramps.be.api_backend.Model.IntermediateTable;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class FilmCreneauId implements Serializable {
    private Long filmId;
    private Long creneauId;

    public FilmCreneauId() {}

    public FilmCreneauId(Long filmId, Long creneauId) {
        this.filmId = filmId;
        this.creneauId = creneauId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getCreneauId() {
        return creneauId;
    }

    public void setCreneauId(Long creneauId) {
        this.creneauId = creneauId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmCreneauId)) return false;
        FilmCreneauId that = (FilmCreneauId) o;
        return Objects.equals(getFilmId(), that.getFilmId())
            && Objects.equals(getCreneauId(), that.getCreneauId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmId(), getCreneauId());
    }
}