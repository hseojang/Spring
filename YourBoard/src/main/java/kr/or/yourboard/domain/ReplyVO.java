package kr.or.yourboard.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyVO {
	
	private int replyNo;
	private int boardNo;
	private String replyTitle;
	private String replyWriter;
	private String replyContent;
	private Date replyRegdate;
	
}