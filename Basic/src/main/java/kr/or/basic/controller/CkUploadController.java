package kr.or.basic.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
		
		
		// 이클립스가 실제로 사용하는 디렉토리와 프로젝트의 webapp 디렉토리가 다름을 인지해야함
		// 이클립스의 임시 폴더에 먼저 저장했다가, 원하는 디렉토리로 파일을 옮기는 식
		// 이 시간차로 파일 프리뷰와 업로드에 문제가 있는 것 처럼 보임(아직 파일이 없어서 뜨는 엑박)
		log.info("webapp/resources : " + req.getServletContext().getRealPath("/resources"));
		log.info("webapp/WEB-INF : " + req.getServletContext().getRealPath("/resources/WEB-INF"));
		log.info("servletContext : " + req.getServletContext());
		log.info(upload.getOriginalFilename());
		
		String realPath = req.getServletContext().getRealPath("/resources/ckUpload"); // 서버가 실제로 파일을 물리적으로 저장하는 경로
		
		UUID uid = UUID.randomUUID();
				
		String fileName = upload.getOriginalFilename();
		// log.info("filename ===>" + fileName);
		
		// 실제 서버 내에 파일이 저장될 경로
		// String ckUploadPath = uploadPath + "/" + uid + "_" + fileName; // 서블릿 컨텍스트로 지정해준 업로드 경로 - 프로젝트 경로
																			// 그동안 서버 디플로이한 버전과 이클립스 버전을 하드코딩해 그때그때 변경했음
																			// 이제는 어느쪽으로 가도 실제 경로에 저장하면 바로 불러올 수 있도록 만듬
																			// = 서버는 원래 서버 경로에 저장하고, 이클립스는 원래 이클립스 경로에 저장하면 링크되게끔
		String ckUploadPath = realPath + "/" + uid + "_" + fileName;
	    
		// 프리뷰가 바로바로 동작하지 않는 문제가 있는데 transferTo하는 서버 딜레이+이클립스에서 만드는 지연 때문
		// 이미지를 링크로 printwriter로 flush 해주면 좀 더 나을수 있지만... 더 느림
		upload.transferTo(new File(ckUploadPath)); // multipart-config 되어있으면 transferTo를 사용해 바로 옮길 수 있음
	    // log.info("ckUploadPath ===>" + ckUploadPath);
	    
	    
	    Thread.sleep(1000);
	    
	    // 전송 되기 전 뷰에서 파일 주소를 요청해서 생기는 404
	    // 동기처리를 해주면 전체적으로 느려져서 X
	    
	    // ckeditor를 이용할 때 정해진 부분 : 개발 지침(fileUrl, callBack변수 전송해주기)
		String callback = req.getParameter("CKEditorFuncNum"); // 뷰의 editor 삽입부에서 form 처리 해주지 않으면 null을 반환함
		log.info("callback ===>" + callback);
		

		log.info("realPath : " + realPath);
		
		String fileUrl = req.getContextPath() + "/ckUpload/" + uid + "_" + fileName; // "/ckUpload/"  : servlet-context에서 지정해준 리소스 매핑
		// String fileUrl = realPath + "/" + uid + "_" + fileName; // 파일 URL을 실제 경로로 바꾸어서 지정해본 것 - URL이 아님(클라이언트에서 서버의 절대경로로 접근할 수 없음)
		// 파일을 실시간으로 이클립스가 작업하는 경로에서 프로젝트로 옮기는 지연을 줄이기 위해 실제 파일이 생성되는 위치와 프로젝트의 리소스 폴더를 연결해 주려고 함
		// 커맨드라인으로(mklink /D) 업로드 폴더명과 같은 symbolic link를 작업하는 폴더에(.metadata 안에) 생성하면 가능해짐
		// 파일이 업로드 되고, 작업 중인 경로의 resource/ckUpload 디렉토리에 파일을 생성하면 바로가기에 의해 프로젝트 폴더에 실제 데이터가 생성됨
		
		log.info("fileUrl ===>" + fileUrl);
		
        /* CKEditor가 원하는 스크립트 문자열을 리턴(아님말공)  */
		// 이미지 업로드 후에 발생할 스크립트
		String scriptStr = "<script type='text/javascript'>"
				+ "window.parent.CKEDITOR.tools.callFunction("
				+ callback + ",'" + fileUrl+"','이미지 업')"
				+ "</script>";
		
		// fileUrl은 img src="" 안에 들어갈 값임
	
		return scriptStr;
		
	}
	
	
	@PostMapping(value="/gUpload",produces = "text/html;charset=utf-8") // 한글이 있을 땐 charset이 포함된 방식으로 해줘야함
	// @PostMapping (value="/gUpload",produces = "application/json;charset=utf-8") // 로 보낼 경우 jQuery 내부 처리 과정에서 실패함
	@ResponseBody
	public String gUpload2(HttpServletRequest req,
			             HttpServletResponse res,
			             MultipartFile upload) throws Exception {

		log.info("webapp/resources : " + req.getServletContext().getRealPath("/resources"));
		log.info("webapp/WEB-INF : " + req.getServletContext().getRealPath("/resources/WEB-INF"));
		log.info("servletContext : " + req.getServletContext());
		log.info(upload.getOriginalFilename());
		
		String realPath = req.getServletContext().getRealPath("/resources/ckUpload"); // 서버가 실제로 파일을 물리적으로 저장하는 경로
		
		UUID uid = UUID.randomUUID();
				
		String fileName = upload.getOriginalFilename();
		String ckUploadPath = realPath + "/" + uid + "_" + fileName;
		upload.transferTo(new File(ckUploadPath)); // multipart-config 되어있으면 transferTo를 사용해 바로 옮길 수 있음

	    // ckeditor를 이용할 때 정해진 부분 : 개발 지침(fileUrl, callBack변수 전송해주기)
		String callback = req.getParameter("CKEditorFuncNum"); // 뷰의 editor 삽입부에서 form 처리 해주지 않으면 null을 반환함

		
		String fileUrl = req.getContextPath() + "/ckUpload/" + uid + "_" + fileName; 
		log.info(fileUrl);
		return fileUrl;
		
	}
}
