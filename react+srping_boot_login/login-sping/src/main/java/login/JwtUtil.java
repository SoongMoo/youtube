package login;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import login.domain.AuthInfoDTO;

@Component
public class JwtUtil {
	private final String SECRET_KEY = UUID.randomUUID().toString();
    public String generateToken(AuthInfoDTO auth) {
    	ObjectMapper objectMapper = new ObjectMapper();
        String authJson;
        try {
            authJson = objectMapper.writeValueAsString(auth);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert auth object to JSON", e);
        }
        return Jwts.builder()
                .setSubject(auth.getUserId())
                .claim("auth", authJson)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간 유효
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public AuthInfoDTO extractAuth(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        String authJson = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("auth", String.class);
        try {
            return objectMapper.readValue(authJson, AuthInfoDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to auth object", e);
        }
    }
    /*
     // 토큰에서 AuthInfoDTO 추출
		AuthInfoDTO extractedAuth = jwtUtil.extractAuth(token);
		System.out.println("Extracted Auth Info: " + extractedAuth.getName());
	*/
}
