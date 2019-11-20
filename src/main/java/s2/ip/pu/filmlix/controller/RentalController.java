package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Rental;
import s2.ip.pu.filmlix.repository.RentalRepository;

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
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void addNew(@RequestParam Rental rental) {
        repository.save(rental);
    }
}
