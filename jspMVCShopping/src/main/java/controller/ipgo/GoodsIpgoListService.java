package controller.ipgo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsIpgoDAO;
import model.dto.GoodsIpgoDTO;

public class GoodsIpgoListService {
	public void execute(HttpServletRequest request) {
		GoodsIpgoDAO dao = new GoodsIpgoDAO();
		List<GoodsIpgoDTO> list =  dao.selectAll();
		request.setAttribute("dtos", list);
	}
}
