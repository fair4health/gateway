package eu.fair4health.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
    String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    String clientSecret;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // Test with Authorization
        /* http.authorizeExchange()
            .pathMatchers(HttpMethod.GET, "/oauth/**")
            .hasAuthority("SCOPE_read")
            .pathMatchers(HttpMethod.POST, "/oauth")
            .hasAuthority("SCOPE_write"); */
        
        http.csrf().disable();
        
        http.cors().disable();

        http.authorizeExchange()
            .pathMatchers("/discovery/swagger-ui.html", "/discovery/v2/api-docs", 
                "/discovery/configuration/ui", "/discovery/swagger-resources/**", 
                "/discovery/configuration/security", "/webjars/**")
            .permitAll();

        http.authorizeExchange()
            .anyExchange()
            .authenticated();
        
        http.oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(token -> token.introspectionUri(this.introspectionUri)
            .introspectionClientCredentials(this.clientId, this.clientSecret)));
        
        return http.build();
    }
}