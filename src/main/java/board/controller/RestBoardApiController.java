package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import board.dto.BoardDto;
import board.service.BoardService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class RestBoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/api/board")
	public ResponseEntity<List<BoardDto>> openBoardList() throws Exception {
		List<BoardDto> list = boardService.selectBoardList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/api/board/write")
	public ResponseEntity<Map<String, Object>> insertBoard(
			@RequestBody BoardDto boardDto) throws Exception {
		int insertedCount = 0;
		try {
			insertedCount = boardService.insertBoard(boardDto);
			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
				result.put("count", insertedCount);
				result.put("boardIdx", boardDto.getBoardIdx());		
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			result.put("message", "등록 중 오류가 발생했습니다.");
			result.put("count", insertedCount);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	
	@GetMapping("/api/board/{boardIdx}")
	public ResponseEntity<BoardDto> openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
		if (boardDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(boardDto);
		}
	}
	
	@PutMapping("/api/board/{boardIdx}")
	public ResponseEntity<Integer> updateBoard(@PathVariable("boardIdx") int boardIdx, @RequestBody BoardDto boardDto) throws Exception {
		boardDto.setBoardIdx(boardIdx);
		int updatedCount = boardService.updateBoard(boardDto);
		if (updatedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updatedCount);
		}
	}
	
	@DeleteMapping("/api/board/{boardIdx}")
	public ResponseEntity<Integer> deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		int deletedCount = boardService.deleteBoard(boardIdx);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
	}
}
