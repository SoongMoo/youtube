package springBootMVCShopping.service.goods;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.FileCommand;
import springBootMVCShopping.command.GoodsCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.GoodsDTO;
import springBootMVCShopping.mapper.EmployeeMapper;
import springBootMVCShopping.mapper.GoodsMapper;

@Service
public class GoodsUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(GoodsCommand goodsCommand, HttpSession session, BindingResult result, Model model) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setDeliveryCost(goodsCommand.getDeliveryCost());
		// 수정한 직원의 정보를 알기 위해 로그인 정보를 이용해서 직원정보를 가지고왔습니다.
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empId = auth.getUserId();
		String empNum = employeeMapper.getEmpNum(empId);
		dto.setUpdateEmpNum(empNum);

		// 기존 수정 부분에 파일을 추가하거나 삭제할 코드를 추가하면 됩니다.
		// 먼저 파일 삭제 정보를 가지고 있는 session을 가지고 올겠습니다.
		List<FileCommand> list = (List<FileCommand>) session.getAttribute("fileList");
		// 파일정보를 가져오기 위해 상품정보를 가지고 오겠습니다.
		GoodsDTO goodsDTO = goodsMapper.selectOne(goodsCommand.getGoodsNum());
		/// 삭제할 파일과 upload할 파일의 디렉터리 정보를 가져오겠습니다/
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile(); // springBootMVCShopping/bin/main
		System.out.println(fileDir); // 경로가 어디인지 콘솔에 출력해 봅니다.
		// 파일 정보를 가지고 올 변수를 지정하겠습니다.
		MultipartFile mf;
		String originalFile;
		String extension;
		String storeName;
		String storeFileName;
		File file;
		// 대문이미지를 삭제할 거라 새로운 이미지가 있는지 확인 해보겠습니다/
		System.out.println(goodsCommand.getGoodsMainStore());
		if (!goodsCommand.getGoodsMainStore().getOriginalFilename().isEmpty()) {
			/// 대문이미지정보를 가지고 옵니다.
			mf = goodsCommand.getGoodsMainStore();// command에 있는 대문이미지를 불러옵니다/.
			originalFile = mf.getOriginalFilename();// 원본 파일명을 가져오겠습니다/ abc.hwp
			extension = originalFile.substring(originalFile.lastIndexOf(".")); // 파일명에서 확장자만 추출 .hwp
			storeName = UUID.randomUUID().toString().replace("-", "");// 이름 중복을 막기위해 새로운 이름을 만들어줍니다.
			storeFileName = storeName + extension;// 새로운 이름에 확장자를 붙여 파일명을 만들어 주겠습니다.
			// 디비에 수정하기 위해 dto에 저장합니다.
			dto.setGoodsMainStore(storeFileName); // storeFile
			dto.setGoodsMainStoreImg(originalFile);
			// fileDir경로에 파일을 저장 합니다.
			file = new File(fileDir + "/" + storeFileName);
			try {
				mf.transferTo(file);// 새 대문 이미지 파일을 저장합니다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (list != null) { // 삭제할 대문이미지가 있는대 새로운 이미지가 없는 경우 오류 메시지를 전달합니다.
				for (FileCommand fileCommand : list) {
					if (fileCommand.getStoreFile().equals(goodsDTO.getGoodsMainStore())) {
						result.rejectValue("goodsMainStore", "error"); // 오류가 발생했는지 확인
						model.addAttribute("error", "대문이미지를 선택해주세요."); // 오류메시지 전달
						model.addAttribute("goodsCommand", goodsDTO);
						session.removeAttribute("fileList");// 페이지로 이동할 때 session을 삭제합니다.
						return;
					}
				}
			}
		}
		// 이제 상품상세이미지를 주정하겠습니다. // 위코드와 다른 것은 없습니다. // 여러개의 파일일 수 있어 반복문을 사용합니다./
		List<String> goodsImages = new ArrayList<String>();
		List<String> goodsOrgImages = new ArrayList<String>();
		if (goodsDTO.getGoodsImages() != null) { // 디비로 부터 가져온 파일정보를 리스트에 저장합니다.
			String[] images = goodsDTO.getGoodsImages().split("-");
			String[] orgImages = goodsDTO.getGoodsImagesImg().split("-");
			for (String img : images) {
				goodsImages.add(img);
			}
			for (String img : orgImages) {
				goodsOrgImages.add(img);
			}
			// session에 있는 값을 list에서 삭제
			if (list != null) {
				for (FileCommand fileCommand : list) {
					for (String str : goodsImages) {
						if (fileCommand.getStoreFile().equals(str)) { // 삭제할 파일 정보를 디비 정보에서 지우는 작업을 합니다.
							goodsImages.remove(fileCommand.getStoreFile());
							goodsOrgImages.remove(fileCommand.getOrgFile());
							break;
						}
					}
				}
			}
		}
		///// 설명이미지추가
		/// 디비에 가져온 수정된 파일 정보를 추가해 줍니다.
		String originalTotal = "";
		String storeTotal = "";
		if (!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()) {
			for (MultipartFile mtf : goodsCommand.getGoodsImages()) {
				// 여기 부터는 위코드와 같습니다.
				originalFile = mtf.getOriginalFilename();
				extension = originalFile.substring(originalFile.lastIndexOf("."));
				storeName = UUID.randomUUID().toString().replace("-", "");
				storeFileName = storeName + extension;
				file = new File(fileDir + "/" + storeFileName);
				try {
					mtf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 여러개의 파일명을 하나로 묶는 작업을 하겠습니다.
				originalTotal += originalFile + "-";
				storeTotal += storeFileName + "-";				
			}
		}
		// 수정된 디비정보에 추가힙니다.
		for (String img : goodsImages) {
			storeTotal += img + "-";
		}
		for (String img : goodsOrgImages) {
			originalTotal += img + "-";
		}
		dto.setGoodsImages(storeTotal);
		dto.setGoodsImagesImg(originalTotal);
		int i = goodsMapper.goodsUpdate(dto);
		if(i >0) { // 디비에 내용이 수정되었다면 파일을 삭제 합니다.
			if (list != null) {
				for (FileCommand fileCommand : list) {
					file = new File(fileDir + "/" + fileCommand.getStoreFile());
					if (file.exists())
						file.delete();
				}
			}
		}
		// session이 필요 없으므로 삭제합니다/.
		session.removeAttribute("fileList");
	}
}













