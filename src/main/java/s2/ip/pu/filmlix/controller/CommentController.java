package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Komentarze;
import s2.ip.pu.filmlix.repository.CommentRepository;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @GetMapping("/{movieId}")
    public List<Komentarze> getAllForMovie(@PathVariable long movieId) {
        return repository.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void addNew(@RequestParam Komentarze comment) {
        repository.save(comment);
    }

    @PutMapping("/edit")
    public void edit(@RequestParam Komentarze comment) {
        repository.save(comment);
    }
}
