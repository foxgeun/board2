package com.example.board2.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	
	//myUtil
	public int getPageCount(int numPerPage, int dataCount) {
		//numPerpage: 5, dataCount: 7
		
		
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}
		return pageCount;
	}
	
	public String pageIndexList(int currentpage, int totalPage, String listUrl) {
		StringBuffer sb = new StringBuffer(); //메모리 낭비의 방지
		
		int numPerBlock = 5; //<이전 6 7 8 9 10 다음>
		int currentPageSetup; // <이전 버튼에 들어갈 값
		int page; //그냥 페이지 숫자를 클릭했을떄 들어갈 값
		
		if(currentpage == 0 || totalPage == 0) return "";
		
		
		//검색어가 있을떄: /list?searchKey=name&searchValue=춘식
		if(listUrl.indexOf("?") != -1) {
			//'?'가 들어있다면(쿼리스트링이 있다면)
			listUrl += "&";
		}else {
			//쿼리스트링이 없다면
			listUrl += "?";
		}
		
		
		//1. <이전 버튼 만들기
		
		//currentpage가 (1~4), (5~9), (10~14), (15~19)...일때 5, 10, 15 
		currentPageSetup = (currentpage / numPerBlock) * numPerBlock;
		
		//currentPage가 5 10 15 20일때 currentPageSetup 5, 10, 15
		if(currentpage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
			
		}
		
		if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href=\"" + listUrl + "pageNum=" 
		+ currentPageSetup + "\">◀이전</a>&nbsp;");
		}
		
		
		//2. 그냥 페이지(6 7 8 9 10) 이동 버튼 만들기
		
		page = currentPageSetup + 1; //1, 6, 11, 16
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			
			if(page == currentpage) {
				//헌재 내가 선택한 페이지라면
				sb.append("<font color=\"red\">" + page + "</font>&nbsp;");
				
			}else {
				//헌재 내가 선택한 페이지가 아니라면
				sb.append("<a href=\"" +listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				
			}
			
			page++;
			
			
		}
		
		
		
		
		//3. 다음> 버튼 만들기
		
		//4. 버튼 합쳐서 문자열로 리턴
		
		return sb.toString();
	}
	
	
	
}
