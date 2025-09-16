package com.example.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    // Lista di endpoint "pubblici" (non richiedono autenticazione)
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",  // registrazione utente
            "/auth/token",     // generazione token JWT
            "/eureka"          // endpoint usato da Eureka (service discovery)
    );

    // Predicato (= funzione booleana) che decide se una richiesta è "protetta" oppure no
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    // controlla se l'URI della richiesta contiene uno degli endpoint aperti
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
    // ritorna TRUE se la richiesta NON è in openApiEndpoints → quindi richiede autenticazione
    // ritorna FALSE se l'endpoint è pubblico → quindi NON richiede autenticazione

}
