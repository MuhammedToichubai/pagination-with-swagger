package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.models.Post;
@Repository
public interface PostRepo extends JpaRepository<Post,Long> {


}
