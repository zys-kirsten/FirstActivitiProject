package zys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import zys.dao.ILeaveBillDao;
import zys.pojo.LeaveBill;

@Repository
public class LeaveBillDaoImpl extends HibernateDaoSupport implements ILeaveBillDao {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public List<LeaveBill> findLeaveBillList(String name) {
	
		String hql = "from LeaveBill o where o.user.name=?";
		List<LeaveBill> list = this.getHibernateTemplate().find(hql, name);
		if(list == null || list.size() == 0)
			return null;
		return list;
	}

	//保存请假单
	@Override
	public void saveLeaveBill(LeaveBill leaveBill) {
		this.getHibernateTemplate().save(leaveBill);
	}

	@Override
	public LeaveBill findLeaveBillById(Long id) {
		return this.getHibernateTemplate().get(LeaveBill.class, id);
	}

	@Override
	public void updateLeaveBill(LeaveBill leaveBill) {
		this.getHibernateTemplate().update(leaveBill);
		
	}

	@Override
	public void deleteLeaveBillById(Long id) {
		  LeaveBill leaveBill = this.findLeaveBillById(id);
		  this.getHibernateTemplate().delete(leaveBill);
	}

	
}
