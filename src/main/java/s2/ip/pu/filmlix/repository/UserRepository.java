package s2.ip.pu.filmlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Uzytkownicy;

@Repository
public interface UserRepository extends JpaRepository<Uzytkownicy, Integer> {

    Uzytkownicy findByLogin(String mail);
}
