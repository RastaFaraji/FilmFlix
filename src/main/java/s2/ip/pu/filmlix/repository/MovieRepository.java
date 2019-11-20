package s2.ip.pu.filmlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Filmy;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Filmy, Integer> {

    List<Filmy> getAllByGatunek(String category);

}
