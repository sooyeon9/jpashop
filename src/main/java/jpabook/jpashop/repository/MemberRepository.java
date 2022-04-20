package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext //TODO 의미
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 member을 넣어줌 commit되는 시점에 쿼리 날아가서 디비에 반영
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id); // pk를 넣어준다
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // sql과 다른 점; 테이블이 아닌 엔티티 객체에 대해서 조회
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
