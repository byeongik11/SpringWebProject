package com.webProject.mvc01;

public class PageInfoDto {
	
	private int gubun;
	private int pageCnt;          	// 출력할 페이지번호 갯수
    private int index;            	// 출력할 페이지번호
    private int pageStartNum;    	// 출력할 페이지 시작 번호
    private int listCnt;          	// 출력할 리스트 갯수
    private int total;            	// 리스트 총 갯수    
    
    private String titleText;			// 검색 제목 	문자열 저장
    private String writerText;			// 검색 작성자	문자열 저장
    private String areaText;			// 검색 작성자	문자열 저장
    
	{
        pageCnt = 5;
        index = 0;
        pageStartNum = 1;
        listCnt = 10;
    }
    
    public PageInfoDto() {}
    
    //실제 쿼리용 시작/끝 번호
    public int getStartData() {
    	return index*listCnt+1;
	}
    public int getEndData() {
    	return listCnt + index*listCnt;
	}
    
    //뷰에서 보여지는 용
	public int getStart() {
        return total - listCnt * index;
    }
    public int getLast() {
        return total;
    }
    public int getPageLastNum() {
        int remainListCnt = total-listCnt*(pageStartNum-1);
        int remainPageCnt = remainListCnt/listCnt;
        if(remainListCnt%listCnt != 0) {
            remainPageCnt++;
        }
        int pageLastNum = 0;
        if(remainListCnt <= listCnt) {
            pageLastNum = pageStartNum;
        }else if(remainPageCnt <= pageCnt) {
            pageLastNum = remainPageCnt+pageStartNum-1;
        }else {
            pageLastNum = pageCnt+pageStartNum-1;
        }
        return pageLastNum;
    }
    public boolean getLastChk() {
        int n = (int)Math.ceil((double)total/listCnt);
        return getPageLastNum()==n ? false : n==0 ? false : true;
    }
    
    public int getGubun() {
		return gubun;
	}
	public void setGubun(int gubun) {
		this.gubun = gubun;
	}
	
    public int getPageCnt() {
        return pageCnt;
    }
    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getPageStartNum() {
        return pageStartNum;
    }
    public void setPageStartNum(int pageStartNum) {
        this.pageStartNum = pageStartNum;
    }
    public int getListCnt() {
        return listCnt;
    }
    public void setListCnt(int listCnt) {
        this.listCnt = listCnt;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    
    //검색 문자열 getter/setter

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String getWriterText() {
		return writerText;
	}

	public void setWriterText(String writerText) {
		this.writerText = writerText;
	}
	
	public String getAreaText() {
		return areaText;
	}

	public void setAreaText(String areaText) {
		this.areaText = areaText;
	}

	@Override
    public String toString() {
        return "PagingDto [pageCnt=" + pageCnt + ", index=" + index + ", pageStartNum=" + pageStartNum + ", listCnt="
                + listCnt + ", total=" + total + "]";
    }
}
