package com.example.api_gateway.filter;

import com.example.api_gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;


    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //exchange rappresenta l’oggetto principale che contiene tutti i dettagli della richiesta e della risposta HTTP che stanno passando attraverso il Gateway.
        return ((exchange, chain) -> {

            // Controlla se la richiesta in arrivo è "protetta" (cioè se deve avere un token)
            if (validator.isSecured.test(exchange.getRequest())) {

                // Controlla se negli header della richiesta c’è l’Authorization header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header"); // Se manca, errore
                }

                // Prende il valore dell’header "Authorization"
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                // Se inizia con "Bearer " → rimuove la parte "Bearer " e tiene solo il token vero
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    // Qui invece si verifica se il token è valido
                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    // Se il token non è valido o scade → stampa messaggio ed errore
                    System.out.println("invalid access...!");
                    throw new RuntimeException("unauthorized access to application");
                }
            }

            // Se tutto va bene, continua la chain (passa al prossimo filtro o al servizio vero e proprio)
            return chain.filter(exchange);
        });
    }


    public static class Config {

    }
}
