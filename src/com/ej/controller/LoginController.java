package com.ej.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ej.dao.TestDao;
import com.ej.models.Test;
import com.ej.service.AlibabaSmsNumSendUtil;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private  TestDao testDao;
	@Autowired
	private AlibabaSmsNumSendUtil code;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get(){
		return new ModelAndView("login/login");
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/{code}")
	public void getCode(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable("code") String phone) throws IOException{
		String verification=code.SmsNumSend(phone);
		System.out.println(phone+":"+verification);
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.write(verification);
		writer.flush();
	}
	

	@RequestMapping(method=RequestMethod.POST)
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
	}
}
