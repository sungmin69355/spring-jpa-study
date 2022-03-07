package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//읽기전용으로 판단하고 최적화시켜준다. 쓰기일때는 사용 x, JPA를 사용할떄 트랜잭션 위에서 동작해아한다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository; //컴파일시점에 오류던지기떄문에 final 꼭 쓰자


    /**
     * 회원 가입
     */
    @Transactional //따로 설정하면 이게 우선권을 가진다. (쓰기일 경우)
    public Long join(Member member){
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    /**
     * 회원 전체조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }



}
