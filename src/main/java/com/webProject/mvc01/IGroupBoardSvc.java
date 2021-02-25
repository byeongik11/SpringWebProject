package com.webProject.mvc01;

import org.springframework.ui.Model;

public interface IGroupBoardSvc {

	String insertGroup(Model model, GroupDto dtoGroup);	//동행자 참가 신청
	
	String deleteGroup(Model model, GroupDto dtoGroup);	//동행자 참가 취소
	
}
