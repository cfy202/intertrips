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
    <div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt; <a href="${ctx!}/member/profile/index.htm" class="a1">用户中心</a> &gt; <a href="${ctx!}/member/profile/user_orders.htm" class="a1">我的订单</a> &gt; 订单详情</div> 
    <div class="widthCenter place">
    	<#include "/front/include/user_left.ftl"/>
        <div class="user_right left shadow">
        	<div class="order-detail">
        		<dl class="desc">
            		<dt>订单详情</dt>
            		<dd>当前订单状态: 
                		${vsiaOrders.orderStatus.name}
                		<#if (vsiaOrders.orderStatus.code)?? && ((vsiaOrders.orderStatus.code)==1||(vsiaOrders.orderStatus.code)==2)>
                		    <a target="_blank" class="action-btn" href="${ctx!}/front/visaorders/payOrders.do?orderno=${vsiaOrders.orderno}">去支付</a>
                		</#if>
                        <div class="clear"></div>
                    </dd>
        		</dl>
                <div class="info">
            		<p>订单信息</p>
                    <ul class="order-info-ul">
                        <li>订单编号: ${vsiaOrders.orderno}</li>
                        <li>产品数量: ${vsiaOrders.quantity}</li>
                        <li>总价（应付款）: ${(vsiaOrders.orderdetails[0].currencySign)!} ${vsiaOrders.totalprice}</li>
                        <li>下单时间: ${vsiaOrders.createtime?string('yyyy-MM-dd HH:mm:ss')}</li>
                        <div class="clear"></div>
                    </ul>
        		</div>
                <div class="order_line_dash"></div>
                <div class="info">
            		<p>预订人信息</p>
                    <ul class="order-info-ul">
                        <li>姓名: ${(vsiaOrders.orderContacter.name)}</li>
                        <li>手机: ${vsiaOrders.orderContacter.tel}</li>
                        <li>邮箱: ${vsiaOrders.orderContacter.email}</li>
                        <div class="clear"></div>
                    </ul>
        		</div>
                <div class="order_line_dash"></div>
                <div class="info">
            		<p>订单详情</p>
            		<#list vsiaOrders.orderdetails as eachProduct>
                    <ul class="order-info-ul">
                        <li style="width:100%;">产品名称: <a href="${ctx!}${eachProduct.product.pagePageid.filepath}" class="a5" target="_blank">${eachProduct.product.name}</a></li>
                        <li>产品编号: ${eachProduct.product.code}</li>
                        <li>出发日期: ${eachProduct.departuredate}</li>
                        <#if eachProduct.adults?? && eachProduct.adults!=0><li>成人人数: ${(eachProduct.adults)!}</li></#if>
                        <#if eachProduct.children?? && eachProduct.children!=0><li>儿童人数: ${(eachProduct.children)!}</li></#if>
                        <#if eachProduct.babies?? && eachProduct.babies!=0><li>婴儿人数：${(eachProduct.babies)!}</li></#if>
                        <div class="clear"></div>
                    </ul>
                    </#list>
        		</div>
                <div class="order_line_dash"></div>
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
   $(function () {
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