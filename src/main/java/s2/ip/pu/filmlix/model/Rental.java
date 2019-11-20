package s2.ip.pu.filmlix.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Wypozyczenia")
public class Rental implements Serializable {

    @Id
    @Column(name = "Id_film")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private User user;

    @Column(name = "Data_wypozyczenia", nullable = false)
    private Date rentalDate;

}
