package com.webProject.mvc01;

import java.util.HashMap;

import org.springframework.ui.Model;

public interface IBoardSvc {

	String boardInsert(Model model, HashMap<String, Object> dataHm);		//글등록
	
	String boardGroupInsert(Model model, HashMap<String, Object> dataHm);	//그룹 글등록
	
	String getUpdate(Model model, int textNo);
	
	String boardUpdate(Model model,int gubun, BoardInfoDto dtoBoard);		//글수정
	
	String boardGroupUpdate(Model model, int gubun, BoardInfoDto dtoBoard);	//그룹 글수정
}
