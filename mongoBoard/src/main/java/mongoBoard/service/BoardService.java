package mongoBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import mongoBoard.BoardRepository;
import mongoBoard.command.BoardCommand;
import mongoBoard.domain.BoardDTO;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;
	public void saveBoard(BoardCommand boardCommand) {
		BoardDTO dto = new BoardDTO();
		//dto.setBoardContent(request.getParametrer("boardWrite"));
		/*
		dto.setBoardContent(boardCommand.getBoardContent());
		dto.setBoardSubject(boardCommand.getBoardSubject());
		dto.setBoardWriter(boardCommand.getBoardWriter());
		*/
		BeanUtils.copyProperties(boardCommand, dto);
		// command는 html로 부터 값을 받아오기 위해 사용 // request역할과 같다.
		// dto는 db에 값을 전달하거나 db로부터 값을 받아오기 위해 사용
		boardRepository.save(dto);
	}
	public void getAllBoards(Model model) {
		// jsp페이지에 값을 전달하는 방법은 spring에서는 model을 사용한다.
		List<BoardDTO> list = boardRepository.findAll();
		model.addAttribute("lists", list);
		// request.setActtribute("lists", list);
	}
	public void getBoardById(String id, Model model) {
		Optional<BoardDTO> optionalBoard = boardRepository.findById(id);
        optionalBoard.ifPresent(board -> model.addAttribute("dto", board));
	}
	public void deleteById(String id) {
		boardRepository.deleteById(id);
	}
}




