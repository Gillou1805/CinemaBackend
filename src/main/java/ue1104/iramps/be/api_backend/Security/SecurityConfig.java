// src/main/java/ue1104/iramps/be/api_backend/Security/SecurityConfig.java
package ue1104.iramps.be.api_backend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ue1104.iramps.be.api_backend.Model.Repositories.UtilisateurRepository;

@Configuration // On signale à Spring que cette classe contient des beans de configuration de sécurité.
@EnableWebSecurity //Spring Security est activé et prend le relais pour sécuriser les endpoints.
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // 1) On recrée le bean UserDetailsService à partir de votre repo
    // ce bean fournit à Spring Security la stratégie pour retrouver, à partir d’un email, l’utilisateur et ses rôles stockés en base, afin de comparer le mot de passe et construire le contexte de sécurité.
    @Bean
    public UserDetailsService userDetailsService(UtilisateurRepository repo) {
        return username -> repo.findByMail(username)
            .map(u -> org.springframework.security.core.userdetails.User.withUsername(u.getMail())
                                  .password(u.getMdp())
                                  .roles(u.getRole())
                                  .build()
            )
            .orElseThrow(() -> 
                new org.springframework.security.core.userdetails.UsernameNotFoundException("Utilisateur introuvable")
            );
    }

    // 2) PasswordEncoder pour BCrypt
    // ce bean rend disponible dans toute l’application un encodeur sécurisé (BCrypt) pour hacher et vérifier les mots de passe selon les meilleures pratiques de Spring Security.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //composant fournit par spring security avec un import
    }

    // 3) Provider DAO (userDetails + encoder)
    // chargé de vérifier les identifiants lors d'une tentative de connexion
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService uds,
                                                            PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds); //indique comment charger un utilisateur à partir d’un login (email) : grâce au UserDetailsService défini précédemment
        provider.setPasswordEncoder(encoder); // fournit le PasswordEncoder (BCrypt) à utiliser pour comparer le mot de passe en clair saisi par l’utilisateur avec le hash stocké en base.
        return provider;
    }

    // 4) AuthenticationManager
    // reçoit un Authentication () et le soumet à un ou plusieurs AuthenticationProvider
    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider authProvider) {
        return new ProviderManager(authProvider);
    }

    // 5) Sécurité HTTP + JWT filters
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authManager,
                                                   UserDetailsService uds,
                                                   UtilisateurRepository userRepo) throws Exception {
        http
          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
          .csrf().disable()

          // désactive la gestion de session côté serveur : chaque requête doit porter un JWT, pas de HttpSession
          .authorizeHttpRequests(auth -> auth

              // 1) Autoriser tous les preflight CORS 
        		  // permet d'envoyer une requete preliminaire uniquement pour négocier la politique CORS
              .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

              // 2) Autoriser Swagger / OpenAPI
              .requestMatchers("/v3/api-docs/**").permitAll()
              .requestMatchers("/swagger-ui/**").permitAll()
              .requestMatchers("/swagger-ui.html").permitAll()

              // 3) Onglet d’erreur
              .requestMatchers("/error").permitAll()

              // 4) Routes publiques GET  
              .requestMatchers(HttpMethod.GET, "/api/salles/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/films/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/image/**").permitAll()
              .requestMatchers("/api/creneaux/*/sieges").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/creneaux/**").permitAll()
              
              


              .requestMatchers(HttpMethod.GET, "/api/semainaire/**").permitAll()
           // On autorise librement le GET /api/utilisateurs/exists
              .requestMatchers(HttpMethod.GET, "/api/utilisateurs/exists").permitAll()
              .requestMatchers("/api/auth/**").permitAll()

              // 5) Routes protégées – SEULS LES ADMINS peuvent modifier les salles
              .requestMatchers(HttpMethod.POST,   "/api/salles/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.PUT,    "/api/salles/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.DELETE, "/api/salles/**").hasRole("ADMIN")

              // 6) Routes protégées – SEULS LES ADMINS peuvent modifier les films
              .requestMatchers(HttpMethod.POST,   "/api/films/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.PUT,    "/api/films/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.DELETE, "/api/films/**").hasRole("ADMIN")

              // 7) Routes protégées – SEULS LES ADMINS peuvent accéder à la gestion des utilisateurs
              .requestMatchers(HttpMethod.GET,    "/api/utilisateurs/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.POST,   "/api/utilisateurs/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.PUT,    "/api/utilisateurs/**").hasRole("ADMIN")
              .requestMatchers(HttpMethod.DELETE, "/api/utilisateurs/**").hasRole("ADMIN")

              // 8) Tout le reste doit être authentifié
              .anyRequest().authenticated()
          )
          .authenticationProvider(authenticationProvider(uds, passwordEncoder()))
          //indique d’utiliser le DaoAuthenticationProvider (chargé du chargement utilisateur + vérification du mot de passe BCrypt)
          .addFilter(new JwtAuthenticationFilter(authManager, jwtUtil, userRepo))
          //JwtAuthenticationFilter intercepte POST /api/auth/login, valide l’utilisateur et émet un JWT.

          .addFilterAfter(new JwtAuthorizationFilter(jwtUtil, uds),
                          JwtAuthenticationFilter.class);
        //vérifie le JWT sur toutes les autres requêtes et, si valide, place l’utilisateur dans le contexte de sécurité.

        return http.build();
    }

    // 6) CORS pour Angular
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // pour tout le monde
                        .allowedOrigins("http://localhost:4200") // On n’autorise que les requêtes provenant du front-end Angular (sur ce domaine et ce port).
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
