package huy.java.server.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		// return null;
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(
				(auth) -> auth
							.requestMatchers(new AntPathRequestMatcher("/products/**", "GET"))
							.hasAnyRole("USER", "ADMIN")
							.requestMatchers(new AntPathRequestMatcher("/products", "POST"))
							.hasRole("ADMIN"))
			.httpBasic(withDefaults())
			.csrf((csrf) -> csrf.disable());
		return http.build();
	}
}
