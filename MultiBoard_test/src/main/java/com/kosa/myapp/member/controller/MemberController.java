package com.kosa.myapp.member.controller;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosa.myapp.member.model.Member;
import com.kosa.myapp.member.service.IMemberService;

@Controller
public class MemberController {
	
	static final Logger logger = Logger.getLogger(MemberController.class);
	
	@Autowired
	IMemberService memberService;
	
	//회원가입창으로 가즈아
	@RequestMapping(value="/member/insert", method=RequestMethod.GET)
	public String joinForm() {
		return "member/form";
	}
	
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
	public String memberInsert(Member member, HttpSession session) {
		memberService.insertMember(member);
		session.invalidate();
		return "home";
	}
	

}
