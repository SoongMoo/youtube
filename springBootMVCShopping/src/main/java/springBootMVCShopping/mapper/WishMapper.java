package springBootMVCShopping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WishMapper {
	public Integer wishGoodsSelect(String goodsNum, String getMemberNum);
	public int wishInsert(String goodsNum,String memberNum);
	public int wishDelete(String goodsNum,String memberNum);
	public int wishGoodsDeletes( @Param("goodsNums")String [] wishGoodsDels,
			@Param("memberNum") String memberNum);
}
