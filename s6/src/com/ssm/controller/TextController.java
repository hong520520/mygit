package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;
import com.ssm.service.UserService;

@SessionAttributes("currentUser")
@Controller
public class TextController {
	
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public String login(@RequestParam("name")String name,
			@RequestParam("password")String password,Model model) throws Exception{
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		User result = userService.loginByNameAndPassword(user);
		if(result!=null){
			//登录成功
//			List<User> lists = userService.findAll();
//			model.addAttribute("userLists", lists);//回显用户信息
			model.addAttribute("currentUser", result.getName());	
			return "redirect:findAll";
		}
		return "error";
	}
	
	@RequestMapping("/findAll")
	public String findPageUser(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage,Model model)throws Exception{
		model.addAttribute("pagemsg", userService.findByPage(currentPage));//回显分页数据
		return "user";
	}
	/*@RequestMapping("/findAll")
	 public String findAllUser(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage, Model model )throws Exception{
//		 List<User> lists = userService.findAll();
//			model.addAttribute("userLists", lists);
			model.addAttribute("pagemsg", userService.findByPage(currentPage));
			return "user";
	 } */
	
	@RequestMapping("/updateById")
	public String updateById(User user) {
		userService.updateById(user);
		return "redirect:findAll"; 
	}
	
	/*@RequestMapping("/save")
	public String save(User user) {
	   System.out.println(user.toString());
	   if(user.getId()==null){
		   userService.add(user);
	   }else{
		   userService.updateById(user);
	   }
		return "redirect:findAll";
	}*/

	@RequestMapping("/deleteById")
	public String deleteById(Integer id) {
		userService.deleteById(id);
		return "redirect:findAll";
	}

	@RequestMapping("/add")
	public String addUser(User user) {
	    userService.add(user);
		return "redirect:findAll";
	}
	
	 @RequestMapping("/toAddUser")
		public String toAddUser(){
			return "addUser";
		}
	
	@RequestMapping("/selectById")
	public ModelAndView selectById(Integer id) {
		User user = userService.selectById(id);
		ModelAndView mav = new ModelAndView("/userInfo");
		mav.addObject("u", user);
		return mav;
	}
	
	/*@RequestMapping("/selectById")
	 public String selectById(@RequestParam(value="id")int id, Model model )throws Exception{
		    User user=userService.selectById(id);
			model.addAttribute("u", user);
			return "userInfo";
	 }  
	*/
	/*@RequestMapping("/selectByName")
	public ModelAndView selectByName(String name) {
		User user=userService.selectByName(name);
		ModelAndView mav = new ModelAndView("/userInfo");
		mav.addObject("u", user);
		return mav;
	}*/
	 
	@RequestMapping("/selectByName")
	 public String selectByName(@RequestParam(value="name")String name, Model model )throws Exception{
		    User user=userService.selectByName(name);
		    if(user!=null){
			model.addAttribute("u", user);
			return "userInfo";
		    }
			return "none";
	 } 
}
