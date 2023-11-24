package huy.java.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class MySecurityConfig {
	
	/*@Bean
	UserDetailsService userDetailService() {
		InMemoryUserDetailsManager userService = new InMemoryUserDetailsManager();
		UserDetails user = User
							.withUsername("user")
							.password(passwordEncoder().encode("password"))
							.authorities("read")
							.build();
		userService.createUser(user);
		return userService;
	}*/
	
	@Bean
	MyAuthenticationProvider authProvider() {
		MyAuthenticationProvider ap = new MyAuthenticationProvider();
		return ap;
	} 
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
			.formLogin(withDefaults());
		return http.build();
	}
}
