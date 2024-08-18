package org.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

////simple request: http://localhost:8080/?name=Recruto&message=Давай дружить
@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String helloRecruto(@RequestParam(name = "name", required = false, defaultValue = "Recruto") String name,
                               @RequestParam(name = "message", required = false, defaultValue = "Давай дружить") String message, HttpServletRequest request,
    HttpServletResponse response) {
        response.addHeader("content-type", "text/html; charset=utf-8");
        return "Hello " + name + "! " + message + "!";
    }
}
