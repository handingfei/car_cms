package com.bw.car.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.car.domain.Driver;
import com.bw.car.service.DriverService;

@Controller
public class PassportController {

	@Resource
	private DriverService driverService;
	
	/**
	 * 
	 * @Title: login
	 * @Description:去 登录
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public  String login() {
		return "login";
	}
	
	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @param
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(Model model,Driver driver,
			String phone, String password,HttpSession session) {
		
		try {
			Driver d = driverService.select(phone, password);
			session.setAttribute("driver", d);
			return "redirect:/driver/cars";
		} catch (RuntimeException e) {
			e.printStackTrace();
			model.addAttribute("message",e.getMessage() );
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统异常");
		}
		
		return "login";
	}
	
	/**
	 * 
	 * @Title: reg
	 * @Description: 去注册
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		return "reg";
	}
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 注册
	 * @param model
	 * @param driver
	 * @param redirectAttributes
	 * @return
	 * @return: boolean
	 */
	@PostMapping("reg")
	@ResponseBody
	public boolean reg(Model model,Driver driver) {
		
		return driverService.insertSelective(driver)>0;
	}
	
	
}
