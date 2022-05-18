package kr.or.yourboard.domain;

import lombok.Getter;
import lombok.ToString;

// PageDTO는 생성자에 의해서만 변수에 따라 내부 변수값을 세팅해야하므로 setter가 없어야 함
// = 읽기 전용 속성
@Getter
@ToString
public class PageDTO {
	
	private int total; // 총 게시글 개수
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	
	// isPrevious 식으로 변수이름을 지으면 boolean 타입의 getter에 prefix 'is'를 생략함
	// 롬복 사용 시 아웃라인을 확인해볼 필요 있음
	private boolean isPrevious;
	private boolean isNext;
	
	private PageCondDTO pageCondDTO; // 입력받은 페이지 조건
	

	public PageDTO(PageCondDTO pageCondDTO, int total) {
	
		this.isNext = true;
		this.isPrevious = true;
		
		this.pageCondDTO = pageCondDTO;
		int pageRange = pageCondDTO.getPageRange();
		
		// 일단 한 화면에 1~10까지 페이지가 10개씩 나타나야 한다고 가정
		// 1 2 3 4 5 6 7 8 9 10 이런식으로 끝 페이지를 항상 10의 배수로 맞추려고 함
		
		// 페이지 넘버가 10일 때면 끝페이지와 같아짐
		this.endPage = (int) Math.ceil(pageCondDTO.getPageNum() / (pageRange*1.0))*pageRange;
		// 현재 페이지를 위치시킬 단위 페이지(곱하고 나누는 수) 묶음으로 변환

		// 페이징이 끝나는 곳에서는 41 42 43 44 45 46 까지만 출력하도록 하려고
		// startPage 설정을 endPage 교정보다 먼저 해줌. 
		this.startPage = this.endPage - (pageRange-1);
		this.total = total;
		
		int realEnd = (int) Math.ceil(total / pageCondDTO.getPageSize()); // 총 게시글 수에 기반한 최종 페이지, 장당 몇개를 보여줄 지가 제수가 됨
		
		if (this.startPage <= 1) { // 시작페이지는 1보다 작아질 수 없음
			this.isPrevious = false; // 시작페이지가 1이면 이전 페이지로 넘어갈 수 없으므로 false
			this.startPage = 1;
		}
		if (realEnd < this.endPage) { // 계산된 단위 페이지의 끝이 실제 최종 페이지보다 클 수 없음
			this.isNext = false; // 다음 페이지가 존재할 수 없으므로 false
			this.endPage = realEnd;
		}
		
		
	}
	
}
