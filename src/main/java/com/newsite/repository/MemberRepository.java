package com.newsite.repository;

import com.newsite.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

        private static Map<Long, Member> store = new HashMap<>();
        private static Long sequence = 0L;

        public List<Member> findAll () {
            return new ArrayList<>(store.values());
        }

        public Optional<Member> findById (long memberId) {
            return Optional.of(store.get(memberId));
        }

        public Member save (Member member) {
            member.setMemberId(++sequence);
            store.put(member.getMemberId(), member);
            return member;
        }

        public void update (long memberId, Member updateParam) {
            Member findMember = findById(memberId);
            findMember.setMemberEmail(updateParam.getMemberEmail());
            findMember.setMemberNickName(updateParam.getMemberNickName());
            findMember.setMemberAge(updateParam.getMemberAge());
        }

        public void delete (long memberId) {
            Member findMember = findById(memberId);
            store.remove(memberId);
        }

        public Optional<Member> findByName(String name) {
            return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny();
        }

        public void clearStroe () {
            store.clear();
        }

}
