package ue1104.iramps.be.api_backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ue1104.iramps.be.api_backend.Model.BL.Calendrier;
import ue1104.iramps.be.api_backend.Service.CalendrierService;

@RestController
@RequestMapping("/api/salles")
public class CalendrierController {

    private final CalendrierService calendrierService;

    public CalendrierController(CalendrierService calendrierService) {
        this.calendrierService = calendrierService;
    }

    @GetMapping("/{idSalle}/calendrier")
    public ResponseEntity<Calendrier> getCalendrierBySalle(@PathVariable Long idSalle) {
        Calendrier calendrier = calendrierService.getCalendrierBySalle(idSalle);
        return ResponseEntity.ok(calendrier);
    }
}
