package controller.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsDAO;
import model.dto.GoodsDTO;

public class GoodsDeleteService {
	public void execute(HttpServletRequest request) {
		//정보가 삭제가 될때 파일도 같이 삭제가 되게 만들자.		
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		//파일을 삭제하기 위해 상품정보를 가지고 온다.
		GoodsDTO dto = dao.selectOne(goodsNum);
		String mainImage = dto.getGoodsMainStore();
		String images[] = dto.getGoodsImages().split("`");
		// 데이터가 삭제 되었는지 정수값으로 받아온다. 삭제가 되었으면 1이상 아니면 0
		int i = dao.goodsDelete(goodsNum);
		if(i > 0 ) { // i가 0보다 크다면 파일 삭제
			String realPath = request.getServletContext()
						   			 .getRealPath("goods/images");
			//대문 이미지 부터 삭제
			File file= new File(realPath + "/" + mainImage);
			if(file.exists()) file.delete();
			// 상세이미지 삭제
			if (images.length > 0 ) {
				for(String fileName : images) {
					file= new File(realPath + "/" + fileName);
					if(file.exists()) file.delete();
				}
			}
		}
	}
}
