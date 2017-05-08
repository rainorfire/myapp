package com.test.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.test.util.JsonUtil;
import com.test.websocket.WebSocketHandlerCustom;

@Controller
@RequestMapping("/admin")
public class TestController {
	
	@Resource
	private DefaultListableBeanFactory dbf ;
	
	@Resource
	private WebSocketHandlerCustom webSocketHandler ;
	
	@Resource
	private SimpMessagingTemplate  simpMessagingTemplate  ;
	
	@PostConstruct
	public void postConstruct(){
		String[] dfNames = dbf.getBeanDefinitionNames();
		for(String name : dfNames){
			System.out.println(name);
		}
		System.out.println("---------------------POST CONSTRUCT---------------");
		
	}
	
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request){
		WebApplicationContext context = RequestContextUtils.findWebApplicationContext(request);
		Map<String, HandlerMapping> matchingBeans =
				BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
		System.out.println(matchingBeans.toString());
		System.out.println("000000000000000");
		
		String username = AssertionHolder.getAssertion().getPrincipal().getName();  
        AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal(); 
        if(principal != null){
        	Map<String, Object> attributes = principal.getAttributes();  
    		System.out.println(attributes+"============"+username);
        }
		return new ModelAndView("/index.jsp");
	}
	
	@RequestMapping("/sendmsg")
	public ModelAndView sendMsg(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", "12");
		map.put("12", "Hello,WebSocked!");
		webSocketHandler.sendTextMsg(JsonUtil.object2Json(map));
		return new ModelAndView("/sendMsg.jsp");
	}
	
//	----------------------- STOMP -----------------------------
	@RequestMapping("/stomp/test")
	public ModelAndView stompTest(HttpServletRequest request){
		return new ModelAndView("/index_stomp.jsp");
	}
	
	@RequestMapping("/stomp/sendmsg")
	public ModelAndView stompSendMsg(HttpServletRequest request){
		simpMessagingTemplate.convertAndSend("/stomp/stomp-test/test", "[2016-01-27]:Hello,stomp socket!....");
		return new ModelAndView("/sendMsg_stomp.jsp");
	}
	
	@ResponseBody 
	@RequestMapping("/heli/server-test")
	public String heliServerTest(String content,HttpServletRequest request,HttpServletResponse response){
//		Map<String, Object> paraMap = getAvailableRequestParaMap(request);
//		 {"IvrVars":"REAL_FROM_CID:13400617157,CLUE_ID:3199992,TASK_ID:10","Account":"N000000006231","Action":"Webcall",
//			 "ActionID":"13400617157","PBX":"1.731.102.101","ServiceNo":"73188048560","Exten":"13400617157"}
		Enumeration<String> parameterNames = request.getParameterNames();
		String nextElement = parameterNames.nextElement();
		final Map<String,String> paraMap = JsonUtil.json2Map(nextElement, String.class, String.class);
		
		try{
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendPost2UserStat(paraMap);
			
			String actionIdf = (String) paraMap.get("ActionID");
			String resultJson = "{\"Command\":\"Response\",\"Succeed\":true,\"ActionID\":\""+actionIdf+"\",\"Response\":\"Dialout\"}";
			return resultJson;
		}finally {
			new Thread(new Runnable() {
				
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sendPost2OpenWindow(paraMap);
				}
			}).start();
		}
	}
	
	/**
	 * 获取有效的请求参数的map（丢弃了空值）
	 * @param request
	 * @return
	 */
	protected Map<String,Object> getAvailableRequestParaMap(HttpServletRequest request){
		Map<String, Object> paraMap = new HashMap<String, Object>(); 
		Enumeration<String> paramNameEnum = request.getParameterNames();
		while(paramNameEnum != null && paramNameEnum.hasMoreElements()){
			String paramName = paramNameEnum.nextElement();
			String param = request.getParameter(paramName);
			if(!StringUtils.isBlank(param)){
				paraMap.put(paramName, param);
			}
		}
		return paraMap;
	}
	
	private static void sendPost2UserStat(Map<String, String> paraMap){
		HttpClient httpClient = HttpClients.createDefault(); 
		//10.37.18.123:8093
		HttpPost postMethod = new HttpPost("http://crm.djtest.cn/heli/userState");
		
		try {
//			{"IvrVars":"REAL_FROM_CID:13400617157,CLUE_ID:3199992,TASK_ID:10","Account":"N000000006231","Action":"Webcall",
//				 "ActionID":"13400617157","PBX":"1.731.102.101","ServiceNo":"73188048560","Exten":"13400617157"}
//			String siteNo = HttpUtil.getStringParameter(request, "Exten", "", true); 
//	        String eventTypeStr = HttpUtil.getStringParameter(request, "Type", "", true);        
//	        String stateStr = HttpUtil.getStringParameter(request, "State", "", true);       
//	        String loginType = HttpUtil.getStringParameter(request, "ExtenType", "", true);       
//	        String busyTypeStr = HttpUtil.getStringParameter(request, "BusyType", "", true);       
//	        String happenTimeStr = HttpUtil.getStringParameter(request, "Time", "", true);
	        
			BasicNameValuePair Exten = new BasicNameValuePair("Exten","8028");
			BasicNameValuePair siteNo = new BasicNameValuePair("Type","1");
			BasicNameValuePair eventTypeStr =  new BasicNameValuePair("State","1");
			BasicNameValuePair ExtenType = new BasicNameValuePair("ExtenType","gateway");
			BasicNameValuePair BusyType = new BasicNameValuePair("BusyType","");
			BasicNameValuePair Time = new BasicNameValuePair("Time","2016-12-22 10:53:00");

			// 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        formparams.add(Exten);
	        formparams.add(siteNo);
	        formparams.add(eventTypeStr);
	        formparams.add(ExtenType);
	        formparams.add(BusyType);
	        formparams.add(Time);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,"UTF-8");
			postMethod.setEntity(entity);
			httpClient.execute(postMethod);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendPost2OpenWindow(Map<String, String> paraMap){
		HttpClient httpClient = HttpClients.createDefault(); 
//		"http://10.37.18.123:8093/heli/openWindow"
		HttpPost postMethod = new HttpPost("http://crm.djtest.cn/heli/openWindow");
		
		try {
//			{"IvrVars":"REAL_FROM_CID:13400617157,CLUE_ID:3199992,TASK_ID:10","Account":"N000000006231","Action":"Webcall",
//				 "ActionID":"13400617157","PBX":"1.731.102.101","ServiceNo":"73188048560","Exten":"13400617157"}
			
//			 String siteNo = HttpUtil.getStringParameter(request, "Agent", "", true); 
//		        String callSheetId = HttpUtil.getStringParameter(request, "callSheetId", "", true);        
//		        String callerProvince = HttpUtil.getStringParameter(request, "callerProvince", "", true);       
//		        String callerCity = HttpUtil.getStringParameter(request, "callerCity", "", true);       
//		        String offeringTimeStr = HttpUtil.getStringParameter(request, "offeringTime", "", true);       
//		        String originCallNo = HttpUtil.getStringParameter(request, "originCallNo", "", true);
//		        String originCalledNo = HttpUtil.getStringParameter(request, "originCalledNo", "", true);
//		        String queueName = HttpUtil.getStringParameter(request, "queueName", "", true);
//		        String customerMobile = HttpUtil.getStringParameter(request, "R_FROM_CID", "", true);
//		        String taskId = HttpUtil.getStringParameter(request, "TASK_ID", "", true);
//		        String clueIdStr = HttpUtil.getStringParameter(request, "CLUE_ID", "", true);
			String IvrVars = paraMap.get("IvrVars");
			String[] strings = IvrVars.split(",");
			String mobile = "";     
			String taskIdStr = "";   
			String clueId = "";   
			for(String str : strings){
				String[] strArray = str.split(":");
				if("REAL_FROM_CID".equals(strArray[0])){
					mobile = strArray[1];
				}else if("CLUE_ID".equals(strArray[0])){
					clueId = strArray[1];
				}else if("TASK_ID".equals(strArray[0])){
					taskIdStr = strArray[1];
				}
			}
			
			BasicNameValuePair siteNo = new BasicNameValuePair("Agent","8028");
			BasicNameValuePair callSheetId =  new BasicNameValuePair("callSheetId","house");
			BasicNameValuePair callerProvince = new BasicNameValuePair("callerProvince","house");
			BasicNameValuePair callerCity = new BasicNameValuePair("callerCity","house");
			BasicNameValuePair offeringTimeStr = new BasicNameValuePair("offeringTimeStr","house");
			BasicNameValuePair originCallNo = new BasicNameValuePair("originCallNo","house");
			BasicNameValuePair originCalledNo = new BasicNameValuePair("originCalledNo","house");
			BasicNameValuePair queueName = new BasicNameValuePair("queueName","house");
			BasicNameValuePair customerMobile = new BasicNameValuePair("R_FROM_CID",mobile);
			BasicNameValuePair taskId = new BasicNameValuePair("TASK_ID",taskIdStr);
			BasicNameValuePair clueIdStr = new BasicNameValuePair("CLUE_ID",clueId);

			// 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        formparams.add(siteNo);
	        formparams.add(callSheetId);
	        formparams.add(callerProvince);
	        formparams.add(callerCity);
	        formparams.add(offeringTimeStr);
	        formparams.add(originCallNo);
	        formparams.add(originCalledNo);
	        formparams.add(queueName);
	        formparams.add(customerMobile);
	        formparams.add(taskId);
	        formparams.add(clueIdStr);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,"UTF-8");
			postMethod.setEntity(entity);
			httpClient.execute(postMethod);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		sendPost2UserStat(null);
	}
	
}
