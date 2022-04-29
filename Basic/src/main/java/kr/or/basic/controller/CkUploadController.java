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
		
		String ckUploadPath = uploadPath + "/" + "ckUpload" + "/" + uid + "_" + fileName;
	    upload.transferTo(new File(ckUploadPath));
				
		String callback = req.getParameter("CKEditorFuncNum");
		
		String fileUrl = req.getContextPath() + "/ckUpload/" + uid + "_" + fileName;
		
        /* CKEditor가 원하는 스크립트 문자열을 리턴(아님말공)  */
		String scriptStr = "<script type='text/javascript'>"
				+ "window.parent.CKEDITOR.tools.callFunction("
				+ callback + ",'" + fileUrl+"','이미지 업')"
				+ "</script>";
	
		return scriptStr;
		
	}
}
