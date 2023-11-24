package controller.ipgo;

import javax.servlet.http.HttpServletRequest;

import model.dao.GoodsIpgoDAO;
import model.dto.GoodsIpgoDTO;

public class GoodsIpgoDetailService {
	public void execute(HttpServletRequest request) {
		String ipgoNum = request.getParameter("ipgoNum");
		String num = request.getParameter("num");
		
		GoodsIpgoDAO dao = new GoodsIpgoDAO();
		GoodsIpgoDTO dto = dao.selectIpgoGoods(ipgoNum, num);
		request.setAttribute("dto", dto);
	}
}
