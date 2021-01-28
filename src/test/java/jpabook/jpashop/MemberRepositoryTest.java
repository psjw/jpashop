package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test(){}

    @Test
    @Transactional //Spring 껏을 사용
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        Member member=new Member();
        member.setUsername("memberA");
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(saveId);
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        // findMember == member 같은 트랜젝션안에서 영속성 컨텍스는 동일 
        //같은 영속성 컨텍스트 안에서 아이디 값이 같은 같은 엔티티로 인식
        // 1차 캐쉬에서 가져옴 -> 셀렉트 하지 않음
        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
    }

}
