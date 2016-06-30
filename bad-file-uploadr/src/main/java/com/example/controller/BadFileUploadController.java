package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bad-file-upload")
public class BadFileUploadController {

    @RequestMapping(method = RequestMethod.GET)
    public String renderBadFileUploadPage() {
        return "file-upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleBadFileUpload() {
        return "";
    }

}
