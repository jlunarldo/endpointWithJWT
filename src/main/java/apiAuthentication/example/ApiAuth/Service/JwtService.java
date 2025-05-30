package apiAuthentication.example.ApiAuth.Service;

import apiAuthentication.example.ApiAuth.Entities.UserGeneral;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.*;


@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";


    public String getToken(UserDetails user) {
        return getToken(new HashMap<String, Object>(), user);
    }

    private String getToken(HashMap<String, Object> string, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(string)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256) //Establece el alg  de encriptación para trabajar la key
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
