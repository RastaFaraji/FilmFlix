package s2.ip.pu.filmlix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import s2.ip.pu.filmlix.repository.RoleRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Uzytkownicy")
public class User implements Serializable {

    @Id
    @Column(name = "Id_uzytkownik", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "Imie", nullable = false, length = 40)
    private String name;

    @Column(name = "Nazwisko", nullable = false, length = 40)
    private String surname;

    @Column(name = "login", nullable = false, length = 40)
    private String login;

    @Column(name = "Haslo", nullable = false, length = 120)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comment;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rental> rentals;

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
