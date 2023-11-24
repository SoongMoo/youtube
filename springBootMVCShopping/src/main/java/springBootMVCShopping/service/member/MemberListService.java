package springBootMVCShopping.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.domain.StartEndPageDTO;
import springBootMVCShopping.mapper.MemberMapper;
import springBootMVCShopping.service.StartEndPageService;

@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	StartEndPageService startEndPageService;
	String searchWord;
	public void execute(Model model, String searchWord, int page) {
		// searchWord에 양옆에 공백문자가 올 수 있으므로 제거를 해준다.
		if(searchWord != null ) { // searchWord가 null이 아닌 경우에만 
			this.searchWord = searchWord.trim();
		}
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
 		List<MemberDTO> list = memberMapper.selectAll(sepDTO);
		// 전체 회원수를 가지고 옵니다.
		int count = memberMapper.memberCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
	}
}
