package jpabook.jpashop.repository;
import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * spring JPA 사용방법(대부분 구현)
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 스프링 데이터 JPA는 JpaRepository 라는 인터페이스를 제공하는데, 여기에 기본적인 CRUD 기능이 모두 제공된다.
     * (일반적으로 상상할 수 있는 모든 기능이 다 포함되어 있다.)
     * findByName 처럼 일반화 하기 어려운 기능도 메서드 이름으로 정확한 JPQL 쿼리를 실행한다.
     * ex) select m from Member m where m.name = :name
     * 개발자는 인터페이스만 만들면 된다. 구현체는 스프링 데이터 JPA가 애플리케이션 실행시점에 주입해준다.
     * @param name
     * @return
     */
    List<Member> findByName(String name);
}