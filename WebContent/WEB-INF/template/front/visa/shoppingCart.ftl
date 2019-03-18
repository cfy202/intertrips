<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>订单支付</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script src="${ctx!}/assets/js/jquery-validate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/js/jquery-validate/localization/messages_zh.js"></script>
	<script src="${ctx!}/assets/js/jquery-validate/jquery.metadata.js"></script>
	<script src="${ctx!}/assets/js/jquery-validate/validate.expand.js"></script>
	<script type="text/javascript">
	 <#-- 遍历显示房间住客信息 
		$(document).ready(function() {
		 $("#kbooker").validate({
			errorPlacement : function(error, element) {
			if (element.is(":radio"))
			error.appendTo(element.parent());
			else if (element.is(":checkbox"))
			error.appendTo(element.parent().parent());
			else
			error.appendTo(element.parent().next());
			}
			
           });
		});
		-->
		var confirmOrder = function(){
		var isError = false;
		if(!$("#agreement_check").attr("checked")){
			alert('请阅读客户协议');
			isError = true;
			return;
		} 	
		$("#contacterForm").find("input").each(function(){
	    				type = $(this).attr("rule");
	    				if(type == 'required'){
		    				if($(this).val() == ''){
		    					isError = true;
		    					alert($(this).attr("warnMessage"));
		    					$(this).focus();
		    					return false;
		    				}
	    				}else if(type == 'number'){
	    					if(!new RegExp("^[0-9]{7,20}$").test($(this).val())){
	    						isError = true;
	    						alert($(this).attr("warnMessage"));
	    						$(this).focus();
	    						return false;
	    					}
	    				}else if(type == 'mail'){
	    					if(!new RegExp("^([a-zA-Z0-9\-_\.])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$").test($(this).val())){
	    						isError = true;
	    						alert($(this).attr("warnMessage"));
	    						$(this).focus();
	    						return false;
	    					}
	    				}
	    			});
	    			if(!isError){
	    			  $("#contacterForm").submit();
	    			}
	    			
		}
		
	</script>
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
       <a href="${ctx!}/"> <img src="${ctx!}/assets-web/logo/logo.png" width="147" height="74"></a>
    </div>
    <div class="pay_step right">
        <img src="${ctx!}/assets-web/images/pay_1.gif" width="324" height="58">
    </div>
    <div class="clear"></div>
</div>
<div class="widthCenter pay_main">
    <div class="pay_left_box left">
        <form name="" method="post" action="" >
            <div class="pay_main_left left shadow">
                <div class="pay_check">
                    <!--<b class="cart_b cart_check"><input id="" class="cart_cbox" type="checkbox" name="" ></b>-->
                    <span><a style="color:#333333;text-decoration:none; font-size:16px;" href="">${(product.name)!}（产品编号: ${(product.code)!}）</a></span>
                </div>
               
                <div class="pay_line"></div>
                <!--默认显示信息-->
                <div class="pay_info" >
                    <div class="pay_day">
                        <p class="left mr"><span class="pay_d1">出发日期:</span><span>${(bookOrderVO.departureDate)!}</span></p>
                        <p class="left mr"><span class="pay_d1">人数:</span><span>${(bookOrderVO.adultNum)!}</span></p>
                        <div class="clear"></div>
                    </div>
                    
                    
                    
                </div>
               
                
                <!--优惠方式-->
                <div class="pay_messages">
                    <!-- <p class="pay_messages_tit">优惠方式</p>-->
                    <div class="pay_line"></div>
                    <div class="pay_mess_main">
                       <!-- <div class="pay_yh">
                            <div class="yh_left left">
                                <label class="pay_d1">优惠方式:</label>
                                <select class="pay_yhfs">                                   

                                </select>
                            </div>                            

                            <div class="yh_right right">-¥123</div>
                            <div class="clear"></div>                            

                        </div>
                       --> 
                        <!--
                        <div class="pay_yh">
                            <div class="yh_left left">
                                <label class="pay_d1">使用积分:</label>
                                <input type="text" class="pay_jf">
                                <input type="button" class="pay_jf_btn" value="确定">
                                <p>您目前有<span class="colorOrange">150</span>积分 (<span class="colorOrange">$1.50</span>) , 本订单最高可用<span class="colorOrange">150</span>积分 (<span class="colorOrange">$1.50</span>) </p>
                            </div>
                            <div class="yh_right right">-¥123</div>
                            <div class="clear"></div>
                        </div>
                       --> 
                        <div class="clear"></div>
                    </div>
                    <div class="pay_mess_main">
                        <div class="pay_price right">
                           <!--p>应该支付:${(costVisa.code)!}&nbsp;${((bookOrderVO.totalPrice)?c)!}</p>-->
                            <!-- <p>使用积分优惠: ¥0</p>--> 
                            <p>实际支付: <span class="pay_price_s">${(costVisa.code)!}&nbsp;${((bookOrderVO.totalPrice)?c)!}</span></p>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <!--客户协议-->
            <div id="userAgreement" class="pay_main_left left shadow">
                    <input id="agreement_check" type="checkbox" class="agreement_check" checked>
                    我已经阅读文景假期提供的<a href="" class="colorBlue">客户协议</a>、<a href="" class="colorBlue">取消和修改条例</a>、<a href="" class="colorBlue">信用卡支付验证书</a>
               </p>
            </div>
           
        </form>
    </div>
    <div class="pay_main_box right" style="position:relative;">
        <div class="pay_main_right shadow">
            <form name="kbooker" id="contacterForm" class="validate" method="post" action="${ctx!}/front/visaorders/submitOrderInfo.do">
                <div class="posi">
                    <h4>联系人信息（必填）</h4>
                    <div class="ordering_info"> 
                        <span class="left">
                            <label>姓名<b style="color:red;">*</b></label>
                        </span> 
                        <span class="left">
                            <input maxlength="50" id="contacterName" name="name" rule="required" warnMessage="联系人的姓名不能为空" type="text" autocomplete="off" value="" >
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="ordering_info"> 
                        <span class="left">
                            <label>手机<b style="color:red;">*</b></label>
                        </span> 
                        <span class="left">
                            <input type="text" id="contacterMobile" name="tel" rule="number" warnMessage="请输入正确的联系人手机号" autocomplete="off" value=""  maxlength="15">
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="ordering_info"> 
                        <span class="left">
                            <label>邮箱<b style="color:red;">*</b></label>
                        </span> 
                        <span class="left">
                            <input maxlength="50" id="contacterMail" name="email" rule="mail" warnMessage="请输入正确的联系人邮箱地址" type="text" autocomplete="off" value="" >
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="order_total mb">
                        已选<span class="colorOrange">1</span> 件，合计 :<span class="colorOrange f24">${(costVisa.code)!}&nbsp;${((bookOrderVO.totalPrice)?c)!}</span>
                    </div>
                    <input type="hidden" name ="departuredate" value="${(bookOrderVO.departureDate)!}">
                    <input type="hidden" name ="adults" value="${(bookOrderVO.adultNum)!}">
                    <input type="hidden" name ="price" value="${((product.minprice)?c)!}">
                    <input type="hidden" name ="productid" value="${(product.id)!}">
                    <input type="hidden" name ="currencySign" value="${(costVisa.code)!}">
                    <input type="hidden" name ="quantity" value="1">
                    <input type="hidden" name ="score" value="${((product.score)?c)!}">
                    <input type="hidden" name ="totalprice" value="${((bookOrderVO.totalPrice)?c)!}">
                    <div class="mt10"><a onclick="confirmOrder();" class="btn_gocheck">确认订单</a></div>
                    <!--<div class="mt10"> <a href="javascript:goShopping();" class="btn_goon">继续购物</a> </div>-->
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div class="certificate widthCenter">
    <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #208037040 陕ICP备15009901号 西安淘游网络科技有限责任公司 </p>
</div>
</body>
<script>
	jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})

    $(function () {
        //添加，删除
    var addHtml = '<div class="pay_room_nums_d"><div class="left pay_room">房间<b>1</b></div><div class="left pay_room_people"><div class="left pay_adult_sel_nums">成人</div><select class="pay_adult_nums"><option value="0">成人</option><option value="1">1</option><option value="2" selected="">2</option><option value="3">3</option></select></div><div class="left pay_room_people"><div class="left pay_child_sel_nums" >儿童</div><select class="pay_child_nums"><option value="0">儿童</option><option value="1">1</option><option value="2">2</option><option value="3">3</option></select></div><div class="left"><a href="javascript:;" class="pay_check_more">[删除]</a></div><div class="clear"></div></div>'
    $(".pay_check_more_one").click(function () {
            $(".pay_room_nums:eq(0) .pay_room_nums_d:last").after(addHtml);
        })
        //$(this).find(".nav_left_pos").stop(false, false).slideUp(300);
        //$(".nav_left").mouseleave();
    $(".pay_check_more").live("click", function () {
        $(this).parents(".pay_room_nums_d").remove();
    });
	
 $(function () {
            //获取要定位元素距离浏览器顶部的距离
            var navH = $(".pay_main_box").offset().top;
            //计算left值
            var zhi = (($(window).width() - 1100)/2)+805;
            //滚动条事件
            $(window).scroll(function () {
                //获取滚动条的滑动距离
                var scroH = $(this).scrollTop();
                //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
                if (scroH >= navH) {
                    $(".pay_main_box").css({
                        "position": "fixed",
                        "top": 0,
                        "left":zhi
                    });
                } else if (scroH < navH) {
                    $(".pay_main_box").css({
                        "position": "static"
                    });
                }
            })
        })

});
<#-- 继续购物 -->
    var goShopping = function(){
    	window.location.href = "${ctx}";
    }
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>

</html>