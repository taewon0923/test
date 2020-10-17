package com.shop.controller;

import com.shop.dto.MemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
public class MemberController {

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(MemberForm memberForm){

        System.out.println(memberForm.toString());

        return "redirect:/";
    }

}
