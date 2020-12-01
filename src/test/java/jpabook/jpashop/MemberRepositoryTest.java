package jpabook.jpashop;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
/*
@SpringBootTest
@RunWith(SpringRunner.class) //spring 에서 제공하는 프로그램으로 test한다는 것을 명시
public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional //spring 제공으로 된걸 선택..entity선언시 필수,,TEST에 선언시 test 실행후 해당 내용을 rollback 해서 데이터가 안남음
    @Rollback(false) //transactional의 rollback 기능 off
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("memberA");


        //when
        String name = memberRepository.save(member);
        Member findMember = memberRepository.findByname(name);


        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member);//저장값이랑 조회값이랑 같은지 확인
        System.out.println("findMember == member : " + (findMember == member)); //영속성 content는 id가 같기 때문

    }

}*/