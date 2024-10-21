package org.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web.implDTO.PersonImpl;
import org.web.service.ProjectService;
import org.web.entity.Project;
import org.springframework.http.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ProjectsController {

    @Autowired
    ProjectService projectDTO;

    @GetMapping("/projects")
    @ResponseBody
    public ResponseEntity<Object> getProjects(@RequestParam(name = "type", defaultValue = "null") String type, @RequestParam(name = "first", defaultValue = "0") Long first, @RequestParam(name = "last", defaultValue = "-1") Long last, HttpServletRequest request,
                                              HttpServletResponse response) throws UnsupportedEncodingException {
        ArrayList<Project> projects = projectDTO.getAllProjects();

        for (Project project : projects) {
            System.out.println(project.getCreated_at());
        }
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        if(type.equals("null")) {
            if (first == 0 && last == -1) {
                return new ResponseEntity<>(projects, headers, HttpStatus.OK);
            }else{
                if(last > first && last < projects.size()){
                    return new ResponseEntity<>(new ArrayList<>(projects.subList((int) (first - 1), (int) (last - 1))), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Filter Error", HttpStatus.BAD_REQUEST);
                }
            }
        }else {
            ArrayList<Project> projects1 = projects.stream().filter(p -> p.getCategory().equals(type)).collect(Collectors.toCollection(ArrayList::new));

            if (first == 0 && last == -1) {
                return new ResponseEntity<>(projects1, HttpStatus.OK);
            }else{
                if(last > first && last < projects1.size()){
                    return new ResponseEntity<>(new ArrayList<>(projects1.subList((int) (first - 1), (int) (last - 1))), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Filter Error", headers, HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    @PostMapping("/projectAdd")
    public ResponseEntity<Object> projectAdd(@RequestBody Project project, HttpServletRequest request) {
        System.out.println(project.getCreated_at());

        return new ResponseEntity<>("Added!", HttpStatus.CREATED);
    }
}