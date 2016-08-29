<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="height: 100%;">
  <head>
	<title>1元夺宝_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
	<script type="text/javascript" src="${base}/admin_js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/admin_js/pn-1.0.js" ></script>
	<script type="text/javascript" src="${base}/admin_js/pony.js"></script>
  </head>
  
  <body >
<div class="header-title lr10"><b>修改管理员</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
<form id="myform" method="post" action="/admin_back/doUpdateAdmin.action">
<input type="hidden" name="admin.id" value="${admin.id!}"/>
<input type="hidden" name="user.userId" value="${admin.user.userId!}"/>
<table width="100%" cellspacing="0" cellpadding="0" style="">
    <tbody><tr>
    	<td width="80">用户名</td> 
   		<td><input type="text" value="${admin.userName!}" class="input-text" name="user.userName"/></td>
    </tr>
    <tr>
    	<td>密码</td>
    	<td><input type="password" value="" id="password" class="input-text" /></td>
    </tr>
    <tr>
    	<td>确认密码</td> 
    	<td><input type="password" id="pwdconfirm" class="input-text" name="user.userPwd"/></td>
    </tr>
    <tr>
    <td>所属角色</td>
    <td>
    	<@p.checkboxlist name="roles.id" valueList=stack.findValue("admin.roles.{id}") list=roleList listKey="id" listValue="name" required="true" class="required"/>
    </td>
    </tr>
    <tr>
		<td align="left" width="150px;"><font color="red">*</font>额外的功能：</td>
		<td align="left">
			<#if funcRoot??>
			<@p.tree root=funcRoot showDeep=1 isUrl=false isCheckBox=true checkBoxName="functions.id" valueContent=stack.findValue("admin.functions.{id}") vld="{required:true}"/>
			</#if>
		</td>
	</tr>
	<tr>
    <td>禁用</td>
    <td>
    	<input type="radio" id="_admin.disabled_0" value="true" <#if admin.disabled?string="true"> checked="checked"</#if> name="admin.disabled"/><label for="_admin.isDisabled_0">是</label> 
    	<input type="radio" id="_admin.disabled_1" value="false" <#if admin.disabled?string="false"> checked="checked"</#if> name="admin.disabled"/><label for="_admin.isDisabled_1">否</label>
    </td>
    </tr>
</tbody>
</table>
<div class="bk10"></div>
    <input type="button" class="button" id="dosubmit" value=" 提交 " />
    <input type="button" value="返回" class="button" onclick="history.go(-1)" />
</form>
</div>

<script type="text/javascript">
	$("#dosubmit").click(function(){
		if($("[name=user.userName]").val()==""){
			alert("用户名不能为空！");
			return false;
		}else if($("#password").val()!=$("#pwdconfirm").val()){
			alert("密码输入不一致，请重新输入！");
			$("#password").val("");
			$("#pwdconfirm").val("");
			return false;
		}else{
			$("form:first").submit(); 
		}
	});
</script>
 </body>
</html>
