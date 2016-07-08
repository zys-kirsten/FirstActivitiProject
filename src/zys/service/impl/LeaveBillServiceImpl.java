package zys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zys.dao.IEmployeeDao;
import zys.dao.ILeaveBillDao;
import zys.pojo.Employee;
import zys.pojo.LeaveBill;
import zys.service.ILeaveBillService;

@Service
public class LeaveBillServiceImpl implements ILeaveBillService {

	@Resource
	private ILeaveBillDao leaveBillDao;

	@Resource
	private IEmployeeDao employeeDao;
	

	//查询自己的请假单
	@Override
	public List<LeaveBill> findLeaveBillList(String name) {
		return leaveBillDao.findLeaveBillList(name);
	}

	//保存请假单
	@Override
	public void saveLeaveBill(LeaveBill leaveBill) {
		String name = leaveBill.getUser().getName();
		Employee employee = employeeDao.findEmployeeByName(name);
		leaveBill.setUser(employee);
		
		leaveBillDao.saveLeaveBill(leaveBill);
	}

	@Override
	public LeaveBill findLeaveBillById(Long id) {
		return leaveBillDao.findLeaveBillById(id);
	}

	@Override
	public void deleteLeaveBillById(Long id) {
		leaveBillDao.deleteLeaveBillById(id);
		
	}

	@Override
	public LeaveBill findCommentByLeaveBillById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
