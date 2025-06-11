package ue1104.iramps.be.api_backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ue1104.iramps.be.api_backend.Model.BL.Salle;
import ue1104.iramps.be.api_backend.Service.SalleService;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    private final SalleService service;

    public SalleController(SalleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Salle> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Salle findById(@PathVariable Long id) {
        return service.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Salle introuvable"
                )
            );
    }

    @PostMapping
    public Salle create(@RequestBody Salle s) {
        return service.save(s);
    }

    @PutMapping("/{id}")
    public Salle update(@PathVariable Long id, @RequestBody Salle s) {
        s.setIdSalle(id);       // très important : fixer l’ID
        return service.save(s); // JPA fera un update
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
