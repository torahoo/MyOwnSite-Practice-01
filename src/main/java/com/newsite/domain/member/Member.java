package com.newsite.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

    private long memberId;
    private String memberEmail;
    private String memberNickName;
    private String password;
    private Integer memberAge;

    public Member () {}

    public Member(String memberEmail, String memberNickName, String password, Integer memberAge) {
        this.memberEmail = memberEmail;
        this.memberNickName = memberNickName;
        this.password = password;
        this.memberAge = memberAge;
    }

    public Member(String memberEmail, String memberNickName, Integer memberAge) {
        this.memberEmail = memberEmail;
        this.memberNickName = memberNickName;
        this.memberAge = memberAge;
    }
}
