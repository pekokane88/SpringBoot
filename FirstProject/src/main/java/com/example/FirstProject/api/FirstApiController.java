package com.example.FirstProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world!";
    }

    //@Controller 에서는 view page 를 반환해 줬지만, restController 에서는 이러면 진짜 greetings 라는 문자를 반환 해준다.
    //@GetMapping("/hi")
    //public String niceToMeetYou(){
    //  return "greetings";
    //}
}
