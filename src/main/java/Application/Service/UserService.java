package Application.Service;

import Application.DataObjects.UserDTO;
import Application.Entity.FollowerEntity;
import Application.Entity.UserEntity;
import Application.RestController.FollowerRepository;
import Application.RestController.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       FollowerRepository followerRepository){
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
    }

    public UserDTO findUser(Long userId) throws Exception {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isPresent()){
            UserDTO userDTO = new UserDTO();
            UserEntity userEntity = userEntityOptional.get();
            List<Long> followingList = new ArrayList<>();
            userEntity.getFollowerEntityList().forEach(followerEntity -> {
                followingList.add(followerEntity.getFollowerId());
            });
            userDTO.setId(userEntity.getId());
            userDTO.setName(userEntity.getName());
            userDTO.setPassword("Not visible in user get call");
            userDTO.setAge(userEntity.getAge());
            userDTO.setTotalTweets(userEntity.getTotalTweets());
            userDTO.setFollowerList(followingList);
            return userDTO;
        }else{
            throw new Exception("ERROR : User with id : "+userId+" NOT FOUND");
        }
    }

    public UserEntity saveUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setTotalTweets(userDTO.getTotalTweets());
        String md5Hash = getMd5(userDTO.getPassword());// storing password as md5 hash
        userEntity.setPassword(md5Hash);
        userRepository.save(userEntity);
        return userEntity;
    }

    public void follow(Long userId, Long followerId) throws Exception {

        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<UserEntity> follower = userRepository.findById(followerId);

        if(!user.isPresent()){
            throw new Exception("ERROR : User with id : "+userId+" NOT FOUND");
        }

        if(!follower.isPresent()){
            throw new Exception("ERROR : User with id : "+followerId+" NOT FOUND");
        }
        Optional<FollowerEntity> followerEntityOptional = followerRepository.findByUserIdAndFollowerId(userId,followerId);
        if(followerEntityOptional.isPresent()){
            throw new Exception("ERROR : User with id : "+followerId+" ALREADY FOLLOWS user with id : " +followerId);
        }

        FollowerEntity followerEntity = new FollowerEntity();
        followerEntity.setUserId(userId);
        followerEntity.setFollowerId(followerId);
        followerRepository.save(followerEntity);
    }

    //method to generate md5 hash of password
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
