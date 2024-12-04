package com.example.portfolio.Member;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.PublicKey;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 (중복 아이디 검사 및 비밀번호 암호화 포함)
    public Member userJoin(String username, String email, String password,String userNickname){
        // 이미 존재하는 사용자 확인
        if (memberRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }

        // 사용자 생성 및 저장
        Member member = Member.builder()
                .username(username)
                .userNickname(userNickname)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        return memberRepository.save(member);
    }


    // 사용자 조회 (username 기준)
    public Member findByUsername(String username) {
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        if (memberOpt.isPresent()){
            return memberOpt.get();
        } else {
            throw new RuntimeException("username not found");
        }
    }

}
