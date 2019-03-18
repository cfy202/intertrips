<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>订单详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/user_center.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
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
    <#include "/front/include/top.ftl"/>
    <div class="clear"></div>
    <div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt; <a href="${ctx!}/member/profile/index.htm" class="a2">用户中心</a> </div>
    <div class="widthCenter place">
    	
        <div class="user_right left shadow" style="width:1100px;">
        	<div class="order-detail">
        		<dl class="desc">
            		<dt>订单详情</dt>
            		<dd>当前订单状态: 
                		${orders.orderStatus.name}
	                         <#if (orders.orderStatus.code)?? && ((orders.orderStatus.code)==1||(orders.orderStatus.code)==2)>
	                		    
	                		    <#if orders.orderdetails[0].product.type??&& orders.orderdetails[0].product.type=2>
	                		    <a target="_blank" class="action-btn" href="${ctx!}/front/visaorders/payOrders.do?orderno=${orders.orderno}">去支付</a>
	                		     <#else>
	                		       <a target="_blank" class="action-btn" href="${ctx!}/front/orders/payAgain.do?orderNo=${orders.orderno}">去支付</a>
	                		    </#if>
	                		    
	                		 </#if>
                		 
                        <div class="clear"></div>
                    </dd>
        		</dl>
                <div class="info">
            		<p>订单信息</p>
                    <ul class="order-info-ul">
                        <li>订单编号: ${orders.orderno}</li>
                        <li>产品数量: ${orders.quantity}</li>
                        <li>总价（应付款）: ${(orders.orderdetails[0].currencySign)!}${orders.totalprice}</li>
                        <li>成交时间: ${orders.createtime?string('yyyy-MM-dd HH:mm:ss')}</li>
                        <div class="clear"></div>
                    </ul>
        		</div>
                <div class="order_line_dash"></div>
                <div class="info">
            		<p>预订人信息</p>
                    <ul class="order-info-ul">
                        <li>姓名: ${orders.orderContacter.name}</li>
                        <li>手机: ${orders.orderContacter.tel}</li>
                        <li>邮箱: ${orders.orderContacter.email}</li>
                        <div class="clear"></div>
                    </ul>
        		</div>
                <div class="order_line_dash"></div>
                <div class="info">
            		<p>订单详情</p>
            		<#list orders.orderdetails as eachProduct>
                    <ul class="order-info-ul">
                        <li style="width:100%;">产品名称: <a href="${ctx!}${(eachProduct.product.pagePageid.filepath)!}" class="a5" target="_blank">${(eachProduct.product.name)!}</a></li>
                        <li>产品编号: ${(eachProduct.product.code)!}</li>
                        <li>出发日期: ${(eachProduct.departuredate)!}</li>
                        <#if eachProduct.enddate??>
                        <li>结束日期: ${(eachProduct.enddate)!}</li>
                        </#if>
                        <#if eachProduct.days??>
                        <li>行程天数: ${eachProduct.days}</li>
                        </#if>
                        <#if eachProduct.roomcount??>
                        <li>房间数量: ${(eachProduct.roomcount)!}</li>
                        </#if>
                        <#if eachProduct.adults?? && eachProduct.adults!=0><li>成人人数: ${(eachProduct.adults)!}</li></#if>
                        <#if eachProduct.children?? && eachProduct.children!=0><li>儿童人数: ${(eachProduct.children)!}</li></#if>
                        <#if eachProduct.babies?? && eachProduct.babies!=0><li>婴儿人数：${(eachProduct.babies)!}</li></#if>
                        <#if eachProduct.depostidate??>
                        <li>最后付款日期: ${(eachProduct.depostidate)!}</li>
                        </#if>
                        <#if eachProduct.despotprice??>
                        <li>已付金额: ${orders.orderdetails[0].currencySign} ${(eachProduct.despotprice)!}</li>
                        </#if>
                        <#--
                        <#if eachProduct.finalpaydate??>
                        <li>最终付款日期: ${(eachProduct.finalpaydate)!}</li>
                        </#if>
                        -->
                        <#if eachProduct.finalprice??>
                        <li>未付金额: ${orders.orderdetails[0].currencySign} ${(eachProduct.finalprice)!}</li>
                        </#if>
                        <div class="clear"></div>
                    </ul>
                    </#list>
        		</div>
                <div class="order_line_dash"></div>
                <#if ((orders.orderdetails[0]).tourPassengerList)?has_content>
                <div class="info">
            		<p>旅客信息</p>
                    <#list orders.orderdetails as eachProduct>
	            		<#list eachProduct.tourPassengerList as tourPassenger>
		                    <div class="mb">
		                        <span class="left">客人 ${tourPassenger_index+1}</span>
		                        <ul class="order-info-ul left">
		                            <li>姓名: ${(tourPassenger.lastName)!}${(tourPassenger.firstName)!}</li>
		                            <li>性别: ${(tourPassenger.gender==1)?string("女","男")}</li>
		                            <li>生日: ${(tourPassenger.birthday?string("yyyy-MM-dd"))!}</li>
		                            <li>国籍: ${(tourPassenger.nationality)!}</li>
		                            <li>护照号: ${(tourPassenger.passportNo)!}</li>
		                            <li>护照有效期: ${(tourPassenger.passportNoExpiryDate?string("yyyy-MM-dd"))!}</li>
		                            <li>联系电话: ${(tourPassenger.mobileNumber)!}</li>
		                            <div class="clear"></div>
		                        </ul>
		                        <div class="clear"></div>
		                    </div>
                       </#list>
                    </#list>
        		</div>
                <div class="order_line_dash"></div>
                </#if>
            </div>
        </div>
        
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <#include "/front/include/bottom.ftl"/>
    <div id="popup_overlay" style="display:none; position: fixed; z-index: 999; top: 0px; left: 0px; width: 100%; height: 100%; opacity: 0.6; background: rgb(0, 0, 0);"></div>
    <div id="popup_container" class="popup-box"  style="display:none;">
    	<a class="close-btn" href="javascript:void(0);" id="popup_close"></a>
        <div class="pop-ctn">
        	<p class="hd" id="popup_title">您确定要删除此订单记录吗？确认后, 该订单记录将被永久删除。</p>
            <p class="bd" id="popup_message"></p>
        </div>
        <div class="pop-btn" id="popup_btn">
        	<input type="button" class="btn-sub" value="&nbsp;确定&nbsp;" id="popup_ok">
            <input type="button" class="btn-esc" value="&nbsp;取消&nbsp;" id="popup_cancel">
        </div>
    </div>
</body>
<script>
  $(function(){
	$(this).find(".nav_left_pos").hide();
	//$(this).find(".nav_left_pos").stop(false, false).slideUp(300);

	//nav下拉
    $(".nav_left").mouseenter(function () {
        $(this).find(".nav_left_pos").stop(false, false).slideDown(300);
    });
    $(".nav_left").mouseleave(function () {
        $(this).find(".nav_left_pos").stop(false, false).slideUp(300);
    });
    
    //nav更多下拉
    $(".nav_left_pos li").mouseenter(function () {
        $(this).find(".nav_left_more").stop(false, false).fadeIn(300);
    });
    $(".nav_left_pos li").mouseleave(function () {
        $(this).find(".nav_left_more").stop(false, false).fadeOut(300);
    });
	
});
    
</script>

</html>