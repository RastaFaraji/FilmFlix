package s2.ip.pu.filmlix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s2.ip.pu.filmlix.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByMovieMovieId(long id);

    List<Comment> findAllByUserUserId(long id);
}
