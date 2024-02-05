package mongoBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mongoBoard.command.BoardCommand;
import mongoBoard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	//@RequestMapping(value="boardList", method = RequestMethod.GET)
	@GetMapping("boardList")
	public String list(Model model) {
		boardService.getAllBoards(model);
		return "boardList";
	}
	@GetMapping("boardWrite")
	public String write() {
		return "boardForm";
	}
	@PostMapping("boardWrite") // boardForm.jsp로 받은 값을  db에 저장
	public String regist(BoardCommand boardCommand) {
		boardService.saveBoard(boardCommand);
		return "redirect:boardList";
	}
	//boardDetail?num=65a0adf89bd65763471ca9e9
	@GetMapping("boardDetail")
	// String id = request.getParameter("num");
	public String detail(@RequestParam(value="num") String id, Model model) {
		boardService.getBoardById(id, model);
		return "boardInfo";
	}
	@GetMapping("boardUpdate")
	public String update(@RequestParam(value="num") String id, Model model) {
		boardService.getBoardById(id, model);
		return "boardModifyForm";
	}
	@RequestMapping(value="boardUpdate", method = RequestMethod.POST)
	public String modify(BoardCommand boardCommand) {
		boardService.saveBoard(boardCommand); // 저장과 수정 코드는 같다.
		return "redirect:boardDetail?num="+boardCommand.getId();
	}
	@GetMapping("boardDelete")
	public String del(@RequestParam(value="num") String id) {
		boardService.deleteById(id);
		return "redirect:boardList";
	}
}










