package com.webProject.mvc01;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupRSvc")
public class GroupRSvcImpl implements IGroupRSvc {

	@Autowired
	IGroupDao groupDao;
	
	@Autowired
	IGroupRDao groupRDao;

	//로그인된 유저가 글보기할때 그룹 참가 여부 조회
	@Override
	public boolean sltJoinGroup(GroupDto groupDto) {
		boolean join = false;
		
		GroupDto gDto = groupDao.sltOneGroup(groupDto);
		
		if(gDto != null) {		//조회 결과가 있을 경우
			join = true;
		}
		
		return join;
	}
	
	// 글보기 요청한 그룹의 현재 신청한 인원수 조회
	@Override
	public int sltJoinTotal(int bod_no) {
		
		ArrayList<GroupDto> list = groupRDao.sltJoinTotal(bod_no);
		
		int cnt = list.size();
		
		return cnt;
	}
	

}
