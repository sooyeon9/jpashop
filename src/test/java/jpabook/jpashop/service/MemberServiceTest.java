package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 실행할때 스프링이랑 같이 엮어서 실행할래
@SpringBootTest // 스프링부트 띄운채로 테스트 진행 <- 없으면 Autowired 실패함
@Transactional // 같은 트렌젝셔널 안에서, 같은 (같은 pk를 가진)엔티티는 같은 영속성 컨텍스트로 동일하다
// ↑ 얘가 테스트 케이스에 달리면 기본으로 커밋을 안하면 롤백을 해버림
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void join() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush(); // 강제로 디비에 exec
        assertEquals(member, memberRepository.findById(saveId).get());
    }

    @Test(expected = IllegalStateException.class)
    public void join_exception() {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생

        //then
        fail("예외 발생해야함");
    }
}