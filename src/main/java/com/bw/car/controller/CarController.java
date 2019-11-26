package com.bw.car.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bw.car.domain.Car;
import com.bw.car.domain.DriverType;
import com.bw.car.service.CarService;

@Controller
public class CarController {

	@Resource
	private CarService carService;
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 去发布车辆
	 * @return
	 * @return: String
	 */
	@GetMapping("add")
	public String add() {
		return "car_add";
	}
	
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 发布车辆
	 * @return
	 * @return: String
	 */
	@RequestMapping("add")
	@ResponseBody
	public boolean add(HttpServletRequest request,Car car,
			MultipartFile file) {
		
		if (!file.isEmpty()) {
			//文件上传路径
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			//为了防止重名文件
			String oldFilename = file.getOriginalFilename();
			//a.jpg
			String newFilename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));
			File f = new File(path,newFilename);
			//写入硬盘
			try {
				file.transferTo(f);
				
				car.setPicUrl(newFilename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		car.setCreated(new Date()); //发布时间
		return carService.insert(car)>0;
	}
	
	/**
	 * 
	 * @Title: cars 
	 * @Description:查看已发布的车辆
	 * @return
	 * @return: String
	 */
	@GetMapping(value = {"","/","index"})
	public String car(Model m) {
		
		List<Car> cars = carService.selects();
		m.addAttribute("cars", cars);
		return "index";
	}
	

	/**
	 * 
	 * @Title: selectTypes 
	 * @Description: 查询所有的驾驶证类型
	 * @return
	 * @return: List<DriverType>
	 */
	@GetMapping("selectTypes")
	@ResponseBody
	public List<DriverType> selectTypes(){
		return carService.selectTypes();
	}
	
	
}
