package s2.ip.pu.filmlix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "Haslo", nullable = false, length = 120)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comment;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rental> rentals;

    @Transient
    @JsonIgnore
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
