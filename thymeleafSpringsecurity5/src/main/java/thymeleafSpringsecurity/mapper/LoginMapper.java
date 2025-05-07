package thymeleafSpringsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;

import thymeleafSpringsecurity.domain.AuthInfo;


@Mapper
public interface LoginMapper {
	public AuthInfo loginSelectOne(String userId);
}
