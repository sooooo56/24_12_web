package com.example.portfolio.Member;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member create(String username, String email, String password,String userNickname) {
        Member member = Member.builder()
                .username(username)
                .userNickname(userNickname)
                .password(password)
                .email(email)
                .build();
        return memberRepository.save(member);
    }
}
