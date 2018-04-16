<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script>
	$(function(){
			 //日期选择控件
		 	$("#startDate").click(function(){
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true, readOnly:true });
			});
			$("#endDate").click(function(){
				WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true, readOnly:true });
			});
		});
	//改变页码
	function ChangePageIndex(pageIndex){
   		$("[name=pageIndex]").val(pageIndex);
		$("[name=queryForm]").submit();
	} 
</script>
<div class="action  divaction">
	<div class="t">请假列表</div>
	<div class="pages">
		<div class="forms">
		<form action="leave_list.do" name="queryForm" method="post">
		   <label>状态</label>	
		   <select name="status">
		   		<option ${status=='全部'?'selected':''}>全部</option>
		   		<option ${status=='待审批'?'selected':''}>待审批</option>
		   		<option ${status=='已审批'?'selected':''}>已审批</option>
		   		<option ${status=='已打回'?'selected':''}>已打回</option>
		   </select>
		   <label>请假类型</label>	
		   <select name="leavetype">
	   			<option ${leavetype=='全部'?'selected':''}>全部</option>
	   			<option ${leavetype=='年假'?'selected':''}>年假</option>
  		   		<option ${leavetype=='事假'?'selected':''}>事假</option>
  		   		<option ${leavetype=='病假'?'selected':''}>病假</option>
  		   		<option ${leavetype=='婚假'?'selected':''}>婚假</option>
  		   		<option ${leavetype=='产假'?'selected':''}>产假</option>
  		   		<option ${leavetype=='丧假'?'selected':''}>丧假</option>
  		   		<option ${leavetype=='休假'?'selected':''}>休假</option>
  		   </select>
	       <label for="time">开始时间</label>
	       <input name="beginDate" id="startDate" class="nwinput" value="${beginDate }" readonly="readonly"/>
	       <label for="end-time">结束时间</label>
	       <input name="endDate" id="endDate" class="nwinput" value="${endDate }" readonly="readonly"/>
	       <input type="hidden" name="pageIndex" value="1"/>
		   <input type="hidden" name="pageSize" value="5"/>
		   <input type="submit" class="submit_01" value="查询"/>
	     </form>
	     </div>
	    <!--请假 区域 开始-->
		<table width="93%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>名称</td>
	        <td>发起时间</td>
	        <td>审批时间</td>
	        <td>审批意见</td>
	        <td>审批状态</td>
	        <td>操作</td>
	      </tr>
	      <c:forEach items="${pageBean.list}" var="leave" begin="0" varStatus="s">
	      <tr>
	        <td><a href="leave_getLeaveById.action?leave.id=${leave.id}">${leave.id}</a></td>
	        <td>${leave.employee.name}请假 ${leave.leaveday}天</td>
	        <td><fmt:formatDate value="${leave.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	        <td><fmt:formatDate value="${leave.modifytime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	        <td>${leave.approveOpinion}</td>
	        <td>${leave.status}</td>
	        <td>
	       	 <a href="leave_getLeaveById.do?id=${leave.id}"><img src="${images}/search.gif" width="16" height="15" /></a>
	       	  <c:if test="${leave.nextDeal.name == sessionScope.employee.name}">
		        <c:if test="${leave.status == '待审批'}">
	       	 		<a href="leave_toCheck.do?id=${leave.id}">
	       	 		<img src="${images}/sub.gif" width="16" height="16" /></a> 
	       	 	</c:if>
	       	 </c:if>
	        </td>
	      </tr>
	      </c:forEach>
	      <c:if test="${pageBean.list[0]!=null}">
		      <tr>
		        <td colspan="7" align="center">
			      	<%--<c:import url="../common/rollPage.jsp" charEncoding="UTF-8">
						<c:param name="formName" value="document.forms[0]"/>
						<c:param name="totalRecordCount" value="${pageSupport.totalCount}"/>
						<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
						<c:param name="currentPageNo" value="${pageSupport.currPageNo}"/>
		  			</c:import> 
	  		  		--%>
	  		  		${pageBean.pagerString }
	  		  	</td>
	  		  </tr>
  		  </c:if>
  		  <c:if test="${pageBean.list[0]==null}">
	      	 <tr>
		        <td colspan="6" align="center">
	  		  		没有查询到符合要求的请假信息。
	  		  	</td>
	  		  </tr>
	      </c:if>
	    </table>        
	    <!--请假 区域 结束-->
       </div>
      </div>