package com.kosa.myapp.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.myapp.member.dao.IMemberRepository;
import com.kosa.myapp.member.model.Member;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	IMemberRepository memberRepository;

	@Override
	public void insertMember(Member member) {
		
		memberRepository.insertMember(member);

	}

}
