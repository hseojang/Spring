package kr.or.blackpink.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.blackpink.domain.OtpVO;

@Mapper
public interface OtpMapper {
	public int insertKey(OtpVO vo);
	public String selectKey(String userId);
}
