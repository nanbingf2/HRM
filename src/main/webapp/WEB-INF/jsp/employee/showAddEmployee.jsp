<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加员工</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
	 
	    $(function(){
	    	/** 员工表单提交 */
			$("#employeeForm").submit(function(){
				var name = $("#name");
				var card_id = $("#card_id");
				var phone = $("#phone");
				var address = $("#address");
				var birthday = $("#birthday");
				var msg = "";
				if ($.trim(name.val()) == ""){
					msg = "姓名不能为空！";
					name.focus();
				}else if ($.trim(card_id.val()) == ""){
					msg = "身份证号码不能为空！";
					card_id.focus();
				}else if (!/^[1-9]\d{16}[0-9A-Za-z]$/.test($.trim(card_id.val()))){
					msg = "身份证号码格式不正确！";
					card_id.focus();
				}else if ($.trim(phone.val()) == ""){
					msg = "手机号码不能为空！";
					phone.focus();
				}else if (!/^1[3|5|8]\d{9}$/.test($.trim(phone.val()))){
					msg = "手机号码格式不正确！";
					phone.focus();
				}else if ($.trim(address.val()) == ""){
					msg = "地址不能为空！";
					address.focus();
				}else if ($.trim(birthday.val()) == ""){
					msg = "出生日期不能为空！";
					birthday.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}
				$("#employeeForm").submit();
			});
	    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理  &gt; 添加员工</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/employee/insertEmployee" id="employeeForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓名：<input type="text" name="name" id="name" size="20"/></td>
		    			<td class="font3 fftd">身份证号码：<input type="text" name="card_id" id="card_id" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">性别：
									<select name="sex" style="width:143px;">
					    			<option value="0">--请选择性别--</option>
					    			<option value="1">男</option>
					    			<option value="2">女</option>
					    		</select></td>
		    			<td class="font3 fftd">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
		    			 <select name="job_id" style="width:143px;">
					    			<option value="0">--请选择职位--</option>
					    			<c:forEach items="${requestScope.jobs }" var="job">
					    				<option value="${job.id }">${job.name }</option>
					    			</c:forEach>
					    		</select>
					    </td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">手机：<input name="phone" id="phone" size="20"/></td>
						<td class="font3 fftd">
							联系地址：<input name="address" id="address" size="40"/>&nbsp;&nbsp;
						</td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>

			<tr>
				<td class="font3 fftd">
					出生日期：<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="birthday" id="birthday" size="40"/>&nbsp;&nbsp;
				</td>

			</tr>
			<tr><td class="main_tdbor"></td></tr>

			  <tr>
				  <td class="font3 fftd">
					  所属部门：
					  <select  name="dept_id" style="width:100px;">
						  <option value="0">--部门选择--</option>
						  <c:forEach items="${requestScope.depts }" var="dept">
							  <option value="${dept.id }">${dept.name }</option>
						  </c:forEach>
					  </select>
				  </td>
			  </tr>
			<tr><td class="main_tdbor"></td></tr>

			<tr><td align="left" class="fftd">
			<input type="submit" value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>