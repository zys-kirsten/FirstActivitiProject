package zys.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zys.utils.SessionUtil;


@Controller
public class LoginAction {

	@RequestMapping("/login")
	public String login(String name,HttpServletRequest request){

		System.out.println("登录用户是："+name);
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		return "main";
	}
	
	/**
	 * 标题
	 * @return
	 */
	@RequestMapping("/top")
	public String top() {
		return "top";
	}
	
	
	/**
	 * 左侧菜单
	 * @return
	 */
	@RequestMapping("/left")
	public String left() {
		return "left";
	}
	
	/**
	 * 主页显示
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	//退出系统
	@RequestMapping("/loginAction_logout")
	public String logout(){
		//清空session
		SessionUtil.getMySession().setAttribute("name",null);
		return "../../login";
	}
}
