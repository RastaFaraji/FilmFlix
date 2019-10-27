package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Comment;
import s2.ip.pu.filmlix.repository.CommentRepository;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @GetMapping("")
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public void addNew(@RequestParam Comment comment) {
        repository.save(comment);
    }

    @PutMapping("/edit")
    public void edit(@RequestParam Comment comment) {
        repository.save(comment);
    }
}
