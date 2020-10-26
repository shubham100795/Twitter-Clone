package Application.Entity;

import javax.persistence.*;

@Entity
@Table(name = "en_followers")
public class FollowerEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
    private Long userId;

    @Column(name = "follower_id")
    private Long followerId; // who all this person follows

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }
}
