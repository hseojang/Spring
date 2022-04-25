package kr.or.basic.controller;

// view 결정자
public class ViewResolver {
	
	public String prefix;
	public String postfix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setPostFix(String postfix) {
		this.postfix = postfix;
	}
	
	public String getView(String viewName) {
		return this.prefix + viewName + this.postfix;
	}

}
