package spring.boot.rest.api.starter.web.security;

public class SecurityConstants {

    public static final String SECRET = "replace-me"; 

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String USER_ID_HEADER_KEY = "user_id";

    private SecurityConstants() {
    }
}