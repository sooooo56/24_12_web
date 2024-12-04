package com.example.portfolio.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if (memberOptional.isEmpty()){
            throw new RuntimeException("잘못된 회원정보입니다.");
        }

        Member member = memberOptional.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        User loginedUser = new User(member.getUsername(),member.getPassword(),authorities);

        return loginedUser;
    }
}
