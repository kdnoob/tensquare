import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwtTest {

    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itcast")
                .setIssuedAt(new Date())
                .setId("1")
                .setSubject("kd");
        System.out.println(builder.compact());
    }

}
