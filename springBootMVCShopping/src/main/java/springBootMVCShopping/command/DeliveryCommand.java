package springBootMVCShopping.command;

import java.util.Date;

import lombok.Data;

@Data
public class DeliveryCommand {
	String purchaseNum;
	String deliveryNumber;
	Date deliveryDate;
	String deliveryState;
	String deliveryCompany;
}
