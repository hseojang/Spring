package kr.or.yourboard.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {
	// NotBlank : 공백과 Null 제한
	// NotNull : Null만 제한
	// NotEmpty : CharSequence, Map, Array, Collection 타입의 내용물이 비어있지 않도록 제한
	
	private int boardNo;
	
	@NotBlank // 컨트롤러에서 @Valid가 있을 때에만 체크함에 유의
	private String boardTitle;
	@NotBlank(message = "작성자를 입력하세요")
	private String boardWriter;
	@NotBlank(message = "내용이 없슴니다")
	private String boardContent;
	private Date boardRegdate;
	private Date boardUpdate;
	
}
