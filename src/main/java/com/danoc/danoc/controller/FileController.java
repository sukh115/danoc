package com.danoc.danoc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danoc.danoc.service.FileService;

import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


    @PostMapping("/upload")
    public String upload(
        @RequestParam("file") MultipartFile file
    ) {
        String url = fileService.upload(file);
        log.debug("컨트롤러");
        return url;
    }

    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
        @PathVariable("fileName") String fileName
    ) {
        Resource resource = fileService.getImage(fileName);
        return resource;
    }

    

}
    
