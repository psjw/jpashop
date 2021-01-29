package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional //public method는 Transaction됨 //javax. 쪽 Transactional 보다 스프링꺼 쓰자
@Transactional(readOnly = true)
//@AllArgsConstructor //롬복
@RequiredArgsConstructor //final 필드만 생성자 생성
public class MemberService {

    //    @Autowired //TDD 어려움
//    private MemberRepository memberRepository;
    private final MemberRepository memberRepository; // final 선언시 생성자 반드시 필요

//    @Autowired //중간에 변경 소스 있는경우 찾기 어려움
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//    @Autowired //생략가능 생성자가 하나 있는경우
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     *  회원가입
     */
    @Transactional //읽기만 사용하여 기본값만 readOnly=false세팅
    public Long join(Member member) throws IllegalAccessException {
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) throws IllegalAccessException {
        //Exception
        //멀티 쓰레드 일경우 동시 접속 가능 -> name을 DB에 Unique Index로 잡아줌

        List<Member> findMembers=memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
//    @Transactional(readOnly = true) //JPA 조회하는 곳에서 성능 최적화
    public List<Member> findMembers(){
        return memberRepository.findALl();
    }

//    @Transactional(readOnly = true) //JPA 조회하는 곳에서 성능 최적화
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
