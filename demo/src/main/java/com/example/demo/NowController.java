package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class NowController {

    @GetMapping("/now")
    public Now now() {
        return new Now();
    }
}