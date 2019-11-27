package s2.ip.pu.filmlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Movie;
import s2.ip.pu.filmlix.model.User;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> getAllByGenereLikeOrderByOriginalTitle(String category);
    List<Movie> getAllByMovieIdAndRentals_User_Login(Integer movieId, String login);
}
