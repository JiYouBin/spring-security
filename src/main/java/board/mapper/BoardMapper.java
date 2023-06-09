package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardListForSample() throws Exception;
	
	List<BoardDto> selectBoardList() throws Exception;
	int insertBoard(BoardDto boardDto) throws Exception;
	void updateHitCount(int boardIdx) throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	int updateBoard(BoardDto boardDto) throws Exception;
	int deleteBoard(int boardIdx) throws Exception;
}
