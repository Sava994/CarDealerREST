package rc.springbootmongodb.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rc.springbootmongodb.Models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurity implements UserDetails {
    private User user;

    public UserSecurity(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(this.user.getType().toString());
        authorityList.add(grantedAuthority);

        return authorityList;
    }

    public String getId() { return user.getId(); }

    public String getType() {
        return user.getType().toString().toLowerCase();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword_hash();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
