package s2.ip.pu.filmlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
