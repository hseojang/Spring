package kr.or.yourboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Getter
@Setter
@Slf4j
public class PageCondDTO {
	private int pageNum;
	private int pageSize;
	private int pageRange; // 노출될 페이지 수 

	// 기본 생성자로 초기값을 세팅
	// 자동으로 호출되게 되어있음
	public PageCondDTO() {
		this(1, 10, 10);
		log.info("초기 생성자 호출됨");
	}
	
	public PageCondDTO(int pageNum, int pageSize, int pageRange) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pageRange = pageRange;
	}
	
}
