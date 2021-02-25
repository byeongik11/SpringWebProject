package com.webProject.mvc01;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cmtCUDSvc")
public class CommentCUDSvcImpl implements ICommentCUDSvc {

	@Autowired
	private ICommentCUDDao cmtCUDDao;

	@Autowired
	private ICommentRDao   cmtRDao;
	
	@Autowired
	private IMemberDao 	   mbrDao;
	
	@Override
	@Transactional
	public String cmtInsert(CommentDto cmtDto) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd kk:mm");
		Calendar 		 cal = Calendar.getInstance();
		String		cmt_date = sdf.format(cal.getTime());
		
		//댓글 등록 회원 단건조회
		int mem_num = cmtDto.getMem_num();
		
		MbrDto mbrDto = mbrDao.mbrSltOne(mem_num);
		
		if( mbrDto == null )
		{
			return "error";
		}
		
		//댓글 등록 max번호 조회
		int cmt_no = cmtRDao.cmtNoMax();
		
		//댓글 번호(PK) 단건조회
		CommentDto cmtDtoDB = cmtRDao.sltCmtNo(cmt_no);
		
		if( cmtDtoDB != null )
		{
			return "error";
		}
		
		System.out.println("======================cmtDto : " + cmtDto.toString());
		
		//여기부터는 댓글, 대댓글 분리 필요
		int prt_cmtno = cmtDto.getPrt_cmtno();
		
		cmtDto.setCmt_no(cmt_no);
		
		cmtDto.setCmt_date(cmt_date);
		
		int ret = 0;
		
		if( prt_cmtno == 0 )
		{
			//댓글 등록
			cmtDto.setPrt_cmtno(cmt_no);
			cmtDto.setCmt_lvl(0);
		}
		else
		{
			//대댓글 등록
			int cmt_lvl = cmtRDao.cmtLvl(prt_cmtno);
			
			cmtDto.setPrt_cmtno(prt_cmtno);
			cmtDto.setCmt_lvl(cmt_lvl);
		}
		
		System.out.println("============insertCMT:"+cmtDto.toString());
		
		ret = cmtCUDDao.cmtInsert(cmtDto);
		
		if( ret < 0 )
		{
			return "error";
		}
		
		return "success";
	}

	@Override
	public String cmtUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cmtDelete() {
		// TODO Auto-generated method stub
		return null;
	}

}
