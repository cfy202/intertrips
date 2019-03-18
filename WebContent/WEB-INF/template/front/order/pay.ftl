<#--
<!DOCTYPE html>
<html>
	<head>
		<title>订单支付页面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	</head>
	<body>
		总订单信息<br/>
		<ul>
			<li>总订单编号:${pageShowInfo.orders.orderno}</li>
			<li>产品数量:${pageShowInfo.orders.quantity}</li>
			<li>订单总价格:${pageShowInfo.eachProductInfoVOList[0].currencySign}${pageShowInfo.orders.totalprice}</li>
		</ul>
		联系人信息<br/>
		<ul>
			<li>联系人名称:${pageShowInfo.orderContacter.name}</li>
			<li>联系人电话:${pageShowInfo.orderContacter.tel}</li>
			<li>联系人邮箱:${pageShowInfo.orderContacter.email}</li>
		</ul>
		订单详情<br/>
		<ul>
		<#list pageShowInfo.eachProductInfoVOList as eachProduct>
			<li>产品名称:${eachProduct.product.name}&nbsp; 产品CODE:${eachProduct.product.code}</li>
			<li>出发日期:${eachProduct.departureDate}&nbsp; 结束日期:${eachProduct.endDate}</li>
			<#if (eachProduct.departureId)??>
			<li>接送地点:${eachProduct.departureId}	</li>
			</#if>
			<li>
				<ul>旅客信息:
				<#list eachProduct.tourPassengerList as tourPassenger>
					<li>客人${tourPassenger_index + 1}&nbsp;姓名：${tourPassenger.lastName}${tourPassenger.firstName} 手机:${tourPassenger.mobileNumber} 国籍:${tourPassenger.nationality}</li>
				</#list>
				</ul>
			</li>
		</#list>
		</ul>
	</body>
</html>
-->	
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

  ga('create', 'UA-38017454-1', 'auto');
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
        <img src="images/pay_2.gif" width="323" height="58">
    </div>
    <div class="clear"></div>
</div>
<div class="widthCenter">
	<h2>订单编号：12345678910110</h2>
    <div class="Payment_Method shadow">
        <table class="Payment_Method_tab" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
            	<th width="10%">日期</th>
                <th width="45%">订购内容</th>
                <th width="15%">人数 / 数量</th>
                <#if orderdetail.fullPrice?has_content>
                <th width="15%">全款</th>
                </#if>
                <#if orderdetail.prePrice?has_content>
                <th width="15%">定金</th>
                </#if>
                <#if orderdetail.totalFinalPrice?has_content>
                <th width="15%">尾款</th>
                </#if>
                <#if orderdetail.finalPrice1?has_content>
                <th width="15%">部分尾款</th>
                </#if>
                <#if orderdetail.finalPrice2?has_content>
                <th width="15%">剩余尾款</th>
                </#if>
            </tr>
            <tr class="Payment_Method_tr">
            	<td>2015-7-12</td>
                <td><a href="" class="a4">美东美西+大瀑布+夏威夷14日 全程奔驰高顶用车，舒适享受旅途之乐！</a></td>
                <td>1</td>
                <#if orderdetail.fullPrice?has_content>
                <td><label><input type="radio" checked="checked" name="Payment_price">${(orderdetail.currencySign)!}${(orderdetail.fullPrice)!}</label></td>
                </#if>
                <#if orderdetail.prePrice?has_content>
                <td><label><input type="radio" name="Payment_price">${(orderdetail.currencySign)!}${(orderdetail.prePrice)!}</label></td>
                </#if>
                <#if orderdetail.totalFinalPrice?has_content>
                <td><label><input type="radio" checked="checked" name="Payment_price">${(orderdetail.currencySign)!}${(orderdetail.totalFinalPrice)!}</label></td>
                </#if>
                <#if orderdetail.finalPrice1?has_content>
                <td><label><input type="radio" name="Payment_price">${(orderdetail.currencySign)!}${(orderdetail.finalPrice1)!}</label></td>
                </#if>
                <#if orderdetail.finalPrice2?has_content>
                <td><label><input type="radio" checked="checked" name="Payment_price">${(orderdetail.currencySign)!}${(orderdetail.finalPrice2)!}</label></td>
                </#if>
            </tr>
            <tr bgcolor="#f5f5f5">
            	<td colspan="3"></td>
                <td><b>合计金额</b></td>
                <td><b style="color:red;">$18972.00</b></td>
            </tr>
        </table>
    </div>
    <h2 align="center" style="color:red;">- 请选择支付方式 -</h2>
    <ul class="Payment_Method_ul">
    	<li class="Payment_ul_curr">美元支付</li>
        <!--<span></span>-->
        <li>人民币支付</li>
        <div class="clear"></div>
    </ul>
    <div class="Payment_Method_content shadow">
    	<div class="Payment_Method_content_top">
        	<p>温馨提示</p>
        	<ul>
            	<li>人民币支付方式主要适用于中国地区用户，文景假期提供多种方便快捷的付款方式，几乎涵盖所有大中型银行发行的银行</li>
                <li>单笔消费限额 10,000 美金，约 62,000 人民币，如果您选择信用卡或快捷支付，各个银行有单独的额度限制，具体请参考您所选银行的支付页面说明；如支付金额超过限额，请分开下单、逐笔支付。</li>
                <li>如果您在支付过程中遇到任何疑问，欢迎联系文静假期7*24小时客服：000-000-0000(美国)，000-000-0000(中国)，我们将及时为您查询处理。</li>
            </ul>
        </div>
        <div class="Payment_Method_box">
        	<ul>
            	<li class="Payment_Method_box_current">
                	<img src="images/paypal.png" style="margin-top:10px;">
                    <p class="Payment_Method_name">贝宝支付<br>Paypal</p>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="Payment_Method_main" >
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="images/paypal.png" style="width:60px; vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
         </div>
    </div>
    <div class="Payment_Method_content shadow"  style="display:none;">
    	<div class="Payment_Method_content_top">
        	<p>温馨提示</p>
        	<ul>
            	<li>人民币支付方式主要适用于中国地区用户，文景假期提供多种方便快捷的付款方式，几乎涵盖所有大中型银行发行的银行</li>
                <li>单笔消费限额 10,000 美金，约 62,000 人民币，如果您选择信用卡或快捷支付，各个银行有单独的额度限制，具体请参考您所选银行的支付页面说明；如支付金额超过限额，请分开下单、逐笔支付。</li>
                <li>如果您在支付过程中遇到任何疑问，欢迎联系文静假期7*24小时客服：000-000-0000(美国)，000-000-0000(中国)，我们将及时为您查询处理。</li>
            </ul>
        </div>
        <div class="Payment_Method_box">
            <ul>
                <li class="Payment_Method_box_current">
                    <img src="images/alipay.png" style="margin-top:28px; ">
                    <p class="Payment_Method_name">支付宝支付<br>Paypal</p>
                </li>
                <li>
                    <img src="images/chinabankpay.png" style="margin-top:29px;  margin-bottom: 13px;">
                    <p class="Payment_Method_name">网银在线支付<br>Chinabank</p>
                </li>
                <li>
                    <img src="images/tenpay.png" style="margin-top:27px;  margin-bottom: 16px;">
                    <p class="Payment_Method_name">财付通支付<br>Tenpay</p>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="Payment_choose">
        <div class="Payment_Method_main" >
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="images/alipay.png" style="width:75px; vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
        </div>
        <div class="Payment_Method_main"  style="display:none;">
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="images/chinabankpay.png" style=" vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="mb">
                <div class="d150 left mr"><label class="t_banks" rel="ICBC_FP"><input type="radio" name="bank" value="ICBC_FP" checked=""> <img src="images/icbc.gif" alt="中国工商银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="BOC_FP"><input type="radio" name="bank" value="BOC_FP"> <img src="images/boc.gif" alt="中国银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="ABC_FP"><input type="radio" name="bank" value="ABC_FP"> <img src="images/abc.gif" alt="中国农业银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="PAB_FP"><input type="radio" name="bank" value="PAB_FP"> <img src="images/pab.gif" alt="平安银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="CEB_FP"><input type="radio" name="bank" value="CEB_FP"> <img src="images/ceb.gif" alt="中国光大银行"></label></div>
                <div class="d150 left mr"><label class="t_banks" rel="CCB_FP"><input type="radio" name="bank" value="CCB_FP"> <img src="images/ccb.gif" alt="中国建设银行"></label></div>
                <div class="clear"></div>
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
         </div>
        <div class="Payment_Method_main"  style="display:none;">
            <div class="f14 mb">
                <span>您已选择 支付宝人民币支付</span><img alt="支付宝" src="images/tenpay.png" style="vertical-align:middle; margin-left:20px;" >
            </div>
            <div class="f15">支付方式说明：</div>
            <div class="f15">* 目前支持的支付宝付款方式为：即时到帐付款。<br>
                 * "即时到帐"是支付宝付款方式的一种，即客户自愿通过支付宝帐户即时向对方支付宝帐户支付，客户付款成功后，所支付的款项将立刻进入对方支付宝帐户。<br>
                 * 有关如何注册、如何激活、如何充值支付宝流程或您在使用支付宝时遇到问题，建议您直接联系支付宝客服中心 <a href="https://help.alipay.com/lab/contact_service.htm" target="_blank">https://help.alipay.com/lab/contact_service.htm</a>，支付宝客服中心电话：<span style="color:#fe6a3c;">0571-88156688</span>
            </div>
         </div>
        </div>
    </div>
    <div class="Payment_terms">
    	<input type="checkbox" value="Y">
         我已阅读并同意文景假期的
         <a href="" target="_blank">客户协议</a>、<a href="" target="_blank">修改取消条款</a>、<a href="" target="_blank">使用和隐私条款</a>        
    </div>
    <div class="Payment_Method_btns">
         <a href="" class="Payment_Method_btn_1">去付款</a>
         <a href="" style="margin-left:10px; padding: 12px 36px; color: #FFF; text-decoration: none; font-size: 14px; font-weight: bold; border-radius: 5px; background: #e0e0e0;">返回上一步</a>
    </div>
    <div class="clear"></div>
</div>
<div class="certificate widthCenter">
    <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #208037040 西安淘游网络科技有限责任公司 </p>
</div>
</body>
<script>
	$(function() { 
	//网页选项卡的切换 
		$(".Payment_Method_ul li").click(function () {
	        $(".Payment_Method_ul li").removeClass("Payment_ul_curr");
	        $(this).addClass("Payment_ul_curr");
	        var Index = $(this).index();
	        $(".Payment_Method_content").hide();
	        $(".Payment_Method_content:eq(" + Index + ")").show();
	    })
	})
	$(".Payment_Method_box ul li").click(function(){
	    var Index = $(this).index();
	    $(".Payment_Method_box ul li").removeClass("Payment_Method_box_current");
	    $(this).addClass("Payment_Method_box_current");
	    $(".Payment_choose .Payment_Method_main").hide();
	    $(".Payment_choose .Payment_Method_main:eq("+Index+")").show();
	});
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>

</html>