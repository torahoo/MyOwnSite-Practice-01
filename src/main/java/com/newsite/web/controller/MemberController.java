package com.newsite.web.controller;

import com.newsite.domain.member.Member;
import com.newsite.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/controller/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @GetMapping
    public String members (Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members" , members);
        log.info("members={}", members);
        return "/member/members-list";
    }

    @GetMapping("/add")
    public String

    //테스트용 데이터
    @PostConstruct
    public void init () {
        memberRepository.save(new Member("user1@naver.com", "user1", "1234", 20));
        memberRepository.save(new Member("user2@naver.com", "user2", "1234", 30));
    }
}
