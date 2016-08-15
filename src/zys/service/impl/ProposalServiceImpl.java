package zys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zys.dao.IEmployeeDao;
import zys.dao.IProposalDao;
import zys.pojo.Employee;
import zys.pojo.Proposal;
import zys.service.IProposalService;

@Service
public class ProposalServiceImpl implements IProposalService {

	@Resource
	private IProposalDao proposalDao;

	@Resource
	private IEmployeeDao employeeDao;
	

	//查询自己的请假单
	@Override
	public List<Proposal> findProposalList(String name) {
		return proposalDao.findProposalList(name);
	}

	//保存请假单
	@Override
	public void saveProposal(Proposal proposal) {
		String name = proposal.getUser().getName();
		Employee employee = employeeDao.findEmployeeByName(name);
		proposal.setUser(employee);
		
		proposalDao.saveProposal(proposal);
	}

	@Override
	public Proposal findProposalById(Long id) {
		return proposalDao.findProposalById(id);
	}

	@Override
	public void deleteProposalById(Long id) {
		proposalDao.deleteProposalById(id);
		
	}

	@Override
	public Proposal findCommentByProposalById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
