package com.colorbook.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrogApiSwaggerController {

    @GetMapping("/swagger")
    public String redirect(String s) {
        return "redirect:".concat("/swagger-ui.html");
    }
}
