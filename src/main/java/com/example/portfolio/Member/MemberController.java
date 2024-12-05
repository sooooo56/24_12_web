package com.example.portfolio.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @GetMapping("/join")
    public String UserJoin(joinForm joinForm){
        return "join";
    }

    @PostMapping("/join")
    public String UserJoin(@Valid joinForm joinForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "join";
        }

        if (!joinForm.getPassword1().equals(joinForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "join";
        }

        memberService.userJoin(joinForm.getUsername(), joinForm.getPassword1()
                , joinForm.getEmail(), joinForm.getUserNickname());
        return "/redirect:/login";
    }

    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(){
        return "mypage";
    }

}
