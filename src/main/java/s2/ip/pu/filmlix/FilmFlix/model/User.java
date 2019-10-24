package s2.ip.pu.filmlix.FilmFlix.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import s2.ip.pu.filmlix.FilmFlix.repository.RoleRepository;
import s2.ip.pu.filmlix.FilmFlix.repository.UserRepository;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Client")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;

    private String password;

    @Transient
    private Set<Role> roles;

    public Set<Role> getRoles() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(RoleRepository.USER_ROLE));
        if (login.equalsIgnoreCase("admin")) {
            roleSet.add(new Role(RoleRepository.ADMIN_ROLE));
        }
        return roleSet;
    }
}
