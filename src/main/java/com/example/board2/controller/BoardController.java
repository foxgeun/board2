package com.example.board2.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board2.dto.Board;
import com.example.board2.service.BoardService;
import com.example.board2.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	MyUtil myUtil;
	
	
	
	@RequestMapping(value="/")
	public String index() {
		
		return "index";
	}
	
	//get 방식으로 request가 들어올떄
	//페이지 보여줌
	@RequestMapping(value="/created", method = RequestMethod.GET)
	public String created() {
		return "bbs/created";
	}
	
	//게시글 등록
	@RequestMapping(value="/created", method = RequestMethod.POST)
	public String createdOK(Board board, HttpServletRequest request, Model model) {
		
		
		
		
		try {
			int maxNum = boardService.maxNum();
			
			board.setNum(maxNum + 1); //num 컬럼에 들어갈 값을 1증가시켜준다
			board.setIpAddr(request.getRemoteAddr()); //클라이언트의 ip주소를 구해준다
			
			boardService.insertData(board);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/list";
	}
	
	
	//리스트 페이지 보여줌(GET, POST전부 여기로들어온다)
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Board board, HttpServletRequest request , Model model) {
		
		try {
			String pageNum = request.getParameter("pageNum"); //바뀌는 페이지번호
			
			int currentPage = 1;//현재페이지(디폴트 1)
			
			if(pageNum != null)  currentPage = Integer.parseInt(pageNum);
			
			String searchKey = request.getParameter("searchKey"); //검색키워드(subject, content, name)
			String searchValue = request.getParameter("searchValue"); //검색어
			
			
			if(searchValue == null) {
				searchKey = "subject"; //검색 키워드의 디폴트는 subject
				searchValue = ""; // 검색어의 디폴트는 빈문자열
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					//get방식으로 request가 왔다면 실행
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
					
					
				}
			}
			
			//1.전체 게시물의 갯수를 가져온다.(페이징 처리에 필요)
			int dataCount = boardService.getDataCount(searchKey, searchValue);
			
			
			
			
			//2. 페이징 처리를 한다.
			
			int numPerPage = 5; //페이지당 보여줄 데이터의 갯수
			int totalPage =  myUtil.getPageCount(numPerPage, dataCount);//페이지의 전체갯수를 구한다
			
			if(currentPage > totalPage) currentPage = totalPage;
				
			int start = (currentPage - 1) * numPerPage + 1; // 1 6 11 16
			int end = currentPage * numPerPage; // 5 10 15 20....
			
			//3.전체 게시물 리스트를 가져온다.
			
			List<Board> lists = boardService.getLists(searchKey, searchValue, start, end);
			
			
			
			
			//4.페이징 처리를 한다
			String param = "";
			
			if(searchValue != null && searchValue.equals("")) {
				
				param = "searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");//컴퓨터의 언어로 인코딩
				
			}
			String listUrl = "/list";
			//list?srcarchKey=name&searchValue=춘식
			if(!param.endsWith("")) listUrl += "?" + param;
				
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return "bbs/list";
	}
	
	
	//수정
	@RequestMapping(value="/updated", method = RequestMethod.GET)
	public String updated(HttpServletRequest request, Model model) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue != null) {
			
		}
		
		return "bbs/updated";
	}
	
	//수정페이지 보여줌
	@RequestMapping(value="/updated_ok", method = RequestMethod.POST)
	public String updatedOK() {
		return "bbs/updated";
	}
	
	
	
	
	
	@RequestMapping(value="/article", method = RequestMethod.GET)
	public String article() {
		return "bbs/article";
	}
	
	
	
	
	
	
	
	
}
