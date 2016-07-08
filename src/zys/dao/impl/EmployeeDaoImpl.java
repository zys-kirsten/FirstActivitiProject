package zys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import zys.dao.IEmployeeDao;
import zys.pojo.Employee;


@Repository
public class EmployeeDaoImpl extends HibernateDaoSupport implements IEmployeeDao {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public Employee findEmployeeByName(String name) {
  
		String hql ="from Employee o where o.name=?";
		List<Employee> employees = this.getHibernateTemplate().find(hql, name);
		if(employees==null || employees.size() == 0)
			return null;
		return employees.get(0);
	}
	
	
}
