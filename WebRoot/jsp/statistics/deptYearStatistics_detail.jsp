<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%
String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>

<div class="action  divaction">
	<div class="t">年度统计详情</div>
	<div class="pages">
	     <s:form action="deptStatistics_createDetailExcel.action" name="queryForm">
	       		<label for="time">年份:</label>
	       		<s:property value="currYear"/>
	       		<s:hidden name="year" value="%{currYear}"/>
	       		<s:hidden name="departmentId" value="%{departmentId}"/>
		        <s:submit cssClass="submit_01" value="导出报表"/>
	     </s:form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>报销人</td>
	        <td>报销总额</td>
	        <td>年份</td>
	        <td>部门</td>
	      </tr>
	      <s:iterator value="deptDetailList" id="claimVoucher" begin="0" status="s">
	      <tr>
	        <td><s:property value="#claimVoucher.creator.sn"/></td>
	        <td><s:property value="#claimVoucher.creator.name"/></td>
	        <td>￥<s:property value="#claimVoucher.totalAccount"/></td>
	        <td><s:property value="currYear"/>年</td>
	        <td><s:property value="#claimVoucher.creator.sysDepartment.name"/></td>
	      </tr>
	      </s:iterator>
	      <tr>
	      	<td></td>
	      	<td bgcolor="yellow">总计</td>
	      	<td bgcolor="yellow">￥<s:property value="detailCount"/></td>
	      	<td></td>
	      	<td></td>
	      	<td></td>
	      </tr>
	    </table>        
       </div>
       <span style="display:none;"><iframe id="downloadIframe" src="" style="width:0px;height:0px;"></iframe></span>
	          <!--增加报销单 区域 结束-->
       </div>
       <div style=""><div align="left"> 
       	  <center><img src="<%=path%>/jsp/statistics/deptStatistics_createDetailChart.action?currYear=<%=request.getAttribute("currYear")%>&departmentId=<%=request.getAttribute("departmentId")%>"></center></div>
       <%-- <img src="<%=path%>/jsp/statistics/claimVoucherStatistics_createDetailChart.action?year=<%=request.getAttribute("year")%>&amp;selectMonth=<%=request.getAttribute("selectMonth")%>&amp;departmentId=<%=request.getAttribute("departmentId")%>"> --%>
       </div>
       
      </div>