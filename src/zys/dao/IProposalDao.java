package zys.dao;

import java.util.List;

import zys.pojo.Proposal;


public interface IProposalDao {

	List<Proposal> findProposalList(String name);

	void saveProposal(Proposal proposal);

	Proposal findProposalById(Long id);

	void updateProposal(Proposal proposal);

	void deleteProposalById(Long id);


}
