package springBootMVCShopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCShopping.domain.GoodsDTO;

@Mapper
public interface CartMapper {
	public List<GoodsDTO> goodsSelectAll();
}
