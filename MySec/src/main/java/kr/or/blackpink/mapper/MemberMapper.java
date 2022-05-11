package kr.or.blackpink.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.or.blackpink.domain.MemberVO;

@Mapper
public interface MemberMapper {
	

	public MemberVO read(String userid);

	
	
}
