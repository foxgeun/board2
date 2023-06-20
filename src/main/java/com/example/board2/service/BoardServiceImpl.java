package com.example.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board2.dao.BoardDao;
import com.example.board2.dto.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardmapper;
	
	@Override
	public int maxNum() throws Exception {
		// TODO Auto-generated method stub
		return boardmapper.maxNum();
	}

	@Override
	public void insertData(Board board) throws Exception {
		boardmapper.insertData(board);
		
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return boardmapper.getDataCount(searchKey, searchValue);
		
	}

	@Override
	public List<Board> getLists(String searchKey, String searchValue, int start, int end) throws Exception {
		return boardmapper.getLists(searchKey, searchValue, start, end);
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		boardmapper.updateHitCount(num);
		
	}

	@Override
	public Board getReadData(int num) throws Exception {
		return boardmapper.getReadData(num);
	}

	@Override
	public void updateData(Board board) throws Exception {
		boardmapper.updateData(board);
	}

	@Override
	public void deleteData(int num) throws Exception {
		boardmapper.deleteData(num);
	}
	
}
