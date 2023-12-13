package com.newsite.web.controller;

import com.newsite.domain.member.Member;
import com.newsite.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        model.addAttribute("status", model.getAttribute("status"));
        model.addAttribute("loginUser", model.getAttribute("loginUser"));
        model.addAttribute("loginStatus", model.getAttribute("loginStatus"));
        log.info("members={}", members);
        log.info("status={}", model.getAttribute("status"));
        log.info("loginStatus={}", model.getAttribute("loginStatus"));
        return "/member/members-list";
    }

    @GetMapping("/add")
    public String addMemberForm () {
        return "member/new-memberForm";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        Member saveMember = memberRepository.save(member);
        log.info("saveMember={}", saveMember);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/controller/members";
    }

    @GetMapping("/login")
    public String loginForm () {
        return "/member/loginForm";
    }

    @PostMapping("/login")
    public String login (@ModelAttribute ("member") Member member, RedirectAttributes redirectAttributes) {
        Member findMember = memberRepository.findById(member.getMemberId())
                .orElseThrow(()->new NoSuchElementException("해당 멤버를 찾을수 없음"));
        if(member.getMemberEmail().equals(findMember.getMemberEmail())) {
            if(member.getPassword().equals(findMember.getPassword())) {
                log.info("loginUser={}", findMember);
            }
        }

        return "redirect:/controller/members";
    }

    //테스트용 데이터
    @PostConstruct
    public void init () {
        memberRepository.save(new Member("user1@naver.com", "user1", "1234", 20));
        memberRepository.save(new Member("user2@naver.com", "user2", "1234", 30));
    }
}
