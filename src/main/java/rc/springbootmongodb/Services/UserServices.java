package rc.springbootmongodb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rc.springbootmongodb.Dtos.PageQuery;
import rc.springbootmongodb.Dtos.PaginationResponse;
import rc.springbootmongodb.Dtos.RegisterUserRequest;
import rc.springbootmongodb.EnumTypes.UserType;
import rc.springbootmongodb.Exceptions.ServiceException;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServices {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User searchByEmail(String email) {
        List<User> all = userRepository.findAll();

        for(User u: all) {
            if(u.getEmail().equals(email))
                return u;
        }
        return null;
    }

    public User getById(String id) {
        Optional<User> u = userRepository.findById(id);

        return u.isPresent() ? u.get() : null;
    }

    public PaginationResponse<User> getAll(PageQuery pageQuery) {

        Pageable pageable = PageRequest.of(pageQuery.getPage(), pageQuery.getPerPage());

        Page<User> users = userRepository.findAll(pageable);

        return new PaginationResponse<>(pageQuery.getPage(), users.getTotalElements(), users.getSize(), users.getTotalPages(), users.getContent());

    }

    public User createUser(RegisterUserRequest registerUserRequest) throws ServiceException{

        Pattern patternPhone = Pattern.compile("^\\d{6,12}$");
        Matcher matcher = patternPhone.matcher(registerUserRequest.getPhone());
        if (!matcher.find()) {
          throw new ServiceException("PHONE_INVALID_FORMAT", "Phone is not in valid format.");
        }

//        String phone = registerUserRequest.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");

        String password_hash = passwordEncoder.encode(registerUserRequest.getPassword());

        User newUser = new User(
               UserType.CUSTOMER,
                registerUserRequest.getUsername(),
                registerUserRequest.getFirstName(),
                registerUserRequest.getLastName(),
                registerUserRequest.getEmail(),
                registerUserRequest.getPhone(),
                password_hash,
                getCurrentDate()
        );

        userRepository.save(newUser);

        return newUser;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private Date getCurrentDate() {
        try {
            //get current ISO date in JAVA
            TimeZone tz = TimeZone.getTimeZone("UTC+2");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            String nowAsISO = df.format(new Date());

            return df.parse(nowAsISO);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
