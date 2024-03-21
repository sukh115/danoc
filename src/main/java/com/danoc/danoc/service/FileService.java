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
