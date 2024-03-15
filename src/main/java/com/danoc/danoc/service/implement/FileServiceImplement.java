package com.danoc.danoc.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danoc.danoc.service.FileService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FileServiceImplement implements FileService{

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        
        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) return null;
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

            try {
                file.transferTo(new File(savePath));
            } catch (Exception exception) {
                exception.printStackTrace();
                log.debug("파일 업로드 중 오류 발생");
                return null;
            }

            String url = fileUrl + saveFileName;
            log.debug("파일 업로드 성공");
            return url;

    }        

    @Override
    public Resource getImage(String fileName) {

        Resource resource = null;

        try {
            resource = new UrlResource("file:" + filePath + fileName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
    
}
