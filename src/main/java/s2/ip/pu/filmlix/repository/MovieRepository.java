package s2.ip.pu.filmlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> getAllByGenere(String category);

}
