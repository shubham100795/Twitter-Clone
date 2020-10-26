package Application.RestController;

import Application.Entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity,Long> {
    Optional<TweetEntity> findById(Long tweetId);

    @Query(value = "Select * from en_tweets where user_id IN :userIds", nativeQuery = true)
    List<TweetEntity> findByUserIdIn(@Param("userIds") List<Long> userIds); // here user id is the id of the person being followed
}
