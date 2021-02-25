package com.webProject.mvc01;

/**
 * 
 * Interface
 * 그룹 DAO
 */

public interface IGroupDao {

	GroupDto sltOneGroup(GroupDto groupDto);	// 참가 신청 여부 조회
	
	int insertJoin(GroupDto dtoGroup);			//동행자 참가 신청
	
	int deleteJoin(GroupDto dtoGroup);			//동행자 참가 취소

}