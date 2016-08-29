<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝测试页面</title>
</head>
<body>
	<form action="alipayTo.jsp" method="post">
		标题:<input type="text" name="title"/><br/>
		金额:<input type="text" name="payBee"/><br/>
		备注:<input type="text" name="comments"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>