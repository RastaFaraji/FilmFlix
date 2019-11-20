package s2.ip.pu.filmlix.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
public class Wypozyczenia implements Serializable {

    @Id
    @Column(name = "Id_film")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFilm;

    @ManyToOne
    private Filmy film;

    @ManyToOne
    private Uzytkownicy klient;

    @Column(name = "Data_wypozyczenia", nullable = false)
    private Date dataWypozyczenia;

}
