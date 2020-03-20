package com.ssm.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.entity.PageBean;
import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;

@Service
public class UserService {
	@Resource
	private UserMapper userMapper;
	
	public List<User> findAll(){
		return userMapper.findAll();
	};

	public void   add(User user){
		 userMapper.add(user);
	};

	public void   updateById(User user){
		userMapper.updateById(user);
	};

	public int deleteById(Integer id){
		return  userMapper.deleteById(id);
	};

	public User selectById(Integer id){
		return userMapper.selectById(id);
	};
	
	public User selectByName(String name){
		return userMapper.selectByName(name);
	};
	
	public User loginByNameAndPassword(User user ){
		return userMapper.loginByNameAndPassword(user);
	};
	
	public int selectCount(){
		return userMapper.selectCount();
	};
	
	public PageBean<User> findByPage(int currentPage){
		HashMap<String,Object> map = new HashMap<String,Object>();
		PageBean<User> pageBean = new PageBean<User>();
		
	    //封装当前页数
        pageBean.setCurrPage(currentPage);
        
		//每页显示的数据
		int pageSize=10;
		pageBean.setPageSize(pageSize);
		
		//封装总记录数
		int totalCount = userMapper.selectCount();
		pageBean.setTotalCount(totalCount);
		
		//封装总页数 
		double tc = totalCount;
//		double tc=Double.valueOf(totalCount);
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
      
		map.put("start",(currentPage-1)*pageSize);
		map.put("size", pageBean.getPageSize());
		//封装每页显示的数据
		List<User> lists = userMapper.findByPage(map);
		pageBean.setLists(lists);
		
		return pageBean;
	};

}
