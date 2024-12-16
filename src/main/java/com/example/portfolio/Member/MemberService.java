package com.example.portfolio.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

//    public Member getMemberId(Long id){
//        Optional<Member> member = memberRepository.findById(id);
//        if (member.isPresent()){
//            return member.get();
//        } else {
//            throw new RuntimeException();
//        }
//    }

    // 현재 로그인한 회원 조회 메서드 추가
    public Member getCurrentMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요합니다."));
    }

}
