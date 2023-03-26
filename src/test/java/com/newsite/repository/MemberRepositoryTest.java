package com.newsite.repository;

import com.newsite.domain.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();

    @AfterEach
    void afterEach () {
        memberRepository.clearStroe();
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("user1@naver.com", "user1", "1234", 20);
        Member member2 = new Member("user2@naver.com", "user2", "1234", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);

    }

    @Test
    void findById() {
        //given
        Member member1 = new Member("user1@naver.com", "user1", "1234", 20);
        memberRepository.save(member1);
        //when
        Member findMember = memberRepository.findById(member1.getMemberId());

        //then
        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    void save() {
        //given
        Member member1 = new Member("user1@naver.com", "user1", "1234", 20);
        //when
        Member saveMember = memberRepository.save(member1);
        //then
        Member findMember = memberRepository.findById(member1.getMemberId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void update() {
        //given
        Member member1 = new Member("user1@naver.com", "user1", "1234", 20);
        Member saveMember = memberRepository.save(member1);
        Long memberId = saveMember.getMemberId();
        //when
        Member updateParam = new Member("update1@naver.com", "update1", 30);
        memberRepository.update(memberId, updateParam);

        //then
        Member findMember = memberRepository.findById(memberId);

        assertThat(findMember.getMemberEmail()).isEqualTo(updateParam.getMemberEmail());
        assertThat(findMember.getMemberNickName()).isEqualTo(updateParam.getMemberNickName());
        assertThat(findMember.getMemberAge()).isEqualTo(updateParam.getMemberAge());
    }

    @Test
    void delete() {
        //given
        Member member1 = new Member("user1@naver.com", "user1", "1234", 20);
        Member member2 = new Member("user2@naver.com", "user2", "1234", 20);

        Member deleteMember = memberRepository.save(member2);
        Member saveMember = memberRepository.save(member1);
        long deleteId = deleteMember.getMemberId();
        //when
        memberRepository.delete(deleteId);
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).contains(member1);
        for(Member member : result) {
            System.out.println(member);
        }
    }
}