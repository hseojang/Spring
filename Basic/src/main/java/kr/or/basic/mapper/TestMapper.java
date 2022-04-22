package kr.or.basic.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

// MyBatis가 다 알아서 JDBCUtil class형식으로 구현해줌

// mapper 패키지를 설정해놓고 어노테이션을 생략할 수도 있다
// 명시적으로 표현해주는게 좋음
@Mapper
public interface TestMapper {

	// getBuyer();를 실행하면 지정된 select 쿼리를 실행하라는 뜻
	@Select("select buyer_name from buyer where buyer_id='${buyer_id}'")
	public String getBuyer(String buyer_id);
	
	@Insert("insert into simple values (${sm_id}, '${sm_name}')")
	public int insertSimple(String sm_id, String sm_name);
	
	@Update("update simple set sm_name='${sm_name}' where sm_id=${sm_id}")
	public int updateSimple(String sm_id, String sm_name);
	
	@Delete("delete from simple where sm_id=${sm_id}") // sm_id는 number형 컬럼으로 문자열처리 하지 않았음
	public int deleteSimple(String sm_id);

}
