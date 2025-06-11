// src/main/java/ue1104/iramps/be/api_backend/DTO/ReservationDto.java
package ue1104.iramps.be.api_backend.DTO;

import java.util.List;

public class ReservationDto {

    private Long userId;
    private Integer nbSiegeStd;
    private Integer nbSiegeSpecial;
    private Integer nbSiegePmr;

    // ← nouveau champ : positions des sièges réservés
    private List<Long> seatIds;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getNbSiegeStd() { return nbSiegeStd; }
    public void setNbSiegeStd(Integer nbSiegeStd) { this.nbSiegeStd = nbSiegeStd; }

    public Integer getNbSiegeSpecial() { return nbSiegeSpecial; }
    public void setNbSiegeSpecial(Integer nbSiegeSpecial) { this.nbSiegeSpecial = nbSiegeSpecial; }

    public Integer getNbSiegePmr() { return nbSiegePmr; }
    public void setNbSiegePmr(Integer nbSiegePmr) { this.nbSiegePmr = nbSiegePmr; }

    public List<Long> getSeatIds() { return seatIds; }
    public void setSeatIds(List<Long> seatIds) { this.seatIds = seatIds; }
}
