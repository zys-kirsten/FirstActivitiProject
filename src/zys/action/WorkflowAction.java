package zys.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import zys.form.UploadForm;
import zys.form.WorkflowBean;
import zys.pojo.Employee;
import zys.pojo.LeaveBill;
import zys.service.ILeaveBillService;
import zys.service.IWorkflowService;
import zys.utils.SessionUtil;



@SuppressWarnings("serial")
@Controller
public class WorkflowAction{

	@Resource
	private IWorkflowService workflowService;
	@Resource
	private ILeaveBillService leaveBillService;
	
	private void getDeploymentList(HttpServletRequest request){
		HttpSession session = request.getSession();
		//查询部署对象信息
		List<Deployment> depList= workflowService.findDeployment();
		
	    //查询流程定义信息
		List<ProcessDefinition> pdList = workflowService.findProcessDefinitionList();
		
		//放置上下文对象中
	   session.setAttribute("depList", depList);
	   session.setAttribute("pdList", pdList);
	}

	private void getTaskList(HttpServletRequest request){
		//从session中获取当前用户名
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		System.out.println("当前执行人为："+name);
		List<Task> list = workflowService.findTaskListByName(name);
		session.setAttribute("taskList", list);
	}
	/**
	 * 部署管理首页显示
	 * @return
	 */
	@RequestMapping("/workflowAction_deployHome")
	public String deployHome(HttpServletRequest request){
		getDeploymentList(request);
		return "workflow/workflow";
	}
	
	/**
	 * 发布流程
	 * @return
	 */
	@RequestMapping("/workflowAction_newdeploy")
	public String newdeploy(HttpServletRequest request){
	
	    workflowService.saveNewDeploye();	
		//查询流程定义
		getDeploymentList(request);
		return "workflow/workflow";
	}
	
	/**
	 * 删除部署信息
	 */
	@RequestMapping("/workflowAction_delDeployment")
	public String delDeployment(WorkflowBean workflowBean,HttpServletRequest request){
		//从页面获取部署对象id
		String deploymentId = workflowBean.getDeploymentId();
		//删除该部署
		workflowService.deleteProcessDefinitionByDeploymentId(deploymentId);
		
		getDeploymentList(request);
		return "workflow/workflow";
	}
	
	/**
	 * 查看流程图
	 * @throws Exception 
	 */
	@RequestMapping("/workflowAction_viewImage")
	public String viewImage(WorkflowBean workflowBean,HttpServletResponse response) throws Exception{
		
		System.out.println(workflowBean);
		//1.获取页面参数：部署ID和资源文件名称
		String deploymentId = workflowBean.getDeploymentId();
		String imageName = workflowBean.getImageName();
		//2.获取资源文件的输入流
		InputStream in = workflowService.findImageInputStream(deploymentId,imageName);
		//3.获取response输出流
		OutputStream out = response.getOutputStream();
		
		//4.向页面输出图片
		for(int b=-1;(b=in.read())!=-1;){
			out.write(b);
		}
		out.close();
		in.close();
		
 		return null;
	}
	
	// 启动流程
	@RequestMapping("/workflowAction_startProcess")
	public String startProcess(WorkflowBean workflowBean,HttpServletRequest request){
		//更新状态，启动流程实例，让启动的流程实例关联业务
		workflowService.saveStartProcess(workflowBean);
		getTaskList(request);
		return "workflow/task";
	}
	
	
	
	/**
	 * 任务管理首页显示
	 * @return
	 */
	@RequestMapping("/workflowAction_listTask")
	public String listTask(HttpServletRequest request){
		getTaskList(request);
		return "workflow/task";
		
	}
	
	// 打开任务表单,准备表单数据
	@RequestMapping("/workflowAction_audit")
	public String audit(WorkflowBean workflowBean,HttpServletRequest request){
		HttpSession session = request.getSession();
		//获取任务id
		String taskId = workflowBean.getTaskId();
		/**一.使用任务ID查找请假单ID，获取请假单信息*/
		LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
		session.setAttribute("leaveBill", leaveBill);
		session.setAttribute("taskId", taskId);
		System.out.println(leaveBill);
		
		/**二.已知任务ID，查询ProcessDefinitionEntity对象，从而获取当前任务完成后的连线名称，并放到list集合中*/
		List<String> outcomeList = workflowService.findOutComeListByTaskId(taskId);
		session.setAttribute("outcomeList", outcomeList);
		
		/**三.查询所有历史审核人的审核信息，帮助当前人完成审核返回List<Comment>*/
		List<Comment> commentList = workflowService.findCommentByTaskId(taskId);
		session.setAttribute("commentList", commentList);
		return "workflow/taskForm";
	}
	
	/**
	 * 提交任务
	 */
	@RequestMapping("/workflowAction_submitTask")
	public String submitTask(WorkflowBean workflowBean,HttpServletRequest request){
		workflowService.saveSubmitTask(workflowBean);
		getTaskList(request);
		return "workflow/task";
	}
	
	
	// 查看历史的批注信息
	@RequestMapping("/workflowAction_viewHisComment")
	public String viewHisComment(WorkflowBean workflowBean){
		//获取请假单id
	Long id = workflowBean.getId();
	//1.使用id查询请假单对象，回显页面
	LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
    SessionUtil.getMySession().setAttribute("leaveBill", leaveBill);;
	//2.使用请假单id，查询历史的批注信息
	List<Comment> commentList = workflowService.findCommentLeaveBillById(id);
	SessionUtil.getMySession().setAttribute("commentList", commentList);
		return "workflow/taskFormHis";
	}

}
