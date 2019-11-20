package s2.ip.pu.filmlix.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
public class Komentarze implements Serializable {

    @Id
    @Column(name = "Id_komentarz", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKomentarz;

    @Column(name = "Data", nullable = false)
    private Date data;

    @Column(name = "Komentarz", nullable = false, length = 200)
    private String komentarz;

    @ManyToOne
    private Uzytkownicy uzytkownik;

    @ManyToOne
    private Filmy film;
}
