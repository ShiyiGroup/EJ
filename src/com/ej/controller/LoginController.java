package com.ej.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ej.dao.TestDao;
import com.ej.models.Test;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private  TestDao testDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get(){
		return new ModelAndView("login/login");
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
