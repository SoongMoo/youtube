package controller.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsDAO;
import model.dto.GoodsDTO;

public class GoodsDeleteService {
	public void execute(HttpServletRequest request) {
		String goodsNum = request.getParameter("goodsNum");
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = dao.goodsSelectOne(goodsNum);
		int i = dao.goodsDelete(goodsNum);
		if(i > 0) {
			String realPath 
			// webapp다음 부터의 경로를 가지고 온다.
				= request.getServletContext().getRealPath("/goods/images");
			String mainImage = dto.getGoodsMainStore();
			File file = new File(realPath + "/" + mainImage);
			if(file.exists()) file.delete();
			String images[] = dto.getGoodsImages().split("`");
			for(String fileName : images) {
				file = new File(realPath + "/" + fileName);
				if(file.exists()) file.delete();
			}
		}	
	}
 }
