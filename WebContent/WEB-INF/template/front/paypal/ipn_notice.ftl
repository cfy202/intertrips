<!DOCTYPE html>
<html>
	<head>
		<#assign ctx = request.contextPath />
		<meta charset="utf-8">
		<title>文景假期_支付通知</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
  		<h3 class="tour-code ipay">${information!}</h3>
		<div class="tour-name itup">
			<h3 class="tour-code ipay">
				请及时联系客服人员。
			</h3>
	    </div>
	    <div class="paypal">
	    	<button class="paypal-btn" onclick="window.location='${ctx!}/'">返回</button>
	    </div>
	</body>
</html>
	