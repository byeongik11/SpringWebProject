package com.webProject.mvc01;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICommentRSvc {
	
	ArrayList<HashMap<String, Object>> cmtSltAll(CommentDto cmtDto);

}
