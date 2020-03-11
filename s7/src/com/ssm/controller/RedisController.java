package com.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssm.entity.User;
import com.ssm.util.CacheUtil;
//洪小太爷的修改
@Controller
public class RedisController {
	@Resource
	private CacheUtil cacheUtil;
	@RequestMapping("/test")
	@ResponseBody
	public void testRedis(){
		//简单字符串处理
		cacheUtil.put( "name", "kobe");
		System.out.println("String---name--"+cacheUtil.get("name"));
		
		//map
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("st1", "jordon");
		map.put("st2", "michael");
		cacheUtil.put("map", map);
		//第一种取值方式
		Map map1 = cacheUtil.get("map",Map.class);
		if(map1 != null){
			System.out.println("first map---"+map1.get("st1"));
		}
		//第二种取值方式
		Map map2 = new Gson().fromJson(cacheUtil.get("map"), new TypeToken<Map<String,Object>>() {}.getType());
		if(map2 != null){
			System.out.println("second map---"+map2.get("st2"));
		}
		
		
		//JavaBean处理
		User user = new User();
		user.setName("臭猴子");
		cacheUtil.put("user",user);
		User user1 = cacheUtil.get("user",User.class);
		System.out.println("javaBean--name--"+user1.getName());
		
		//List<JavaBean>处理
		List<User> list = new ArrayList<User>();
		list.add(user);
		cacheUtil.put("list", list);
		List<User> list1 = new Gson().fromJson(cacheUtil.get("list"), new TypeToken<List<User>>() {}.getType());
		if(list1 != null){
			System.out.println("List<JavaBean>--"+list1.get(0).getName());
		}
		
		
		//list<String>
		List<String> newlist = new ArrayList<String>();
		newlist.add("str1");
		newlist.add("str2");
		cacheUtil.put("newlist", newlist);
		List<String> newlist1 =  new Gson().fromJson(cacheUtil.get("newlist"), new TypeToken<List<String>>(){}.getType());
		System.out.println("list<String>--"+newlist1);
		
		//List<Map<String,Object>>
		List<Map<String,Object>> nowlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> newmap = new HashMap<String,Object>();
		newmap.put("key1", "value1");
		newmap.put("key2", "value2");
		nowlist.add(newmap);
		cacheUtil.put("nowlist", nowlist);
		List<Map<String,Object>> nowlist1 =  new Gson().fromJson(cacheUtil.get("nowlist"), new TypeToken<List<Map<String,Object>>>(){}.getType());
		if(nowlist1 !=null ){
			System.out.println(nowlist1.get(0).get("key1"));
		}
		System.out.println("List<Map<String,Object>>--"+nowlist1);
		
		//List<Map<String,TUser>>
		List<Map<String,User>> lastList = new ArrayList<Map<String,User>>();
		Map<String,User> lastMap = new HashMap<String, User>();
		lastMap.put("user", user);
		lastList.add(lastMap);
		cacheUtil.put("lastList", lastList);
		List<Map<String,User>> lastList1 =  new Gson().fromJson(cacheUtil.get("lastList"), new TypeToken<List<Map<String,User>>>(){}.getType());
		if(lastList1 != null){
			System.out.println("List<Map<String,TUser>>---"+lastList1.get(0).get("user").getName());
		}
		System.out.println(lastList1);

	}
}
