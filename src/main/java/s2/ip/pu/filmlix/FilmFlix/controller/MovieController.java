package s2.ip.pu.filmlix.FilmFlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.FilmFlix.model.Movie;
import s2.ip.pu.filmlix.FilmFlix.repository.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping("/all")
    public List<Movie> getAll() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public void addNew(@RequestParam Movie movie) {
        repository.save(movie);
    }
}

