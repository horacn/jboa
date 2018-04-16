<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>北大青鸟办公自动化管理系统</title>
	<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form id="myForm" name="myForm" method="post" >
    <div class="action  divaction">	
    	<div class="t">查看报销单</div>
   		<div class="pages">
        	<!--增加报销单 区域 开始-->
                <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
                  <caption>基本信息</caption>
                  <tr>
                  	<td >编&nbsp;&nbsp;号：${claimVoucher.id}</td>
                    <td>填&nbsp;写&nbsp;人：${claimVoucher.employeeByCreateSn.name}</td>
                    <td>部&nbsp;&nbsp;门：${claimVoucher.employeeByCreateSn.department.name}</td>
                    <td>职&nbsp;&nbsp;&nbsp;&nbsp;位：${claimVoucher.employeeByCreateSn.position.nameCn}</td>
                  </tr>
                  <tr>
                    <td>总金额：${claimVoucher.totalAccount}</td>
                    <td>填报时间：<fmt:formatDate value="${claimVoucher.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                    <td>状态：${claimVoucher.status}</td>
                    <td>待处理人：${claimVoucher.employeeByNextDealSn.name}</td>
                  </tr>
                  <tr>
                  	<td colspan="4">事&nbsp;&nbsp;由：${claimVoucher.event }</td>
                  </tr>
                  <tr>
                  	<td colspan="4"><p>-------------------------------------------------------------------------------</p></td>
                  </tr>
                </table>
         		<p>&nbsp;</p>
                <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
                  <tr>
                    <td>项目类别</td>
                    <td>项目金额</td>
                    <td>费用说明</td>
                  </tr>
                <c:forEach items="${claimVoucher.voucherDetails}" var="claimDetail" begin="0" varStatus="s">
					<tr>
						<td>${claimDetail.item}</td>
						<td>￥${claimDetail.account}</td>
						<td>${claimDetail.description}</td>
					</tr>
				</c:forEach>
	      </table>
	      <p>&nbsp;</p>
	      <p>-------------------------------------------------------------------------------</p>
	      
	      <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
	      	<c:forEach items="${claimVoucher.results}" var="checkResult" begin="0" varStatus="s">
	         <tr>
	           <td width="27%">${checkResult.checkEmployee.name}(${checkResult.checkEmployee.position.nameCn})</td>
	           <td width="20%"><fmt:formatDate value="${checkResult.checkTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	           <td width="38%">审核：<span class="red"><strong>${checkResult.result}</strong></span></td>
	         </tr>
	         <tr>
	           <td>审核意见：<strong>${checkResult.comm}</strong></td>
	           <td>&nbsp;</td>
	           <td>&nbsp;</td>
	         </tr>
	         <tr>
	         	<td colspan="3">
	         		<p>-------------------------------------------------------------------------------</p>
	           	</td>
	         </tr>
	         </c:forEach>
	    </table>
	    <p>&nbsp;</p>
	    <p>&nbsp;</p>
	    <p><input type="button" value="返回" onclick="window.history.go(-1)" class="submit_01"/></p>       
		<!--增加报销单 区域 结束-->
        </div>
    </div>
</form>
</body>