package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/good-file-upload")
public class GoodFileUploadController {
    @Value("${uploads.dir}")
    private String uploadPath;

    @RequestMapping(method = RequestMethod.GET)
    public String renderBadFileUploadPage() {
        return "good/file-upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleGoodFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            Files.copy(file.getInputStream(),
              Paths.get(uploadPath, file.getOriginalFilename()),
              StandardCopyOption.REPLACE_EXISTING);
        }
        return "good/file-upload-success";
    }
}
