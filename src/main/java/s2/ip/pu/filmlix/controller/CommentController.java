package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Comment;
import s2.ip.pu.filmlix.repository.CommentRepository;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @GetMapping("/{movieId}")
    public List<Comment> getAllForMovie(@PathVariable Integer movieId) {
        return repository.findAllByMovie_MovieIdOrderByData(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<Comment> getAllForUser(@PathVariable Integer userId) {
        return repository.findAllByUser_UserIdOrderByData(userId);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void addNew(@RequestBody Comment comment) {
        repository.save(comment);
    }

    @PutMapping("")
    public void edit(@RequestBody Comment comment) {
        repository.save(comment);
    }
}
