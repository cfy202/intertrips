<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>支付方式</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js" ></script>
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
        <img src="${ctx!}/assets-web/images/pay_2.gif" width="323" height="58">
    </div>
    <div class="clear"></div>
</div>
<div class="widthCenter">
	<h2>订单编号：${bookTourVO.orderNumber}</h2>
    <div class="Payment_Method shadow">
        <table class="Payment_Method_tab" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<th width="10%">日期</th>
                <th width="30%">订购内容</th>
                <th width="15%">人数 / 数量</th>
                <th width="26%">费用明细</th>
                <th width="12%">全款</th>
            </tr>
            <tr class="Payment_Method_tr">
            	<td>${bookInfo.departureDate?string('yyyy-MM-dd')}</td>
                <td><a href="${ctx!}${bookInfo.product.pagePageid.filepath}" class="a4">${bookInfo.product.name}</a></td>
                <td>${bookInfo.totalNumber}</td>
                <td align="left">
                	<p class="Payment_td_p">基础团费:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.tourFee}</p>
                <#if bookInfo.guideServeFee??> 	
                	<p class="Payment_td_p">导游服务费:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.guideServeFee}</p>
                </#if>
                <#if bookInfo.steamFee??>
                    <p class="Payment_td_p">行程外精彩景点/观光/演出:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.steamFee}</p>
                </#if>
                <#if bookInfo.transferFee??>    
                    <p class="Payment_td_p">接送机费用:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.transferFee}</p>
                </#if>
                <#if bookInfo.airticketFee??>  
                    <p class="Payment_td_p">机票费用:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.airticketFee}</p>
                </#if>  
                <#if bookInfo.selfPayFee??>
                	<p clas="Payment_td_p">自费项:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.selfPayFee}</p>
                </#if>
                <#--
                	 <p class="Payment_td_p">积分兑换金额:&nbsp;&nbsp;USD${pageShowInfo.eachProductInfoVOList[0].scoreExchangedPrice}</p>
                -->	 
                <#--
                	<p class="Payment_td_p">优惠券兑换金额:&nbsp;&nbsp;${pageShowInfo.eachProductInfoVOList[0].currencySign}${pageShowInfo.eachProductInfoVOList[0].couponseExchangedPrice}</p>
                --> 
                </td>                
                <td><label><input type="radio" checked="checked" paytype="5" name="Payment_price" currencySign="${bookInfo.cost.sign}" price="${bookInfo.totalPrice}">${bookInfo.cost.sign}${bookInfo.totalPrice}</label></td>
            </tr>
            <tr bgcolor="#f5f5f5">
            	<td colspan="3"></td>	
                <td><b>合计金额</b></td>
                <td><b id="totalPrice" style="color:red;">${bookInfo.cost.sign}${bookInfo.totalPrice}</b></td>
            </tr>
        </table>
    </div>
    <div id="enPayway" class="Payment_Method_content shadow">
    	<!--
    	<div class="Payment_Method_content_top">
        	<p>温馨提示</p>
        	<ul>
            	<li>人民币支付方式主要适用于中国地区用户，文景假期提供多种方便快捷的付款方式，几乎涵盖所有大中型银行发行的银行</li>
                <li>单笔消费限额 10,000 美金，约 62,000 人民币，如果您选择信用卡或快捷支付，各个银行有单独的额度限制，具体请参考您所选银行的支付页面说明；如支付金额超过限额，请分开下单、逐笔支付。</li>
                <li>如果您在支付过程中遇到任何疑问，欢迎联系文景假期7*24小时客服：888-736-4685(美国)，400-071-0197(中国)，604-800-6411(加拿大)，我们将及时为您查询处理。</li>
            </ul>
        </div>
        -->
        <div class="Payment_Method_box">
        	<ul>
            	<li class="Payment_Method_box_current">
            		<a href="javascript:toPayment('Paypal');">
                		<img src="${ctx!}/assets-web/images/paypal.png" style="margin-top:10px;">
                    </a>
                    <p class="Payment_Method_name">贝宝支付<br>Paypal</p>
                </li>
            	<li>
            		<a href="javascript:toPayment('Paypal');">
                		<img src="${ctx!}/assets-web/images/paypalCard.png" style="margin-top:30px;">
                    </a>
                    <p class="Payment_Method_name">信用卡支付<br>VISA/MASTER</p>
                </li>
                <li>
            		<a href="javascript:toPayment('authorizeSim');">
                		<img src="${ctx!}/assets-web/images/creditCard.png" style="margin-top:30px;">
                    </a>
                    <p class="Payment_Method_name">信用卡支付<br>Credit Card</p>
                </li>
                <#--
                <li>
                	<a href="javascript:toPayment('cheque');">
                		<img src="${ctx!}/assets-web/images/cheque.png" style="margin-top:30px;">
                	</a>
                	<p class="Payment_Method_name">汇票或现金支票<br>Draft/Cheque</p>
                </li>
                -->
                <div class="clear"></div>
            </ul>
        </div>
        <!--
        <div class="Payment_Method_main" >
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="${ctx!}/assets-web/images/paypal.png" style="width:60px; vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15" style="display:none;">支付方式说明：</div>
            <div class="f15" style="display:none;">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div> 
         </div>
         -->
    </div>
    <!--
    <div id="cnPayway" class="Payment_Method_content shadow" style="display:none;">
    	<!--
    	<div class="Payment_Method_content_top">
        	<p>温馨提示</p>
        	<ul>
            	<li>人民币支付方式主要适用于中国地区用户，文景假期提供多种方便快捷的付款方式，几乎涵盖所有大中型银行发行的银行</li>
                <li>单笔消费限额 10,000 美金，约 62,000 人民币，如果您选择信用卡或快捷支付，各个银行有单独的额度限制，具体请参考您所选银行的支付页面说明；如支付金额超过限额，请分开下单、逐笔支付。</li>
                <li>如果您在支付过程中遇到任何疑问，欢迎联系文景假期7*24小时客服：888-736-4685(美国)，400-071-0197(中国)，我们将及时为您查询处理。</li>
            </ul>
        </div>
        -->
        <!--
        <div class="Payment_Method_box">
            <ul>
                <li class="Payment_Method_box_current" >
                	<a href="javascript:toPayment('Alipay');">
                    	<img src="${ctx!}/assets-web/images/alipay.png" style="margin-top:28px;">
                    </a>
                    <p class="Payment_Method_name">支付宝支付<br>Alipay</p>
                </li>
            <#--
        		<li>
                    <img src="${ctx!}/assets-web/images/chinabankpay.png" style="margin-top:29px;  margin-bottom: 13px;">
                    <p class="Payment_Method_name">网银在线支付<br>Chinabank</p>
                </li>  
            -->
            <!--
                <li>
                    <a href="javascript:toPayment('Tenpay');">
                    	<img src="${ctx!}/assets-web/images/tenpay.png" style="margin-top:27px;  margin-bottom: 16px;">
                    </a>
                    <p class="Payment_Method_name">财付通支付<br>Tenpay</p>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
        <!--
        <div class="Payment_choose">
        <div class="Payment_Method_main" >
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="${ctx!}/assets-web/images/alipay.png" style="width:75px; vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15" style="display:none;">支付方式说明：</div>
            <div class="f15" style="display:none;">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
        </div>
        
        <div class="Payment_Method_main"  style="display:none;">
            <div class="f14 mb">
                <span>您已选择 财付通人民币支付</span><img alt="财付通" src="${ctx!}/assets-web/images/tenpay.png" style="vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15" style="display:none;">支付方式说明：</div>
            <div class="f15" style="display:none;">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
        </div>
        </div>
    </div>
    -->
    <div class="Payment_terms">
    	<input id="agreement" type="checkbox" checked="checked" value="Y">
	 我已阅读并同意文景假期的
         <a href="" target="_blank">客户协议</a>、<a href="" target="_blank">修改取消条款</a>、<a href="" target="_blank">使用和隐私条款</a>        
    </div>
    <!--
    <div class="Payment_Method_btns">
         <a onclick="toPayment();" class="Payment_Method_btn_1">去付款</a>
         <a href="" style="margin-left:10px; padding: 12px 36px; color: #FFF; text-decoration: none; font-size: 14px; font-weight: bold; border-radius: 5px; background: #e0e0e0; display:none;" >返回上一步</a>
    </div>
    -->
    <div class="clear"></div>
</div>
<div class="certificate widthCenter">
    <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #2080370 陕ICP备15009901号 西安淘游网络科技有限责任公司 </p>
</div>
<div id="payForm" style="display:none;">
	<#--
	<form id="alipayForm" name="alipayment" action="${ctx!}/alipay/alipayapi.jsp" method="post" target="_blank">
		<!-- 订单编号 
		<input type="hidden" name="WIDout_trade_no" value="${bookInfo.orderNumber}_1" />	
		-->	
		<!-- 商品名称
		<input type="hidden" name="WIDsubject" value="${bookInfo.orderNumber}" />
		-->	
		<!--	
		<input type="hidden" name="WIDbody" value="订单详情描述描述"/>
		<input type="hidden" name="WIDshow_url" value=""/>
		-->
		<!-- 本次支付金额
		<input type="hidden" class="price" name="WIDtotal_fee" />
		 -->
		<#-- 支付类型 
		<input type="hidden" class="paytype" name="extra_common_param" />
	</form>
	<form id="tenpayForm" action="${ctx!}/tenpay/payRequest.jsp" method="post" name="directFrm" target="_blank">
		<input type="hidden" name="order_no" value="${bookInfo.orderNumber}_1" />
	    <input type="hidden" name="product_name" size="18" maxlength="50" value="${bookInfo.orderNumber}"/>
		<#-- 产品CODE 
	    <input type="hidden" name="remarkexplain" value="${pageShowInfo.eachProductInfoVOList[0].product.code}" />
	    <input type="hidden" class="price" name="order_price" /> 
	    -->
	    <#--即时到帐
	    <input type="hidden" name="trade_mode" value="1"/>
	    -->
	    <#-- 支付类型
	    <input type="hidden" class="paytype" name="attach" />
	</form>
	 -->
	<#--
	<form id="paypalForm" name="formbill" action="${ctx!}/pay/checkout.do?" method="post" target="_blank">
		<input type="hidden" name="id" value="${pageShowInfo.orders.id}"/>
		<input type="hidden" class="price" name="fee" />	
		<input type="hidden" class="paytype" name="payStatus" />	
		<input type="hidden" class="currencyType" name="currencyType" value="${orderdetail.currencySign}"/>	
	</form>
	-->
	<#--		
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${pageShowInfo.eachProductInfoVOList[0].product.name}"/>
        <input type="hidden" name="item_number" value="${pageShowInfo.orders.orderno}"/> 
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(orderdetail.currencySign)!}"/>
        <input type="hidden" name="cancel_return" value="https://192.168.1.117:8080/uswenjing/"/>
		<input type="hidden" name="return" value="https://192.168.1.117:8080/uswenjing/ipnPay/return.do"/>
	    <input type="hidden" name="notify_url" value="https://192.168.1.117:8080/uswenjing/ipnPay/notify2.do"/>
		<input type="hidden" name="custom" class="paytype"/>		
	</form>
	-->
	
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${bookInfo.product.name}"/>
        <input type="hidden" name="item_number" value="${bookInfo.orderNumber}"/> 
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(bookInfo.cost.code)!}"/>
        <input type="hidden" name="cancel_return" value="https://www.intertrips.com/">
		<input type="hidden" name="return" value="https://www.intertrips.com/ipnPay/return.do">
	    <input type="hidden" name="notify_url" value="https://www.intertrips.com/ipnPay/notify2.do"/>
	    <input type="hidden" name="custom" class="paytype"/>
	</form>
	<FORM id='authorizeSimForm' method='post' target="_blank" action='${simGateway!}' >
		<INPUT type='hidden' name='x_login' value='${loginId!}' />
		<INPUT type='hidden' name='x_amount' class='price'/>
		<INPUT type='hidden' name='x_currency_code' value='${(bookInfo.cost.code)!}' />
		<INPUT type='hidden' name='x_description' value='线路编号${bookInfo.product.code}' />
		<INPUT type='hidden' name='x_invoice_num' value='${bookInfo.orderNumber}' />
		<INPUT type='hidden' name='x_fp_sequence' id="x_fp_sequence" />
		<INPUT type='hidden' name='x_fp_timestamp' id="x_fp_timestamp" />
		<INPUT type='hidden' name='x_fp_hash' id="x_fp_hash"/>
		<INPUT type='hidden' name='x_test_request' value='false' />
		<INPUT type='hidden' name='x_show_form' value='PAYMENT_FORM' />
		<INPUT type='hidden' name='x_version' value='3.1' />
		<INPUT type='hidden' name='x_relay_response' value='TRUE' />
		<INPUT type='hidden' name='x_relay_url' value='https://www.intertrips.com/authorizeSim/relay.do'/>
	</FORM>
	<#--
	<form id="chequeForm" action="${ctx!}/order_notice.htm" method="post">
		<input type="hidden" name="payway" value="10"/>
		<input type="hidden" name="orderNo" value="${bookInfo.orderNumber}"/>
	</form>
	-->
</div>
</body>
<script>
$(function() { 
	/*网页选项卡的切换 
	$(".Payment_Method_ul li").click(function () {
	        $(".Payment_Method_ul li").removeClass("Payment_ul_curr");
	        $(this).addClass("Payment_ul_curr");
	        var Index = $(this).index();
	        $(".Payment_Method_content").hide();
	        $(".Payment_Method_content:eq(" + Index + ")").show();
	})*/
	<#--
	/*
     *	如果币种为人民币，则隐藏英文的支付块，显示中文的支付块
	*/
	if('${orderdetail.currencySign}' == 'CNY'){
		$("#enPayway").hide();
		$("#cnPayway").show();
	}else{
		if('${orderdetail.currencySign}' == 'USD'){
			$("#SIM").show();
		}
	}
	-->
});	

	/*
	    切换点击时的样式
	*/
	$(".Payment_Method_box ul li").click(function(){
	    var Index = $(this).index();
	    $(".Payment_Method_box ul li").removeClass("Payment_Method_box_current");
	    $(this).addClass("Payment_Method_box_current");
	   /* $(".Payment_choose .Payment_Method_main").hide();
	    $(".Payment_choose .Payment_Method_main:eq("+Index+")").show(); */
	});
	
	<#-- 设置价格 
	var setPrice = function(input){
		var currencySign = $(input).attr("currencySign");	
		var price = $(input).attr("price");
		$("#totalPrice").html(currencySign + '' + price);
	}-->
	
	<#-- 支付 -->
	var toPayment = function(payMethod){
	   /*
		* 如果用户没有同意用户协议，则弹出提示		
		*/
		var $agreement = $("#agreement");
		var checked = $agreement.attr('checked');
		if(checked != 'checked'){
			alert('请同意客户协议.');
			$agreement.focus();	
			return;	
		}
		
		var selectedPrice = $("input:checked");
		var price = selectedPrice.attr("price");
	  
	  	alert(price);
	   /*
		*	去除数字格式中的逗号		
		*/
		var priceArray = price.split(",");
		if(priceArray.length == 2){
			price = priceArray[0] + priceArray[1];
		}
		var paytype = selectedPrice.attr("paytype");
		
		var $payForm = $("#payForm");
		$payForm.find("input.paytype").val(paytype);
		$payForm.find("input.price").val(price);
		
		if("Paypal" == payMethod){
			$("#paypalForm").submit();
		}else if("authorizeSim" == payMethod){
			$.ajax({
				type:"post",
				url:"${ctx}/front/orders/getFingerprint.do",
				data:{price:price,currencySign:"${(bookInfo.cost.code)!}"},
				dataType:"json",
				success:function(data){
					$("#x_fp_sequence").val(data.sequence);
					$("#x_fp_timestamp").val(data.timeStamp);
					$("#x_fp_hash").val(data.fingerprintHash);
					$("#authorizeSimForm").submit();
				},
				error:function(data){
					alert(data);
				},
			});
		}	
	}
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
</html>