package com.customerlist.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.customerlist.api.security.jwt.JwtRequestFilter;
import com.customerlist.api.security.jwt.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
	    private JwtUserDetailsService jwtUserDetailsService;
	    @Autowired
	    private JwtRequestFilter jwtRequestFilter;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(jwtUserDetailsService);
	    }

	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.csrf().disable()
	                .authorizeRequests().antMatchers(new String[] {"/","/user/*","/h2-console","/h2-console/**"}).permitAll().
	                anyRequest().authenticated().and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	    }

	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @SuppressWarnings("deprecation")
		@Bean
	    public PasswordEncoder passwordEncoder()
	    {
	        return NoOpPasswordEncoder.getInstance();
	    }

}
