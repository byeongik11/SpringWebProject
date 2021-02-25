package com.webProject.mvc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("groupSvc")
public class GroupSvcImpl implements IGroupSvc {

	@Autowired
	IGroupDao groupDao;

	//참가 신청
	@ Override
	public String insertJoin(Model model, GroupDto groupDto){
		
		System.out.println(groupDto.getBod_no() + groupDto.getMem_num());
		
		int ret = groupDao.insertJoin(groupDto);
		
		if(ret > 0) {
			model.addAttribute("MSG", "신청 등록 완료");
		}
		else {
			model.addAttribute("MSG", "신청 등록 실패");
		}
		
		return "redirect:/groupContent.do";
	}

	//참가 취소
	@ Override
	public String deleteJoin(Model model, GroupDto groupDto){
		
		int ret = groupDao.deleteJoin(groupDto);
		
		if(ret > 0) {
			model.addAttribute("MSG", "신청 취소 완료");
		}
		else {
			model.addAttribute("MSG", "신청 취소 실패");
		}
		
		return "redirect:/groupContent.do";
	}
	
	
	

}
