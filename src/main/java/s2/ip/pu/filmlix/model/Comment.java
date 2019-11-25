package s2.ip.pu.filmlix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Komentarze")
public class Comment implements Serializable {

    @Id
    @Column(name = "Id_komentarz", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "Data", nullable = false)
    private Date data;

    @Column(name = "Komentarz", nullable = false, length = 400)
    private String comment;

    @Column(name = "Ocena")
    private Short rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_uzytkownik")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_film")
    private Movie movie;
}
