<!doctype html>
<html>
<head>
<meta charset="utf-8">
<#assign ctx = request.contextPath />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<title>文景假期_支付通知</title>
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
		<a href="javascript:window.history.back()"><span class="icon-back"></span></a>
		<span class="header-tit">支付通知：</span>
	</header>
	<div class="main">
		<div class="tour-name itup">
    		<h3 class="tour-code ipay">${mesage!}</h3>
        </div>
		<#--
		<#if (result.L_ERRORCODE0?? && result.L_ERRORCODE0 == '11607')>
	    <div class="tour-name itup">
    		<h3 class="tour-code ipay">您已经支付成功,请不要重复提交.</h3>
        </div>
        <div class="paypal">
    		<button class="paypal-btn" onclick="window.location='${ctx!}/'">返回</button>
        </div>
		<#else>
		-->
		<#if (result.L_ERRORCODE0?? && result.L_ERRORCODE0 != '11607')>
		<div class="tour-name itup">
			<h3 class="tour-code ipay">
				请复制这些代码，发送至PayPal帮助中心，并且联系他们的客服人员。
			 <#--   Please copy these codes, send them to help center of
					PayPal and contact with their customer service staffs.
			 -->
			</h3>
	    </div>
	    <div>
		    <h3 class="tour-code ipay">
			    	errorcode: ${result.L_ERRORCODE0} errormessage:	${result.L_LONGMESSAGE0}
			</h3>
	    </div>
	    <div class="paypal">
	    	<button class="paypal-btn" onclick="window.location='${ctx!}/'">返回</button>
	    </div>
		</#if>
	</div>
</body>
</html>



