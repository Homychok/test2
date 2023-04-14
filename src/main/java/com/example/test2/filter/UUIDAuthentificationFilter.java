package com.example.test2.filter;

import com.example.test2.model.Token;
import com.example.test2.service.AuthentificationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class UUIDAuthentificationFilter extends OncePerRequestFilter {
    private  AuthentificationService authentificationService;

    public UUIDAuthentificationFilter(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uuid = request.getHeader("X-Auth-Token");
        if (StringUtils.hasText(uuid)) {
            Optional<Token> token = authentificationService.findbyUUID(uuid);
            if(token.isPresent()) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        token.get(),
                        null,
                        List.of(new SimpleGrantedAuthority("USER"))
                );
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request,response);
    }
}