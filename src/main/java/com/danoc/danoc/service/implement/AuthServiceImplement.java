package com.danoc.danoc.service.implement;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danoc.danoc.common.CertificationNumber;
import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.dto.request.auth.CheckCertificationRequestDto;
import com.danoc.danoc.dto.request.auth.EmailCertificationRequestDto;
import com.danoc.danoc.dto.request.auth.IdCheckRequestDto;
import com.danoc.danoc.dto.request.auth.SignInRequestDto;
import com.danoc.danoc.dto.request.auth.SignUpRequestDto;
import com.danoc.danoc.dto.response.auth.CheckCertificationResponseDto;
import com.danoc.danoc.dto.response.auth.EmailCertificationResponseDto;
import com.danoc.danoc.dto.response.auth.IdCheckResponseDto;
import com.danoc.danoc.dto.response.auth.SignInResponseDto;
import com.danoc.danoc.dto.response.auth.SignUpResponseDto;
import com.danoc.danoc.entity.CertificationEntity;
import com.danoc.danoc.entity.UserEntity;
import com.danoc.danoc.provider.EmailProvider;
import com.danoc.danoc.provider.JwtProvider;
import com.danoc.danoc.repository.CertificationRepository;
import com.danoc.danoc.repository.UserRepository;
import com.danoc.danoc.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {

        try {
            String username = dto.getUsername();
            
            boolean isExistId = userRepository.existsByUsername(username);
            if  (isExistId)  {
                return IdCheckResponseDto.duplicateId();
            }

        } catch (Exception e) {
            log.debug("아이디중복확인실패", e);
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();

    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            
            String username = dto.getUsername();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUsername(username);
            if (isExistId) return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCerificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(username, email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception e) {
            log.debug("이메일인증실패", e);
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();

    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {
            
                    String username = dto.getUsername();
                    String email = dto.getEmail();
                    String certificationNumber = dto.getCertificationNumber();
        
                    CertificationEntity certificationEntity = certificationRepository.findByUsername(username);
                    if (certificationEntity == null) return CheckCertificationResponseDto.certificationFail();
        
                    boolean isMatch = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
                    if (!isMatch) return CheckCertificationResponseDto.certificationFail();
        
        
                } catch (Exception e) {
                    log.debug("회원가입 인증", e);
                    return ResponseDto.databaseError();
                }
        
                return CheckCertificationResponseDto.success();
                
            }

    @Override
    @Transactional
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        Logger log =LoggerFactory.getLogger(this.getClass());
        try {
            
            String username = dto.getUsername();
            boolean isExistId = userRepository.existsByUsername(username);
            if (isExistId) {
                log.info("Username {} already exists.", username);
                return SignUpResponseDto.duplicateId();
            
            }

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUsername(username);
            boolean isMatched = certificationEntity.getEmail().equals(email) &&
                              certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) {
                log.info("Certification failed for user ID {}", username);
                return SignUpResponseDto.certificationFail();
            }

            String password = dto.getPassword();

            String name = dto.getName();
            String tel = dto.getTel();

            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            dto.setDate(currentDate);
            

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            certificationRepository.deleteByUsername(username);


        } catch (Exception exception) {
            // exception.getStackTrace();
            log.error("회원가입 실패", exception);
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
       
        String token = null;
       
        try {

            String username = dto.getUsername();
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity == null) return SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFail();

            Long userId = userEntity.getUserId();
            token = jwtProvider.create(username, userId);
        
       } catch (Exception e) {
        log.debug("로그인 실패", e);
            return ResponseDto.databaseError();
       }

       return SignInResponseDto.success(token);

    }

}
