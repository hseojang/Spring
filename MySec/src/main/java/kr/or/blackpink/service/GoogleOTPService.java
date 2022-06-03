package kr.or.blackpink.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.blackpink.domain.OtpVO;
import kr.or.blackpink.mapper.OtpMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PC-13
 *
 */
@Slf4j
@Service
public class GoogleOTPService {

	@Autowired
	private OtpMapper otpMapper;

	private static final int secretSize = 5;
	private static final int numOfScratchCodes = 5;
	private static final int scratchCodeSize = 5;

	
	/**
	 * 사용자의 비밀키를 생성해서 DB에 저장하는 메서드
	 * @param userid 로그인 한 계정 id
	 * @param host 호스트네임(사이트 도메인을 써야하지만 일단 임의로 정함)
	 * @return 
	 */
	public HashMap<String, String> generate(String userid, String host) {

		// 암호화된 키와 url을 리턴해줄 맵
		HashMap<String, String> map = new HashMap<String, String>();

		// 버퍼를 채움(채울 버퍼 수를 이렇게 구성하게 되어있음)
		byte[] buffer = new byte[(secretSize + numOfScratchCodes * scratchCodeSize)];

		// 랜덤한 숫자로 버퍼 채우기
		new Random().nextBytes(buffer);

		// 버퍼를 암호화 시키기
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, secretSize);
		byte[] bEncodedKey = codec.encode(secretKey);
		String encodedKey = new String(bEncodedKey);

		// qr코드 url을 생성, 인코딩 과정에서 발생할 수 있는 예외처리
		String url="";
		try {
			url = getQRBarcodeURL(userid, host, encodedKey);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// model로 확인해보려고 임의로 map 구성
		map.put("encodedKey", encodedKey);
		map.put("url", url);

		// vo에 담아서 테이블에 저장
		OtpVO vo = new OtpVO();
		vo.setSecretKey(encodedKey);
		vo.setUserid(userid);

		// 저장이 성공하면 map 반환, 아니면 null 반환(나중에 수정할 예정)
		if (otpMapper.insertKey(vo) > 0) {
			return map;
		} else {
			return null;
		}

	}

	public static String getQRBarcodeURL(String userid, String host, String encodedKey) throws UnsupportedEncodingException {
		// 구글에서 제공하는 QR코드 주소 생성
		String prefix = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
		String suffix = String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", host, userid, encodedKey, host);
		// 인코딩 처리를 해주어야 깨지지 않음
		String url = prefix + URLEncoder.encode(suffix, "UTF-8");
		
		// 만들어진 url 반환
		return url;
	}

	/**
	 * @param encodedKey db에 저장된 비밀키
	 * @param inputCode   사용자가 입력한 숫자
	 * @param time      OTP 숫자가 만들어진 시간
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private static boolean check_code(String encodedKey, long inputCode, long time)
			throws NoSuchAlgorithmException, InvalidKeyException {
		
		// 암호화할 코덱 생성
		Base32 codec = new Base32();
		
		// 암호화된 키를 다시 복호화
		byte[] decodedKey = codec.decode(encodedKey);

		// window는 시간 범주를 가리키는 변수임. = 30초
		// 지정된 window의 크기만큼 OTP 생성 번호가 유효함
		int window = 3;
		// 시간 범위만큼 생성된 해시값을 전부 비교
		// 아래에서 설정한 time이 30초 단위의 시간이므로 앞뒤로 3*30초 = 1분 30초씩 비교하게됨
		for (int i = -window; i <= window; ++i) {
			long hash = verify_code(decodedKey, time + i);

			if (hash == inputCode) {
				// 일치하는 경우
				return true;
			}
		}
		// 일치하지 않으면 false 반환
		return false;
	}

	private static int verify_code(byte[] decodedKey, long time) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = time;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(decodedKey, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;


		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}

	
	/**
	 * @param inputCode 사용자로부터 입력받은 숫자
	 * @param userid 
	 * @return
	 */
	public boolean auth(int inputCode, String userid) {
		String key = otpMapper.selectKey(userid);
		long code = inputCode;
		long time = new Date().getTime() / TimeUnit.SECONDS.toMillis(30);
		// 30초 단위로 쪼갠 시간
		try {
			
			return check_code(key, code, time);
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return false;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}

	}
}
