<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>我的积分</title>
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
    <div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt; <a href="${ctx!}/member/profile/index.htm" class="a1">用户中心</a> &gt; 我的积分
    </div>
    <div class="widthCenter place">
    	<#include "/front/include/user_left.ftl"/>
        <div class="user_right left shadow">
        	<div class="us_code">
        		<div class="s_title">
                    <ul>
                      <li class="s_curr"><a href="javascript:;">我的积分详情</a></li>
                    </ul>
				</div>
                <div class="tab_box">
	                <div class="my-bd">
	                	<div class="tab_box_2">
                            <div class="m-orderList">
                                <div class="bd">
                                    <table cellspacing="0" cellpadding="0" data-flagorder="header">
                                        <tr>
                                            <th width="32%">项目名称</th>
                                            <th width="15%">积分变动</th>
                                            <th width="15%">变动时间</th>
                                            <th width="19%">关联订单号</th>
                                        </tr>
                                        <#list getScoreList as getScore>
                                        <tr>
                                        	<td class="o_info">
                                        		${getScore.projectName}
                                        	</td>
                                        	<td class="o_info">
                                        		<#if getScore.score &gt; 0>
                                        			+${getScore.score}
                                        		<#else>
                                        			${getScore.score}		
                                        		</#if>
                                        	</td>
                                        	<td class="o_info">
                                        		${getScore.getTimeString}
                                        	</td>
                                        	<td class="o_info">
                                        		<#if getScore.orderNo??>
                                        			<a href="${ctx!}/member/profile/orderdetail.htm?orderNo=${getScore.orderNo}">${getScore.orderNo}</a>
                                        		</#if>
                                        	</td>
                                        </tr>
                                        </#list>
                                    </table>
                                                                                         总积分：${info.score}
                                    <!--<a data-hide="1" href="javascript:void(0);" class="order-del">
                                        <i></i>删除
                                    </a>-->
                                    <#--
                                    <span>订单编号：<a href="${ctx!}/member/profile/orderdetail.htm?orderNo=${order.orderno}">${order.orderno}</a></span>
                                    <span>下单时间：${(order.createtime)?string("yyyy-MM-dd HH:mm:ss")}</span>
                                    -->
                                 </div>
                                 <div class="clear"></div>
                            </div>
	                	</div>
	                </div>
	                <div class="my-bd" style="display:none;">
	                </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <#include "/front/include/bottom.ftl"/>
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
    
	/*分页
	$(function(){
		$.easypage({'contentclass':'all_orders','navigateid':'all_orders_page','everycount':5,'navigatecount':5});	
	})
	*/
</script>
</html>