package zys.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import zys.dao.IEmployeeDao;
import zys.dao.impl.EmployeeDaoImpl;
import zys.pojo.Employee;
import zys.service.IEmployeeService;
import zys.service.impl.EmployeeServiceImpl;

/**
 * 员工经理任务分配
 *
 */
//用类实现办理人必须实现TaskListener接口
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
	
		//下面的做法会报错：懒加载异常（页面只有employee而没有他对应的manager）
//		//1.从session中获取当前用户
//		Employee employee = SessionContext.get();
//		 //2.设置个人任务的办理人
//		delegateTask.setAssignee(employee.getManager().getName());
		
		//正确做法：重新查询当前用户，再获取当前用户对应的领导
		
		String name = (String) SessionUtil.getMySession().getAttribute("name");
		//从web中获取spring容器（该容器在web启动的时候就创建了，所以不用再次加载，只需直接获取）
		WebApplicationContext wac = RequestContextUtils.getWebApplicationContext(SessionUtil.getRequest());  
		IEmployeeService employeeService = (IEmployeeService) wac.getBean("employeeService");
		
		Employee emp = employeeService.findEmployeeByName(name);
		System.out.println("指定的下一个任务办理人是："+emp.getManager().getName());
		delegateTask.setAssignee(emp.getManager().getName());
	}
	
}
