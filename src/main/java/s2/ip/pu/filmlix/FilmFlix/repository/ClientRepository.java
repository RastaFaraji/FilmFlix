package s2.ip.pu.filmlix.FilmFlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.FilmFlix.model.User;

@Repository
public interface ClientRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
