package jpabook.jpashop.Service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)//junit4 실행시 spring이랑 같이 실행할 경우 선언
@SpringBootTest//스프링 부트 사용하면서 test시 선언
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService; //필드 주입
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em; //DB에 영속성을 반영가히 위해 Entitymanager 주입



    @Test
    @Rollback(false)//@Transactionl은 기본 rollback이라 이걸 꺼줌
    public void 회원가입()  throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);


        //then
        //em.flush(); //DB에 쿼리로 insert 시키기 위해 즉 영속성을 반영하기 위해
        Assert.assertEquals(member, memberRepository.findOne(saveId));


    }

    @Test(expected = IllegalStateException.class) //try catch문이랑 같음
    public void 중복_회원_가입() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
        memberService.join(member2); //이름은 같지만 객체가 같은 경우 에러발생

        //then
        Assert.fail("예외가 발생해야 한다."); //오류 발생 코드



    }

}