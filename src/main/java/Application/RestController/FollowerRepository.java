package Application.RestController;

import Application.Entity.FollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<FollowerEntity,Long> {

    Optional<FollowerEntity> findByUserIdAndFollowerId(Long userId, Long followerId);
}
