<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北大青鸟办公自动化管理系统</title>
		<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			$(function(){
				 //日期选择控件
			 	$("#startDate").click(function(){
					WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true, readOnly:true });
				});
				$("#endDate").click(function(){
					WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true, readOnly:true });
				});
			});
			function submitLeave(){
		   		if(!confirm('确定提交请假申请吗')) return;
		   		document.leaveForm.submit();
		   		
		   	}
			/**
				显示请假天数
			*/
			function showLeaveday(){
				if($("#startDate").val().length==0 || $("#endDate").val().length==0)	return;
				//结束时间  
				end_str = $("#endDate").val().replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。  
				var end_date = new Date(end_str);//将字符串转化为时间  
				//开始时间  
				sta_str = $("#startDate").val().replace(/-/g,"/");  
				var sta_date = new Date(sta_str);  
				var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
				if(num<=0){
					alert("结束时间必须大于开始时间！");
					$("#endDate").val("");
					$("#leaveday").val("");
					return;
				}
				var days = 0;
				if(num<=0.5){
					days = 0.5;
				}else{
					days = parseInt(Math.round(num));//转化为整天（小于零的话剧不用转了）
				}
				$("#leaveday").val(days);
			}
		</script>
	</head>
	<body>
	<div class="action divaction">
		<div class="t">请假申请</div>
		<div class="pages">
	<!--请假申请 区域 开始-->
	<form action="leave_add.do" name="leaveForm" method="post">
     <input type="hidden" name="employee.sn" value="${sessionScope.employee.sn}"/>
     <table class="leave">
       <tr>
         <td class="width1"><label for="name">姓名：</label></td>
         <td class="width2"><input name="name" value="${sessionScope.employee.name}" class="nwinput" readonly="readonly"/></td>
         <td class="width1"><label>部门：</label></td>
         <td class="width2">
            <select name="" class="nwselect">
             <option value="${sessionScope.employee.department.id}">${sessionScope.employee.department.name}</option>
           </select>
         </td>
       </tr>
        <tr>
         <td class="width1"><label for="time">开始时间：</label></td>
         <td class="width2"><input name="startTime" id="startDate" class="nwinput"onchange="showLeaveday();"/></td>
         <td class="width1"><label for="end-time">结束时间：</label></td>
         <td class="width2"><input name="endTime" id="endDate" class="nwinput" onchange="showLeaveday();"/></td>
       </tr>
        <tr>
         <td class="width1"><label for="size">请假天数：</label></td>
         <td class="width2"><input name="leaveday" id="leaveday" class="nwinput" readonly="readonly"/>(天)</td>
         <td class="width1"><label>休假类型：</label></td>
         <td class="width2">
          <select name="leavetype" class="nwselect">
	   			<option><%=Constants.LEAVE_YEAR %></option>
  		   		<option><%=Constants.LEAVE_THING%></option>
  		   		<option><%=Constants.LEAVE_ILLNESS%></option>
  		   		<option><%=Constants.LEAVE_MARRY%></option>
  		   		<option><%=Constants.LEAVE_LAY%></option>
  		   		<option><%=Constants.LEAVE_FUNERAL%></option>
  		   		<option><%=Constants.LEAVE_REST%></option>
  		   </select>
  		</td>
       </tr>
       <tr>
         <td class="width1"><label>请假事由：</label></td>
         <td colspan="3">
         	<textarea name="reason" class="textarea"></textarea>
         </td>
       </tr>
     </table>
      	<div class="forms ">
          <p class="leave">
           <label>下一审批人：</label>
           <select name="nextDeal.sn" class="nwselect" >
             <option value="${manager.sn}">${manager.name}</option>
           </select>
          </p>
          <p class="marg">
           <input name="button" type="button"  value="提交流程" class="submit_01" onclick="submitLeave()"/>
           <input onclick="history.back();" type="button"  value="取消"  class="submit_01"/>
          </p>
     	</div>
     	</form>
     <!--请假申请 区域 结束-->
     </div>
     </div>
	</body>
</html>
