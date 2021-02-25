package com.webProject.mvc01;

import org.springframework.ui.Model;

public interface IGroupSvc {

	String insertJoin(Model model, GroupDto dtoGroup);	//동행자 참가 신청
	
	String deleteJoin(Model model, GroupDto dtoGroup);	//동행자 참가 취소
	
}
