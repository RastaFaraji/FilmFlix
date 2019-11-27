package s2.ip.pu.filmlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.config.JwtTokenProvider;
import s2.ip.pu.filmlix.model.Movie;
import s2.ip.pu.filmlix.repository.MovieRepository;

import javax.servlet.ServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("")
    public List<Movie> getAll() {
        return repository.getAllByOrderByOriginalTitleDescRatingDesc();
    }

    @GetMapping("/category/{category}")
    public List<Movie> getByCategory(@PathVariable String category) {
        return repository.getAllByGenereLikeOrderByOriginalTitleDescRatingDesc(category);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addNew(@RequestBody Movie movie) {
        repository.save(movie);
    }

    @GetMapping("/{movieId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public boolean isRentedByLoggedUser(@PathVariable Integer movieId, ServletRequest req) {
        String login = tokenProvider.getUserLogin(req);
        return repository.getAllByMovieIdAndRentals_User_Login(movieId, login).size() > 0;
    }
}

