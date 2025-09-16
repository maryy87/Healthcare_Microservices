package com.example.api_gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
@Component
public class JwtUtil {


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
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
