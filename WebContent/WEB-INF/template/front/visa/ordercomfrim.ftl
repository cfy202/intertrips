<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>支付方式</title>
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
        <a href="${ctx!}/"><img src="${ctx!}/assets-web/logo/logo.png" width="147" height="74"></a>
    </div>
    <div class="pay_step right">
        <img src="${ctx!}/assets-web/images/pay_2.gif" width="323" height="58">
    </div>
    <div class="clear"></div>
</div>
<div class="widthCenter">
	<h2>订单编号：${(vsiaOrders.orderno)!}</h2>
    <div class="Payment_Method shadow">
        <table class="Payment_Method_tab" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<th width="10%">出行日期</th>
                <th width="45%">订购内容</th>
                <th width="15%">人数 / 数量</th>
                <th width="15%">全款</th>
               
            </tr>
            <#list vsiaOrders.orderdetails as eachProduct>
               <tr class="Payment_Method_tr">
            	<td>${(eachProduct.departuredate)!}</td>
                <td><a href="${ctx!}${eachProduct.product.pagePageid.filepath}" class="a4">${(eachProduct.product.name)!}</a></td>
                <td>${(eachProduct.adults)!}</td>
                <td><label><input type="radio" checked="checked" name="Payment_price">${(vsiaOrders.orderdetails[0].currencySign)!}${vsiaOrders.totalprice}</label></td>
               </tr>
                    
             </#list>
            
            <tr bgcolor="#f5f5f5">
            	<td colspan="3"></td>
                <td><b>合计金额</b></td>
                <td><b style="color:red;">${(vsiaOrders.orderdetails[0].currencySign)!}${vsiaOrders.totalprice}</b></td>
            </tr>
        </table>
    </div>
   <#if vsiaOrders.orderdetails[0].currencySign??&&vsiaOrders.orderdetails[0].currencySign='USD'>
      <div class="Payment_Method_content shadow">
        <!--
    	<div class="Payment_Method_content_top">
        	<p>温馨提示</p>
        	<ul>
            	<li>人民币支付方式主要适用于中国地区用户，文景假期提供多种方便快捷的付款方式，几乎涵盖所有大中型银行发行的银行</li>
                <li>单笔消费限额 10,000 美金，约 62,000 人民币，如果您选择信用卡或快捷支付，各个银行有单独的额度限制，具体请参考您所选银行的支付页面说明；如支付金额超过限额，请分开下单、逐笔支付。</li>
                <li>如果您在支付过程中遇到任何疑问，欢迎联系文静假期7*24小时客服：400-071-0197(中国)，，我们将及时为您查询处理。</li>
            </ul>
        </div>
        -->
        <div class="Payment_Method_box">
        	<ul>
            	<li class="Payment_Method_box_current">
            	    <a href="javascript:toPayment('Paypal');">
                	<img src="images/paypal.png" style="margin-top:10px;">
                	</a>
                    <p class="Payment_Method_name">贝宝支付<br>Paypal</p>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
        <!--
        <div class="Payment_Method_main" >
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="${ctx!}/assets-web/images/paypal.png" style="width:60px; vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
         </div>
         -->
    </div>
    <#else>
    <div class="Payment_Method_content shadow" >
      <!--
    	<div class="Payment_Method_content_top">
        	<p>温馨提示</p>
        	<ul>
            	<li>人民币支付方式主要适用于中国地区用户，文景假期提供多种方便快捷的付款方式，几乎涵盖所有大中型银行发行的银行</li>
                <li>单笔消费限额 10,000 美金，约 62,000 人民币，如果您选择信用卡或快捷支付，各个银行有单独的额度限制，具体请参考您所选银行的支付页面说明；如支付金额超过限额，请分开下单、逐笔支付。</li>
                <li>如果您在支付过程中遇到任何疑问，欢迎联系文静假期7*24小时客服：400-071-0197(中国)，，我们将及时为您查询处理。</li>
            </ul>
        </div>
        -->
        <div class="Payment_Method_box" id="pays">
            <ul>
                <li>
                    <a href="javascript:zfB.submit();">
                    <img src="${ctx!}/assets-web/images/alipay.png" style="margin-top:28px; ">
                    </a>
                    <p class="Payment_Method_name">支付宝支付<br></p>
                </li>
                <li>
                    <a href="javascript:cfT.submit();">
                    <img src="${ctx!}/assets-web/images/tenpay.png" style="margin-top:27px;  margin-bottom: 16px;">
                    </a>
                    <p class="Payment_Method_name">财付通支付<br></p>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="Payment_choose">
        <!---
        <div class="Payment_Method_main" >
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="${ctx!}/assets-web/images/alipay.png" style="width:75px; vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
        </div>
        <!--
        <div class="Payment_Method_main"  style="display:none;">
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="images/chinabankpay.png" style=" vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="mb">
                <div class="d150 left mr"><label class="t_banks" rel="ICBC_FP"><input type="radio" name="bank" value="ICBC_FP" checked=""> <img src="${ctx!}/assets-web/images/icbc.gif" alt="中国工商银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="BOC_FP"><input type="radio" name="bank" value="BOC_FP"> <img src="${ctx!}/assets-web/images/boc.gif" alt="中国银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="ABC_FP"><input type="radio" name="bank" value="ABC_FP"> <img src="${ctx!}/assets-web/images/abc.gif" alt="中国农业银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="PAB_FP"><input type="radio" name="bank" value="PAB_FP"> <img src="${ctx!}/assets-web/images/pab.gif" alt="平安银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="CEB_FP"><input type="radio" name="bank" value="CEB_FP"> <img src="${ctx!}/assets-web/images/ceb.gif" alt="中国光大银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="CCB_FP"><input type="radio" name="bank" value="CCB_FP"> <img src="${ctx!}/assets-web/images/ccb.gif" alt="中国建设银行"></label></div>
                <div class="clear"></div>
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
         </div>
         
        <div class="Payment_Method_main" style="display:none;" >
            <div class="f14 mb">
                <span>您已选择 财付通人民币支付</span><img alt="财富通" src="${ctx!}/assets-web/images/tenpay.png" style="vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的财付通付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
         </div>
        </div>
     -->
   </#if>
    
    
    </div>
    <div class="Payment_terms">
    	<input type="checkbox" value="Y" checked>
         我已阅读并同意文景假期的
         <a href="" target="_blank">客户协议</a>、<a href="" target="_blank">修改取消条款</a>、<a href="" target="_blank">使用和隐私条款</a>        
    </div>
    <!--
    <div class="Payment_Method_btns">
         <a href="javascript:void(0)" onclick="subForm()" class="Payment_Method_btn_1">去付款</a>
         <a href="" style="margin-left:10px; padding: 12px 36px; color: #FFF; text-decoration: none; font-size: 14px; font-weight: bold; border-radius: 5px; background: #e0e0e0;">返回上一步</a>
    </div>
    -->
    <div class="clear"></div>
</div>
      <form name="alipayment" id="zfB" action="${ctx!}/alipay/alipayapi.jsp" method="post" target="_blank">
			<input type="hidden" name="WIDout_trade_no" value="${(vsiaOrders.orderno)!}"/>		
			<input type="hidden" name="WIDsubject" value="签证订单"/>	
			<#--	
			<input type="hidden" name="WIDbody" value="订单详情描述描述"/>
			<input type="hidden" name="WIDshow_url" value=""/>
			-->
			<input type="hidden" name="WIDtotal_fee" value="${((vsiaOrders.totalprice)?c)!}"/>
			<input type="hidden" name="extra_common_param" value="5"/>
		</form>
		
		<form action="${ctx!}/tenpay/payRequest.jsp" id="cfT" method="post" name="directFrm" target="_blank">
			<input type="hidden" name="order_no" value="${(vsiaOrders.orderno)!}"/>
		    <input type="hidden" name="product_name"  size="18" maxlength="50" value="签证订单"/>
		    <input type="hidden" name="remarkexplain" value="订单详情描述描述"/>
		    <input type="hidden" name="order_price" value="${((vsiaOrders.totalprice)?c)!}"/> 
		    <input type="hidden" name="trade_mode" value="1"/>
		    <input type="hidden" name="attach" value="5"/>    
        </form>
        
<div class="certificate widthCenter">
    <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #208037040 陕ICP备15009901号 西安淘游网络科技有限责任公司 </p>
</div>
</body>
<script>
var ischecked = 0;

$(".Payment_Method_box ul li").click(function(){
    var Index = $(this).index();
    ischecked = Index;
    $(".Payment_Method_box ul li").removeClass("Payment_Method_box_current");
    $(this).addClass("Payment_Method_box_current");
    $(".Payment_choose .Payment_Method_main").hide();
    $(".Payment_choose .Payment_Method_main:eq("+Index+")").show();
});
 
 function subForm(){
 	if(ischecked == 0){
 	  document.getElementById("zfB").submit();
 	};
 	if(ischecked == 1){
 	  document.getElementById("cfT").submit();
 	};
   
 }
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>

</html>