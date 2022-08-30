package com.crm.Security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTSecutiryFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserDetailsService userService;

    public JWTSecutiryFilter(JWTService jwtService, UserDetailsService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = response.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            boolean tokenValid = jwtService.isValidToken(token);

            if (tokenValid) {
                String username = jwtService.getLoginUsuario(token);
                UserDetails user = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken userSecurity = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                userSecurity.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userSecurity);
            }
        }

        filterChain.doFilter(request, response);
    }
}
