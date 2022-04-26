package kr.or.basic.domain;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Minhuk {
	private String nm_name;
	private String nm_special;
	private String nm_girl;
	private List<MultipartFile> files;
	
	public Minhuk() {
	System.out.println("민혁 객체 생성됨");
	}
	
	public String getNm_name() {
		return nm_name;
	}
	public void setNm_name(String nm_name) {
		System.out.println("민혁 name setter");
		this.nm_name = nm_name;
	}
	public String getNm_special() {
		return nm_special;
	}
	public void setNm_special(String nm_special) {
		System.out.println("민혁 special setter");
		this.nm_special = nm_special;
	}
	public String getNm_girl() {
		return nm_girl;
	}
	public void setNm_girl(String nm_girl) {
		System.out.println("민혁 girl setter");
		this.nm_girl = nm_girl;
	}

	public List<MultipartFile>  getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		System.out.println("민혁 files setter");
		this.files = files;
	}
	
	
}
