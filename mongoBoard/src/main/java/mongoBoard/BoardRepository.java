package mongoBoard;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongoBoard.domain.BoardDTO;

public interface BoardRepository extends MongoRepository<BoardDTO, String>{
	
}
