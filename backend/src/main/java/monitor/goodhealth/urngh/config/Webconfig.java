package monitor.goodhealth.urngh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {
	@CrossOrigin
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@CrossOrigin
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@CrossOrigin
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**") // Allow CORS on all endpoints
					.allowedOrigins("http://192.168.1.16:8080") // Allow your phone IP
					.allowedOrigins("http://127.0.0.1:5500") // Allow the frontend URL
					.allowedOrigins("http://192.168.1.10:5500") // Allow the frontend URL
					.allowedOrigins("http://localhost:5500") // Allow the frontend URL
					.allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // Allowed HTTP methods
					.allowedHeaders("*"); // Allow all headers
			}
		};
	}
}
