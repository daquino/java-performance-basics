package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/bad-file-upload")
public class BadFileUploadController {
    private final String ROOT = "uploads";

    @RequestMapping(method = RequestMethod.GET)
    public String renderBadFileUploadPage() {
        return "bad/file-upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleBadFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                if(!Files.isDirectory(Paths.get(ROOT))) {
                    Files.createDirectory(Paths.get(ROOT));
                }
                Files.write(Paths.get(ROOT, file.getOriginalFilename()), file.getBytes(), StandardOpenOption.CREATE);
            }
            catch (IOException|RuntimeException exc) {
                exc.printStackTrace();
                return "bad/file-upload-failed";
            }
        }
        return "bad/file-upload-success";
    }
}
