<!doctype html>
<html>
<head>
<meta charset="utf-8">
<#assign ctx = request.contextPath />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<title>文景假期_支付确认</title>
<#--
<link href="${ctx!}/resources/mobiles/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx!}/resources/mobiles/styles/framework.css" rel="stylesheet" type="text/css">
<link href="${ctx!}/resources/mobiles/styles/tours.css" rel="stylesheet" type="text/css">
-->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-67304615-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body>
	<header class="header-top">
		<a href="javascript：window.history.back()"><span class="icon-back"></span></a>
		<span class="header-tit">支付确认：</span>
	</header>
	<div class="main">
	    <div class="tour-name itup">
	    	<h3 class="tour-code ipay"><span>支付账户：</span>${result.EMAIL}</h3>
	        <div class="tour-line" style="margin：10px 0 10px 0;"></div>
	        <h3 class="tour-code ipay"><span>订单号：</span>${result.orderNo}</h3>
	        <div class="tour-line" style="margin：10px 0 10px 0;"></div>
	        <h3 class="tour-code ipay"><span>金额：</span>${result.PAYMENTREQUEST_0_CURRENCYCODE}&nbsp;${result.L_PAYMENTREQUEST_0_AMT0}</h3>
	    </div>
		<form action="${ctx!}/pay/complete.do" method="post">
			<p align="center">
				<input type="hidden" name="id" value="${id!}" /> 
				<input type="hidden" name="token" value="${token!}" />
			 	<input type="hidden" name="method" value="${method!}"> 
			 	<input type="hidden" name="payerId" value="${payerId!}"> 
			 	<input type="hidden" name="price" value="${(price?c)!}" /> 
			 	<input type="hidden" name="orderNo" value="${(result.orderNo)!}" /> 
			</p>
			<div class="paypal">
				<button class="paypal-btn" onclick="this.form.submit()">确认支付</button>
			</div>
		</form>
	</div>
</body>
</html>

