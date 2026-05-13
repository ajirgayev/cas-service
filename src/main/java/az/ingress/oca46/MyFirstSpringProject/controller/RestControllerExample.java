package az.ingress.oca46.MyFirstSpringProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerExample {

    @GetMapping("/project")
    public String displayInfo(){
        return "My First Project";
    }
}
