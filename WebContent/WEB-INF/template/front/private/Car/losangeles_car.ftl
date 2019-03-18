<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>洛杉矶用车</title>
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
                    <li><a href="${ctx!}/private-tour/losangeles-bus.htm" class="private_tour_lfnav_current">洛杉矶用车</a></li>
                    <li><a href="${ctx!}/private-tour/newyork-bus.htm">纽约用车</a></li>
                    <li><a href="${ctx!}/private-tour/seattle-bus.htm">西雅图用车</a></li>
                </ul>
                <div class="private_tour_lfnav_bottom"><a href="${ctx!}/private-tour/wenjing-bus.htm">文景假期车队介绍</a></div>
                <div class="clear"></div>
            </div>
            <div class="private_tour_ribox right">
            	<div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">洛杉矶地区单程接送</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="6"><img src="${ctx!}/assets-web/images/private_tour_new/losangeles_car1.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$99</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>7座凯迪拉克</td>
                            <td>4</td>
                            <td>4</td>
                            <td>$180</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座VAN普通商务车</td>
                            <td>10</td>
                            <td>6</td>
                            <td>$200</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>14</td>
                            <td>8</td>
                            <td>$220</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>22</td>
                            <td>8</td>
                            <td>$320</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/次，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">便捷的中文接送服务，洛杉矶地区从LAX机场、火车站或其他指定地点单程接送。如有航班信息，请在“客人要求信息表”告知。</td>
                        </tr>
                        
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">洛杉矶市区包车一日</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="6"><img src="${ctx!}/assets-web/images/private_tour_new/losangeles_car2.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$280</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>7座凯迪拉克</td>
                            <td>4</td>
                            <td>4</td>
                            <td>$480</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座VAN普通商务车</td>
                            <td>10</td>
                            <td>6</td>
                            <td>$380</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>14</td>
                            <td>8</td>
                            <td>$480</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>22</td>
                            <td>8</td>
                            <td>$600</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">推荐行程：
<p>A、	盖蒂中心/圣塔莫妮卡一日游</p>
<p>B、	主题公园选一（迪斯尼/环球影城/诺氏百乐坊）</p>
<p>C、	星光大道/比弗利山庄/天文台</p>
<p>D、	名校包车一日游（加州大学洛杉矶分校UCLA/加州理工学院CAL TECH/南加州大学UCLA等）</p>
</td>
                        </tr>
                    </table>
                </div>
                <div class="private_tour_car_box">
                	<table cellpadding="0" cellspacing="0" border="0" class="private_tour_car_tab">
                    	<tr height="35">
                        	<th style="font-size:14px; width:225px; padding:5px 10px;">洛杉矶近郊一日游（当天往返）</th>
                            <th>车型</th>
                            <th>乘客数</th>
                            <th>行李位</th>
                            <th>价格</th>
                            <th>备注</th>
                            <th>超时费</th>
                        </tr>
                        <tr>
                        	<td rowspan="6"><img src="${ctx!}/assets-web/images/private_tour_new/losangeles_car3.jpg" style="margin-right:15px;"></td>
                            <td>7座商务车</td>
                            <td>6</td>
                            <td>2</td>
                            <td>$380</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>7座凯迪拉克</td>
                            <td>4</td>
                            <td>4</td>
                            <td>$620</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座VAN普通商务车</td>
                            <td>10</td>
                            <td>6</td>
                            <td>$480</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr>
                            <td>15座Benz豪华商务车</td>
                            <td>14</td>
                            <td>8</td>
                            <td>$620</td>
                            <td>司兼导</td>
                            <td>$60/小时</td>
                        </tr>
                        <tr class="private_tour_car_tr">
                            <td>25座中型巴士</td>
                            <td>22</td>
                            <td>8</td>
                            <td>$720</td>
                            <td>司机和导游</td>
                            <td>$100/小时</td>
                        </tr>
                        <tr>
                            <td colspan="6" align="left" style="padding-left:45px; border-bottom:1px solid #cccccc;">25座以上用车，请邮件联系客服。 服务时间1小时/天，超时现付。</td>
                        </tr>
                        <tr>
                            <td colspan="7" align="left" style="padding:10px 10px 0 290px;">
                            推荐行程：
<p>A、	圣塔芭芭拉一日游
早晨从LA出发, 约一个半小时车程 , 抵达范多拉码头村,您可乘坐游船环绕运河所建成的百万豪它区, 沿着运河行驶,届时 , 相信您会有彷佛置身于水都--威尼斯之感。 码头村还有风格独特的商店及礼品屋,沿街两边的露天咖啡座令人垂涎不已,更不可错过冰淇淋专卖店。 下午抵达圣塔芭芭拉, 参观被誉为全美国最美的政府大楼参观, 它是建于一九二九年的法院大厦 , 是北美独有的西班牙殖民复兴建筑风格颠峰之作,精致石雕像和拱门, 令人感觉仿如置身城堡内, 并可登上钟楼 360 度瞰望海天一色的市景。 稍作停留后。 转往圣塔芭芭拉博物馆参观,馆内藏有各式美国早期文物, 油画及古菫展示外, 并收藏有难得一见的大清帝国末代官服一套及中式祠堂, 令人印象深刻。</p><br>

<p>B、	棕榈泉奥特拉斯购物一日游
上午从洛杉矶出发，沿着十号公路东行直往加州最著名度假区棕榈泉，到达美国西海岸最大的工厂直销广场，这里集中了世界各地品牌110多家，每一年吸引上百万购物客。到达后导游会安排大约6小时停留购物及享用午餐。这里不但有流行的大众品牌，更有Burberry、Coach、A/X、Calvin Klein、Diesel、Christine Dior、DKNY、D& G、Polo、Giorgio Armani、Gucci、Hugo Boss、Levis、Tommy Hilfiger等一线高端品牌在此聚集，规模之大，品牌之全堪称美西之最。下午结束一天的疯狂购物之后，将客人送抵洛杉矶。</p><br>

<p>C、	圣地亚哥一日游
游览圣地亚哥军港登中途岛航母、远观海湾大桥。漫步墨西哥老城，它是一个美丽而充满乐趣的历史古城，是加州的诞生地，是西班牙早期殖民地，同时也是第一批欧洲人定居所。这里有150多个商店，屡获殊荣的餐厅，17个博物馆和历史遗迹。蒂华纳：近距离感受能歌善舞特的南美民族——墨西哥人。吉他和手风琴在他们手里，霎时就给您摆弄出花腔很浓的南美旋律。</p><br>

<p>D、	乐高乐园一日游
出发前往圣地亚哥北面号称美国最好的儿童乐园--- 乐高乐园，在这里您可以看到用小小的乐高块创造出来的奇迹。园内所有的游乐设施从亚马逊雨林、自由女神像、泰姬玛哈陵到古埃及，全是由一块块的乐高积木堆积而成。乐高迷可在飞车区乘坐火龙飞车或星际大战雪车飞翔于九宵之上。迷你乐园在园内的心脏区，有包含纽约在内的全美五大胜景，俨然就是一个小人国。爱因斯坦大头旁的建筑内，则有乐高机器人给年龄大些的朋友组合。还有快乐城内的乐高工厂带孩子们见识乐高玩具的生产线；附近还有乐高合唱团和惊奇屋，都相当有趣。</p>

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
    <#--
    <footer class="width100">
    	<div class="foot">
            <dl>
                <dt>新手入门</dt>
                <dd>
                    <a href="#" class="a3">预订指南</a>
                </dd>
                <dd>   
                    <a href="#" class="a3">服务中心</a>
                </dd>
                <dd>
                    <a href="#" class="a3">旅游资讯</a>
                </dd>
                <dd>
                    <a href="#" class="a3">会员积分</a>
                </dd>
            </dl>
            <dl>
                <dt>客服服务</dt>
                <dd>
                    <a href="#" class="a3">签证协助</a>
                </dd>
                <dd>   
                    <a href="#" class="a3">旅游保险</a>
                </dd>
                <dd>
                    <a href="#" class="a3">评价回访</a>
                </dd>
                <dd>
                    <a href="#" class="a3">问题咨询</a>
                </dd>
            </dl>
            <dl>
                <dt>预订须知</dt>
                <dd>
                    <a href="#" class="a3">客户协议</a>
                </dd>
                <dd>   
                    <a href="#" class="a3">隐私条例</a>
                </dd>
                <dd>
                    <a href="#" class="a3">信用卡支付验证书</a>
                </dd>
                <dd>
                    <a href="#" class="a3">取消和修改条例</a>
                </dd>
            </dl>
            <dl>
                <dt>关于我们</dt>
                <dd>
                    <a href="#" class="a3">关于我们</a>
                </dd>
                <dd>   
                    <a href="#" class="a3">联系我们</a>
                </dd>
                <dd>
                    <a href="#" class="a3">版权</a>
                </dd>
            </dl>
            <dl class="dl_last">
                <dt>邮件订阅</dt>
                <dd>
                	<p>为您提供最新的折扣与优惠信息</p>
                    <input type="text" class="foot_email" name="rssemail" placeholder="请输入您的邮箱地址" value="">
                    <input type="button" class="foot_btn"  value="订阅" >
                </dd>
            </dl>
            <div class="clear"></div>
    	</div>
    </footer>
    <div class="certificate widthCenter">
    	<img src="images/footer_icon.png" width="501" height="41">
        <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #208037040   西安淘游网络科技有限责任公司 </p>
    	
    </div>
    <div class="asid_share" id="asid_share">
	<div class="asid_share_box">
		<a href="https://wpa.qq.com/msgrd?v=3&uin=2790820042&site=qq&menu=yes"><img alt="QQ客服" title="QQ客服" class="adid_icon" src="background/right_2.png"></a>
	</div>
	<div class="asid_share_box">
		<a href="#"><img alt="联系我们" title="联系我们" class="adid_icon" src="background/right_1.png"></a>
		<div class="asid_share_triangle">
			<em class="border_sj">&#9670;</em>
	    	<span class="con_sj">&#9670;</span>
    	</div>
		<div class="asid_sha_layer" style="width:200px;">
			<p class="asid_sha_num">北京：400-071-0197</p>
            <p class="asid_sha_num">西安：400-071-0197</p>
            <p class="asid_sha_num">苏州：400-071-0197</p>
		</div>
	</div>
	<div class="asid_share_box">
		<a href="#"><img alt="扫二微码" title="扫二微码" class="adid_icon" src="background/right_3.png" style="margin-top:5px;"></a>
		<div class="asid_share_triangle" style="display:none;">
			<em class="border_sj">&#9670;</em>
	    	<span class="con_sj">&#9670;</span>
    	</div>
        <div class="asid_sha_layer" style="width:150px;display:none;">
			<p class="sweep_img"><img src="images/weixin_1.jpg" ></p>
            <p class="sweep_img"><img src="images/weixin_2.jpg" ></p>
			<p class="pb6"><b>扫一扫二维码图案</b></p>
		</div>
	</div>
	<div class="asid_share_box" style="display:none;" >
		<a href="#" class="asid_share_fhtop"><img alt="返回顶部" title="返回顶部" class="adid_icon" src="background/right_4.png"></a>
	</div>
</div>
	-->
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