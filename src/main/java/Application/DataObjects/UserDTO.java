package Application.DataObjects;

import org.springframework.lang.NonNull;

import java.util.List;

public class UserDTO {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @NonNull
    private int age;

    @NonNull
    private int totalTweets;

    private List<Long> followerList; // list of user whom the current user follows

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTotalTweets() {
        return totalTweets;
    }

    public void setTotalTweets(int totalTweets) {
        this.totalTweets = totalTweets;
    }

    public List<Long> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<Long> followerList) {
        this.followerList = followerList;
    }
}
