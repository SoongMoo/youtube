package controller.goods;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsDAO;
import model.dto.GoodsDTO;

public class GoodsListService {
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		GoodsDAO dao = new GoodsDAO();
		List<GoodsDTO> list = dao.allSelect();	
		request.setAttribute("dtos", list);
	}
}
