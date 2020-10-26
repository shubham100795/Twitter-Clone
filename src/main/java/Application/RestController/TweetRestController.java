package Application.RestController;

import Application.DataObjects.TweetDTO;
import Application.Entity.TweetEntity;
import Application.Service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetRestController {

    private final TweetService tweetService;

    @Autowired
    public TweetRestController(TweetService tweetService){
        this.tweetService = tweetService;
    }

    //create a new tweet
    @RequestMapping(value = "/tweets/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createTweet(@RequestBody final TweetDTO tweet){
        try{
            TweetEntity tweetEntity = tweetService.saveTweet(tweet);
            return "New tweet made with id : " + tweetEntity.getId();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //like a particular tweet
    @RequestMapping(value = "/tweets/like", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String likeTweet(@RequestParam final Long tweetId){
        try{
            TweetEntity tweetEntity = tweetService.increaseLike(tweetId);
            return "Number of likes for tweet with id : " + tweetEntity.getId()+" are now "+ tweetEntity.getTotalLikes();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //fetch feed for a user(tweets by other users whom the current person follows)
    @RequestMapping(value = "/tweets/feed", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TweetDTO> getFeed(@RequestParam final Long userId) throws Exception {
        try{
            List<TweetDTO> tweetList = tweetService.getFeedData(userId);
            return tweetList;
        }catch (Exception exception){
            throw exception;
        }
    }
}
