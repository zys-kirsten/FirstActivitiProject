package zys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import zys.dao.IProposalDao;
import zys.pojo.Proposal;

@Repository
public class ProposalDaoImpl extends HibernateDaoSupport implements IProposalDao {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);	
	}
	
	@Override
	public List<Proposal> findProposalList(String name) {
	
		String hql = "from Proposal o where o.user.name=?";
		List<Proposal> list = this.getHibernateTemplate().find(hql, name);
		if(list == null || list.size() == 0)
			return null;
		return list;
	}

	//保存请假单
	@Override
	public void saveProposal(Proposal proposal) {
		this.getHibernateTemplate().save(proposal);
	}

	@Override
	public Proposal findProposalById(Long id) {
		return this.getHibernateTemplate().get(Proposal.class, id);
	}

	@Override
	public void updateProposal(Proposal proposal) {
		this.getHibernateTemplate().update(proposal);
		
	}

	@Override
	public void deleteProposalById(Long id) {
		Proposal proposal = this.findProposalById(id);
		this.getHibernateTemplate().delete(proposal);
	}

	
}
