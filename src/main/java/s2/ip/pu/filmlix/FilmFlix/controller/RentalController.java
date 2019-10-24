package s2.ip.pu.filmlix.FilmFlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.FilmFlix.model.Rental;
import s2.ip.pu.filmlix.FilmFlix.repository.RentalRepository;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalRepository repository;

    @GetMapping("")
    public List<Rental> getAll() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public void addNew(@RequestParam Rental rental) {
        repository.save(rental);
    }
}
