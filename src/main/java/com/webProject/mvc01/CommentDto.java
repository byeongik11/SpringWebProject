package com.webProject.mvc01;

import java.lang.reflect.Field;

public class CommentDto {
//댓글 DTO
	private int    bod_no; 		// 게시글 번호
	private int    cmt_no;		// 댓글 번호
	private int    mem_num;		// 회원 번호
	private int    prt_cmtno;	// 부모 댓글 번호
	private int    cmt_lvl;		// 댓글 레벨
	private String cmt_text;	// 댓글 내용
	private String cmt_date;	// 댓글 날짜
	private String mem_name;	// 회원 이름
	private String mem_id;		// 회원 아이디
	
	public int getBod_no() {
		return bod_no;
	}
	public void setBod_no(int bod_no) {
		this.bod_no = bod_no;
	}
	public int getCmt_no() {
		return cmt_no;
	}
	public void setCmt_no(int cmt_no) {
		this.cmt_no = cmt_no;
	}
	public int getCmt_lvl() {
		return cmt_lvl;
	}
	public void setCmt_lvl(int cmt_lvl) {
		this.cmt_lvl = cmt_lvl;
	}
	public String getCmt_text() {
		return cmt_text;
	}
	public void setCmt_text(String cmt_text) {
		this.cmt_text = cmt_text;
	}
	public String getCmt_date() {
		return cmt_date;
	}
	public void setCmt_date(String cmt_date) {
		this.cmt_date = cmt_date;
	}
	public int getPrt_cmtno() {
		return prt_cmtno;
	}
	public void setPrt_cmtno(int prt_cmtno) {
		this.prt_cmtno = prt_cmtno;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public String toString() {

		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		result.append(this.getClass().getName());
		result.append("{");
		result.append(newLine); 
		
		Field[] fields = this.getClass().getDeclaredFields();
		
		for (Field field : fields) { 
			result.append(" "); 
			try {
				result.append(field.getName());
				result.append(": ");
				result.append(field.get(this)); 
			} catch (IllegalAccessException ex) {
				System.out.println(ex); 
			} 
			result.append(newLine); 
		}
		result.append("}");
		
		return result.toString();

	}
}
