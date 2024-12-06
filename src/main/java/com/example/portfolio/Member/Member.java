package com.example.portfolio.Member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Column(unique = true)
    private String userNickname;

    @Column(unique = true)
    private String email;

}
