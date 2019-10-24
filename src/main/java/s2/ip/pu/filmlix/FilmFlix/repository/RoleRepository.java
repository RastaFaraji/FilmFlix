package s2.ip.pu.filmlix.FilmFlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.FilmFlix.model.Role;

@Repository
public class RoleRepository {


    public static String ADMIN_ROLE = "ADMIN";

    public static String USER_ROLE = "USER";

    public Role findByRole(String role) {
        return new Role(role.equals(ADMIN_ROLE) ? ADMIN_ROLE : USER_ROLE);
    }
}
