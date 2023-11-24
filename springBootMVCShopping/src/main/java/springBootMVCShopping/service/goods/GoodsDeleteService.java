package springBootMVCShopping.service.goods;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCShopping.domain.GoodsDTO;
import springBootMVCShopping.mapper.GoodsMapper;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum) {
		//파일 삭제코드를 추가합니다. // 일단 bin에만 파일을 놓겠습니다.
		GoodsDTO dto = goodsMapper.selectOne(goodsNum); // 먼저 삭제할 파일의 정보를 자져옵니다.
		int i = goodsMapper.goodsDelete(goodsNum);
		//디비가 삭제되면 파일이 삭제가 되게 삭제되었는 지 확인하기 위해 정수를 받아 옵니다.
		if(i > 0) {//bin에 있는 경로
			URL resource = getClass().getClassLoader().getResource("static/upload");
			String fileDir = resource.getFile(); 
			File file = new File(fileDir + "/" + dto.getGoodsMainStore());
			if(file.exists())file.delete();//메인이미지 삭제
			if(dto.getGoodsImages() != null) { // 설명 이미지들이 있는 파일명르 가지고와서 split을 한다.
				String [] img = dto.getGoodsImages().split("-");
				for(String fileName : img) { // 그리고 반복하여 삭제
					file = new File(fileDir + "/" +  fileName);
					if(file.exists())file.delete();
				}
			}
		}
	} //오늘 수업은 여기까지하고 다은 수업엔 파일UPLOAD한 파일을 변경하는 작업을 하겠습니다. 수고하셨습니다,.
}
