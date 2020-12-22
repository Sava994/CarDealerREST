package rc.springbootmongodb.Security;

public class JwtProperties {
    public static final String SECRET = "KULu%rWSGLAA)Bx/^SKv?2S!5}C?j,.M|@O;UU[qD6%>WASup:B*B>vVJ=px!" +
            "YElucKdYV|@:octT/?n;vc+C:$<Z3j&l`DMH)EnZqsVHteP8^Q#;h:x?o>5snXCkS";
    public static final int EXPIRATION_TIME = 21600000; //6h -> 1h = 3600000
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String AUTH_HEADER = "Authorization";

}
