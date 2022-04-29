package kr.or.basic.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CkUploadController {

	@Autowired
	private String uploadPath;
	
	@PostMapping(value="/ckUpload",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String ckUpload2(HttpServletRequest req,
			             HttpServletResponse res,
			             MultipartFile upload) throws Exception { // CkEditor에서 ajax로 보내줄것임 
		
		log.info(upload.getOriginalFilename());
		
		UUID uid = UUID.randomUUID();
				
		String fileName = upload.getOriginalFilename();
		log.info("filename ===>" + fileName);
		
		// 실제 서버 내에 파일이 저장될 경로
		String ckUploadPath = uploadPath + "/" + uid + "_" + fileName;
	    
		// 프리뷰가 바로바로 동작하지 않는 문제가 있는데 transferTo하는 서버 딜레이+이클립스에서 만드는 지연 때문
		// 이미지를 링크로 printwriter로 flush 해주면 좀 더 나을수 있지만...
		upload.transferTo(new File(ckUploadPath)); // multipart-config 되어있으면 transferTo를 사용해 바로 옮길 수 있음
	    log.info("ckUploadPath ===>" + ckUploadPath);
	    
	    
	    Thread.sleep(2000);
	    
	    // 전송 되기 전 뷰에서 파일 주소를 요청해서 생기는 404
	    // 동기처리를 해주면 전체적으로 느려져서 X
	    
	    // ckeditor를 이용할 때 정해진 부분 : 개발 지침
		String callback = req.getParameter("CKEditorFuncNum"); // 뷰의 editor 삽입부에서 form 처리 해주지 않으면 null을 반환함
		log.info("callback ===>" + callback);
		
		String fileUrl = req.getContextPath() + "/ckUpload/" + uid + "_" + fileName; // servlet-context에서 지정해준 리소스 주소 매핑
		log.info("fileUrl ===>" + fileUrl);
		
        /* CKEditor가 원하는 스크립트 문자열을 리턴(아님말공)  */
		// 이미지 업로드 후에 발생할 스크립트
		String scriptStr = "<script type='text/javascript'>"
				+ "window.parent.CKEDITOR.tools.callFunction("
				+ callback + ",'" + fileUrl+"','이미지 업')"
				+ "</script>";
	
		return scriptStr;
		
	}
}
