package s2.ip.pu.filmlix.repository;

import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Role;

@Repository
public class RoleRepository {


    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    public static final Role ADMIN = new Role(ADMIN_ROLE);
    public static final Role USER = new Role(USER_ROLE);

    public Role findByRole(String role) {
        return role.equals(ADMIN_ROLE) ? ADMIN : USER;
    }
}
