<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>拉斯维加斯赌城婚礼</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/private_tour_index.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
</head>

<body>
	<#include "/front/include/privateTop.ftl"/>
    <div class="width100" id="pt_Banner">
      <div class="commendation_banner">
      	<div class="commendation_tit">
        	<h2>简约而浪漫-拉斯维加斯赌城婚礼</h2>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="width100" style="background-color:#fff; position:relative;" id="pt_Con">
    	<div class="widthCenter">
        	<div class="commendation_main">
            	<div class="commendation_main_top">
                	<div class="commendation_img left">
                    	<img src="${ctx!}/assets-web/images/private_tour_new/commendation_10.jpg">
                    </div>
                    <div class="commendation_info left">
                    	<p>目  的 地：<em>拉斯维加斯</em></p>
                        <p>行程天数：<em>1天</em></p>
                        <p><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon.png"></p>
                        <p>给她惊喜！给她承诺！拉斯维加斯浪漫之旅</p>
                        <p style="text-align:right;"><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon_r.png"></p>
                    </div>
                	<div class="clear"></div>
                </div>
                <div class="commendation_main_down">
                	<div class="commendation_hightlight">
                    	<h2>行程简介</h2>
                        <p style="text-indent:2em;">没有繁琐的形式没有宾客的絮叨，只有你和我在属于对方共有的一张纸上签下对未来的期许，书写浪漫人生。随意而简单；只有你眼里的我，我眼里的你，穿着白色的婚纱，着笔挺的西装，在精致的教堂里，在牧师的见证下，交换与对方相守一生的许诺。</p>
                        <p style="text-indent:2em;">浪漫而华丽的拉斯维加斯灯火璀璨，夜景美轮美奂，看得到高贵的埃菲尔铁塔，奇妙的卢克索金字塔，百乐宫五彩的喷泉秀，还有辉煌的罗马凯撒皇宫。在拉斯维加斯的结婚将得到来自全世界的充满异国情调的祝福，浪漫、古典、又时尚。</p>
                        <p style="text-indent:2em;">带着您心爱的她踏上赴拉斯维加斯的旅程吧！我们在这里相约！</p>
                        <p style="text-indent:2em;"><b>特色婚礼推荐</b></p>
                        <p style="text-indent:2em;">A、教堂婚礼--不管预算的多少、喜好何种风格，在拉斯维加斯永远不愁找不到梦想中的结婚教堂。</p>
                        <p style="text-indent:2em;">B、户外婚礼--有些新人选择在距离拉斯维加斯50英哩的查尔斯顿峰举行一场罗曼蒂克的婚礼；有些则在密德湖上立下山盟海誓。</p>
                        <p style="text-indent:2em;">C、刺激冒险婚礼--喜爱刺激的新人们可以尝试小白教堂主人夏洛特理查斯（Charlotte Richards）精心设计的热汽球，遨翔于拉斯维加斯上空并交换盟约。</p>
                        <p style="text-indent:2em;">D、搭乘游艇沿著科罗拉多河，举行一个难忘的香槟游艇婚礼</p>
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