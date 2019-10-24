package s2.ip.pu.filmlix.FilmFlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.FilmFlix.model.User;
import s2.ip.pu.filmlix.FilmFlix.repository.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping("")
    public List<User> getAll() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public void addNew(@RequestParam User user) {
        repository.save(user);
    }
}
