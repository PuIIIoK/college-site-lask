package puiiiokiq.collegelask.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import puiiiokiq.collegelask.model.Student;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Генерация токена
    public String generateToken(Student student) {
        return Jwts.builder()
                .subject(student.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 час
                .signWith(secretKey)
                .compact();
    }

    // Получение имени пользователя из токена
    public String getUsernameFromToken(String token) {
        Claims claims = getParser().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    // Проверка токена
    public boolean validateToken(String token) {
        try {
            getParser().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Парсер с подписью
    private JwtParser getParser() {
        return Jwts.parser().verifyWith(secretKey).build();
    }
}
