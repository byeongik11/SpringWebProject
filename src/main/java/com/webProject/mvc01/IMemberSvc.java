package com.webProject.mvc01;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface IMemberSvc {

	//ID중복조회
	String mbrIdChk(String mem_id);		
	
	//회원PK 단건조회
	String mbrSltOne(Model model, int mem_num);		
	
	//회원가입
	String mbrInsert(Model model, MbrDto mbrDto);		
	
	//회원수정
	String mbrUpdate(Model model, int mem_num, MbrDto mbrDto);	
	
	//회원탈퇴
	String mbrDelete(Model model, int mem_num, MbrDto mbrDto);		
	
	//ID찾기
	String searchID(Model model, MbrDto mbrDto);	
	
	//PW찾기
	String searchPW(Model model, MbrDto mbrDto);	
	
	//로그인 실행
	String login(Model model, HttpSession session, MbrDto mbrDto);		
}
