package s2.ip.pu.filmlix.FilmFlix.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private Set<Comment> comments;
}
