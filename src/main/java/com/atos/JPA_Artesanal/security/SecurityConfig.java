package com.atos.JPA_Artesanal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.atos.JPA_Artesanal.auth.JWT_Filter.JWTAuthenticationFilter;
import com.atos.JPA_Artesanal.auth.JWT_Filter.JWTAuthorizationFilter;
import com.atos.JPA_Artesanal.auth.serviceJWT.JWTService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTService jwtService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/produs/all").authenticated().antMatchers("/").permitAll().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService)).csrf().disable().cors()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//		String idForEncode = "SHA-256";
//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//		encoders.put(idForEncode, new MessageDigestPasswordEncoder("SHA-256"));
//		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//		encoders.put("scrypt", new SCryptPasswordEncoder());
//
//		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
//
//		String pword = Jwts.builder().signWith(SignatureAlgorithm.HS512,
//				"pwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpwordpword"
//						.getBytes())
//				.compact();

		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("jose")
				.password("JOSE123").roles("ADMIN", "USER").and()
				// .passwordEncoder(passwordEncoder);
				.withUser("user").password("xxx").roles("USER");
		// .passwordEncoder(passwordEncoder);

	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		final CorsConfiguration configuration = new CorsConfiguration();
//		List<String> ao = new ArrayList<>();
//		ao.add("*");
//		configuration.setAllowedOrigins(ao);
//
//		List<String> am = new ArrayList<>();
//		am.add("HEAD");
//		am.add("GET");
//		am.add("POST");
//		am.add("PUT");
//		am.add("DELETE");
//		am.add("PATCH");
//		am.add("PING");
//
//		configuration.setAllowedMethods(am);
//		// setAllowCredentials(true) is important, otherwise:
//		// The value of the 'Access-Control-Allow-Origin' header in the response must
//		// not be the wildcard '*' when the request's credentials mode is 'include'.
//		configuration.setAllowCredentials(true);
//		// setAllowedHeaders is important! Without it, OPTIONS preflight request
//		// will fail with 403 Invalid CORS request
//		List<String> hs = new ArrayList<>();
//		hs.add("Authorization");
//		hs.add("Cache-Control");
//		hs.add("Content-Type");
//		configuration.setAllowedHeaders(hs);
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
}