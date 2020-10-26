package Application.RestController;

import Application.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findById(Long userId);
}
