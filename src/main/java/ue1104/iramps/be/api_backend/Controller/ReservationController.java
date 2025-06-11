// src/main/java/ue1104/iramps/be/api_backend/Controller/ReservationController.java
package ue1104.iramps.be.api_backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ue1104.iramps.be.api_backend.DTO.ReservationDto;
import ue1104.iramps.be.api_backend.Model.BL.Reservation;
import ue1104.iramps.be.api_backend.Service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/creneaux/{creneauId}/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    /** Liste les réservations existantes pour un créneau */
    @GetMapping
    public List<Reservation> list(@PathVariable Long creneauId) {
        return service.findByCreneau(creneauId);
    }

    /** Crée une réservation nouvelle */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(
        @PathVariable Long creneauId,
        @RequestBody ReservationDto dto
    ) {
        return service.create(creneauId, dto);
    }
}
