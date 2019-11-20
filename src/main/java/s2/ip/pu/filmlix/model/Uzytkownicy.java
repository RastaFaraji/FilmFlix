package s2.ip.pu.filmlix.model;

import lombok.Data;
import s2.ip.pu.filmlix.repository.RoleRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Uzytkownicy implements Serializable {

    @Id
    @Column(name = "Id_uzytkownik", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUzytkownik;

    @Column(name = "Imie", nullable = false, length = 20)
    private String imie;

    @Column(name = "Nazwisko", nullable = false, length = 20)
    private String nazwisko;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "Haslo", nullable = false, length = 44)
    private String haslo;

    @OneToMany(mappedBy = "uzytkownik", fetch = FetchType.LAZY)
    private List<Komentarze> komentarze;

    @OneToMany(mappedBy = "klient", fetch = FetchType.LAZY)
    private List<Wypozyczenia> wypozyczenia;

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
