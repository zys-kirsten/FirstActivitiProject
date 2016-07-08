package zys.service;

import java.util.List;

import zys.pojo.LeaveBill;


public interface ILeaveBillService {

	List<LeaveBill> findLeaveBillList(String name);

	void saveLeaveBill(LeaveBill leaveBill);

	LeaveBill findLeaveBillById(Long id);

	void deleteLeaveBillById(Long id);

	LeaveBill findCommentByLeaveBillById(Long id);

}
