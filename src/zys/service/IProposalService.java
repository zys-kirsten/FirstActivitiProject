package zys.service;

import java.util.List;

import zys.pojo.Proposal;


public interface IProposalService {

	List<Proposal> findProposalList(String name);

	void saveProposal(Proposal proposal);

	Proposal findProposalById(Long id);

	void deleteProposalById(Long id);

	Proposal findCommentByProposalById(Long id);

}
