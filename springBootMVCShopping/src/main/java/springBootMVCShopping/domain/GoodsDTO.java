package springBootMVCShopping.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("goods")
@NoArgsConstructor //기본 생성자를 만듭니다.
@AllArgsConstructor //모든 멤버 필드 생성자.
public class GoodsDTO {
	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	Integer deliveryCost;
	String goodsContent;
	String empNum;
	//먼저 설명놓친 부분을 말씀드리겠습니다. 
	/* 마이바티스에서 생성자를 사용하는 경우 데이터 타입을 int로 사용하면 타입오류가 발생합니다.
	 * 타입 오류를 발생 하지 않게 하려면  Integer를 사용하셔야합니다. type오류는 int여서 생긴 것입니다.
	 */
	Integer visitCount;
	Date goodsRegist;
	String updateEmpNum;
	Date goodsUpdateDate;
	
	// 디비에 파일명을 저장하기 위해 주가합니다.
	String goodsMainStore;
	String goodsMainStoreImg;
	String goodsImages;
	String goodsImagesImg;

}
