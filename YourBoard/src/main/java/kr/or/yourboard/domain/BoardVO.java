package kr.or.yourboard.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {

	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardRegdate;
	private Date boardUpdate;
	
}
