package s2.ip.pu.filmlix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Embeddable
@Table(name = "Filmy")
public class Movie implements Serializable {

    @Id
    @Column(name = "Id_film", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(name = "Tytul_polski", nullable = false, length = 120)
    private String polishTitle;

    @Column(name = "Tytul_orginalny", nullable = false, length = 120)
    private String originalTitle;

    @Column(name = "Gatunek", nullable = false, length = 80)
    private String genere;

    @Column(name = "Aktorzy", nullable = false, length = 800)
    private String actors;

    @Column(name = "Opis", nullable = false, length = 120)
    private String description;

    @Column(name = "Cena", nullable = false)
    private BigDecimal price;

    @Column(name = "Plakat_url", length = 60)
    private String imageUrl;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rental> rentals;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;

}
