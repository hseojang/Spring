package kr.or.basic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// .do로 끝나는 url은 다 이 서블릿에서 관리하도록
@WebServlet("*.do")
public class MyDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlersMapping handlersMapping = new HandlersMapping();
	private ViewResolver viewResolver = new ViewResolver();
	
    public MyDispatcher() {
        super();
    }

	// 서블릿의 init 메소드 : 객체가 생성되고 바로 실행되는 초기화 블럭
	@Override
	public void init() throws ServletException {
		handlersMapping.getMappings().put("/aaa.do", new MyController()); // 스프링은 이걸 자동으로 해줌
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setPostFix(".jsp");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		process(request, response);
	}
	
	// get하고 post 억지로 묶어주기
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URI vs URL
		
		String reqUrl = request.getRequestURI();
		// 제일 마지막 / 뒤의 부분만 가져오기
		String path = reqUrl.substring(reqUrl.lastIndexOf("/"));
		System.out.println(path);
		
		Controller ctrl = handlersMapping.getMappings().get(path);
		System.out.println("controller: " + ctrl);
		String viewName = ctrl.handleRequest();
		System.out.println("viewName: " + viewName);
		String viewPath = viewResolver.getView(viewName);
		System.out.println("viewPath: " + viewPath);
		
		// 포워딩
		request.getRequestDispatcher(viewPath).forward(request, response);

	}

}
