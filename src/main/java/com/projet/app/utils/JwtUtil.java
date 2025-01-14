//Gère la création, la validation et l'extraction des informations du token JWT.
package com.projet.app.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	 public static final String SECRET = "59703373367639792F423F4528482B4D6251655468576D5A713474375367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }
        //extractUsername : Récupère le nom d'utilisateur (ou sujet) contenu dans le token.
	    
	 // Extraction du rôle
	    public String extractRole(String token) {
	        return extractClaim(token, claims -> claims.get("role", String.class));
	    }

	    
	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	    //extractExpiration : Récupère la date d'expiration du token.

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }//Utilise une fonction pour extraire des informations spécifiques des revendications (Claims:ses infos) contenues dans le token.


	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	    }//decoder un jwt et retourne tous ses infos

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }//validateToken : Vérifie si :
	    //Le token correspond bien au nom d'utilisateur.
	    //Le token n'a pas expiré.


	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        // Ajouter le rôle aux revendications
	       
	        String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst() // Prendre le premier rôle (seul rôle)
                    .orElse("user"); // Valeur par défaut si aucun rôle trouvé (par exemple, ROLE_USER)

             claims.put("role", role);
	        
	        return createToken(claims, userDetails.getUsername());
	    }

	    
	  
	    private String createToken(Map<String, Object> claims, String userName) {
	        return Jwts
	                .builder()
	                .setClaims(claims)
	                .setSubject(userName)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }//Ajoute :
	    //Les revendications.
	    //Le nom d'utilisateur.
	    //La date d'émission.
	    //La date d'expiration (30 minutes ici).
	    //La signature HMAC-SHA256 avec la clé secrète.


	    private Key getSignKey() {
	        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }//Décodage de la clé secrète en base64
	    
	    
}

