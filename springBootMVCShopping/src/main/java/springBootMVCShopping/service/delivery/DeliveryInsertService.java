package springBootMVCShopping.service.delivery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCShopping.command.DeliveryCommand;
import springBootMVCShopping.domain.DeliveryDTO;
import springBootMVCShopping.repository.DeliveryRepository;

@Service
public class DeliveryInsertService {
	// 이번에 interface를 사용하지 않고  class를 사용하겠습니다.
	@Autowired
	DeliveryRepository deliveryRepository;
	public void execute(DeliveryCommand deliveryCommand) {
		DeliveryDTO deliveryDTO= new DeliveryDTO();
		// command에 있는 값을 dto에 복사해준다./
		BeanUtils.copyProperties(deliveryCommand, deliveryDTO);
		deliveryRepository.delveryInsert(deliveryDTO);
	}
}
