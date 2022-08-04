package com.spring.springdeep01.controller;

import com.spring.springdeep01.model.user.UserRoleEnum;
import com.spring.springdeep01.security.UserDetailsImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {




//    @GetMapping("/")
//    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        if(userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
//            model.addAttribute("admin_role", true);
//        }
//
//        return "index";
//    }
}