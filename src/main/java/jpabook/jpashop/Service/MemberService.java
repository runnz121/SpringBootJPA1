package jpabook.jpashop.Service;


import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//데이터 변경은 transaction 안에서 이루어져야 하기 때문에 무조건 있어야함
@RequiredArgsConstructor//final 이 있는 것만 생성자를 만들어줌
public class MemberService {


    //생성자가 한개만있으면 스프링이 자동 injection 해줌
    private final MemberRepository memberRepository; //이 필드는 더이상 변경할 일이 없기 때문에 final로 지정

    /*@Autowired//memberRepository를 injection 해준다. 생성자 injection을 쓴다.
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } //Testcase작성하기도 편하고 서비스 작동중 오류가 날일도 없다*/

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);//저장된 member를 넘김
        return member.getId(); //문제없으면 member id를 반환
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByname(member.getName());//같은이름이 있는지 찾기
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)//읽기 전용은 해당문 선언 해주면 성능 향상
    public List<Member> findMembers() {
        return memberRepository.findAll();//전체조회

    }
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId); //단건조회
    }


}
