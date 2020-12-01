package jpabook.jpashop.Repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.lang.reflect.Array;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext//jpa 지원 엔티티메니저 만들어서 저장>>엔티티메니저 주입 할 수 있다.
    private final EntityManager em;

    //@PersistenceUnit : Entity manager factory를 직접 주입 할 수 있따.

    public void save(Member member) {//member를 저장하는 로직
        em.persist(member);
    }

    public Member findOne(Long id) { //member를 찾아서 반환
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class ) //createQuery : JPQL문 Member m에서 m을 찾아서 반환(여기서 Members는 table이 아닌 entity이다)
                .getResultList();

        /*List<Member> result = em.createQuery("select m from Member m", Member.class ) //이 코드를 inline으로 변환해서 쓰면 위처럼 쓸수있다.
                .getResultList();
        return result;*/
    }

    public List<Member> findByname(String name){ //이름을 조건으로 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}



