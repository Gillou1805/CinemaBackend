package ue1104.iramps.be.api_backend.Controller;

import org.springframework.web.bind.annotation.*;
import ue1104.iramps.be.api_backend.Model.BL.Siege;
import ue1104.iramps.be.api_backend.Service.SiegeService;

import java.util.List;

@RestController
@RequestMapping("/api/creneaux/{creneauId}/sieges")
public class SiegeController {

    private final SiegeService siegeService;

    public SiegeController(SiegeService siegeService) {
        this.siegeService = siegeService;
    }

    /**
     * GET /api/creneaux/{creneauId}/sieges
     * Renvoie la liste de tous les Siege déjà réservés pour ce créneau
     */
    @GetMapping
    public List<Siege> list(@PathVariable Long creneauId) {
        return siegeService.findByCreneau(creneauId);
    }
}
