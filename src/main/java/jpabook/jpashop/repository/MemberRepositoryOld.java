package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository //빈등록
@RequiredArgsConstructor //스프링부트에서는 생성자 주입으로 레파지토리 생성가능, 엔티티도 @PersistenceContext 없이 @Autowired로 가능
public class MemberRepositoryOld {

    private final EntityManager em; //엔티티매니저만들어서 위임한다.

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class) //쿼리,반환형식
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
