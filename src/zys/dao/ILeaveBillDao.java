package zys.dao;

import java.util.List;

import zys.pojo.LeaveBill;


public interface ILeaveBillDao {

	List<LeaveBill> findLeaveBillList(String name);

	void saveLeaveBill(LeaveBill leaveBill);

	LeaveBill findLeaveBillById(Long id);

	void updateLeaveBill(LeaveBill leaveBill);

	void deleteLeaveBillById(Long id);


}
