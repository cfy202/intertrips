<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>女神都出海了，小鲜肉们还宅在家玩游戏吗？</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/private_tour_index.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/jQuery.hhShare.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/slider_2.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/privateMy97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
</head>

<body>
<#include "/front/include/privateTop.ftl"/>
	
    <div class="width100" id="pt_Banner">
      <div class="commendation_banner">
      	<div class="commendation_tit">
        	<h2>女神都出海了，小鲜肉们还宅在家玩游戏吗？</h2>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="width100" style="background-color:#fff; position:relative;" id="pt_Con">
    	<div class="widthCenter">
        	<div class="commendation_main">
            	<div class="commendation_main_top">
                	<div class="commendation_img left">
                    	<img src="${ctx!}/assets-web/images/private_tour_new/commendation_4.jpg">
                    </div>
                    <div class="commendation_info left">
                    	<p>目  的 地：<em>加利福尼亚州</em></p>
                        <p>行程天数：<em>1天</em></p>
                        <p><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon.png"></p>
                        <p>海钓、皮划艇、红酒牛排、看海豚海象</p>
                        <p style="text-align:right;"><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon_r.png"></p>
                    </div>
                	<div class="clear"></div>
                </div>
                <div class="commendation_main_down">
                	<div class="commendation_hightlight">
                    	<h2>行程简介</h2>
                        <p style="text-indent:2em;">面朝大海，放松你心情！ 一周紧张的工作情绪被清新的海风一扫而过。</p>
                        <p style="text-indent:2em;">穿着比基尼的女神们，在豪华游艇上摆着各种撩人的姿势，接受加州阳光的沐浴。小鲜肉们脸红心跳，拿着红酒杯向他们的女神靠近。或者，他可以一显身手，钓一条硕大的鲜活红石斑来显要自己的与众不同。或者，踏着浆板相约阳光美少女在海道上逛一逛，这里没有红绿灯和摄像头。不时人类的亲密伙伴还会纵身一跃，为你们带去美好的祝福。</p>
                        <p style="text-indent:2em;">BEACH 醉人的风光，追逐的海鸥、海豚。日照夕阳，这就是享受生活！</p>
                        <p style="text-indent:2em;">活动包含豪华游艇、船长、渔具鱼饵、皮划艇、站立式浆板</p>
                        <p style="text-indent:2em;">提供餐点、红酒牛排（需提前预定）、饮料酒水</p>
                        <p style="text-indent:2em;">温馨提示：多备一套替换衣服，鼓励穿泳装。另带一件档风外套，以防天气转凉。船上赤脚最方便，也不容易滑，请大家尽量穿拖鞋。自行做好防晒工作。</p>
                    </div>
                
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

	//导航下拉标签效果
	$(function () {
		var navLi=$(".private_tour_nav .private_tour_nav_ul li");
		navLi.mouseover(function () {
		//alert($(this).offset().left);
		$('.private_tour_sub_in').css('margin-left',$(this).offset().left);
		$(this).find("a.private_tour_nav_ul_bg").addClass("private_tour_nav_ul_bg_2");
		$(this).find(".private_tour_sub_nav_new").stop().slideDown(0);
		})
		navLi.mouseleave(function(){
		$(this).find("a.private_tour_nav_ul_bg").removeClass("private_tour_nav_ul_bg_2");
		$(this).find(".private_tour_sub_nav_new").stop().slideUp(0);
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
		var navH=headerH+bannerH;   //计算页面划到导航时 悬浮
        
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