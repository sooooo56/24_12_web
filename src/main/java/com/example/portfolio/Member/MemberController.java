package com.example.portfolio.Member;

import jakarta.validation.Valid;
import lombok.Getter;
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
    public String UserJoin(MemberForm memberForm){
        return "join";
    }

    @PostMapping("/join")
    public String UserJoin(@Valid MemberForm memberForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "join";
        }

        if (!memberForm.getPassword1().equals(memberForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "join";
        }

        memberService.userJoin(memberForm.getUsername(),memberForm.getPassword1()
                ,memberForm.getEmail(),memberForm.getUserNickname());
        return "/redirect:/";
    }

    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
