package springBootMVCShopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.domain.StartEndPageDTO;

@Service
public class StartEndPageService {
	int limit;

	public StartEndPageDTO execute(int page, String searchWord) {
		// 회원수가 심만명인라면 한페이지에 볼 수 없습니다. 이런경우 한페이지에 10명씩 보여주면 좋은데 이것을 페이징이라 함
		limit = 10; // 한페이지에 몇개를 보여줄것인지?
		// 만약 1페이지라면 1번 부터 10번까지 , 2페이지라면 11번부터 20번까지 가져와야 할 것입니다.
		// 시작 번호와 끝번호를 알기 위해서는 페이지 번호가 필요하다.
		int startRow = ((page - 1) * limit) + 1; // page를 이용해서 시작번호를 가지고 옵니다.
		int endRow = startRow + limit - 1; // 시작번호를 이용해서 마지막 번호를 가지고 옵니다.
		// startRow와 endRow 그리고 searchWord를 마이바티스에 넘기기 위해 DTO를 만듭니다.
		StartEndPageDTO sepDTO = new StartEndPageDTO();
		sepDTO.setEndRow(endRow);
		sepDTO.setSearchWord(searchWord);
		sepDTO.setStartRow(startRow);
		return sepDTO;
	}

	public void execute(int page, int count, Model model, List list, String searchWord) {

		// 페이지에 보여줄 페이지 번호의 갯수를 정해준다.
		int limitPage = 10;
		// 시작 페이지 번호와 마지막 페이지 번호를 가지고 온다.
		// 독자 여러분들이 코드를 다르게 짜서 해보는 것도 좋은 방법입니다.
		int startPage = (int) ((double) page / limitPage + 0.95 - 1) * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		// 전체 페이지 갯수를 구한다.
		int maxPage = (int) ((double) count / limit + 0.95);
		// endPage가 maxPage 페이지 보다 크지 않게 만들어 준다.
		if (maxPage < endPage)
			endPage = maxPage;
		// 리스트 페이지로 넘겨준다.
		model.addAttribute("dtos", list);
		// 검색할 때 사용한 단어를 html에 출력되게 한다.
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("maxPage", maxPage);
	}
}
