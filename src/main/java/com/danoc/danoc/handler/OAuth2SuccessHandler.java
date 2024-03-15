package com.danoc.danoc.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.danoc.danoc.entity.CustomOAuth2User;
import com.danoc.danoc.provider.JwtProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private final JwtProvider jwtProvider;
    
    @Override
	public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
        ) throws IOException, ServletException {
		
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            String username = oAuth2User.getName();
            Long userId = oAuth2User.getUserId();

            String token = jwtProvider.create(username, userId);

            response.sendRedirect("http://localhost:3000/auth/oauth-response/" + token + "/3600");

	}
}
