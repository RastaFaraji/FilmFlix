package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Movie;
import s2.ip.pu.filmlix.repository.MovieRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping("/all")
    public List<Movie> getAll() {
        return repository.findAll().stream().sorted(Comparator.comparing(Movie::getOriginalTitle)).collect(Collectors.toList());
    }

    @GetMapping("/category/{category}")
    public List<Movie> getByCategory(@PathVariable String category) {
        return repository.getAllByGenere(category).stream().sorted(Comparator.comparing(Movie::getOriginalTitle)).collect(Collectors.toList());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addNew(@RequestBody Movie movie) {
        repository.save(movie);
    }
}

