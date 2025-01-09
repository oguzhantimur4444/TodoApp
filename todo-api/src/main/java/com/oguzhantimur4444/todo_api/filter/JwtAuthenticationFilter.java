package com.oguzhantimur4444.todo_api.filter;

import com.oguzhantimur4444.todo_api.service.JwtService;
import com.oguzhantimur4444.todo_api.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsImp;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImp userDetailsImp) {
        this.jwtService = jwtService;
        this.userDetailsImp = userDetailsImp;
    }
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){

            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails userDetails = userDetailsImp.loadUserByUsername(username);

                if (jwtService.validateJwtToken(token,userDetails)){

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
