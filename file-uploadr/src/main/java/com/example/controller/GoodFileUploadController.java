package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/good-file-upload")
public class GoodFileUploadController {
    private final String ROOT = "uploads";

    @RequestMapping(method = RequestMethod.GET)
    public String renderBadFileUploadPage() {
        return "good/file-upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleGoodFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                if(!Files.isDirectory(Paths.get(ROOT))) {
                    Files.createDirectory(Paths.get(ROOT));
                }
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException|RuntimeException exc) {
                exc.printStackTrace();
                return "good/file-upload-failed";
            }
        }
        return "good/file-upload-success";
    }
}
