package login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import login.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public Integer idCheckSelectOne(String userId);
	public Integer emailCheckSelectOne(String userEmail);
	public AuthInfoDTO loginSelectOne(String userId);
}








