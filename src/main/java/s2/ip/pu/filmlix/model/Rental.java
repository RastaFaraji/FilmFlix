package s2.ip.pu.filmlix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Wypozyczenia")
public class Rental implements Serializable {

    @Id
    @Column(name = "Id_wypozyczenia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "Id_film")
    private Movie movie;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "Id_klient")
    private User user;

    @Column(name = "Data_wypozyczenia", nullable = false)
    private Date rentalDate;

}
