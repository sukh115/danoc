package com.danoc.danoc.filter;

import java.util.List;
import java.io.IOException;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.GrantedAuthority;

import com.danoc.danoc.entity.UserEntity;
import com.danoc.danoc.provider.JwtProvider;
import com.danoc.danoc.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    String token = parseBearerToken(request);
                    
                    if (token != null && jwtProvider.validateToken(token)) {
                        Long userId = jwtProvider.extractUserId(token);
                        String username = jwtProvider.extractUsername(token);
                        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(userId);
                        if (optionalUserEntity.isPresent()) {

                            UserEntity userEntity = optionalUserEntity.get();
                            String role = userEntity.getRole();
                            
                            List<GrantedAuthority> authorities = new ArrayList<>();
                            authorities.add(new SimpleGrantedAuthority(role));
                            
                            AbstractAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userId, null, authorities);
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            
                            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                            securityContext.setAuthentication(authenticationToken);
                            SecurityContextHolder.setContext(securityContext);
                        } else {
                            log.error("User not found for userId: {}", userId);
                        }
                    }
        
                } catch (Exception e) {
                    log.error("Error occurred while processing JWT token", e);
                }
        
                filterChain.doFilter(request, response);
            }
        
        public String parseBearerToken(HttpServletRequest request) {
            String authorization = request.getHeader("Authorization");
    
            boolean hasAuthorization = StringUtils.hasText(authorization);
            if (!hasAuthorization) {
                log.debug("Authorization헤더에 토큰값이 없음");    
                return null;
            }
    
            boolean isBearer = authorization.startsWith("Bearer ");
            if (!isBearer){
                log.debug("Authorization헤더값이 Bearer가 아님");
                return null;
            } 
    
            return authorization.substring(7);
        }
    }