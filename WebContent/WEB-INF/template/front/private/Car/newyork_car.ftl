<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>纽约包车</title>
    <#assign ctx = request.contextPath />
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
                	<li><a href="${ctx!}/private-tour/sanfrancisco-bus.htm">旧金山用车</a></li>
                    <li><a href="${ctx!}/private-tour/losangeles-bus.htm">洛杉矶用车</a></li>
                    <li><a href="${ctx!}/private-tour/newyork-bus.htm" class="private_tour_lfnav_current">纽约用车</a></li>
                    <li><a href="${ctx!}/private-tour/seattle-bus.htm">西雅图用车</a></li>
                </ul>
                <div class="private_tour_lfnav_bottom"><a href="${ctx!}/private-tour/wenjing-bus.htm">文景假期车队介绍</a></div>
                <div class="clear"></div>
            </div>
            <div class="private_tour_ribox right">
            	<div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding: 5px 10px;">纽约地区单程接送（JFK/LGA/曼哈顿地区）</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/newyork_car1.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$100</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>11</td>
                            <td>7</td>
                            <td>$250</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>24</td>
                            <td>8</td>
                            <td>$550</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/次，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">便捷的中文接送服务，纽约市区内从JFK/LGA机场、曼哈顿市区内火车站、游轮码头或其他指定地点单程接送。如有航班信息，请在“客人要求信息表”告知。</td>
                        </tr>
                        
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">纽约地区单程接送（EWR机场）</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/newyork_car2.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$150</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>11</td>
                            <td>7</td>
                            <td>$280</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>24</td>
                            <td>8</td>
                            <td>$550</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            便捷的中文接送服务，从EWR机场迎送至纽约曼哈顿地区指定地点。如有航班信息，请在“客人要求信息表”告知。
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">纽约市区一日游</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/newyork_car3.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$360</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>11</td>
                            <td>7</td>
                            <td>$650</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>24</td>
                            <td>8</td>
                            <td>$850</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            推荐行程：漫步世界金融、证券交易中心华尔街,三一教堂,象征牛市的铜牛雕塑和纽约股票交易所。 参观大都会博物馆,时代广场,杜莎夫人蜡像馆与总统奥巴马等名人合影。 途径第五大道,洛克菲勒中心,联合国总部,世贸遗址。登纽约市地标-帝国大厦嘹望观景台,欣赏中央公园和纽约全景。 乘坐观光环岛游轮游览哈德逊河,与自由女神像和布鲁克林桥摄影留念。 登上花费1.2亿美元重新整修开放的国家历史文物-大无畏号航空母舰,并参观协和号超音速客机和各种战机。
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">西点军校/WOODBURY奥特莱斯购物一日游</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/newyork_car4.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$420</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>11</td>
                            <td>7</td>
                            <td>$760</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>24</td>
                            <td>8</td>
                            <td>$920</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间10小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            推荐行程：早上参观‘美国将军的摇篮’- 西点军校，这里历来是兵家必争之地,颇有“一夫当关，万夫莫开”之势。许多美军名将如格兰特、罗伯特•李、艾森豪威尔、巴顿、麦克阿瑟、布莱德利等均是该校的毕业生。后前往Woodbury Common Premium Outlets－全美最大、最新的奥特莱斯名牌购物商场，共有200 多间商店，有Tod's、Gucci、Fendi、Burberry等，凡你所能点出的世界著名品牌,都能在这里找到,打折超过50％以上。
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">纽约–费城/普林斯顿大学一日游</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="4"><img src="${ctx!}/assets-web/images/private_tour_new/newyork_car5.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$420</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>11</td>
                            <td>7</td>
                            <td>$760</td>
                            <td>司兼导</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>24</td>
                            <td>8</td>
                            <td>$920</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间10小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            推荐行程：一早出发前往美国著名的常春藤高校 – 普林斯顿大学参观。然后前往国家独立歷史公园。沿著鹅卵石铺就的街道前行，寂寂的自由鐘矗立在刚经修葺的自由鐘中心，虽无声却仿佛歷史的鐘声縈绕於耳。穿过年代久远的独立厅，瞻仰一下国会厅，这儿见证了现代民主的建立，《独立宣言》和《宪法》的诞生。还将於本杰明佛兰克林的墓前追忆伟人的丰功伟绩。自由鐘的斜对面是美国造币厂，每小时大约有的硬币从这里生產出来。傍晚回到纽约。
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