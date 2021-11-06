package com.customerlist.api.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final String HEADER_AUTHORIZATION = "Authorization";
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {

			final String requestTokenHeader = request.getHeader(HEADER_AUTHORIZATION);
			
			final String jwtToken = jwtTokenUtil.extractTokenFromAuthorization(requestTokenHeader);
			if ("".equals (jwtToken) || null == jwtToken || "null".equals(jwtToken)) {
				chain.doFilter(request, response);
				return;
			}
			if (jwtTokenUtil.isTokenExpired(jwtToken)) {
				chain.doFilter(request, response);
				return;
			}
			String email = jwtTokenUtil.extractUsername(jwtToken);
			if (email == null) {
				SecurityContextHolder.clearContext();
				return;
			}


			UserDetails userDetails = userDetailsService.loadUserByUsername(email);

			if (userDetails == null) {
				SecurityContextHolder.clearContext();
				chain.doFilter(request, response);
				return;
			}
			// configure Spring Security to manually set authentication
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);
		} catch (Exception e) {
			if (response instanceof HttpServletResponse) {
				HttpServletResponse httpServletResponse = (HttpServletResponse) response;
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			}
		}
	}
}
