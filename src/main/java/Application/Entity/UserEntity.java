package Application.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "en_user")
public class UserEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@OneToMany
	@JoinColumn(name = "user_id")
	private List<FollowerEntity> followerEntityList;

	@Column(name = "total_tweets")
	private int totalTweets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<FollowerEntity> getFollowerEntityList() {
		return followerEntityList;
	}

	public void setFollowerEntityList(List<FollowerEntity> followerEntityList) {
		this.followerEntityList = followerEntityList;
	}

	public int getTotalTweets() {
		return totalTweets;
	}

	public void setTotalTweets(int totalTweets) {
		this.totalTweets = totalTweets;
	}
}
