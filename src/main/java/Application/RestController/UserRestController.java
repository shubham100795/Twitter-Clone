package Application.RestController;

import Application.DataObjects.UserDTO;

import Application.Entity.UserEntity;
import Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    //register a new user
    @RequestMapping(value = "/users/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createUser(@RequestBody final UserDTO user){
        try{
            UserEntity userEntity = userService.saveUser(user);
            return "New user registered with user id : "+user.getId();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //search for a particular user(this will show all the info including hashed password as well along with list of people the given user follows)
    @RequestMapping(value = "/users/search", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDTO getUser(@RequestParam final Long userId) throws Exception {
        try{
            UserDTO userDTO = userService.findUser(userId);
            return userDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //follow some other user
    @RequestMapping(value="/users/follow", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String follow(@RequestParam Long userId, @RequestParam Long followerId) {
        try{
            userService.follow(userId,followerId);
            return "User with userId : "+ userId+" is now following user with id :"+followerId;
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
