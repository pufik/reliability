package ua.edu.lp.reliability.web.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		Calendar calendar1  = Calendar.getInstance();
		calendar1.setTime(new Date());
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		calendar1.compareTo(calendar2);
		
		return "hello";
	}
}
