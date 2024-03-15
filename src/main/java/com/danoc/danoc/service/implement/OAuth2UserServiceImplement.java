package com.danoc.danoc.service.implement;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.danoc.danoc.entity.CustomOAuth2User;
import com.danoc.danoc.entity.UserEntity;
import com.danoc.danoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{
    
        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
            log.debug("OAuth2 user attributes: {}", new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception exception) {
            log.error("Error occurred while logging OAuth2 user attributes", exception);
        }

        UserEntity userEntity = null;
        String username = null;
        Long userId = null;
        String email = "email@email.com";
        String name = null;
        Timestamp date = new Timestamp(System.currentTimeMillis());

        if (oauthClientName.equals("kakao")) {
            username = "kakao_" + oAuth2User.getAttributes().get("username");
            userEntity = new UserEntity(username, email, name, "kakao", date);
        }
        if (oauthClientName.equals("naver"))
        {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            username = "naver_"+responseMap.get("username").substring(0, 14);
            email = responseMap.get("email");
            userEntity = new UserEntity(username, email, name,"naver", date);

        }

        userRepository.save(userEntity);

        return new CustomOAuth2User(username, userId);
    }
}
