package kr.or.blackpink.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpVO {
	private String userid;
	private String secretKey;

}
