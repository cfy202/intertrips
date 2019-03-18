<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>订单完成</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
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
<div class="widthCenter pay_top shadow">
    <div class="pay_logo left">
    	<a href="${ctx!}/">
        	<img src="${ctx!}/assets-web/logo/logo.png" width="147" height="74">
        </a>
    </div>
    <div class="pay_step right">
        <img src="${ctx!}/assets-web/images/pay_3.gif" width="323" height="58">
    </div>
    <div class="clear"></div>
</div>
<div class="widthCenter">
	<div class="pay_finish_tit"><h2>成功提交订单</h2></div>
    <div class="Payment_Method shadow">
        <div class="pay_finish_info">
        	<h2>订单编号：${orders.orderno}</h2>
            <p>我们已收到您的订单，已发送您的预订单信息至${orders.orderContacter.email}</p>
            <p>您的付款方式是<b>汇票或现金支票</b>，请注意以下条款：</p>
            <p>请您务必于<b>下一个工作日</b>之内<b>致电文景假期服务热线。</b></p>
            <p>文景假期必须于<b>订购后7天</b>收到付款，否则您的预订将被自动取消。</p>
            <p>文景假期必须于<b>出发日14天之前</b>收到付款，否则您的预订将被自动。</p>
            <p>收到您的付款后两个工作日内我们会将最终确认的“电子旅行凭证”发到您的邮箱。</p>
            <p>您也可以登录<a href="${ctx!}/" class="a4">https://www.wenjing.com</a>的会员账号来查询您订单的状态。</p>
        </div>
    </div>
    <!--<h2 align="center" style="color:red;">- 请选择支付方式 -</h2>-->
    <!--<ul class="Payment_Method_ul">
    	<li class="Payment_ul_curr">美元支付</li>
        <span></span>
        <li>人民币支付</li>
        <div class="clear"></div>
    </ul>-->
    <div class="clear"></div>
</div>
<div class="certificate widthCenter">
    <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #208037040 西安淘游网络科技有限责任公司 </p>
</div>
<script type="text/javascript">
	var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>