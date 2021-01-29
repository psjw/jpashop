package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest //Autowired 실패(스프링 컨테이너 안에서 테스트)
@Transactional //JPA에서 같은 Transaction 안에서 같은 ID값이면 같은 영속성 컨텍스트에서 똑같은 애가 관리
//자동 롤백 함
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원가입()throws  Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId=memberService.join(member);

        //then
//        em.flush(); //영속성 컨텍스트가 DB에 반영
        assertEquals(member, memberRepository.findOne(saveId));
    }

  /*  @Test
    public void 중복_회원_예외()throws  Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        try {
            memberService.join(member2); //예외가 발생해야한다.
        }catch (IllegalStateException e){
            return;
        }

        //then
        fail("예외가 발생해야 한다."); //여기까지 오면 안댐
    }*/


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외()throws  Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야한다.


        //then
        fail("예외가 발생해야 한다."); //여기까지 오면 안댐
    }
}