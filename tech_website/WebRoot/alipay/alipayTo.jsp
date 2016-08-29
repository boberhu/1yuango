<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tech.alipay.services.*"%>
<%@ page import="com.tech.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String subject = request.getParameter("title");
		String totalFee = request.getParameter("payBee");
		String body = request.getParameter("comments");
		String out_trade_no = UtilDate.getOrderNum();
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("payment_type", "1");
        //订单系统中的唯一订单号
        sParaTemp.put("out_trade_no", out_trade_no);
        //订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
        sParaTemp.put("subject", subject);
        //订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
        sParaTemp.put("body", body);
        //订单总金额，显示在支付宝收银台里的“应付总额”里
        sParaTemp.put("total_fee", totalFee);
      //商品展示地址，要用http:// 格式的完整路径，不允许加?id=123这类自定义参数
        sParaTemp.put("show_url", "");
      //默认支付方式，取值见“即时到帐接口”技术文档中的请求参数列表
        sParaTemp.put("paymethod", "");
      //默认网银代号，代号列表见“即时到帐接口”技术文档“附录”→“银行列表”
        sParaTemp.put("defaultbank", "");
      //防钓鱼时间戳
        sParaTemp.put("anti_phishing_key", "");
      //获取客户端的IP地址，建议：编写获取客户端IP地址的程序
        sParaTemp.put("exter_invoke_ip", "");
      //自定义参数，可存放任何内容（除=、&等特殊字符外），不会显示在页面上
        sParaTemp.put("extra_common_param", "");
      //默认买家支付宝账号
        sParaTemp.put("buyer_email", "");
      //提成类型，该值为固定值：10，不需要修改
        sParaTemp.put("royalty_type", "");
      //提成信息集
        sParaTemp.put("royalty_parameters", "");
		
		//构造函数，生成请求URL
		String sHtmlText = AlipayService.create_direct_pay_by_user(sParaTemp);
		out.println(sHtmlText);
	%>
</body>
</html>