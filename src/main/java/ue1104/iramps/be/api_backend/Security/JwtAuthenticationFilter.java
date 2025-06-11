// src/main/java/ue1104/iramps/be/api_backend/Security/JwtAuthenticationFilter.java
package ue1104.iramps.be.api_backend.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ue1104.iramps.be.api_backend.DTO.LoginRequest;
import ue1104.iramps.be.api_backend.Model.BL.Utilisateur;
import ue1104.iramps.be.api_backend.Model.Repositories.UtilisateurRepository;

import java.io.IOException;
import java.util.Date;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UtilisateurRepository userRepo;  

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtUtil jwtUtil,
                                   UtilisateurRepository userRepo) {   
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;             
        setFilterProcessesUrl("/api/auth/login"); //la route sur laquelle le filtre s'applique
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
                                                throws AuthenticationException {
        try {
            LoginRequest creds = new ObjectMapper() // je désérialise ce JSON dans un objet LoginRequest à deux propriétés.
                .readValue(request.getInputStream(), LoginRequest.class); // je recupere le flux d'entrée que contient le json envoyé par le client

            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken( // j instancie ce token a partir du DTO mail et mdp
                    creds.getMail(),
                    creds.getMdp(),
                    Collections.emptyList()
                );

            return authenticationManager.authenticate(authToken); // Spring security va charger le user, vérifié que le mot 
        } catch (IOException e) {							// de passe haché est correct à la saisie
            throw new RuntimeException(e);			//si oui, renvoie authentication, si non exception
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
                                            throws IOException, ServletException {
        String username = authResult.getName();

        // Récupère l'utilisateur complet pour extraire le prénom
        Utilisateur user = userRepo.findByMail(username)
                                  .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String role = authResult.getAuthorities().stream()
                             .findFirst()
                             .map(granted -> granted.getAuthority())
                             .map(r -> r.startsWith("ROLE_") ? r.substring(5) : r)
                             .orElse("USER");

        Long userId = user.getIdUser();
        String prenom = user.getPrenom();   //  récupère le prénom

        String token = Jwts.builder()  // crée un token avec le mail
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId) // claim est une paire clé/valeur qu on inclue dans le payload
                .claim("prenom", prenom)     //  ajoute le claim prenom
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtUtil.EXPIRATION_MS)) // Donne 1 heure d'utilisation
                .signWith(jwtUtil.getKey(), SignatureAlgorithm.HS256) //algo du token
                .compact();

        response.addHeader("Authorization", "Bearer " + token); // place le token dans le header http
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush(); // on retourne le tokent au client Flush force un envoi immediat
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
                                              throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // code 401
        response.setContentType("application/json"); //spécifie que la réponse sera un objet JSON, ce qui facilite son traitement côté client.
        response.getWriter()
                .write("{\"error\":\"Authentification échouée : " + failed.getMessage() + "\"}"); //ce message précise la cause de l’échec (mot de passe erroné, utilisateur inconnu…).
        response.getWriter().flush();
    }
}
