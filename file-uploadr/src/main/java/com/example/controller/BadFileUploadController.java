package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/bad-file-upload")
public class BadFileUploadController {
    private final String ROOT = "uploads";

    @Value("${uploads.dir}")
    private String uploadPath;

    @RequestMapping(method = RequestMethod.GET)
    public String renderBadFileUploadPage() {
        return "bad/file-upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleBadFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            Files.write(Paths.get(uploadPath, file.getOriginalFilename()),
              file.getBytes(), StandardOpenOption.CREATE);
        }
        return "bad/file-upload-success";
    }
}
