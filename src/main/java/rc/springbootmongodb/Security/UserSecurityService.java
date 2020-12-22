package rc.springbootmongodb.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    private UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);
        UserSecurity userSecurity = new UserSecurity(user);

        return userSecurity;
    }
}
