package board.service;

import java.util.List;

import board.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> selectBoardListForSample() throws Exception;
	
	public List<BoardDto> selectBoardList() throws Exception;
	public int insertBoard(BoardDto boardDto) throws Exception;
	public BoardDto selectBoardDetail(int boardIdx) throws Exception;
	public int updateBoard(BoardDto boardDto) throws Exception;
	public int deleteBoard(int boardIdx) throws Exception;
}
