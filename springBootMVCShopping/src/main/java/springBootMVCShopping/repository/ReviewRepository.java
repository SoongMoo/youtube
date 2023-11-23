package springBootMVCShopping.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springBootMVCShopping.domain.ReviewDTO;

@Repository
public class ReviewRepository {
	@Autowired
	SqlSession sqlSession;
	String namespace="reviewMapper";
	String statement;
	public Integer reviewWrite(ReviewDTO dto) {
		statement = namespace + ".reviewWrite";
		return sqlSession.insert(statement, dto);
	}
	public ReviewDTO reviewSelect(Integer reviewNum) {
		statement = namespace + ".reviewSelect";
		return sqlSession.selectOne(statement, reviewNum); // dto에 담아서 가져올 때는 selectOne으로 받아옵니다. 
	}
	public Integer reviewUpdate(ReviewDTO dto) {
		statement = namespace + ".reviewUpdate";
		return sqlSession.update(statement, dto) ;
	}
	public Integer reviewDelete(Integer reviewNum) {
		statement = namespace + ".reviewDelete";
		return sqlSession.delete(statement, reviewNum) ;
	}
	public List<ReviewDTO> goodsReviewList(String goodsNum) {
		statement = namespace + ".goodsReviewList";
		return sqlSession.selectList(statement, goodsNum) ;
	}     ///    list로 받아올 때는 selectList함수를 사용합니다.
}
