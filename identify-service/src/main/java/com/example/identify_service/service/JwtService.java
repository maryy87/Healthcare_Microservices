package com.example.identify_service.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtService {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public void validateToken(final String token) {
        Jwts.parserBuilder().//→ crea un parser di JWT (della libreria jjwt).
                setSigningKey(getSignKey())//→ imposta la chiave segreta per verificare la firma del token.
                .build()//→ costruisce il parser.
                .parseClaimsJws(token);//→ controlla se:
                            //        la firma del token è valida;
                            //        il token non è scaduto (exp);
                            //        se qualcosa non va, lancia un’eccezione (JwtException).
    }



//È il metodo pubblico che viene chiamato dall’esterno (es. dal controller quando l’utente fa login).
//Si occupa di preparare i dati necessari per il token come il claims e Chiama il metodo createToken(...) per generare effettivamente il JWT..
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }


//mi crea un token
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)//→ puoi aggiungere ruoli, permessi, altri dati.
                .setSubject(userName)//identifica l’utente.
                .setIssuedAt(new Date(System.currentTimeMillis()))//→ quando è stato creato il token.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))//→ scadenza del token (qui 30 minuti).
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();//→ firma il token con chiave e algoritmo e restituisce il JWT come stringa da inviare al client.
    }



    //Decodifica la stringa Base64 della chiave.
    //Genera un oggetto Key compatibile con HMAC-SHA256 (HS256).
    //Ogni volta che firmi o validi un token, questa chiave serve per garantire che il token non sia stato modificato.
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
