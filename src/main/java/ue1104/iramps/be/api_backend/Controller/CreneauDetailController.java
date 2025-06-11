package ue1104.iramps.be.api_backend.Controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ue1104.iramps.be.api_backend.Model.BL.Creneau;
import ue1104.iramps.be.api_backend.Model.BL.Film;
import ue1104.iramps.be.api_backend.Model.Repositories.FilmRepository;
import ue1104.iramps.be.api_backend.Service.CreneauService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

	
	@RestController
	@RequestMapping("/api/creneaux")
	public class CreneauDetailController {
	  private final CreneauService service;
	  public CreneauDetailController(CreneauService service) {
	    this.service = service;
	  }

	  @GetMapping("/{id}")
	  public Creneau getById(@PathVariable Long id) {
	    return service.findById(id)
	      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	  }
	}



