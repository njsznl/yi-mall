package com.yi.mall.member;

import com.yi.mall.member.entity.MemberEntity;
import com.yi.mall.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallMemberApplicationTests {


    @Autowired
    MemberService memberService;

    @Test
    void contextLoads() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setCity("北京");
        memberEntity.setJob("程序员");
        memberService.save(memberEntity);
    }

}
