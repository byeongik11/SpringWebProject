package com.webProject.mvc01;

import java.lang.reflect.Field;

public class CmmntyDto {

//게시판 DTO	
	private int rnum; // 글순서 (ROW_NUM)
	private int bod_no; // 게시글번호
	private int ctgr_no; // 카테고리번호
	private int mem_num; // 회원번호(작성자)
	private String mem_name; // 회원이름(작성자)
	private String bod_title; // 제목
	private String bod_date; // 작성일
	private String bod_text; // 내용
	private int bod_cnt; // 조회수
	private int bod_delchk; // 삭제여부 (0:유지, 9:삭제)

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getBod_no() {
		return bod_no;
	}

	public void setBod_no(int bod_no) {
		this.bod_no = bod_no;
	}

	public int getCtgr_no() {
		return ctgr_no;
	}

	public void setCtgr_no(int ctgr_no) {
		this.ctgr_no = ctgr_no;
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

	public String getBod_title() {
		return bod_title;
	}

	public void setBod_title(String bod_title) {
		this.bod_title = bod_title;
	}

	public String getBod_date() {
		return bod_date;
	}

	public void setBod_date(String bod_date) {
		this.bod_date = bod_date;
	}

	public String getBod_text() {
		return bod_text;
	}

	public void setBod_text(String bod_text) {
		this.bod_text = bod_text;
	}

	public int getBod_cnt() {
		return bod_cnt;
	}

	public void setBod_cnt(int bod_cnt) {
		this.bod_cnt = bod_cnt;
	}

	public int getBod_delchk() {
		return bod_delchk;
	}

	public void setBod_delchk(int bod_delchk) {
		this.bod_delchk = bod_delchk;
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
