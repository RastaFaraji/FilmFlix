package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.model.Comment;
import s2.ip.pu.filmlix.repository.CommentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @GetMapping("/{movieId}")
    public List<Comment> getAllForMovie(@PathVariable Integer movieId) {
        return repository.findAllByMovie_MovieId(movieId).stream().sorted(Comparator.comparing(Comment::getData)).collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<Comment> getAllForUser(@PathVariable Integer userId) {
        return repository.findAllByUser_UserId(userId).stream().sorted(Comparator.comparing(Comment::getData)).collect(Collectors.toList());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void addNew(@RequestParam Comment comment) {
        repository.save(comment);
    }

    @PutMapping("/edit")
    public void edit(@RequestParam Comment comment) {
        repository.save(comment);
    }
}
