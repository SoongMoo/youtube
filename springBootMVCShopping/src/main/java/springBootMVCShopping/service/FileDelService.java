package springBootMVCShopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.FileCommand;

@Service
public class FileDelService {
	public String execute(FileCommand fileCommand, HttpSession session) {
		String num = ""; //저장할 파일인지 아니지를 정해줄 문자열 변수를 선언 했습니다.
		// 새로운 상품인지 아니지를 확인 하기 위한 변수
		Boolean newFile = true;
		// 파일을 무조건 지우면 안되므로 일단 임시로 삭제할 파일을 session에 저장합니다.
		// 그러기 위해서는 이미 삭제할 파일 정보를 가진 session이 있는지 확인 해야 합니다.
		// 파일이 여러개를 삭제할 수 있으므로 List에 저장하여 session에 만들었습니다.
		List<FileCommand> list = //파일 정보를 가지고 있는 session이 fileList입니다.
				(List<FileCommand>)session.getAttribute("fileList");
		// 첫 파일인 경우 session이 존재하지 않는 다는 것은 list가 없다.
		// session이 없으면 list부터 만든다.
		if(list == null) {
			list = new ArrayList<FileCommand>();
		}
		// session이 있다면 list가 있으므로 list에 어떻한 파일이 존재하는 지 확인
		for(int i = 0 ; i < list.size() ; i++) {
			//만약 리스트가 존재하고 파일정보가 있다면 취소를 해야할 것입니다.
			if(list.get(i).getStoreFile().equals(fileCommand.getStoreFile())) {
				list.remove(i);
				newFile = false;// 새 파일이 아니므로 리스트에 추가 하지 않게 false를 줍니다.
				num =  "0";
				break;
			}
		}
		// 삭제할 파일이 새로 선택된 경우 
		if(newFile) {
			list.add(fileCommand);
			num = "1";
		}
		// 그리고 변경된 내용을 session에 저장합니다.
		session.setAttribute("fileList", list);
		return num; //num은 ajax에 전달할 값입니다. 삭제취소가 된 파일은 0, 삭제하려고 하는 파일은 1값을 전달합니다.
	}
}











