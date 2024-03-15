package com.danoc.danoc.service;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

// import lombok.extern.slf4j.Slf4j;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

public interface FileService {

    String upload(MultipartFile file);
    Resource getImage(String fileName);
    
}

// @Value("${file.upload-dir}")
// private String uploadDir;

// public String storeFile(MultipartFile file) {

//     try {
//         // 업로드 디렉토리 생성
//         Path directory = Paths.get(uploadDir).toAbsolutePath().normalize();
//         Files.createDirectories(directory);

//         // 파일명 생성
//         String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

//         // 파일 저장 경로
//         Path targetLocation = directory.resolve(fileName);

//         // 파일 저장
//         Files.copy(file.getInputStream(), targetLocation);

//         return fileName; // 저장된 파일명 반환
        
//     } catch (IOException ex) {
//         log.debug("파일저장에러", ex);
//         throw new RuntimeException("파일을 저장할 수 없습니다.");
//     }

// }