package com.example.FirstProject.controller;

import com.example.FirstProject.dto.MemberForm;
import com.example.FirstProject.entity.Member;
import com.example.FirstProject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public String index(Model model){
        ArrayList<Member> memberArrayList = memberRepository.findAll();
        model.addAttribute("membersList", memberArrayList);
        return "members/index";
    }

    @GetMapping("/signup")
    public String newMembersForm() {return "/members/new";}

    @PostMapping("/join")
    public String newMembersJoin(MemberForm form){

        Member member = form.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        if (memberEntity != null){
            model.addAttribute("members", memberEntity);
            return "members/show";
        }
        else{
            return "members/new";
        }
    }
}
