package kr.or.basic.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Controller
@RequestMapping("/bypass")
public class ByPassController {

	/* 서버 우회 단순히 받은대로 문자열을 되돌려줌*/
	@GetMapping("/googleNews")
	@ResponseBody
	public String getGoogleNews(String schWord) throws Exception {
		String url = "https://news.google.com/rss/search?q="+schWord+"&hl=en-US&gl=US&ceid=US:en";
		// String url = "https://www.flickr.com/services/feeds/photos_public.gne?tags=blackpink&format=json";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url);
		
		CloseableHttpResponse response = httpClient.execute(getRequest);
		
		String json = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
				
        httpClient.close();
		return json;
	}
	
	@GetMapping(value="/google2",produces = "application/xml;charset=utf-8")
	@ResponseBody
	public Document getGoogleNews2() throws Exception {
		String url = "https://news.google.com/rss/search?q=blackpink&hl=ko&gl=KR&ceid=KR:ko";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url);
		
		CloseableHttpResponse response = httpClient.execute(getRequest);
		//response.setHeader("Content-Type", "application/xml");
		
		Document doc = null;
		DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = docBuilder.newDocumentBuilder();
		
		doc = builder.parse(response.getEntity().getContent());
		

		return doc;
			
	}
	
	
	@GetMapping(value="/google3",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getGoogleNews3(String schWord) throws Exception {
		String url = "https://news.google.com/rss/search?q="+schWord+"&hl=en-US&gl=US&ceid=US:en";
																	//&hl=ko&gl=KR&ceid=KR:ko  - 한국 뉴스를 검색할 때
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url);
		
		CloseableHttpResponse response = httpClient.execute(getRequest);
		//response.setHeader("Content-Type", "application/xml");
		
		XmlMapper xmlMapper = new XmlMapper(); // jackson 라이브러리를 이용한 결과 파싱
		JsonNode node = xmlMapper.readTree(response.getEntity().getContent());
		

		ObjectMapper jsonMapper = new ObjectMapper();
		
		httpClient.close();
		return jsonMapper.writeValueAsString(node);  
	}
	

	// 유튜브 동영상 검색 만들기
	@GetMapping("/utube")
	@ResponseBody
	public String getUtubeList(String schWord) throws Exception {
		String url = "https://www.youtube.com/results?search_query="+schWord;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url);
		
		CloseableHttpResponse response = httpClient.execute(getRequest);
		
		String json = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
				
        httpClient.close();
		return json;
	}
	


	@GetMapping("/utubeTitle")
	@ResponseBody
	public String getUtubeTitle(String schWord) throws Exception {
		String url = "https://www.youtube.com/"+schWord;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url);
		
		CloseableHttpResponse response = httpClient.execute(getRequest);
		
		String json = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
				
        httpClient.close();
		return json;
	}
	
}
