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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //1. get id and find data in DB
        Member targetMember = memberRepository.findById(id).orElse(null);
        //2. If find add model
        if (targetMember != null){
            model.addAttribute("member", targetMember);
        }
        return "/members/edit";
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //1. Get target Entity
        Member targetEntity = memberRepository.findById(id).orElse(null);
        //2. delete
        if (targetEntity != null){
           memberRepository.delete(targetEntity);
           log.info("DB delete");
        }
        //3. redirect the page.
        return "redirect:/members";
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

    @PostMapping("/update")
    public String update(MemberForm form){
        //1. Get data and change entity
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        //2. Update That Entity
        Member newMember = memberRepository.save(memberEntity);
        log.info(newMember.toString());
        //3. Redirect to detail page
        return "redirect:/members/" + newMember.getId();
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
