package rc.springbootmongodb.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Models.UserRoles;
import rc.springbootmongodb.Repository.UserRepository;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserSecurityService userSecurityService;

    private UserRepository userRepository;

    public SecurityConfiguration(UserSecurityService userSecurityService, UserRepository userRepository) {
        this.userSecurityService = userSecurityService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                //remove csrf and state in session because in jwt we do not need them
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //add jwt filters (1.auth, 2. authorization, 3. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                //configure access rules
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/ads/all").permitAll()
                .antMatchers("/api/ads/**").permitAll()
                .antMatchers("/api/ads/vehicle/**").permitAll()
                .antMatchers("/api/ads/filter").permitAll()
                .antMatchers("/api/ads/user/**").permitAll()
                .antMatchers("/api/users/{id}").permitAll()
                .antMatchers("/api/users/me").hasAnyAuthority(UserRoles.ADMIN, UserRoles.CUSTOMER)
                .antMatchers("/api/ads/update/**").hasAuthority(UserRoles.CUSTOMER)
                .antMatchers("/api/ads/create").hasAuthority(UserRoles.CUSTOMER)
                .antMatchers("/api/ads/delete/**").hasAnyAuthority(UserRoles.ADMIN, UserRoles.CUSTOMER)
                .antMatchers("/api/users/all").hasAnyAuthority(UserRoles.CUSTOMER, UserRoles.ADMIN)
                .antMatchers("/api/users/**").hasAuthority(UserRoles.ADMIN)
                //
                .antMatchers("/api/users/search/email").hasAuthority(UserRoles.ADMIN)

                .antMatchers("/api/users/add").hasAuthority(UserRoles.CUSTOMER)
                .antMatchers("/api/users/update/**").hasAuthority(UserRoles.CUSTOMER)
                .antMatchers("/api/users/delete/**").hasAuthority(UserRoles.ADMIN)

//                .antMatchers("/api/users/login").permitAll()
                .antMatchers("/api/stats/dashboard").hasAuthority(UserRoles.ADMIN)
                .antMatchers("/api/stats/users").hasAuthority(UserRoles.ADMIN)
                .antMatchers("/api/stats/ads").hasAuthority(UserRoles.ADMIN);

//                .and()
                //.httpBasic(); .formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll();
                //.and().logout().logoutRequestMatvher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userSecurityService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
