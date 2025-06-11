package ue1104.iramps.be.api_backend.Model.IntermediateTable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class CalendrierKey implements Serializable {

    @Column(name = "id_calendrier")
    private Integer idCalendrier;

    @Column(name = "id_salle")
    private Long idSalle;

    public CalendrierKey() {}

    public CalendrierKey(Integer idCalendrier, Long idSalle) {
        this.idCalendrier = idCalendrier;
        this.idSalle = idSalle;
    }

    public Integer getIdCalendrier() { return idCalendrier; }
    public void setIdCalendrier(Integer idCalendrier) { this.idCalendrier = idCalendrier; }

    public Long getIdSalle() { return idSalle; }
    public void setIdSalle(Long idSalle) { this.idSalle = idSalle; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalendrierKey)) return false;
        CalendrierKey that = (CalendrierKey) o;
        return Objects.equals(idCalendrier, that.idCalendrier) && Objects.equals(idSalle, that.idSalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCalendrier, idSalle);
    }
}

