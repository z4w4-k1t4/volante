package jp.co.volante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						// ① OPTIONSメソッドは認証不要
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						// ② それ以外は認証必須
						.anyRequest().authenticated())
				.addFilterBefore(firebaseAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public FirebaseAuthenticationFilter firebaseAuthenticationFilter() {
		return new FirebaseAuthenticationFilter();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("http://localhost:*") // Next.jsのdevサーバ
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
