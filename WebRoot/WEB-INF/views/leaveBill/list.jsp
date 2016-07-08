<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假管理</title>
</head>
<body>
 	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">请假申请列表列表</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		        <td height="20" bgcolor="#FFFFFF" class="STYLE10" colspan="8"><div align="left">
					<a href="${pageContext.request.contextPath }/leaveBillAction_input.do">添加请假申请</a>
				</div></td>
		  </tr> 
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
		      <tr>
		        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假人</span></div></td>
		        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假天数</span></div></td>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假事由</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假备注</span></div></td>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假时间</span></div></td>
		        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假状态</span></div></td>
		        <td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		      </tr>
		      <c:forEach items="${sessionScope.leaveBilllist}" var="l">
		             <tr>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center">${l.id }</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${l.user.name}</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${l.days}</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${l.content}</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${l.remark}</div></td>
			      	<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${l.leaveDate }</div></td>
			        <td height="20" bgcolor="#FFFFFF" class="STYLE19">
			        	<div align="center">
			        	      <c:if test="${l.state ==0}">
			        	                       初始录入
			        	      </c:if>
			        	      <c:if test="${l.state ==1}">
			        	                       审核中
			        	      </c:if>
			        	       <c:if test="${l.state ==2}">
			        	                       审核完成
			        	      </c:if>
		            	</div>
		            </td>
			        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
			        	     <c:if test="${l.state ==0}">
			        	         <a href="#" >修改</a>
						         <a href="#" >删除</a>
						         <a href="${pageContext.request.contextPath }/workflowAction_startProcess.do?id=${l.id }" >申请请假</a>
			        	      </c:if>
			        	      <c:if test="${l.state ==1}">
			        	         <a href="${pageContext.request.contextPath }/workflowAction_viewHisComment.do?id=${l.id }" >查看审核记录</a>
			        	      </c:if>
			        	       <c:if test="${l.state ==2}">
			        	         <a href="#" >删除</a>
			        	    	 <a href="${pageContext.request.contextPath }/workflowAction_viewHisComment.do?id=${l.id }" >查看审核记录</a>
			        	      </c:if> 
			        	
			        	
					</div></td>
			    </tr> 
		       </c:forEach>
		    </table></td>
		  </tr>
	</table>
</body>
</html>