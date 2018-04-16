<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北大青鸟办公自动化管理系统</title>
		<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<script type="text/javascript">
			$(function(){				
			//表单提交校验
		
			//$("#myTable tr").eq(0).hide();	
			$("form[name='claimForm']").submit(function(){
				//判断是否加入了问题 
				if($("#rowNumber").val()<1){
					//$.messager.defaults={ok:"确定"};$.messager.alert("提示信息",$("#rowNumber").val());
					$("#notice").text("* 添加报销单的明细，至少一条 ！");
					return false;
				}	
				$("#notice").text("*");
				for(var s=0;s<$("#rowNumber").val();s++){
					$("#account"+s).next(".notice").text("*");
					$("#desc"+s).next(".notice").text("*");
					if(isEmpty($("#account"+s).val())){
						$("#account"+s).next(".notice").text("* 金额不能为空  ！");
						return false;
					}		
					if(isEmpty($("#desc"+s).val())){
						$("#desc"+s).next(".notice").text("* 金额不能为空  ！");
						return false;
					}		
								
				}
				$(".buttons").hide();
				return true;
			});	
			$("#AddRow").click(function(){		
				var tr=$("#myTable tr").eq(0).clone();
				++i;
				var j=i-1;
				var item = $("#item").val();
				var account = $("#account").val();
				totalAccount=parseFloat(totalAccount)+parseFloat(account);
				var desc = $("#desc").val();
				tr.find("td").get(0).innerHTML="<input  name=item id=item"+j+" type=hidden value="+item+" />"+item;
				tr.find("td").get(1).innerHTML="<input  name=account id=account"+j+"  type=hidden value="+account+" />"+account;
				tr.find("td").get(2).innerHTML="<input  name=description  id=desc"+j+" type=hidden value="+desc+" />"+desc;		
				tr.find("td").get(3).innerHTML="<img src=${images}/delete.gif width=16 height=16 id=DelRow"+j+" onclick=delRow("+j+") />";
				tr.show();
				tr.appendTo("#myTable");
				//传递一共添加多少问题 
				rowNumber=i;
				$("#account").attr("value","");
				$("#desc").attr("value","");
				$("#totalAccount").attr("value",totalAccount);
		
			});	
			
		});
		var voucherDetails_size = ${rowNumber};
		var i=parseInt(voucherDetails_size);
		
		var rowNumber=parseInt(voucherDetails_size);
		var totalAccount = ${claimVoucher.totalAccount};
		function delRow(id){	
			var account = $("#account"+id).val();
			$("#myTable tr").eq(id+1).remove();
			var rowNumber=$("#rowNumber").val();
				for(var s=id+1;s<rowNumber;s++){
				$("#item"+s).attr("name","detailList["+(s-1)+"].item");
				$("#item"+s).attr("id","item"+(s-1));
				$("#account"+s).attr("name","detailList["+(s-1)+"].account");
				$("#account"+s).attr("id","account"+(s-1));
				$("#desc"+s).attr("name","detailList["+(s-1)+"].desc");
				$("#desc"+s).attr("id","desc"+(s-1));		
				var js="delRow("+(s-1)+");";
				var newclick=eval("(false||function (){"+js+"});");
				$("#DelRow"+s).unbind('click').removeAttr('onclick').click(newclick);
				$("#DelRow"+s).attr("id","DelRow"+(s-1));					
				}
			$("#rowNumber").attr("value",rowNumber-1);
			--i;
			totalAccount=parseFloat(totalAccount)-parseFloat(account);
			$("#totalAccount").attr("value",totalAccount);
		}
		function submitClaimVoucher(action){
	   		if(!confirm("确定"+action+"报销单吗")) return;
	   		if (action == '保存'){
	   			//document.claimForm.status.value = "新创建";
	   		}else{
	   			document.claimForm.status.value = '<%=Constants.VOUCHER_SUBMITTED%>';
	   		}
	   		document.claimForm.submit();
		   		
		 }
		</script>
		
		</head>
	<body>
	<div class="action  divaction">
		<div class="t">报销单更新</div>
		<div class="pages">
			<form action="voucher_edit.do" name="claimForm" method="post">
			
				<input type="hidden" id="rowNumber" name="rowNumber" value="${rowNumber}"/>
				<input type="hidden" id="claimId" name="id" value="${claimVoucher.id}"/>
				<table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
                  <caption>基本信息</caption>
                  <tr>
                  	<td >编&nbsp;&nbsp;号：${claimVoucher.id}</td>
                    <td>填&nbsp;写&nbsp;人：${claimVoucher.employeeByCreateSn.name}</td>
                    <td>部&nbsp;&nbsp;门：${claimVoucher.employeeByCreateSn.department.name}</td>
                    <td>职&nbsp;&nbsp;&nbsp;&nbsp;位：${claimVoucher.employeeByCreateSn.position.nameCn}</td>
                  </tr>
                  <tr>
                    <td>总金额：
                    <input name="totalAccount" id="totalAccount" readonly="true" value="${claimVoucher.totalAccount}"/></td>
                    <td>填报时间：<fmt:formatDate value="${claimVoucher.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                    <td>状态：${claimVoucher.status}</td>
                    <td>待处理人：${claimVoucher.employeeByNextDealSn.name}</td>
                    <input type="hidden" id="status" name="status" value="${claimVoucher.status}"/>
                  </tr>
                  <tr>
                  	<td colspan="4"><p>-------------------------------------------------------------------------------</p></td>
                  </tr>
                </table>
				<table id="myTable" width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
					<tr>
						<td width="30%">项目类别</td>
						<td width="30%">项目金额</td>
						<td width="30%">费用说明</td>
						<td width="10%">操作</td>
					</tr>
					<c:forEach items="${claimVoucher.voucherDetails}" var="claimDetail" begin="0" varStatus="s">
					<tr>
						<td>
							<input type="hidden" id="id${s.index}" name="oldId" value="${claimDetail.id}"/>
							<input type="hidden" id="item${s.index}" name="oldItem" value="${claimDetail.item}"/>
							${claimDetail.item}</td>
						<td>
							<input type="hidden" id="account${s.index}" name="oldAccount" value="${claimDetail.account}"/>
							${claimDetail.account}</td>
						<td>
							<input type="hidden" id="desc${s.index}" name="oldDescription" value="${claimDetail.description}"/>
							${claimDetail.description}
						</td>
						<td>
							<img src="${images}/delete.gif" width="16" height="16" id="DelRow${s.index}"  
							onclick="delRow(${s.index});" />
						</td>
					</tr>
				</c:forEach>
				</table>
				<table id="detailTable" width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
					<tr>
						<td width="30%">
							<select name="_item" id="item">
								<option value="<%=Constants.VOUCHERDETAIL_CITYWALLTRAFFIC%>"><%=Constants.VOUCHERDETAIL_CITYWALLTRAFFIC%></option>
								<option value="<%=Constants.VOUCHERDETAIL_CITYTRAFFIC%>"><%=Constants.VOUCHERDETAIL_CITYTRAFFIC%></option>
								<option value="<%=Constants.VOUCHERDETAIL_COMMUNICATION%>"><%=Constants.VOUCHERDETAIL_COMMUNICATION%></option>
								<option value="<%=Constants.VOUCHERDETAIL_GIFT%>"><%=Constants.VOUCHERDETAIL_GIFT%></option>
								<option value="<%=Constants.VOUCHERDETAIL_WORK%>"><%=Constants.VOUCHERDETAIL_WORK%></option>
								<option value="<%=Constants.VOUCHERDETAIL_SOCIETYMEAL%>"><%=Constants.VOUCHERDETAIL_SOCIETYMEAL%></option>
								<option value="<%=Constants.VOUCHERDETAIL_MEAL%>"><%=Constants.VOUCHERDETAIL_MEAL%></option>
								<option value="<%=Constants.VOUCHERDETAIL_HOUSE%>"><%=Constants.VOUCHERDETAIL_HOUSE%></option>
							</select>
						</td>
						<td width="30%"><input type="text" name="_account" id="account" /><span class=notice>*</span></td>
						<td width="30%"><input type="text" name="_desc" id="desc" /><span class=notice>*</span></td>
						<td width="10%"><img src="${images}/add.gif" width="16" height="16" id="AddRow"/></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>*事由：</td>
						<td colspan="3">
							<textarea name="event" id="event" rows="5" cols="66">${claimVoucher.event}</textarea>
						</td>
					</tr>
					<tr align="center">
						<td>
							&nbsp; 
						</td>
						<td colspan="3">
							<input  type="button" id="1" name="1" value="保存" onclick="submitClaimVoucher('保存');" class="submit_01"/>
							<input type="button" id="2" name="2" value="提交" class="submit_01" onclick="submitClaimVoucher('提交');"/>
							<input type="button" value="返回" class="submit_01" onclick="window.history.back();"/>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<script type="text/javascript">
			var msg = '${requestScope.message}';
			if (msg.length != 0) alert(msg);
		</script>
	</body>
</html>
