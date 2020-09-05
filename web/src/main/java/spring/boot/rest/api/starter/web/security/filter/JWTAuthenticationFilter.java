package spring.boot.rest.api.starter.web.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import spring.boot.rest.api.starter.web.security.Credentials;
import spring.boot.rest.api.starter.web.security.UserDetailsImpl;
import spring.boot.rest.api.starter.web.security.SecurityConstants;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/user/action/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            Credentials credentials = new ObjectMapper().readValue(req.getInputStream(), Credentials.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.username, credentials.password, new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + createToken(user));
        response.addHeader(SecurityConstants.USER_ID_HEADER_KEY, String.valueOf(user.getId()));
    }

    private String createToken(UserDetailsImpl user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
    }
}