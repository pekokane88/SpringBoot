package com.example.FirstProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GroupController {

    @GetMapping("/groups/{groupId}")
    public String show(@PathVariable Long groupId, Model model){

        //String groupname = "H";
        //model.addAttribute("groupname", groupname);
        //model.addAttribute("teamDtoList", teamDtoList);

        return "groups/show";
    }

}
