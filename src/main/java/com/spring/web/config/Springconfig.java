package com.spring.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.web.repository.MemberRepository;
import com.spring.web.repository.MemoryMemberRepsitory;
import com.spring.web.service.MemberService;

@Configuration
public class Springconfig {

	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepsitory();
	}
}
