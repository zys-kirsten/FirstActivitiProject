package zys.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zys.pojo.Employee;
import zys.pojo.LeaveBill;
import zys.service.IEmployeeService;
import zys.service.ILeaveBillService;

@Controller
@SuppressWarnings("serial")
public class LeaveBillAction{

	
	@Resource
	private ILeaveBillService leaveBillService;
	
	private void getLeaveBillList(HttpServletRequest request){
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		System.out.println("session 中获得的登录用户名："+name);
		//查询自己的请假单
		List<LeaveBill> list = leaveBillService.findLeaveBillList(name);
		session.setAttribute("leaveBilllist", list);
	}

	/**
	 * 请假管理首页显示
	 * @return
	 */
	@RequestMapping("/leaveBillAction_home")
	public String home(HttpServletRequest request){
		getLeaveBillList(request);
		return "leaveBill/list";
	}
	
	/**
	 * 添加请假申请
	 * @return 
	 */
	@RequestMapping("/leaveBillAction_input")
	public String input(){
		return "leaveBill/input";
	}
	
	/**
	 * 保存/更新，请假申请
	 * 
	 * */
	@RequestMapping("/leaveBillAction_save")
	public String save(LeaveBill leaveBill,HttpServletRequest request) {
		//执行保存
		leaveBillService.saveLeaveBill(leaveBill);
		getLeaveBillList(request);
		return "leaveBill/list";
	}
	
}
