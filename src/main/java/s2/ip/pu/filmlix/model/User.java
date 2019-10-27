package s2.ip.pu.filmlix.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import s2.ip.pu.filmlix.repository.RoleRepository;

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
        roleSet.add(RoleRepository.USER);
        if (login.equalsIgnoreCase("admin")) {
            roleSet.add(RoleRepository.ADMIN);
        }
        return roleSet;
    }
}
