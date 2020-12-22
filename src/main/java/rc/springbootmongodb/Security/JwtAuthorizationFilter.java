package rc.springbootmongodb.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import rc.springbootmongodb.Models.RestException;
import rc.springbootmongodb.Models.User;
import rc.springbootmongodb.Repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Read the Authorization header, where the JWT token should be
        String header = request.getHeader(JwtProperties.AUTH_HEADER);

        //If header does not contain BEARER or is null delegate to Spring impl and exit
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            //If header is present, try grab user principal from database and preform authorization
            Authentication authentication = getUsernamePasswordAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Continue filter execution
            chain.doFilter(request, response);
        } catch (JWTVerificationException ex) {
            RestException restException = new RestException("AUTH_TOKEN_INVALID", "Token has expired.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(restException));
        }
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) throws JWTVerificationException{
        String token = request.getHeader(JwtProperties.AUTH_HEADER);
        if(token != null) {
            //parse the token and validate id
            String userName = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            //Search in the DB if we find the user by token subject/username
            //If so, then grab user details and create spring auth using username, pass, authorities
            if(userName != null) {
                User user = userRepository.findByUsername(userName);
                UserSecurity principal = new UserSecurity(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }


}
