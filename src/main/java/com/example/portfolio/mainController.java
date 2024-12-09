package com.example.portfolio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class mainController {

    @GetMapping("/")
    public String root(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(){
        return "mypage";
    }

}
