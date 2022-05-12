package kr.or.blackpink.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TX2Mapper {
	// 짧은 예제에 일일히 xml파일 만들기 번거로우므로 어노테이션으로 작성함
	
	@Insert("insert into tx2 values (#{col01})")
	public int insTx2(String col01);
}
