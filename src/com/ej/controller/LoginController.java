package com.ej.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ej.dao.UserDao;
import com.ej.models.NewAccount;
import com.ej.models.User;
import com.ej.service.AlibabaSmsNumSendUtil;
import com.google.gson.Gson;

@Controller
public class LoginController {
	
	@Autowired
	private  UserDao userDao;
	@Autowired
	private AlibabaSmsNumSendUtil code;
	
	
	@RequestMapping(method=RequestMethod.GET,value="/test")
	public void test(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.write("hello");
		writer.flush();
	}
	
	//手机登录功能
		@RequestMapping(method=RequestMethod.GET,value="/login/{phone}/{password}")
		public void login(HttpSession session,HttpServletRequest request,
				HttpServletResponse response,@PathVariable("phone") String phone,@PathVariable("password") String password) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			User user=userDao.findByPhone(phone);
			System.out.println(user.getPassword());
			String corPassword=user.getPassword();
			if (user!=null&& corPassword.equals(password)) {
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				writer.write("登录成功");
				writer.flush();
			}else{
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				writer.write("用户名或密码不正确");
				writer.flush();
			}
			
		
		
		}
	
	
	
	//手机验证码功能--注册
	@RequestMapping(method=RequestMethod.GET,value="/code/{code}")
	public void getCode(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable("code") String phone) throws IOException{
		String verification=code.SmsNumSend(phone);
		System.out.println(phone+":"+verification);
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.write(verification);
		writer.flush();
	}
	
	//手机注册
		@RequestMapping(method=RequestMethod.GET,value="/sign/{data}")
		public void sign(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable("data") String data) throws IOException, JSONException{
			response.setContentType("text/html;charset=utf-8");
			System.out.println(data);
			
			JSONObject object=new JSONObject(data);
			String input_phone=object.getString("Phone");
			String input_username=object.getString("Username");
			String input_password=object.getString("Password");
			
	    	System.out.println(input_phone+input_username+input_password);   
			
			
			User user=new User();
			user.setPhone(input_phone);
			user.setUsername(input_username);
			user.setPassword(input_password);
			System.out.println(user.getUsername());
			userDao.add(user);
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			writer.write("注册成功");
			writer.flush();
		}
	

/*	@RequestMapping(method=RequestMethod.POST)
	public void post(HttpSession session,HttpServletRequest req,
			@RequestParam String userName,@RequestParam String password
			 ){
		System.out.println(userName);
		System.out.println(password);
			Test user=testDao.findByName(userName);
			System.out.println("===============");
			List<Test> lists=testDao.findAll();
			for (Test test : lists) {
				System.out.println(test.getUsername());
			}
		
		if(user!=null && userName.equals(user.getUsername())&&password.equals(user.getPassword())){
			session.setAttribute("userName", userName);
					
			System.out.println("succes");	
		}		
	}*/
}
