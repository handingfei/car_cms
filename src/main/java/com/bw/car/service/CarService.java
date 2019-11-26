package com.bw.car.service;

import java.util.List;

import com.bw.car.domain.Car;
import com.bw.car.domain.Driver;
import com.bw.car.domain.DriverType;

public interface CarService {

	/**
	 * 
	 * @Title: insert 
	 * @Description: 发布车辆(管理员增加车辆)
	 * @param record
	 * @return
	 * @return: int
	 */
	int insert(Car record);

	List<Car> selects();

	List<DriverType> selectTypes();
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据驾驶证类型 查询所有的汽车.
	 * @return
	 * @return: List<Car>
	 */
	List<Car> selectCarsByCode(Driver driver);

}
