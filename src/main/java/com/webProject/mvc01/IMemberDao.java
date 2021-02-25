package com.webProject.mvc01;


//회원관리 DAO
public interface IMemberDao {
	
	//ID로 단건 조회 
	MbrDto mbrIdChk(String mem_id);	
	
	//MEM_NUM으로 단건 조회 
	MbrDto mbrSltOne(int mem_num);	
	
	//회원코드 max값 
	int mbrNumMax();	
	
	//id찾기
	MbrDto findId(MbrDto mbrDto);
	
	//pw찾기
	MbrDto findPw(MbrDto mbrDto);		
			
	//회원가입
	int mbrInsert(MbrDto mbrDto);	
	
	//회원수정
	int mbrUpdate(MbrDto mbrDto);	
	
	//회원탈퇴
	int mbrDelete(MbrDto mbrDto);	
	
	//group 작성자 정보 전달
	MbrDto sltGroupWriter(int mem_num);
	
}
