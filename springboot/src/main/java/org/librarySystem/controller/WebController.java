package org.librarySystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/weather")
    public String weather() {
        return "今天天气：25摄氏度 晴";
    }

    @GetMapping("/count")
    public Integer count() {
        return 15;
    }
}
