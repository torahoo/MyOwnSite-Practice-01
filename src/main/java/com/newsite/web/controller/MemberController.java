package com.newsite.web.controller;

import com.newsite.domain.member.Member;
import com.newsite.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/controller/members")
@RequiredArgsConstructor
public class MemberController {

        private final MemberRepository memberRepository;

    //테스트용 데이터
    @PostConstruct
    public void init () {
        memberRepository.save(new Member("user1@naver.com", "user1", "1234", 20));
        memberRepository.save(new Member("user2@naver.com", "user2", "1234", 30));
    }
}
