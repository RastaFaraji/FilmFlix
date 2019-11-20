package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Filmy;
import s2.ip.pu.filmlix.repository.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping("/all")
    public List<Filmy> getAll() {
        return repository.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Filmy> getByCategory(@PathVariable String category) {
        return repository.getAllByGatunek(category);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addNew(@RequestBody Filmy movie) {
        repository.save(movie);
    }
}

