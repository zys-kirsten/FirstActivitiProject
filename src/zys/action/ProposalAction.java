package zys.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zys.pojo.Proposal;
import zys.service.IProposalService;

@Controller
@SuppressWarnings("serial")
public class ProposalAction{

	
	@Resource
	private IProposalService proposalService;
	
	private void getProposalList(HttpServletRequest request){
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		System.out.println("session 中获得的登录用户名："+name);
		//查询自己的请假单
		List<Proposal> list = proposalService.findProposalList(name);
		session.setAttribute("Proposallist", list);
	}

	/**
	 * 请假管理首页显示
	 * @return
	 */
	@RequestMapping("/proposalAction_home")
	public String home(HttpServletRequest request){
		getProposalList(request);
		return "proposal/list";
	}
	
	/**
	 * 添加请假申请
	 * @return 
	 */
	@RequestMapping("/proposalAction_input")
	public String input(){
		return "proposal/input";
	}
	
	/**
	 * 保存/更新，请假申请
	 * 
	 * */
	@RequestMapping("/proposalAction_save")
	public String save(Proposal proposal,HttpServletRequest request) {
		//执行保存
		proposalService.saveProposal(proposal);
		getProposalList(request);
		return "proposal/list";
	}
	
}
