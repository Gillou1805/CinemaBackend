package ue1104.iramps.be.api_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ue1104.iramps.be.api_backend.Model.BL.Film;
import ue1104.iramps.be.api_backend.Model.Repositories.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film filmDetails) {
        Film film = getFilmById(id);
        film.setTitre(filmDetails.getTitre());
        film.setDuree(filmDetails.getDuree());
        film.setDescription(filmDetails.getDescription());
        film.setImage(filmDetails.getImage()); //  on met à jour le champ image
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}
