package com.example.WellnessVision;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class index {
    @GetMapping("/")

    public String myf(){
        return "Hello World";
    }


}
