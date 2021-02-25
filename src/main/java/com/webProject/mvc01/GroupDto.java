package com.webProject.mvc01;

public class GroupDto {
//그룹정보 DTO
	
	private int bod_no;			//게시글번호
	private int mem_num;		//회원번호(참가자)
	
	public int getBod_no(){
	
		return bod_no;
	}
	public void setBod_no(int bod_no){
	
		this.bod_no = bod_no;
	}
	public int getMem_num(){
	
		return mem_num;
	}
	public void setMem_num(int mem_num){
	
		this.mem_num = mem_num;
	}
	
	
	
}
