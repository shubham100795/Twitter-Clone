package Application.DataObjects;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class TweetDTO {

    private Long id;

    @NonNull
    @Size(max = 140, message = "Tweet can not contain more than 140 characters")
    private String content;

    @NonNull
    private Long userId;

    @NonNull
    private Long totalLikes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        this.totalLikes = totalLikes;
    }
}
