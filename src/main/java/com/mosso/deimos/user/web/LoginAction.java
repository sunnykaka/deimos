package com.mosso.deimos.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginAction {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage() {
		return "/login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerPage() {
		return "/register";
	}
}
