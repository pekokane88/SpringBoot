package com.example.FirstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String NiceToMeetYou(Model model){
        model.addAttribute("username", "프뭐시기 선샌님");
        return "greetings";
    }
    
    @GetMapping("/bye")
    public String SeeYouNextTime(Model model){
        model.addAttribute("username", "선샌니");
        return "goodbye";
    }
}
