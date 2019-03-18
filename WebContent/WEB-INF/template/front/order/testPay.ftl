<!DOCTYPE html>
<html>
	<head>
		<#assign ctx = request.contextPath />
		<title>订单支付页面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
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
		<div>
			总订单信息<br/>
			<ul>
				<li>总订单编号:2015 6 3 9:40 </li>
				<li>产品数量:1</li>
				<li>订单总价格:$0.01</li>
			</ul>
			联系人信息<br/>
			<ul>
				<li>联系人名称:xie</li>
				<li>联系人电话:13152488327</li>
				<li>联系人邮箱:724217558@qq.com</li>
			</ul>
			订单详情<br/>
			<ul>
				<li>产品名称:芝加哥美东7日游 &nbsp; 产品CODE:11016701</li>
				<li>出发日期:2015-07-16&nbsp; 结束日期:2015-07-23</li>
				<li>接送地点:LAX	</li>
			</ul>
		</div>
		
		<form name="alipayment" action="${ctx!}/alipay/alipayapi.jsp" method="post" target="_blank">
			<input type="hidden" name="WIDout_trade_no" value="1507180001and345423"/>		
			<input type="hidden" name="WIDsubject" value="线路订单"/>	
			<#--	
			<input type="hidden" name="WIDbody" value="订单详情描述描述"/>
			<input type="hidden" name="WIDshow_url" value=""/>
			-->
			<input type="hidden" name="WIDtotal_fee" value="0.01"/>
			<input type="hidden" name="extra_common_param" value="1"/>
			<input type="submit" name="submit" value="支付宝支付"/>
		</form>
		
		<form action="${ctx!}/tenpay/payRequest.jsp" method="post" name="directFrm" target="_blank">
			<input type="hidden" name="order_no" value="170023"/>
		    <input type="hidden" name="product_name"  size="18" maxlength="50" value="线路订单"/>
		    <input type="hidden" name="remarkexplain" value="订单详情描述描述"/>
		    <input type="hidden" name="order_price" value="0.01"/> 
		    <input type="hidden" name="trade_mode" value="1"/>
		    <input type="hidden" name="attach" value="1"/>
		    <input name="submit" type="submit" value="财付通支付">       
        </form>
        
        <form name=formbill action="${ctx!}/wangyin/Send.jsp" method="post" target="_blank">
        	<input type="hidden" name="v_oid" value="201 6 3 9:40"/>
            <input type="hidden" name="v_amount" value="0.01"/>          
            <input name="submit" type="submit" value="网银在线支付">           
  		</form>
  		
  		<form name=formbill action="${ctx!}/pay/checkout.do?" method="post" target="_blank">
  			<input type="hidden" name="id" value="e6684863983d40d4aa41feeb4f2325dd"/>	<#--总单id-->
  			<input type="hidden" name="fee" value="0.01"/>	<#--付款金额-->
  			<input type="hidden" name="payStatus" value="5"/>	<#--支付方式-->
  			<input name="submit" type="submit" value="paypal支付"> 
  		</form>
  		
		<form id="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_blank" >
		    <input type="hidden" name="cmd" value="_xclick"/>
		    <input type="hidden" name="business" value="JaredSell@paypal.com"/>
			<input type="hidden" name="item_name" value="线路订单"/>		
		    <input type="hidden" name="item_number" value="123123"/> 		
			<input type="hidden" name="amount" class="price" value="888"/>
			<input type="hidden" name="currency_code" value="USD"/>
	        <input type="hidden" name="cancel_return" value="https://192.168.1.117:8080/uswenjing/"/>
			<input type="hidden" name="return" value="https://192.168.1.117:8080/uswenjing/ipnPay/return.do"/>	
		    <input type="hidden" name="notify_url" value="https://192.168.1.117:8080/uswenjing/ipnPay/notify2.do"/>
			<input type="hidden" name="custom" class="paytype" value="1" />	
			<input name="submit" type="submit" value="paypalIPN支付"> 		
		</form>
		
		<form id="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_blank" >
		    <input type="hidden" name="cmd" value="_xclick"/>
		    <input type="hidden" name="business" value="JaredSell@paypal.com"/>
			<input type="hidden" name="item_name" value="线路订单"/>		
		    <input type="hidden" name="item_number" value="123123"/> 		
			<input type="hidden" name="amount" class="price" value="888"/>
			<input type="hidden" name="currency_code" value="USD"/>
	        <input type="hidden" name="cancel_return" value="https://192.168.1.117:8080/uswenjing/"/>
			<input type="hidden" name="return" value="https://192.168.1.117:8080/uswenjing/ipnPay/return.do"/>	
		    <input type="hidden" name="notify_url" value="https://192.168.1.117:8080/uswenjing/ipnPay/notify2.do"/>
			<input type="hidden" name="custom" class="paytype" value="1" />	
			<input name="submit" type="submit" value="paypalIPN支付"> 		
		</form>
		
		<FORM method='post' target="_blank" action='${simGateway!}' >
			<INPUT type='hidden' name='x_login' value='${loginId!}' />
			<INPUT type='hidden' name='x_amount' value='0.01' />
			<INPUT type='hidden' name='x_currency_code' value='USD' />
			<INPUT type='hidden' name='x_description' value='商品描述描述' />
			<INPUT type='hidden' name='x_invoice_num' value='5554455' />
			<INPUT type='hidden' name='x_fp_sequence' value='${((fingerprint.sequence)?c)!}' />
			<INPUT type='hidden' name='x_fp_timestamp' value='${((fingerprint.timeStamp)?c)!}' />
			<INPUT type='hidden' name='x_fp_hash' value='${(fingerprint.fingerprintHash)!}' />
			<INPUT type='hidden' name='x_test_request' value='FALSE' />
			<INPUT type='hidden' name='x_show_form' value='PAYMENT_FORM' />
			<INPUT type='hidden' name='x_version' value='3.1' />
			<INPUT type='hidden' name='x_relay_response' value='TRUE' />
			<INPUT type='hidden' name='x_relay_url' value='https://www.wenjing.com/authorizeSim/relayTest.do'/>
			<input type='submit' value='信用卡支付' />
		</FORM>
		${transactionKey!}
		
		<form id="CForm" action="${ctx}/authorizeChargeCreditCard/run.do" method="post" target="_blank" >
		    <input type="hidden" name="cardNumber" value="371710952062003"/>
		    <input type="hidden" name="cardCode" value="3609"/>
		    <input type="hidden" name="expirationDate" value="0920"/>
		    <input type="hidden" name="firstName" value="Bo"/>
		    <input type="hidden" name="lastName" value="Wang"/>
		    <input type="hidden" name="phoneNumber" value="6263779969"/>
		    <input type="hidden" name="email" value="wang@gmail.com"/>
			<input type="hidden" name="amount" class="price" value="0.01"/>
			<input type="hidden" name="currencyCode" class="price" value="USD"/>
			<input type="hidden" name="orderNo" value="5555555"/>		
			<input type="hidden" name="zip" value="94118"/>
			<input type="hidden" name="address" value="918 Clement St,STE 101"/>
			<input type="hidden" name="city" value="San Francisco"/>
			<input type="hidden" name="state" value="CA"/>
			<input type="hidden" name="country" value="US"/>
			<input name="submit" type="submit" value="Charge"> 		
		</form>
		
		<#--
		<form id="CForm" action="${ctx}/authorizeChargeCreditCard/run.do" method="post" target="_blank" >
		    <input type="hidden" name="cardNumber" value="371710952062003"/>
		    <input type="hidden" name="cardCode" value="3609"/>
		    <input type="hidden" name="expirationDate" value="0920"/>
			<input type="hidden" name="amount" class="price" value="0.01"/>
			<input type="hidden" name="currencyCode" class="price" value="USD"/>
			<input type="hidden" name="orderNo" value="5555555"/>		
			<input name="submit" type="submit" value="Charge2"> 		
		</form>
		-->
		
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
		
	</body>
</html>
	