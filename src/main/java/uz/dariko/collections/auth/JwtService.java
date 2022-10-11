package uz.dariko.collections.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
  /*  private final EntityGetter entityGetter;
    private final AdminMapper adminMapper;
    @Value("${jwt.secret}")
    private String secretWord;
    private Key key;
    private final long expiredDate = 1000L * 60 * 240;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretWord.getBytes());
    }


    public String createJwt(String username) throws JsonProcessingException {
        Admin admin = entityGetter.getAdmin(username);
        Claims claims = Jwts.claims().setSubject(username);
        String json = new ObjectMapper().writeValueAsString(adminMapper.toDTO(admin));
        String compact = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate))
                .signWith(key).compact();
        return compact;
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }
*/
}
