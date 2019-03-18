<!doctype html>
<html>
<head>
<meta charset="utf-8">
<#assign ctx = request.contextPath />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<title>文景假期_支付成功</title>
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
	<div class="main">
		<div class="tour-name itup">
	    	<h3 class="tour-code ipay">您已支付成功 ！</h3>
	    </div>
        <div class="paypal">
	    	<button class="paypal-btn" onclick="window.location='${ctx!}/'">返回</button>
	    </div>
	</div>
</body>
</html>



