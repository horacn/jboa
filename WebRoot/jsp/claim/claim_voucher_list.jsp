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
   	function delVoucher(id){
   		if(!confirm('确定删除报单吗')) return;
   		
   		location.href = "voucher_deleteById.do?id="+id;
   	}
   	//改变页码
	function ChangePageIndex(pageIndex){
   		$("[name=pageIndex]").val(pageIndex);
		$("[name=queryForm]").submit();
	}   	
</script>
<div class="action  divaction">
	<div class="t">报销单列表</div>
	<div class="pages">
		<div class="forms">
			 <form action="voucher_list.do" name="queryForm" method="post">
	       	   <label>报销单状态</label>
	  		   <select name="status">
	  		   		<option ${status=='全部'?'selected':''}>全部</option>
	  		   		<c:if test="${sessionScope.employee.position.nameCn =='员工'}">
	  		   			<option ${status=='新创建'?'selected':''}>新创建</option>
	  		   		</c:if>
	  		   		<option ${status=='已提交'?'selected':''}>已提交</option>
	  		   		<option ${status=='待审批'?'selected':''}>待审批</option>
	  		   		<option ${status=='已打回'?'selected':''}>已打回</option>
	  		   		<option ${status=='已审批'?'selected':''}>已审批</option>
	  		   		<option ${status=='已付款'?'selected':''}>已付款</option>
	  		   		<option ${status=='已终止'?'selected':''}>已终止</option>
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
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>填报日期</td>
	        <td>填报人</td>
	        <td>总金额</td>
	        <td>状态</td>
	        <td>待处理人</td>
	        <td>操作</td>
	      </tr>
	      <c:forEach items="${pageBean.list}" var="claimVoucher" begin="0" varStatus="s">
	      <tr>
	        <td><a href="claimVoucher_getClaimVoucherById.action?claimVoucher.id=${claimVoucher.id}">${claimVoucher.id}</a></td>
	        <td><fmt:formatDate value="${claimVoucher.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	        <td>${claimVoucher.employeeByCreateSn.name}</td>
	        <td>${claimVoucher.totalAccount}</td>
	        <td>${claimVoucher.status}</td>
	        <td>${claimVoucher.employeeByNextDealSn.name}</td>
	        <td>
	        	<%--报销单创建人 --%>
	        	<c:if test="${claimVoucher.employeeByCreateSn.sn == sessionScope.employee.sn && sessionScope.employee.position.nameCn =='员工'}">
		        	<c:if  test="${claimVoucher.status=='新创建' || claimVoucher.status == '已打回'}">
		        		<a href="voucher_toEdit.do?id=${claimVoucher.id}" title="修改">
				        	<img src="${images}/edit.gif" width="16" height="16" /> 
				        </a>
				        <a href="javascript:delVoucher(${claimVoucher.id});" title="删除">
				        	<img src="${images}/delete.gif" width="16" height="16" />
				        </a>
				        <a href="voucher_submit.do?id=${claimVoucher.id}" title="提交" onclick="return confirm('确定提交报销单吗?');">
				        	<img src="${images}/save.gif" width="16" height="16"/>
				        </a> 
			        </c:if>
			        <c:if test="${claimVoucher.status!='新创建'}">
			        	 <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
			        </c:if>
	        	</c:if>
	        	<%--部门经理 --%>
		        <c:if test="${sessionScope.employee.position.nameCn =='部门经理'}">
		       		<c:if test="${claimVoucher.employeeByNextDealSn.sn == sessionScope.employee.sn && claimVoucher.status == '已提交'}">
				        <a href="voucher_toCheck.do?id=${claimVoucher.id}">
				         	<img src="${images}/sub.gif" width="16" height="16" title="审批"/>
				        </a>
				         <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
				   </c:if>
			       <c:if test="${(claimVoucher.employeeByNextDealSn.sn != sessionScope.employee.sn || claimVoucher.status != '已提交') && claimVoucher.status != '新创建'}">
			        	 <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
			      </c:if>
		        </c:if>
		        <%--总经理 --%>
		        <c:if test="${sessionScope.employee.position.nameCn =='总经理'}">
		       		<c:if test="${claimVoucher.employeeByNextDealSn.sn == sessionScope.employee.sn && claimVoucher.status == '待审批'}">
				        <a href="voucher_toCheck.do?id=${claimVoucher.id}">
				         	<img src="${images}/sub.gif" width="16" height="16" title="审批"/>
				        </a>
				         <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
				   </c:if>
			       <c:if test="${(claimVoucher.employeeByNextDealSn.sn != sessionScope.employee.sn || claimVoucher.status != '待审批') && claimVoucher.status != '新创建'}">
			        	 <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
			      </c:if>
		        </c:if>
		        <%--财务 --%>
		        <c:if test="${sessionScope.employee.position.nameCn =='财务'}">
		       		<c:if test="${claimVoucher.employeeByNextDealSn.sn == sessionScope.employee.sn && claimVoucher.status == '已审批'}">
				        <a href="voucher_toCheck.do?id=${claimVoucher.id}">
				         	<img src="${images}/sub.gif" width="16" height="16" title="审批"/>
				        </a>
				         <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
				   </c:if>
			       <c:if test="${(claimVoucher.employeeByNextDealSn.sn != sessionScope.employee.sn || claimVoucher.status != '已审批') && claimVoucher.status != '新创建'}">
			        	 <a href="voucher_getVoucherById.do?id=${claimVoucher.id}"  title="查询">
				        	<img src="${images}/search.gif" width="16" height="15" />
				        </a>
			      </c:if>
		        </c:if>
	        </td>
	      </tr>
	      </c:forEach>
	      <c:if test="${pageBean.list[0]!=null}">
	      	 <tr>
		        <td colspan="6" align="center">
			      	<%--
			      	<c:import url="rollPage.jsp" charEncoding="UTF-8">
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
	  		  		没有查询到符合要求的报销单。
	  		  	</td>
	  		  </tr>
	      </c:if>
	    </table>      
       </div>
     </div>