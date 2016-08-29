<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>1元夺宝_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
	<script type="text/javascript" src="${base}/admin_js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/admin_js/pn-1.0.js" ></script>
	<script type="text/javascript" src="${base}/admin_js/pony.js"></script>
  </head>
  
  <body>
  <div class="bk10"></div>
  <div align="center" class="header">
    <h3>添加角色</h3>
  </div>
  <div class="bk10"></div>
 <div class="table_form lr10" style="">
 <form action="/admin_back/doAddRole.action" method="post">
 <table width="100%" cellspacing="0" cellpadding="0" style="">
		<tbody style="">
		<tr>
			<td align="left" width="150px;"><font color="red">*</font>角色名称：</td>
			<td align="left"><input type="text" id="name" name="role.name" class="input-text wid100 bg" />
            </td>
		</tr>
        <tr>
			<td align="left" width="150px;"><font color="red">*</font>角色描述：</td>
			<td align="left">
			<textarea class="input-text" style="height:50px; width:400px" id="description" name="role.description"></textarea>
			</td>
		</tr>
		<tr>
			<td align="left" width="150px;"><font color="red">*</font>拥有的功能：</td>
			<td align="left">
				<#if funcRoot??>
				<@p.tree root=funcRoot showDeep=1 isUrl=false isCheckBox=true checkBoxName="functions.id" vld="{required:true}"/>
				</#if>
			</td>
		</tr>
        <tr>
			<td align="left"></td>
			<td align="left">
			<input type="submit" value="保存" id="submit" class="button" />
			<input type="button" value="返回" class="button" onclick="history.go(-1)" />
			</td>
		</tr>
	</tbody></table>
</form>
</div>
	<script language="javascript" type="text/javascript">
 		$("#submit").click(function(){
 			if($("[name='role.name']").val()==""){
 				alert("请输入角色名称！");
 				return false;	
 			}
 			$("form:first").submit(); 
 		});
 	</script>
  </body>
</html>
