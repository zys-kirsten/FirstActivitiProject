package zys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zys.dao.IEmployeeDao;
import zys.pojo.Employee;
import zys.service.IEmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Resource
	private IEmployeeDao employeeDao;


	//使用用户名作为条件查询用户对象
	@Override
	public Employee findEmployeeByName(String name) {
		Employee employee = employeeDao.findEmployeeByName(name);
		return employee;
	}
	
	
}
