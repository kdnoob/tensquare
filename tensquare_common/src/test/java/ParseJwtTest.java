import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NDM4OTQyMjgsImp0aSI6IjEiLCJzdWIiOiJrZCJ9.kBGXo8TL4vNiyaRrP1kBHpzM_H82kuXsUox7NiDQOC4")
                .getBody();
        System.out.println(claims.getId());
    }
}
