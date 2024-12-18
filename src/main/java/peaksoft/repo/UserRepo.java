package peaksoft.repo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotfoundException;
import peaksoft.models.User;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findUserByEmailEqualsIgnoreCase(String email);

    default User getUserByEmail(String email) {
        return findByEmail(email).orElseThrow(() ->
                new NotfoundException("User with " + email + " not found!")
        );
    }

    @Query("select case when count(u) > 0 then true else false end from User u where u.email like :email")
    boolean existsByEmail(String email);
}
