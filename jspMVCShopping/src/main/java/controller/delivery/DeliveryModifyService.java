package controller.delivery;

import javax.servlet.http.HttpServletRequest;

import model.dao.DeliveryDAO;

public class DeliveryModifyService {
	public void execute(HttpServletRequest request) {
		String purchaseNum = request.getParameter("purchaseNum");
		DeliveryDAO dao = new DeliveryDAO();
		dao.deliveryStateUpdate(purchaseNum);
	}
}
