package ue1104.iramps.be.api_backend.Security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Cinema V2")
                .version("1.0.0")
                .description("Documentation interactive des endpoints REST de Cinema V2")
            );
    }

    // (facultatif) Grouper les endpoints en « Public » / « Admin »
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("Public")
            .pathsToMatch("/api/salles/**", "/api/films/**", "/api/auth/**")
            .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
            .group("Admin")
            .pathsToMatch("/api/salles/**")
            .build();
    }
}
