package controller.delivery;

import javax.servlet.http.HttpServletRequest;

import model.dao.DeliveryDAO;
import model.dto.DeliveryDTO;

public class DeliveryDetailService {
	public void execute(HttpServletRequest request) {
		String purchaseNum = request.getParameter("purchaseNum");
		DeliveryDAO dao =  new DeliveryDAO();
		DeliveryDTO dto = dao.deliverySelectOne(purchaseNum);
		request.setAttribute("purchaseNum", purchaseNum);
		if(dto != null) {
			request.setAttribute("deliveryNum", dto.getDeliveryNum());;
		}
	}
}
