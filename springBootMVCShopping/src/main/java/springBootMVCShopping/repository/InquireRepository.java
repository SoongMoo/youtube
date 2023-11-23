package springBootMVCShopping.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springBootMVCShopping.domain.InquireDTO;

@Repository
public class InquireRepository {
	@Autowired
	SqlSession sqlSession;
	String namespace = "InquireMapper";
	String statement;
	public Integer inquireInsert(InquireDTO dto) {
		statement = namespace + ".inquireInsert";
		return sqlSession.insert(statement, dto);
	}
	public List<InquireDTO> inquireList(String goodsNum , Integer inquireNum){
		// @Repository에서는 두개를 넘겨줄수 없으므오 dto에 담아야 합니다.
		InquireDTO dto = new InquireDTO();
		dto.setGoodsNum(goodsNum);
		dto.setInquireNum(inquireNum);
		statement = namespace + ".inquireList";
		return sqlSession.selectList(statement,dto);
	}
	public Integer inquireAnswerUpdate(InquireDTO dto) {
		statement = namespace + ".inquireAnswerUpdate";
		return sqlSession.update(statement, dto);
	}
	public Integer inquireUpdate(InquireDTO dto) {
		statement = namespace + ".inquireUpdate";
		return sqlSession.update(statement, dto);
	}
	public Integer inquireDelete(Integer inquireNum) {
		statement = namespace + ".inquireDelete";
		return sqlSession.delete(statement, inquireNum);
	}
}
