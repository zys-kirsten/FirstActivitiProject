package zys.utils;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import zys.dao.IEmployeeDao;
import zys.dao.impl.EmployeeDaoImpl;
import zys.pojo.Employee;

public class test {
	@Test
	public void testDao(){
		IEmployeeDao employeeDao = new EmployeeDaoImpl();
		Employee employee = employeeDao.findEmployeeByName("小红");
		System.out.println(employee+"000000000000");
	}

}
