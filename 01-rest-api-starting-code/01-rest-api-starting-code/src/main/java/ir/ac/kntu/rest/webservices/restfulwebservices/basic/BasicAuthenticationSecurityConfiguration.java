package ir.ac.kntu.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
					auth -> 
					auth
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.antMatchers("/h2-console/**").permitAll() 
					// Allow access to H2 console
					.anyRequest().authenticated()
				);
		http.httpBasic(Customizer.withDefaults());
		
		http.sessionManagement(
					session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);
		http.csrf().disable();
		
		// Disable frame options to allow H2 console to be embedded in an iframe
        http.headers().frameOptions().disable();
		
		return http.build();
	}
}
