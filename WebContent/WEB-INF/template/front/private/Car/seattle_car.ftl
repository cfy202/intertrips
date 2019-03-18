<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>西雅图用车</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/private_tour_index.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/jQuery.hhShare.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/slider_2.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
    
</head>

<body>
<#include "/front/include/privateTop.ftl"/>
    <div class="clear"></div>
    <div class="widthCenter place">当前位置：<a href="/" class="a1">首页</a> &gt; 包车服务 </div>
    <div class="clear"></div>
    <div class="width100" style="background-color:#fff;">
    	<div class="widthCenter" style="margin-top:15px;">
        	<div class="private_tour_lfnav left">
            	<div class="private_tour_lfnav_tit">包车服务</div>
            	<ul>
                	<li><a href="${ctx!}/private-tour/sanfrancisco-bus.htm" >旧金山用车</a></li>
                    <li><a href="${ctx!}/private-tour/losangeles-bus.htm">洛杉矶用车</a></li>
                    <li><a href="${ctx!}/private-tour/newyork-bus.htm">纽约用车</a></li>
                    <li><a href="${ctx!}/private-tour/seattle-bus.htm" class="private_tour_lfnav_current">西雅图用车</a></li>
                </ul>
                <div class="private_tour_lfnav_bottom"><a href="${ctx!}/private-tour/wenjing-bus.htm">文景假期车队介绍</a></div>
                <div class="clear"></div>
            </div>
            <div class="private_tour_ribox right">
            	<div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">西雅图地区单程接送</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/seattle_car1.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$200</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>10</td>
                            <td>7</td>
                            <td>$350</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>24座中型巴士</td>
                            <td>20</td>
                            <td>6</td>
                            <td>$650</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/次，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">便捷的24小时中文接送服务，从西雅图国际机场（SEA）接送到指定地点。如有航班信息，请在“客人要求信息表”告知。</td>
                        </tr>
                        
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">西雅图市区包车一日</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/seattle_car1.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$520</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>10</td>
                            <td>7</td>
                            <td>$780</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>24座中型巴士</td>
                            <td>20</td>
                            <td>6</td>
                            <td>$1200</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间10小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            推荐行程：观光西雅图市区及地标景点，参观西雅图市中心著名的派克市场（Pike Place Market）和第一家星巴克咖啡的店面及太空针（Space Needle）俯瞰西雅图。
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">西雅图近郊包车一日</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/seattle_car1.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$890</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>10</td>
                            <td>7</td>
                            <td>$1280</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>24座中型巴士</td>
                            <td>20</td>
                            <td>6</td>
                            <td>$1600</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间10小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            推荐行程：游览雷尼尔雪山、波特兰、亚基玛葡萄园、奥林匹亚国家公园等
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="private_tour_consultbox" style="display:none;">
		         <div class="private_tour_consult">
                	<div class="pt_name">Gigi</div>
                    <div class="pt_img"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic6.png" style="width:100px; height:100px;"></div>
                    <div class="pt_message">
                    	<p>Tel: 626-478-3519</p>
                        <p>QQ: 2790820042</p>
                        <p>Email: Gigi.w@wenjing.com</p>
                    </div>
                </div>
                <div class="private_tour_consult">
                	<div class="pt_name">Jessica</div>
                    <div class="pt_img"><img src="${ctx!}/assets-web/images/private_tour_new/FullSizeRender.png" style="width:100px; height:100px;"></div>
                    <div class="pt_message">
                    	<p>Tel: 626-478-3519</p>
                        <p>QQ: 1467301533</p>
                        <p>Email: Jessica.z@wenjing.com</p>
                    </div>
                </div>
                
        </div>
    </div>
    <div class="clear"></div>
<#include "/front/include/bottom.ftl"/>
   
</body>
<script>
			
	$(function(){
		$(this).find(".private_tour_nav_left_pos").hide();
		//$(this).find(".nav_left_pos").stop(false, false).slideUp(300);
	
		//nav下拉
	    $(".private_tour_nav_left").mouseenter(function () {
	        $(this).find(".private_tour_nav_left_pos").stop(false, false).slideDown(0);
	    });
	    $(".private_tour_nav_left").mouseleave(function () {
	        $(this).find(".private_tour_nav_left_pos").stop(false, false).slideUp(0);
	    });
	    
	    //nav更多下拉
	    $(".private_tour_nav_left_pos li").mouseenter(function () {
	        $(this).find(".private_tour_nav_left_more").stop(false, false).fadeIn(0);
	    });
	    $(".private_tour_nav_left_pos li").mouseleave(function () {
	        $(this).find(".private_tour_nav_left_more").stop(false, false).fadeOut(0);
	    });
    });

	
	$(function () {
		var navLi=$(".private_tour_nav .private_tour_nav_ul li");
		navLi.mouseover(function () {
			//alert($(this).offset().left);
			//$('.sub_in').css('margin-left',$(this).offset().left);
			$(this).find("a.private_tour_nav_ul_bg").addClass("private_tour_nav_ul_bg_2");
			$(this).find(".private_tour_sub_in").stop().slideDown(0);
		})
		navLi.mouseleave(function(){
			$(this).find("a.private_tour_nav_ul_bg").removeClass("private_tour_nav_ul_bg_2");
			$(this).find(".private_tour_sub_in").stop().slideUp(0);
		})
	})
	
	//币种切换
	
	$(".choose_gq").click(function(){
    	var div = $(".brand_op");
    	if ( div.css("display") === "none" ) {
        	div.show();
    	}
	})

    //切换
    var on = 0;
    $(".F_right_po_jt_left").click(function () {
        var posDiv = $(this).parent().parent().find(".F_right_po");
        var Len = posDiv.find(".F_one").length;
        if (on == 0) {
            var lastLeft = -(Len - 1) * 810;
            posDiv.stop(false, false).animate({
                left: lastLeft
            }, 500);
            on = Len - 1;
        } else {
            on--;
            var changeLeft = -on * 810;
            posDiv.stop(false, false).animate({
                left: changeLeft
            }, 500);
        }
    });
    $(".F_right_po_jt_right").click(function () {
        var posDiv = $(this).parent().parent().find(".F_right_po");
        var Len = posDiv.find(".F_one").length;
        if (on == Len - 1) {
            posDiv.stop(false, false).animate({
                left: "0px"
            }, 500);
            on = 0;
        } else {
            on++;
            var changeLeft = -on * 810;
            posDiv.stop(false, false).animate({
                left: changeLeft
            }, 500);
        }
    });
    $(".F_right_title .left span").click(function () {
        var Index = $(this).index();
        $(this).parent().find("span").removeClass("F_right_span_active");
        $(this).addClass("F_right_span_active");
        $(this).parents(".F_right").find(".F_right_main").hide();
        $(this).parents(".F_right").find(".F_right_main:eq(" + Index + ")").show();
    });
	<#--
	$(function(){
	//右侧浮动特效
	$('#asid_share').hhShare({
		cenBox     : 'asid_share_box',  //里边的小层
		icon       : 'adid_icon',
		addClass   : 'red_bag',
		titleClass : 'asid_title',
		triangle   : 'asid_share_triangle', //鼠标划过显示图层，边上的小三角
		showBox    : 'asid_sha_layer' //鼠标划过显示图层
	});
	});
	-->	
	
	//个性定制滚动图
	$(function(){

		$('#demo01').flexslider({
			animation: "slide",
			direction:"horizontal",
			easing:"swing"
		});
		
	});
	//获取要定位元素距离浏览器顶部的距离
		var headerH=$("#pt_header").height();
		var bannerH=$("#pt_Banner").height();
		var navH=headerH+bannerH+85;   //计算页面划到导航时 悬浮
        
        //计算left值
        var zhi = (($(window).width() - 1100)/2)+1110;
        
        //滚动条事件
        $(window).scroll(function(){
			
            //获取滚动条的滑动距离
            var scroH = $(this).scrollTop();
            //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
            if (scroH >= navH) {
				$(".private_tour_consultbox").show(300);
                $(".private_tour_consultbox").css({
                    "position": "fixed",
                    "top": 0,
                    "left":zhi
                });
            } else if (scroH < navH) {
				$(".private_tour_consultbox").hide();
                $(".private_tour_consultbox").css({
                    "position": "static"
                });
            }
        });
</script>

</html>