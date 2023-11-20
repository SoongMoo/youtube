package springBootMVCShopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCShopping.domain.GoodsDTO;
import springBootMVCShopping.domain.GoodsDetailStockDTO;
import springBootMVCShopping.domain.StartEndPageDTO;

@Mapper
public interface GoodsMapper {
	//번호 자동부여의 코드는 같은데 매번 같은코드를 짜는 것도 하나의 일입니다.
	//코드가 같다면 공유해서 사용하도록 만드는 것도 좋은 방법일 것입니다..
	//나중에 할 상품입고번호 자동부여와 같이 사용하는 코드를 만들어보겠습니다.
	//원래 마이바티스는 인자를 하나만 사용해야 하는 데 간 혹 인자를 여러개를 사용해야 하는 경우가 있을 때 @Parma을 사용
	public String ipgoAndGoodsAutoNum(@Param("tableName") String tableName
			   						,@Param("columnName") String columnName
			   						,@Param("sep") String sep
			   						,@Param("seq") Integer seq);
	public int goodsInsert(GoodsDTO dto);
	public List<GoodsDTO> allSelect(StartEndPageDTO sepDTO);
	public int goodsCount(String searchWord);
	//배열로 전달 된값을 마이바티스에서 사용하려면 @Param을 사용합니다
	public int productsDelete(@Param("products") String products[]);
	public GoodsDTO selectOne(String goodsNum);
	public int goodsUpdate(GoodsDTO dto);
	public int goodsDelete(String goodsNum);
	public GoodsDetailStockDTO goodsDetailStock(String goodsNum);
	public int visitCount(String goodsNum);
	public List<GoodsDTO> wishGoodsList(String memberNum);
}
