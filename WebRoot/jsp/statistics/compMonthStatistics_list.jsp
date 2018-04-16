<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<div class="action  divaction">
	<div class="t">月度统计列表</div>
	<div class="pages">
	     <s:form action="compMonStatistics_getList.action" name="queryForm">
	     		<label for="time">年份</label>
	     		<s:select name="year" list="#{2013:'2013',2014:'2014',2015:'2015',2016:'2016',2017:'2017'}" 
	  			listKey="key" listValue="value" theme="simple"></s:select>
	       		<label for="time">开始月份</label>
	       		<s:select name="startMonth" list="#{1:'1月份',2:'2月份',3:'3月份',4:'4月份',5:'5月份',6:'6月份',7:'7月份',8:'8月份',9:'9月份',10:'10月份',11:'11月份',12:'12月份' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
	       		<label for="end-time">结束月份</label>
	       		<s:select name="endMonth" list="#{1:'1月份',2:'2月份',3:'3月份',4:'4月份',5:'5月份',6:'6月份',7:'7月份',8:'8月份',9:'9月份',10:'10月份',11:'11月份',12:'12月份' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
		 	    <!-- <input type="hidden" name="year" value="2013"/> -->
		        <s:submit cssClass="submit_01" value="查询"/>
	     </s:form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>总计</td>
	        <td>年份</td>
	        <td>月份</td>
	        <!-- <td>部门</td> -->
	        <td>操作</td>
	      </tr>
	      <s:iterator value="compMonthList" id="claimVoucherStatistics" begin="0" status="s">
	      <tr>
	        <td><s:property value="#claimVoucherStatistics.id"/></td>
	        <td>￥<s:property value="#claimVoucherStatistics.totalCount"/></td>
	        <td><s:property value="#claimVoucherStatistics.year"/>年</td>
	        <td><s:property value="#claimVoucherStatistics.month"/>月</td>
	        <%-- <td><s:property value="#claimVoucherStatistics.department.name"/></td> --%>
	        <td>
	        <%-- <a href="claimVoucher_getClaimVoucherById.action?claimVoucher.id=<s:property value="#claimVoucher.id"/>">
	        	<img src="${ images}/search.gif" width="16" height="15" />
	        </a> --%>
	        <a href="compMonStatistics_getDetail.action?year=<s:property value="#claimVoucherStatistics.year"/>&currMonth=<s:property value="#claimVoucherStatistics.month"/>">
	        <!-- <a href="claimVoucherStatistics_getDeptVoucherDetailByMonth.action?year=2013&selectMonth=7&departmentId=2"> -->
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </s:iterator>
	      <%-- <tr>
	        <td colspan="6" align="center">
		      	<c:import url="../claim/rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalRecordCount" value="${pageSupport.totalCount}"/>
				<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
				<c:param name="currentPageNo" value="${pageSupport.currPageNo}"/>
  			</c:import> 
  			</td>
	      </tr> --%>
	    </table>        
       </div>
      </div>