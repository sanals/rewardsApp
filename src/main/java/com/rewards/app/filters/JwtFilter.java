package com.rewards.app.filters;

import com.rewards.app.security.JwtService;
import com.rewards.app.security.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

//    @Autowired
//    private ApplicationContext applicationContext;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String name = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            try {
                name = jwtService.extractUsername(token);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        if(name != null && SecurityContextHolder.getContext().getAuthentication()==null){
//            MyUserDetailsService userDetailsService2 = applicationContext.getBean(MyUserDetailsService.class);
//            userDetailsService2.loadUserByUsername(name);
//OR directly
//            UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(name);
//OR autowire
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);

            try {
                if(jwtService.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(request, response);
    }
}
