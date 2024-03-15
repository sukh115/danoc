package com.danoc.danoc.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailProvider {
    
    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[다녹] 인증메일입니다.";

    public boolean sendCertificationMail(String email, String certificationNumber) {
    

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            
            log.debug("메일생성에러", e);
            return false;
        }
        
        return true;
    }
        private String getCertificationMessage (String certificationNumber) {

            String certificationMessge = "";
            certificationMessge += "<h1 style='text=align: center;'>[다녹] 인증메일</h1>";
            certificationMessge += "<h3 style='text=align: center;'>인증코드 : <string Style='font-size: 32px; letter-space: 8px;>'>" + certificationNumber + "</string></h3>";
            return certificationMessge;
        }


} 
