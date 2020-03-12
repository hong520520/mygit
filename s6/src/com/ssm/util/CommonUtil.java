package com.ssm.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonUtil {
	/**
	 * 输出json字符串
	 * @param response
	 * @param jsonstr
	 * @throws IOException
	 */
	public static void printjson(HttpServletResponse response,String jsonstr) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonstr);
		out.close();
	}
	
	/**
	 * 输出字符串
	 * @param response
	 * @param jsonstr
	 * @throws IOException
	 */
	public static void print(HttpServletResponse response,String jsonstr) throws IOException{
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonstr);
		out.close();
	}
	
	public static HttpServletRequest getrequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * java对象转jsonStr
	 * @param javaObj
	 * @return
	 */
	public static final String toJsonView(Object javaObj) {
		 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		 return gson.toJson(javaObj);
	}

}
