package com.spring.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.spring.web.domain.Member;
import com.spring.web.repository.MemoryMemberRepsitory;

class MemberServiceTest {

	MemoryMemberRepsitory memberRepository;
	MemberService memberService;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepsitory();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("hello");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		
		Member member2 = new Member();
		member2.setName(member1.getName());
		
		//when 
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
//		try{
//			memberService.join(member2);
//			fail("예외가 발생해야 합니다.");
//		}catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		
		//then
	}
}
