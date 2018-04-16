<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%
String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
</script>

<div class="action  divaction">
	<div class="t">年度统计详情</div>
	<div class="pages">
		<s:form action="compYearStatistics_getDetailExcel.action" name="queryForm">
	       		<label for="time">年份:</label>
	       		<s:property value="currYear"/>
	       		<s:hidden name="currYear" value="%{currYear}"/>
		        <s:submit cssClass="submit_01" value="导出报表"/>
	     </s:form>
	     
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
		  <tr class="even">
	        <td>部门编号</td>
	        <td>部门</td>
	        <td>报销总额</td>
	        <td>年份</td>
	      </tr>
	      <s:iterator value="compYearDetail" id="deptClaimVoucher" begin="0" status="s">
	      <tr>
	        <td><s:property value="#deptClaimVoucher.department.id"/></td>
	        <td><s:property value="#deptClaimVoucher.department.name"/></td>
	        <td>￥<s:property value="#deptClaimVoucher.totalCount"/></td>
	        <td><s:property value="#deptClaimVoucher.year"/>年</td>
	        <%-- <td><s:property value="#deptClaimVoucher.month"/>月</td> --%>
	        
	      </tr>
	      </s:iterator>
	      <tr>
	      	<td></td>
	      	<td bgcolor="yellow">总计</td>
	      	<td bgcolor="yellow">￥<s:property value="totalCount"/></td>
	      	<td></td>
	      	<td></td>
	      	<td></td>
	      </tr>		
		
	      
	    </table>        
       </div>
       <span style="display:none;"><iframe id="downloadIframe" src="" style="width:0px;height:0px;"></iframe></span>
	          <!--增加报销单 区域 结束-->
       </div>
       <div style="">
       	  <center><img src="<%=path%>/jsp/statistics/compYearStatistics_getDetailChart.action?currYear=<%=request.getAttribute("currYear")%>"></center> 
   
       </div>
       
      </div>