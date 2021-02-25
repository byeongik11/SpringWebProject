package com.webProject.mvc01;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service("cmmntyCUDSvc")
public class CmmntyCUDSvcImpl implements ICmmntyCUDSvc {

	@Autowired
	private ICmmntyCUDDao cmmntyCUDDao;
	
	@Autowired
	private ICmmntyRDao cmmntyDao;
	
	@Override
	@Transactional
	public String cmmntyInsert(Model model, CmmntyDto cmmntyDto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		Calendar 		 cal = Calendar.getInstance();
		String		bod_date = sdf.format(cal.getTime());	//작성일
		
		int bod_noMax = cmmntyCUDDao.bodNoMax();			//게시글번호
		int bod_no	  = bod_noMax + 1; 

		cmmntyDto.setBod_no(bod_no);
		cmmntyDto.setBod_date(bod_date);
		
		CmmntyDto cmmntyDtoDB = cmmntyDao.sltPost(bod_no);
		
		if( cmmntyDtoDB != null )
		{
			return "error";
		}
		
		int ret = cmmntyCUDDao.cmmntyInsert(cmmntyDto);
		
		if( ret < 0 )
		{
			return "error";
		}
		
		model.addAttribute("CMMNTYDTO", cmmntyDto);

		return "cmmntyRead";
	}

	@Override
	public String getUpdate(Model model, CmmntyDto cmmntyDto) {

		int bod_no = cmmntyDto.getBod_no();
		
		CmmntyDto cmmntyDtoDB = cmmntyDao.sltPost(bod_no);
		
		if( cmmntyDtoDB == null )
		{
			return "error";
		}
		
		System.out.println(cmmntyDto.toString());

		model.addAttribute("CMMNTYDTO", cmmntyDtoDB);
		
		return "communityUpdate";
	}

	@Override
	@Transactional
	public String cmmntyUpdate(Model model, CmmntyDto cmmntyDto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar 		 cal = Calendar.getInstance();
		
		int bod_no = cmmntyDto.getBod_no();
		
		CmmntyDto cmmntyDtoDB = cmmntyDao.sltPost(bod_no);
		
		if( cmmntyDtoDB == null )
		{
			return "error";
		}
		
		int ctgr_no 	= cmmntyDtoDB.getCtgr_no();		//카테고리 구분
		int mem_num  	= cmmntyDtoDB.getMem_num();		//회원 번호
		String mem_name = cmmntyDtoDB.getMem_name();	//회원 이름
		String bod_date = sdf.format(cal.getTime());	//수정일
		int bod_cnt 	= cmmntyDtoDB.getBod_cnt();		//조회수
		int bod_delchk  = cmmntyDtoDB.getBod_delchk();	//삭제여부 (0:유지, 9:삭제)
		
		cmmntyDto.setCtgr_no(ctgr_no);
		cmmntyDto.setMem_num(mem_num);
		cmmntyDto.setMem_name(mem_name);
		cmmntyDto.setBod_date(bod_date);
		cmmntyDto.setBod_cnt(bod_cnt);
		cmmntyDto.setBod_delchk(bod_delchk);
		
		System.out.println(cmmntyDto.toString());
		
		int ret = cmmntyCUDDao.cmmntyUpdate(cmmntyDto);
		
		if( ret < 0 )
		{
			return "error";
		}
		
		model.addAttribute("CMMNTYDTO", cmmntyDto);
		
		return "cmmntyRead";
	}

	@Override
	@Transactional
	public String cmmntyDelete(Model model, CmmntyDto cmmntyDto) {
		
		int bod_no = cmmntyDto.getBod_no();
		
		CmmntyDto cmmntyDtoDB = cmmntyDao.sltPost(bod_no);
		
		if( cmmntyDtoDB == null )
		{
			return "error";
		}
		
		int bod_delchk = 9;
		
		cmmntyDtoDB.setBod_delchk(bod_delchk);
		
		int ret = cmmntyCUDDao.cmmntyDelete(cmmntyDtoDB);
		
		if( ret < 0 )
		{
			return "error";
		}
		
		return "redirect:communityBoard.do";
	}
	
	
	

}
