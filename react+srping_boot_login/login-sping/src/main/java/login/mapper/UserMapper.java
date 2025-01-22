package login.mapper;

import org.apache.ibatis.annotations.Mapper;

import login.domain.MemberDTO;

@Mapper
public interface UserMapper {
	public Integer userInsert(MemberDTO dto);
}
