package s2.ip.pu.filmlix.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Embeddable
public class Filmy implements Serializable {

    @Id
    @Column(name = "Id_film", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFilm;

    @Column(name = "Tytul_polski", nullable = false, length = 60)
    private String tytulPolski;

    @Column(name = "Tytul_orginalny", nullable = false, length = 60)
    private String tytulOrginalny;

    @Column(name = "Gatunek", nullable = false, length = 20)
    private String gatunek;

    @Column(name = "Aktorzy", nullable = false, length = 20)
    private String aktorzy;

    @Column(name = "Opis", nullable = false, length = 60)
    private String opis;

    @Column(name = "Cena", nullable = false)
    private BigDecimal cena;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Wypozyczenia> wypozyczenia;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Komentarze> komentarze;

}
