package com.webProject.mvc01;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cmtRSvc")
public class CommentRSvcImpl implements ICommentRSvc {

	@Autowired
	private ICommentRDao cmtRDao;
	
	@Override
	public ArrayList<HashMap<String, Object>> cmtSltAll(CommentDto cmtDto) {
		
		ArrayList<HashMap<String, Object>> hmlist = new ArrayList<HashMap<String, Object>>();
		
		//게시물의 댓글 불러오기
		ArrayList<CommentDto> cmtList = cmtRDao.sltCmtAll(cmtDto);
		
		if( cmtList.size() > 0 )
		{
			for(int i=0; i<cmtList.size(); i++)
			{
				HashMap<String, Object> hm = new HashMap<>();
				hm.put("BODNO", String.valueOf(cmtList.get(i).getBod_no()));
				hm.put("CMTNO", String.valueOf(cmtList.get(i).getCmt_no()));
				hm.put("CMTLVL", cmtList.get(i).getCmt_lvl());
				hm.put("CMTTEXT", cmtList.get(i).getCmt_text());
				hm.put("CMTDATE", cmtList.get(i).getCmt_date());
				hm.put("PRTCMTNO", cmtList.get(i).getPrt_cmtno());
				hm.put("MEMNAME", cmtList.get(i).getMem_name());
				hm.put("MEMID", cmtList.get(i).getMem_id());
				
				
				hmlist.add(hm);
			}
		}
		
		return hmlist;
	}
	
}
