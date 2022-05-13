package kr.or.blackpink.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	private String bdNo;
	private String bdUsername;
	private String bdPassword;
	private String bdTitle;
	private String bdContent;
	private String bdCategory;
	private Date bdCreatedDate;
	private Date bdLastEdit;
	private int bdViews;
	
	
}
