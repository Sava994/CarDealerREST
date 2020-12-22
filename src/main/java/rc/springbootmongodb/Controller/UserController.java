package rc.springbootmongodb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rc.springbootmongodb.Dtos.PageQuery;
import rc.springbootmongodb.Dtos.PaginationResponse;
import rc.springbootmongodb.Dtos.RegisterUserRequest;
import rc.springbootmongodb.EnumTypes.UserType;
import rc.springbootmongodb.Exceptions.ServiceException;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.UserRepository;
import rc.springbootmongodb.Services.UserServices;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get all users
    @GetMapping("/all")
    public ResponseEntity<PaginationResponse<User>> getAll(PageQuery pageQuery) {

        PaginationResponse<User> res = userServices.getAll(pageQuery);

        if (res.getData().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //get user by his id
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {

        User u = userServices.getById(id);

        if(u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    //search user by his email address
    @PostMapping("/search/email")
    public ResponseEntity<User> searchEmail(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        User u = userServices.searchByEmail(searchTerm);

        if(u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // get current user
    @GetMapping("/me")
    public ResponseEntity<User> currentUser(Principal principal) {
        User user = userServices.getByUsername(principal.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //register user account
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequest userRequest) throws ServiceException {

        if (Stream.of(userRequest).allMatch(Objects::isNull)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userServices.createUser(userRequest);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //update users data
    @PutMapping("/update/data/{id}")
    public ResponseEntity<User> updateData(@PathVariable String id, @RequestBody Map<String, String> body,Principal principal) {

        //if logged user is trying to update ad that is not his send forbidden
        User user = userServices.getById(id);

        if(user.getUsername() != principal.getName()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setFirst_name(body.get("first_name"));
        user.setLast_name(body.get("last_name"));
        user.setEmail(body.get("email"));
        user.setPhone(body.get("phone"));

        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //update users password
    @PutMapping("/update/password/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable String id, @RequestBody Map<String, String> body, Principal principal) {

        User user = userServices.getById(id);

        //if logged user is trying to change password of different user send forbidden
        if(user.getUsername() != principal.getName()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setPassword_hash(body.get("password_hash"));

        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //delete account by admin (sends false if there is no user with that id otherwise send true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        User u = userServices.getById(id);

        if(u == null) {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }

        userRepository.delete(u);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
