package kr.or.basic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// MyBatis가 다 알아서 JDBCUtil class형식으로 구현해줌

// mapper 패키지를 설정해놓고 어노테이션을 생략할 수도 있다
// 명시적으로 표현해주는게 좋음
@Mapper
public interface TestMapper {

	// getBuyer();를 실행하면 지정된 select 쿼리를 실행하라는 뜻
	@Select("select buyer_name from buyer where buyer_id='P10101'")
	public String getBuyer();
	

}
