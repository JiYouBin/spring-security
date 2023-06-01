package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.BoardDto;
import board.mapper.BoardMapper;

@Service
// @Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public int insertBoard(BoardDto boardDto) throws Exception {
		return boardMapper.insertBoard(boardDto);		
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateHitCount(boardIdx);				// 조회수를 증가
		// int i = 10 / 0;									// 오류가 발생하는 코드
		return boardMapper.selectBoardDetail(boardIdx);		// 게시판 상세 내용을 조회
	}

	@Override
	public int updateBoard(BoardDto boardDto) throws Exception {
		return boardMapper.updateBoard(boardDto);		
	}

	@Override
	public int deleteBoard(int boardIdx) throws Exception {
		return boardMapper.deleteBoard(boardIdx);
	}

	@Override
	public List<BoardDto> selectBoardListForSample() throws Exception {
		return boardMapper.selectBoardListForSample();
	}
}
