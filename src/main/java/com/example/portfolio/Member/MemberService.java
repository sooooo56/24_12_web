package com.example.portfolio.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 (중복 아이디 검사 및 비밀번호 암호화 포함)
    public Member userJoin(JoinForm joinForm){
        // 이미 존재하는 사용자 확인
        if (memberRepository.findByUsername(joinForm.getUsername()).isPresent()){
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }


        // 사용자 생성 및 저장
        Member member = Member.builder()
                .username(joinForm.getUsername())
                .userNickname(joinForm.getUserNickname())
                .password(passwordEncoder.encode(joinForm.getPassword1()))
                .email(joinForm.getEmail())
                .build();
        return memberRepository.save(member);
    }


}
