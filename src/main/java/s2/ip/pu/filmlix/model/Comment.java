package s2.ip.pu.filmlix.model;

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

    @Column(name = "Komentarz", nullable = false, length = 200)
    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;
}
