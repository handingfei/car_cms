package com.bw.car.dao;

import java.util.List;

import com.bw.car.domain.Car;
import com.bw.car.domain.DriverType;

public interface CarMapper {

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

	List<Car> selectCarsByCode(String[] code);

}
