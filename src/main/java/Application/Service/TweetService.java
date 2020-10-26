package Application.Service;

import Application.DataObjects.TweetDTO;
import Application.Entity.FollowerEntity;
import Application.Entity.TweetEntity;
import Application.Entity.UserEntity;
import Application.RestController.TweetRepository;
import Application.RestController.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository,
                        UserRepository userRepository){
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public TweetEntity saveTweet(TweetDTO tweetDTO) throws Exception {
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setContent(tweetDTO.getContent());
        tweetEntity.setTotalLikes(tweetDTO.getTotalLikes());
        Optional<UserEntity> userEntityOptional = userRepository.findById(tweetDTO.getUserId());
        if(!userEntityOptional.isPresent()){
            throw new Exception("ERROR : Specified user with id : "+tweetDTO.getUserId()+" was NOT FOUND");
        }
        tweetEntity.setUser(userEntityOptional.get());
        UserEntity userEntity= userEntityOptional.get();//increasing no of tweets made by that person by 1 in user table
        userEntity.setTotalTweets(userEntity.getTotalTweets()+1);
        userRepository.save(userEntity);
        tweetRepository.save(tweetEntity);
        return tweetEntity;
    }

    public TweetEntity increaseLike(Long tweetId) throws Exception {
        Optional<TweetEntity> tweetEntityOptional = tweetRepository.findById(tweetId);
        if(tweetEntityOptional.isPresent()){
            TweetEntity tweetEntity = tweetEntityOptional.get();
            tweetEntity.setTotalLikes(tweetEntity.getTotalLikes()+1);
            tweetRepository.save(tweetEntity);
            return tweetEntity;
        }else{
            throw new Exception("ERROR : Specified tweet with id : "+tweetId+" was NOT FOUND");
        }
    }

    //show tweets of user whom the current person follows
    public List<TweetDTO> getFeedData(Long userId) throws Exception {

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isPresent()){
            List<TweetDTO> feedList = new ArrayList<>();
            UserEntity userEntity = userEntityOptional.get();
            List<FollowerEntity> followerEntityList = userEntity.getFollowerEntityList();
            List<Long> followerIdList = followerEntityList.stream().map(FollowerEntity::getFollowerId).collect(Collectors.toList());
            List<TweetEntity> tweetEntityList = tweetRepository.findByUserIdIn(followerIdList);
            tweetEntityList.forEach(tweetEntity -> {
                TweetDTO tweetDTO = new TweetDTO();
                tweetDTO.setId(tweetEntity.getId());
                tweetDTO.setContent(tweetEntity.getContent());
                tweetDTO.setUserId(tweetEntity.getUser().getId());
                tweetDTO.setTotalLikes(tweetEntity.getTotalLikes());
                feedList.add(tweetDTO);
            });
            return feedList;
        }else{
            throw new Exception("ERROR : User with specified id : "+userId+" NOT FOUND");
        }
    }


}
