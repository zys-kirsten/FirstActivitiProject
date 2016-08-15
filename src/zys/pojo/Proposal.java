package zys.pojo;

import java.util.Date;

/**
 * 请假单
 */
public class Proposal {
	private Long id;//主键ID
	private String content;// 提案内容
	private Date submitDate = new Date();// 请假时间
	private Employee user;// 请假人
	
	private Integer state=0;// 提案状态 0初始提交,1.开始审批,2为审批完成

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "LeaveBill [id=" + id + ", content=" + content + ", submitDate=" + submitDate
				+ ", user=" + user + ", state=" + state + "]";
	}
	
	
}
