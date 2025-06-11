package ue1104.iramps.be.api_backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ue1104.iramps.be.api_backend.Model.BL.Cinema;
import ue1104.iramps.be.api_backend.Service.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
@CrossOrigin(origins = "http://localhost:4200")

public class CinemaController {

    private final CinemaService service;

    public CinemaController(CinemaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cinema> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getOne(@PathVariable Long id) {
        return service.findById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cinema create(@RequestBody Cinema cinema) {
        return service.save(cinema);
    }

    @PutMapping("/{id}")
    //path variable sert a recup l'id de l'url afin del'extraire et de récuperer la ressource pour l'implémenter dans la méthode
    //Sert à désérialiser le corps (body) de la requête HTTP généralement du JSON  en un objet Java.
    public ResponseEntity<Cinema> update(@PathVariable Long id,
                                         @RequestBody Cinema cinema) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cinema.setIdCinema(id);
        return ResponseEntity.ok(service.save(cinema));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
