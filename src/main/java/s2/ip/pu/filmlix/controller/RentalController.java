package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.config.JwtTokenProvider;
import s2.ip.pu.filmlix.model.Rental;
import s2.ip.pu.filmlix.repository.RentalRepository;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<Rental> getAll(ServletRequest req) {
        String login = tokenProvider.getUserLogin(req);
        return repository.findAllByUser_Login(login);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void addNew(@RequestBody Rental rental) {
        repository.save(rental);
    }
}
