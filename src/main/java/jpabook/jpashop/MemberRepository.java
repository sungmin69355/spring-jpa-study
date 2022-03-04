package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em; //스프링부트가 만들어줌

    public Long save(Member member){
        em.persist(member);
        return member.getId(); //커맨드랑 코드랑 분리하기위해 아이디만 반환
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
