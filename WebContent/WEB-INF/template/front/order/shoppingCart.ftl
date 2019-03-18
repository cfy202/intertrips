<!DOCTYPE html>
<html>
<head>
	<#assign ctx = request.contextPath />
    <meta charset="utf-8">
    <title>信息填写</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/orderModule.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js"/></script>
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
        	<img src="${ctx!}/assets-web/logo/logo.png" >
        </a>
    </div>
    <div class="pay_step right">
        <img src="${ctx!}/assets-web/images/pay_1.gif" width="324" height="58">
    </div>
    <div class="clear"></div>
</div>
<div id="emptyShoppingCart" class="widthCenter" style="display:none">
    <div class="Payment_cart_area shadow">
        <p class="cart_area">您的购物车为空，快去选择旅行团吧。</p>
        <div class="cart_area_btn"><input type="button" value="返回首页"  class="Payment_Method_btn_1"></div>
    </div>
    <div class="clear"></div>
</div>
<div class="widthCenter pay_main">
    <div class="pay_left_box left">
        <form>
        	<#list loadOrderInfoVO.eachProductInfoVOList as eachProductInfoVO>
	            <div class="pay_main_left left shadow">
	                <div class="pay_check">
	                    <b id="checkProduct_${eachProductInfoVO_index}" class="cart_b cart_check" shoppingCartId="${eachProductInfoVO.shoppingCartId}" productId="${eachProductInfoVO.product.id}" days="${eachProductInfoVO.days}" onclick="checkProduct(this,${eachProductInfoVO_index});"><input class="cart_cbox" type="checkbox" name=""></b>
	                    <span><a class="a2" style="text-decoration:none; font-size:22px;" href="${ctx!}${eachProductInfoVO.product.pagePageid.filepath}">${eachProductInfoVO.product.name}（产品编号: ${eachProductInfoVO.product.code}）</a></span>
	                </div>
	                <div class="close_btn_dw">
	                	<img src="${ctx!}/assets-web/images/icon_close.png" width="16" height="15">
	                </div>
	                <div class="pay_line"></div>
	                <!--默认显示信息-->
	                <div id="simpleShow_${eachProductInfoVO_index}" class="pay_info">
	                    <div class="pay_day">
	                        <p class="left mr"><span class="pay_d1">出发日期:</span><span id="departureDate_${eachProductInfoVO_index}" class="colorOrange departureDate">${eachProductInfoVO.departureDate}</span></p>
	                        <p class="left"><span class="pay_d1">结束日期:</span><span id="endDate_${eachProductInfoVO_index}" class="colorOrange endDate">${eachProductInfoVO.endDate}</span></p>
	                        <div class="clear"></div>
	                    </div>
	                    <div class="pay_day">
	                        <p class="left"><span class="pay_d1">预订房间:</span></p>
	                        <div id="roomInfoArea_${eachProductInfoVO_index}" class="left">
	                        	<#list eachProductInfoVO.roomInfoList?sort_by("roomNum") as roomInfo>
		                        	<div class="pay_room_1">
				                        <p class="left mr"><span class="colorOrange">房间${roomInfo.roomNum}</span></p>
				                        <p class="left"><span class="pay_d1">成人:</span><span class="pay_d1 colorOrange">${roomInfo.adultNum}</span></p>
				                        <p class="left"><span class="pay_d1">儿童:</span><span class="pay_d1 colorOrange">${roomInfo.childNum}</span></p>
				                        <div class="clear"></div>
			                        </div>
		                        </#list>
	                        </div>	                        
	                        <div class="clear"></div>
	                    </div>
	                    <#if (eachProductInfoVO.selectedAirportPickUp)??>
	                    <div class="pay_day">
	                        <p class="left mr"><span class="pay_d1">机场接送:</span><span class="colorOrange" id="airportPickUpShow_${eachProductInfoVO_index}">
		                        		${(eachProductInfoVO.selectedAirportPickUp.title)!}
		                        		<#if eachProductInfoVO.selectedAirportPickUp.id != '12345678'>
			                        		<#if eachProductInfoVO.selectedAirportPickUp.price?? && eachProductInfoVO.selectedAirportPickUp.price = 0>
			                        			免费接送
			                        		<#else>
			                        			USD${(eachProductInfoVO.selectedAirportPickUp.price)!}
			                        		</#if>
		                        		</#if>
	                        	</span></p>
	                        <div class="clear"></div>            	
	                    </div>
	                    </#if>
	                    <#if (eachProductInfoVO.selectedDeparture)??>
		                    <div class="pay_day">
		                        <p class="left mr"><span class="pay_d1">接送机地点:</span><span class="colorOrange" id="departureSpan_${eachProductInfoVO_index}">${eachProductInfoVO.selectedDeparture.name} ${eachProductInfoVO.selectedDeparture.city}</span></p>
		                        <div class="clear"></div>
		                    </div>
	                    </#if>
	                    <#if (eachProductInfoVO.selectedAirTicketPrice)??>
		                    <div class="pay_day">
		                        <p class="left mr"><span class="pay_d1">机票:</span><span class="colorOrange" id="airTicketPriceSpan_${eachProductInfoVO_index}">${eachProductInfoVO.selectedAirTicketPrice.departureName} USD${eachProductInfoVO.selectedAirTicketPrice.price}</span></p>
		                        <div class="clear"></div>
		                    </div>
	                    </#if>
	                    <div class="pay_revise">
	                        <span class="pay_revise_btn"><a onclick="modifyButton(this);">修改</a></span>
	                    </div>
	                    <div class="clear"></div>
	                </div>
	                <!--修改信息-->
	                <div id="detailShow_${eachProductInfoVO_index}" class="pay_info" style="display:none;">
	                    <div class="pay_day">
	                        <p class="left mr"><span class="pay_d1">出发日期:</span><input id="departureDateInput_${eachProductInfoVO_index}" type="text" class="pay_day_input" readOnly="readOnly" value="${eachProductInfoVO.departureDate}"><#--<span class="pay_rili_icon">--></p>
	                        <p class="left"><span class="pay_d1">结束日期:</span><span id="endDateInput_${eachProductInfoVO_index}">${eachProductInfoVO.endDate}</span></p>
	                        <div class="clear"></div>
	                    </div>
	                    <div>
		                    <div id="calendar_${eachProductInfoVO_index}" tourlineId="${eachProductInfoVO.tourlineId}">
		                    	${(eachProductInfoVO.tourCalendar)!}
		                    </div>
		                    <#-->
		                    <div class="new_calendar_con">温馨提示：建议提早订购时间<span class="colorBlue">45天</span></div> 
		                    -->
	                    </div> 
	                    <div class="pay_day roomInfo_${eachProductInfoVO_index}">
	                        <p class="left"><span class="pay_d1">预订房间:</span></p>
	                        <div class="pay_room_nums left">
	                        	<#list eachProductInfoVO.roomInfoList?sort_by("roomNum") as roomInfo>
		                            <div class="pay_room_nums_d" style="margin-bottom:5px;">
		                                <div class="left pay_room">房间<b class="roomNum">${roomInfo.roomNum}</b>
		                                </div>
		                                <div class="left pay_room_people">
		                                    <div class="left pay_adult_sel_nums ">成人</div>
		                                    <select class="pay_adult_nums passenger_num" onchange="changeNum(this,${eachProductInfoVO_index});" id="roomInfoAdultSelect_${eachProductInfoVO_index}_${roomInfo_index}">
		                                        <option value="1">1</option>
		                                        <option value="2" selected="">2</option>
		                                        <option value="3">3</option>
		                                        <option value="4">4</option>
		                                    </select>
		                                </div>
		                                <div class="left pay_room_people">
		                                    <div class="left pay_child_sel_nums ">儿童</div>
		                                    <select class="pay_child_nums passenger_num" onchange="changeNum(this,${eachProductInfoVO_index});" id="roomInfoChildrenSelect_${eachProductInfoVO_index}_${roomInfo_index}">
		                                        <option value="0">0</option>
		                                        <option value="1">1</option>
		                                        <option value="2">2</option>
		                                        <option value="3">3</option>
		                                    </select>
		                                </div>
		                                <div class="clear"></div>
		                            </div>
	                            </#list>
	                            <div class="left pay_room_btn" id="afterRoomInfo_${eachProductInfoVO_index}"><a href="javascript:;" productIndex="${eachProductInfoVO_index}" class="pay_check_more_one">+添加房间</a>
	                            </div>
	                            <div class="clear"></div>
	                        </div>
	                        <div class="clear"></div>
	                    </div>
	                    <#if (eachProductInfoVO.selectedAirportPickUp)??>
	                    <div class="pay_day">
	                        <p class="left pay_d1"><span class="shopping_cart_title">机场接送:</span></p>
	                        <p>
	                            <select id="airportPickUpSelect_${eachProductInfoVO_index}" class="pay_pick_up">
                            		<option value="12345678" price="0">不使用该服务</option>
	                            	<#list eachProductInfoVO.airportPickUpList as airportPickUp>
	                            		 <option value="${(airportPickUp.id)!}" price="${(airportPickUp.price)!}" 
	                            		 <#if eachProductInfoVO.selectedAirportPickUp.id == airportPickUp.id>selected="selected"</#if>>
	                            	   	 	${(airportPickUp.title)!}
	                            	   	 <#if airportPickUp.price?? && airportPickUp.price == 0>
	                            	   	 	免费接送
	                            	   	 <#else>
	                            	   	 	USD${(airportPickUp.price)!}
	                            	   	 </#if>
	                            	   	 </option> 
	                            	</#list>
	                            </select>
	                        </p>
	                        <div class="clear"></div>
	                    </div>
	                    </#if>
	                    <#if (eachProductInfoVO.selectedDeparture)??>
	                    <div class="pay_day">
	                        <p class="left pay_d1"><span class="shopping_cart_title">接送机地点:</span></p>
	                        <p>
	                            <select id="pickUp_${eachProductInfoVO_index}" class="pay_pick_up">
	                            	<#list eachProductInfoVO.departureList as departure>
	                            		<option value="${departure.id}" <#if eachProductInfoVO.selectedDeparture.id == departure.id>selected="selected"</#if>>${departure.name} ${departure.city}</option>
	                            	</#list>
	                            </select>
	                        </p>
	                        <div class="clear"></div>
	                    </div>
	                    </#if>
	                    <#if (eachProductInfoVO.selectedAirTicketPrice)??>
	                    <div class="pay_day">
	                        <p class="left pay_d1"><span class="shopping_cart_title">选择机票:</span></p>
	                        <p>
	                            <select id="airTicketPriceSelect_${eachProductInfoVO_index}" class="pay_pick_up">
	                            	<#list eachProductInfoVO.airTicketPriceList as airTicketPrice>
	                            		<option value="${airTicketPrice.id}" price="${airTicketPrice.price}" <#if eachProductInfoVO.selectedAirTicketPrice.id == airTicketPrice.id>selected="selected"</#if>>${airTicketPrice.departureName} USD${airTicketPrice.price}</option>
	                            	</#list>
	                            </select>
	                        </p>
	                        <div class="clear"></div>
	                    </div>
	                    </#if>
	                    <div class="pay_day_2">
	                        <input type="button" class="pay_btn" onclick="comfirmRoomInfo(${eachProductInfoVO_index});" value="确定">
	                        <div class="clear"></div>
	                    </div>
	                    <div class="pay_rili" >
	                        <div class="" style="display:none;">
	                            <div class="clear"></div>
	                        </div>
	                    </div>
	                    <div class="clear"></div>
	                </div>
	                <!--填写客户信息-->
	                <div id="roomInfo_${eachProductInfoVO_index}" class="pay_messages steps_dd pay_un_check pay_bg">	                
	                    <div id="pay_messages_steps" class="pay_messages_tit"><span class="pay_span">顾客信息</span><b class="pay_messages_tit_b"></b></div>
	                    <!--<div class="pay_line"></div>-->
	                    <div id="pay_messages_list0"  class="airport_list" style="display: none;">
	                    <#list 1..eachProductInfoVO.adultNum as i>
              		        <div class="roomInfo adultRoom">
                   		    <div class="pay_mess_left left"><i class="pay_mess_left_num customerNum">${i}</i>成人</div>   
	                        <div class="pay_m left">
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">姓 <b style="color:red;"></b></label>
	                                    <input type="text" maxlength="25" warnMessage="顾客的姓不能为空." rule="required" class="pay_mess_input lastNameInput" >
	                                </p>
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">名 <b style="color:red;"></b></label>
	                                    <input type="text" maxlength="25" warnMessage="顾客的名不能为空." rule="required" class="pay_mess_input firstNameInput" >
	                                </p>
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">性别 <b style="color:red;"></b></label>
	                                    <select class="pay_adult_nums genderSelect">
	                                        <option value="0">男</option>
	                                        <option value="1">女</option>
	                                    </select>
	                                </p>	                                
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">生日 <b style="color:red;"></b></label>
	                                    <input type="text" warnMessage="顾客的生日不能为空." rule="required" onClick="WdatePicker({startDate:'{%y-20}-01-01',maxDate:'{%y-11}-12-31'});" readOnly="readOnly" class="pay_mess_input Wdate birthdayInput">
	                                </p>
	                                <div class="clear"></div>
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">手机 <b style="color:red;"></b></label>
	                                    <input type="text" maxlength="15" warnMessage="请输入正确的手机号." rule="number" class="pay_mess_input mobileNumberInput">
	                                </p>
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">国籍</label>
	                                    <input type="text" maxlength="25" class="pay_mess_input nationalityInput">
	                                </p>
	                                <p class="left pay_mess_people">
	                                    <label class="left pay_adult_sel_nums_1">护照号</label>
	                                    <input type="text" maxlength="15" class="pay_mess_input passportNoInput">
	                                </p>
	                                <p class="left pay_mess_people">
	                                	<label class="left pay_adult_sel_nums_1">护照有效期</label>
	                                	<input type="text" onClick="WdatePicker({minDate:'%y-%M-%d'});" readOnly="readOnly" class="pay_mess_input Wdate passportNoExpiryDateInput" >
	                                </p>
	                        </div>
	                        <div class="clear"></div>
	                    </div>
	                    </#list>
	                    <#if (eachProductInfoVO.childrenNum >= 1)>
		                    <#list 1..eachProductInfoVO.childrenNum as i>
						    <div class="roomInfo childrenRoom">
						        <div class="pay_mess_left left"><i class="pay_mess_left_num customerNum">${i + eachProductInfoVO.adultNum}</i>儿童</div>
						        <div class="pay_m left">
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">姓 <b style="color:red;"></b></label>
						                    <input type="text" maxlength="25" warnMessage="顾客的姓不能为空." rule="required" class="pay_mess_input lastNameInput" >
						                </p>
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">名 <b style="color:red;"></b></label>
						                    <input type="text" maxlength="25" warnMessage="顾客的名不能为空." rule="required" class="pay_mess_input firstNameInput" >
						                </p>
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">性别 <b style="color:red;"></b></label>
						                    <select class="pay_adult_nums genderSelect">
						                        <option value="0">男</option>
						                        <option value="1">女</option>
						                    </select>
						                </p>
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">生日 <b style="color:red;"></b></label>
						                    <input type="text" warnMessage="顾客的生日不能为空." rule="required" onClick="WdatePicker({startDate:'{%y-10}-01-01',minDate:'{%y-10}-01-01',maxDate:'%y-%M-%d',onpicked:calculatePrice});" readOnly="readOnly" class="pay_mess_input Wdate birthdayInput">
						                </p>
						                <div class="clear"></div>
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">手机 <b style="color:red;"></b></label>
						                    <input type="text" maxlength="15" rule="number" warnMessage="请输入正确的手机号." class="pay_mess_input mobileNumberInput">
						                </p>
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">国籍</label>
						                    <input type="text" maxlength="25" class="pay_mess_input nationalityInput">
						                </p>
						                <p class="left pay_mess_people">
						                    <label class="left pay_adult_sel_nums_1">护照号 </label>
						                    <input type="text" maxlength="15" class="pay_mess_input passportNoInput">
						                </p>
		                                <p class="left pay_mess_people">
		                                	<label class="left pay_adult_sel_nums_1">护照有效期</label>
		                                	<input type="text" onClick="WdatePicker({minDate:'%y-%M-%d'});" readOnly="readOnly" class="pay_mess_input Wdate passportNoExpiryDateInput">
		                                </p>
						        </div>
						        <div class="clear"></div>
						    </div>  
		                    </#list>
	                    </#if>
	                    </div>
	                    <div class="clear"></div>
	                </div>
	                <!-- 优惠方式 -->
	                <div class="pay_messages">
	                    <p class="pay_messages_tit">优惠方式</p>                    
	                    <div class="pay_line"></div>
	                    <div class="pay_mess_main">
	                    <#if loadOrderInfoVO.scoreLevelArray?has_content>
	                        <div class="pay_yh">
	                            <div class="yh_left left">
	                                <label class="pay_d1">使用积分:</label>
	                                <select id="scoreSelect" class="pay_yhfs" onchange="scoreChange();">
	                                <#list loadOrderInfoVO.scoreLevelArray as chooseLevel>
	                                	<option value="${chooseLevel}">${chooseLevel}</option>
	                                </#list>
	                                </select>
	                                <input id="useScoreComfirm" type="button" class="pay_jf_btn" value="确定">
	                            </div>
	                            <div id="showScore" class="yh_right right" currency="${eachProductInfoVO.currencySign}">-${eachProductInfoVO.currencySign}0</div>
	                            <div class="clear"></div>
	                        </div>
	                       </#if>
	                        <#if eachProductInfoVO.couponseList?has_content>
	                        <div class="pay_yh">
	                            <div class="yh_left left">
	                                <label class="pay_d1">用优惠券:</label>
	                                <input type="text" value="" id="uscouponse" class="pay_jf">
	                                <input type="button" id="couponseComfrim" class="pay_jf_btn" value="确定" onclick="exchange()">
	                                <#--<p>您目前有<span class="colorOrange">0</span>积分 (<span class="colorOrange">${eachProductInfoVO.currencySign}0.00</span>) , 本订单最高可用<span class="colorOrange">0</span>积分 (<span class="colorOrange">${eachProductInfoVO.currencySign}0.00</span>) </p>-->
	                            </div>
	                            <div class="yh_right right" id ="uscouponseShow">-${eachProductInfoVO.currencySign}0</div>
	                            <div class="clear"></div>
	                        </div>
	                        </#if> 
	                        <div class="clear"></div>
	                    </div>
	                    
	                    <div class="pay_mess_main">
	                    	<span class="left" style="margin-right: 40px; width: 50px; display: inline-block; text-align: center;">备注</span>
							<textarea id="orderRemark" style="width: 515px; height: 42px; float:left;"></textarea>
							<div class="clear"></div>
	                    </div>
	                    <div class="pay_mess_main">
	                        <div class="pay_price right">
	                            <div style="margin-bottom: 5px;"><span class="left">应该支付: </span><span class="right">${eachProductInfoVO.currencySign}<i id="shouldPay_${eachProductInfoVO_index}" class="shouldPay"></i></span><div class="clear"></div></div>
	                            <div style="margin-bottom: 20px;"><span class="left">使用积分优惠:</span><span class="right">${eachProductInfoVO.currencySign}<span id="scorePrivilege">0</span></span><div class="clear"></div></div>
	                            <div style="margin-bottom: 20px;"><span class="left">使用优惠券优惠:</span><span class="right">${eachProductInfoVO.currencySign}<span id="couponseUs">0</span></span><div class="clear"></div></div>
	                           
	                            <div><span class="left">实际支付: </span><span class="pay_price_s right">${eachProductInfoVO.currencySign}<i class="actualDelivery" id="actualDelivery_${eachProductInfoVO_index}"></i></span></div>
	                        	<div class="clear"></div>
	                        </div>
	                        <div class="clear"></div>
	                    </div>
	                </div>
	            </div>
            </#list>
            <!--客户协议-->
            <div id="userAgreement" class="pay_main_left left shadow">
                    <input id="agreement_check" type="checkbox" class="agreement_check" checked="checked">
                    我已经阅读文景假期提供的<a href="#" class="colorBlue">客户协议</a>、<a href="#" class="colorBlue">取消和修改条例</a>、<a href="#" class="colorBlue">信用卡支付验证书</a>
               </p>
            </div>
        </form>
    </div>
    <div class="right pay_main_box" style="position:relative;">
        <div class="pay_main_right shadow">
            <form id="contacterForm" name="kbooker" method="post" action="">
                <div class="posi">
                    <h4>联系人信息（必填）</h4>
                    <div class="ordering_info"> 
                        <span class="left">
                            <label>姓名<b style="color:red;">*</b></label>
                        </span> 
                        <span class="left">
                            <input maxlength="50" id="contacterName" rule="required" warnMessage="联系人的姓名不能为空" type="text" autocomplete="off" value="" >
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="ordering_info"> 
                        <span class="left">
                            <label>手机<b style="color:red;">*</b></label>
                        </span> 
                        <span class="left">
                            <input type="text" id="contacterMobile" rule="number" warnMessage="请输入正确的联系人手机号" autocomplete="off" value=""  maxlength="15">
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="ordering_info"> 
                        <span class="left">
                            <label>邮箱<b style="color:red;">*</b></label>
                        </span> 
                        <span class="left">
                            <input maxlength="50" id="contacterMail" rule="mail" warnMessage="请输入正确的联系人邮箱地址" type="text" autocomplete="off" <#if (loadOrderInfoVO.orderContacter)??>value="${loadOrderInfoVO.orderContacter.email}"</#if>>
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="order_total mb">
			         <#--              
			                        已选<span class="colorOrange" id="orderNumber">0</span> 件，-->
                                                       合计 :<span class="colorOrange" style="font-size:20px;"><#if (loadOrderInfoVO.eachProductInfoVOList[0])??>${loadOrderInfoVO.eachProductInfoVOList[0].currencySign}</#if><i id="totalPriceShow">0</i></span>
                    </div>
                    <div class="mt10"> <a href="javascript:confirmOrder();" class="btn_gocheck">确认订单</a></div>
                    <!--
                    <div class="mt10"> <a href="javascript:goShopping();" class="btn_goon">继续购物</a> </div>
                     -->
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div style="display:none;">
    <div id="adultTemplate" class="roomInfo adultRoom">
	    <div class="pay_mess_left left"><i class="pay_mess_left_num customerNum"></i>成人</div>   
		    <div class="pay_m left">
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">姓 <b style="color:red;"></b></label>
		                <input type="text" maxlength="25" warnMessage="顾客的姓不能为空." rule="required" class="pay_mess_input lastNameInput">
		            </p>
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">名 <b style="color:red;"></b></label>
		                <input type="text" maxlength="25" warnMessage="顾客的名不能为空." rule="required" class="pay_mess_input firstNameInput">
		            </p>
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">性别 <b style="color:red;"></b></label>
		                <select class="pay_adult_nums genderSelect">
		                    <option value="0">男</option>
		                    <option value="1">女</option>
		                </select>
		            </p>	                                
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">生日 <b style="color:red;"></b></label>
		                <input type="text" warnMessage="顾客的生日不能为空." rule="required" onClick="WdatePicker({startDate:'{%y-20}-01-01',maxDate:'{%y-11}-12-31'});" readOnly="readOnly" class="pay_mess_input Wdate birthdayInput">
		            </p>
		            <div class="clear"></div>
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">手机 <b style="color:red;"></b></label>
		                <input type="text" maxlength="15" warnMessage="请输入正确的手机号." rule="number" class="pay_mess_input mobileNumberInput">
		            </p>
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">国籍 </label>
		                <input type="text" maxlength="25" class="pay_mess_input nationalityInput">
		            </p>
		            <p class="left pay_mess_people">
		                <label class="left pay_adult_sel_nums_1">护照号 </label>
		                <input type="text" maxlength="15" class="pay_mess_input passportNoInput">
		            </p>
		            <p class="left pay_mess_people">
		            	<label class="left pay_adult_sel_nums_1">护照有效期 </label>
		            	<input type="text" onClick="WdatePicker({minDate:'%y-%M-%d'});" readOnly="readOnly" class="pay_mess_input Wdate passportNoExpiryDateInput" >
		            </p>
		    </div>
    	<div class="clear"></div>
	</div>
    <div id="childrenTemplate" class="roomInfo childrenRoom">
        <div class="pay_mess_left left"><i class="pay_mess_left_num customerNum"></i>儿童</div>  
        <div class="pay_m left">
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">姓 <b style="color:red;"></b></label>
                    <input type="text" maxlength="25" warnMessage="顾客的姓不能为空." rule="required" class="pay_mess_input lastNameInput">
                </p>
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">名 <b style="color:red;"></b></label>
                    <input type="text" maxlength="25" warnMessage="顾客的名不能为空." rule="required" class="pay_mess_input firstNameInput">
                </p>
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">性别 <b style="color:red;"></b></label>
                    <select class="pay_adult_nums genderSelect">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select>
                </p>
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">生日 <b style="color:red;"></b></label>
                    <input type="text" warnMessage="顾客的生日不能为空." rule="required" onClick="WdatePicker({startDate:'{%y-10}-01-01',minDate:'{%y-10}-01-01',maxDate:'%y-%M-%d',onpicked:calculatePrice});" readOnly="readOnly" class="pay_mess_input Wdate birthdayInput">
                </p>
                <div class="clear"></div>
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">手机 <b style="color:red;"></b></label>
                    <input type="text" maxlength="15" warnMessage="请输入正确的手机号." rule="number" class="pay_mess_input mobileNumberInput">
                </p> 
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">国籍 </label>
                    <input type="text" maxlength="25" class="pay_mess_input nationalityInput">
                </p>
                <p class="left pay_mess_people">
                    <label class="left pay_adult_sel_nums_1">护照号 </label>
                    <input type="text" maxlength="15" class="pay_mess_input passportNoInput">
                </p>
                <p class="left pay_mess_people">
                	<label class="left pay_adult_sel_nums_1">护照有效期</label>
                	<input type="text" onClick="WdatePicker({minDate:'%y-%M-%d'});" readOnly="readOnly" class="pay_mess_input Wdate passportNoExpiryDateInput">
                </p>
        </div>
        <div class="clear"></div>
    </div> 
	<div id="roomInfoTemplate" class="pay_room_1">
	    <p class="left mr"><span>房间<i class="roomNumber"></i></span></p>
	    <p class="left"><span class="pay_d1">成人:</span><span class="pay_d1 adultNum colorOrange"></span></p>
	    <p class="left"><span class="pay_d1">儿童:</span><span class="pay_d1 childrenNum colorOrange"></span></p>
	    <div class="clear"></div>
	</div>
</div>
<div class="certificate widthCenter">
    <p>Copyright &copy; 2013-2014 All Rights Reserved - California Seller of Travel #2080370 陕ICP备15009901号 西安淘游网络科技有限责任公司 </p>
</div>
<form id="dataForm" method="POST" action="${ctx}/submit_order.htm" style="display:none;">
</form>
<#include "/front/include/alertFrame.ftl"/>
</body>
<script>
	javascript:window.history.forward(1); 
	
	<#-- 汇率 -->
	var exchangeRate = parseFloat('${loadOrderInfoVO.exchangeRate}');
	
	<#-- 存储房间住客信息(为二维数组,第一维为产品,第二维为房间) -->
	var roomInfoTotalArray = new Array();
	
	<#-- 存储线路价格(为产品列表) -->
	var tourPriceArray = new Array();
	
	<#-- 储存出发日期所属的tourdate的Id -->
	var tourDateIdArray = new Array();
	
	<#-- 自费和小费之和(每人价格)(已转为当前币种) -->
	var tourlineTipArray = new Array();	
	
	<#-- 基本团费 -->
	var baseTourPriceArray = new Array();
	
	<#-- 自费和消费之和(已转为当前币种) -->
	var eachProductTips = new Array();
	
	<#-- 接送机价格(已转为当前币种) -->
	var selectedAirportPickUpPriceArray = new Array();

	<#-- 已选中的机票价格(已转为当前币种) -->
	var selectedAirTicketPriceArray = new Array();
	
	<#-- 储存子订单的价格,纯数字,无符号 -->
	var orderPriceArray = new Array();
	
	<#-- 用户选择的积分 -->
	var chooseScore = 0;
	
	<#-- 优惠券code -->
	var couponseCode = '';
	
	<#-- 用户确定的积分 -->
	var conformScore = 0;

	<#-- 用积分兑换的价格 -->
	var exchangedPrice = 0;
	
	<#-- 当前币种 -->
	var courrysign = '';

	<#-- 储存总价格 -->
	var totalPrice = 0;        

	<#-- 产品数量的展示 -->
   	//var $orderNumber = $("#orderNumber");
	
	<#-- 总价格的展示 -->
	var $totalPriceShow = $("#totalPriceShow");
	
	<#list loadOrderInfoVO.eachProductInfoVOList as eachProductInfoVO>
	   courrysign = '${eachProductInfoVO.currencySign}';
		<#-- 把每个产品的价格信息储存起来 -->
		tourPriceArray[${eachProductInfoVO_index}] = new TourPrice(${eachProductInfoVO.stringTourPrice.sellingprice},${eachProductInfoVO.stringTourPrice.singleroomprice},${eachProductInfoVO.stringTourPrice.nobedprice},${eachProductInfoVO.stringTourPrice.threesellingprice},${eachProductInfoVO.stringTourPrice.foursellingprice},${eachProductInfoVO.stringTourPrice.extrabedprice},${eachProductInfoVO.stringTourPrice.babyPrice},${eachProductInfoVO.stringTourPrice.childPrice});
		tourPriceArray[${eachProductInfoVO_index}].currencySign = '${eachProductInfoVO.currencySign}';
		tourDateIdArray[${eachProductInfoVO_index}] = '${eachProductInfoVO.tourDateId}';
		
		<#-- 储存小费自费(已转为当前币种) -->
		tourlineTipArray[${eachProductInfoVO_index}] = parseFloat('${eachProductInfoVO.tourlineTips}');
		
		<#-- 把每个产品所对应的房间住客信息储存起来 -->
		roomInfoArray = new Array();
		<#list eachProductInfoVO.roomInfoList?sort_by("roomNum") as roomInfo>
			<#-- 把该产品下每个房间的住客信息储存起来 -->
			roomInfoArray[${roomInfo_index}] = new RoomInfo(${roomInfo.roomNum},${roomInfo.adultNum},${roomInfo.childNum});
			
			<#-- 根据获取的数值将页面上的大人和儿童选项进行复选 -->
			$("#roomInfoAdultSelect_${eachProductInfoVO_index}_${roomInfo_index}").val(${roomInfo.adultNum});
			$("#roomInfoChildrenSelect_${eachProductInfoVO_index}_${roomInfo_index}").val(${roomInfo.childNum});
		</#list>
		roomInfoTotalArray[${eachProductInfoVO_index}] = roomInfoArray;
		
		<#-- 储存接送机的价格(已转为当前币种) -->
		<#if (eachProductInfoVO.selectedAirportPickUp)??>
			selectedAirportPickUpPriceArray[${eachProductInfoVO_index}] = parseFloat(cancelFormat('${eachProductInfoVO.selectedAirportPickUp.price}')) * exchangeRate * parseInt('${eachProductInfoVO.totalNum}');	
		<#else>
			selectedAirportPickUpPriceArray[${eachProductInfoVO_index}] = 0;
		</#if>
		
		<#-- 储存机票价格(已转为当前币种) -->
		<#if (eachProductInfoVO.selectedAirTicketPrice)??>
			selectedAirTicketPriceArray[${eachProductInfoVO_index}] = parseFloat(cancelFormat('${eachProductInfoVO.selectedAirTicketPrice.price}')) * exchangeRate * parseInt('${eachProductInfoVO.totalNum}');
		<#else>
			selectedAirTicketPriceArray[${eachProductInfoVO_index}] = 0;
		</#if>

		<#-- 根据产品价格和住客情况计算每个产品的价格并显示到页面 -->
		baseTourPriceArray[${eachProductInfoVO_index}] = fnGenerateTotalPriceSimple(roomInfoArray,tourPriceArray[${eachProductInfoVO_index}]);
		eachProductTips[${eachProductInfoVO_index}] = parseInt('${eachProductInfoVO.totalNum}') * tourlineTipArray[${eachProductInfoVO_index}];
		eachProductPrice = baseTourPriceArray[${eachProductInfoVO_index}] + eachProductTips[${eachProductInfoVO_index}] + selectedAirportPickUpPriceArray[${eachProductInfoVO_index}] + selectedAirTicketPriceArray[${eachProductInfoVO_index}];
		
		<#-- 保存每个产品的总价格  -->	
		orderPriceArray[${eachProductInfoVO_index}] = Math.ceil(eachProductPrice);
		
		totalPrice += orderPriceArray[${eachProductInfoVO_index}];
		
		<#-- 每个产品的应该支付价格  -->
		$("#shouldPay_${eachProductInfoVO_index}").html(formatPrice(orderPriceArray[${eachProductInfoVO_index}]));
		<#-- 每个产品的实际支付价格 -->
		$("#actualDelivery_${eachProductInfoVO_index}").html(formatPrice(orderPriceArray[${eachProductInfoVO_index}]));
		
		$("#scoreSelect").change();
	</#list> 
	
	$(function(){
        
        //获取要定位元素距离浏览器顶部的距离
        var navH = $(".pay_main_box").offset().top;
        
        //计算left值
        var zhi = (($(window).width() - 1100)/2)+805;
        
        //滚动条事件
        $(window).scroll(function(){
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
        });
	
    	<#-- 添加房间(与价格无关) -->
	    $(".pay_check_more_one").click(function () {
	    	<#-- 将新房间的默认住客情况存入房间住客信息数组 -->
	    	var productIndex = $(this).attr("productIndex");
			var maxRoomIndex = roomInfoTotalArray[productIndex].length;
			roomInfoTotalArray[productIndex][maxRoomIndex] = new RoomInfo(maxRoomIndex + 1, 2, 0);
			
			<#-- 将新的房间信息输入框加入页面 -->
	        var $addHtml = fnGeneralRoomInput(maxRoomIndex,productIndex);
	        $("#afterRoomInfo_" + productIndex).before($addHtml);
	    });
	    
	    <#-- 删除房间(与价格无关) -->
	    $(".pay_check_more").live("click", function () {
	    	var productIndex = $(this).attr("productIndex") * 1;
	    	var roomIndex = $(this).attr("roomIndex") * 1;
	    	
	    	<#-- 在房间住客信息中删除该房间信息 -->
	    	for(var i= roomIndex+1; i<roomInfoTotalArray[productIndex].length; i++){
	    		roomInfoTotalArray[productIndex][i].roomNum = i;
	    	}
	    	roomInfoTotalArray[productIndex].splice(roomIndex,1); 
	    	
	    	<#-- 删除房间输入,重新排列房间号 -->
	    	var $root = $(this).parent().parent().parent();
	    	$(this).parents(".pay_room_nums_d").remove();
	    	fnReorderRoomNumber($root);
	    });
		
		<#-- 购物车产品选项选择框 -->
		$(".cart_b").click(function(){
			<#-- 取消选中 -->
			if($(this).hasClass("cart_check")){
				$(this).removeClass("cart_check");
			<#-- 选中 -->
			}else{
				$(this).addClass("cart_check");
			}
		});
		
		<#-- 购物车选项关闭 -->
		$(".close_btn_dw").click(function(){
			if(confirm("您要删掉该产品吗？")){
				var $cart_b = $(this).prev().find(".cart_b");
				var productIndex = $cart_b.attr("id").split("_")[1];
				var shoppingCartId = $cart_b.attr("shoppingCartId");
				
				$.post("${ctx!}/front/orders/removeProduct.do",{"shoppingCartId":shoppingCartId},function(result){
					if(result == 'success'){
						<#-- 如果产品已被选中 -->
						if($cart_b.hasClass("cart_check")){
							<#-- 调整产品件数和总价格 -->
							//$orderNumber.html($orderNumber.html()*1 - 1);
							totalPrice -= orderPriceArray[productIndex];
							$totalPriceShow.html(formatPrice(totalPrice));
						}
						$cart_b.parent().parent().remove();
						$("#userAgreement").hide();
						$("#contacterForm").parent().parent().hide();
						$("#emptyShoppingCart").show();
					}else{
					}
				});
			}
		});
		
		<#-- 如果购物车中没有产品 -->
		if($(".pay_main_left").size() == 1){
			$("#userAgreement").hide();	
			$("#contacterForm").parent().parent().hide();
			$("#emptyShoppingCart").show();
		}
		
		$("input.Payment_Method_btn_1").click(function(){
			window.location.href = "${ctx!}/";
		});
		
		<#-- 点击确定选择 -->
	    $("#useScoreComfirm").click(function(){
			conformScore = chooseScore;
			$("#scorePrivilege").html(exchangedPrice);
			var actualDelivery = orderPriceArray[0] - exchangedPrice;
			$(".actualDelivery").html(formatPrice(actualDelivery));
			totalPrice = actualDelivery;
			$("#totalPriceShow").html(formatPrice(totalPrice));
	    });
	    
		//$orderNumber.html(1);
		$totalPriceShow.html(formatPrice(totalPrice));
		showColorInterval();
	});
	
    <#-- 选择起始时间 -->
    var selectStartDate = function(startTime,sellingPrice,singleRoomPrice,noBedPrice,threeSellingPrice,fourSellingPrice,extraBedPrice,babyPrice,childPrice,tourdateId,tourPriceId,button){
    	<#-- 获得产品的编号 -->
    	var productIndex = $(button).parent().parent().parent().parent().parent().attr("id").split("_")[1];
    	<#-- 将选中时间的产品价格储存起来 -->
    	tourPriceArray[productIndex] = new TourPrice(sellingPrice,singleRoomPrice,noBedPrice,threeSellingPrice,fourSellingPrice,extraBedPrice,babyPrice,childPrice,tourPriceId);
    	<#-- 变动页面上的出发日期 -->
    	$("#departureDateInput_" + productIndex).val(startTime);
    	tourDateIdArray[productIndex] = tourdateId;
    	
    	<#-- 通过起始日期计算结束日期,并改变页面上结束日期值 -->
    	var dateArray = startTime.split("-");
    	var endDate = new Date(dateArray[0],dateArray[1],dateArray[2]);
		endDate.setDate(endDate.getDate() + parseInt($("#checkProduct_" + productIndex).attr("days"))); 
		var month = endDate.getMonth(); 
		var day = endDate.getDate(); 
		if(month<10){ 
			month = "0" + month; 
		} 
		if(day<10){ 
			day = "0" + day; 
		} 
		var endDateString = endDate.getFullYear() + "-" + month + "-" + day; 
    	$("#endDateInput_" + productIndex).html(endDateString);
    	
    	<#-- 重新计算并显示价格 -->
    	<#-- 异步获取出发地选项 -->
    	var $departureSelect = $("#pickUp_" + productIndex);
    	var $flightTicks = $("#airTicketPriceSelect_" + productIndex);
    	$departureSelect.parent().parent().hide();
    	$flightTicks.parent().parent().hide();
    	
	   	$.ajax({
	        async: false,
	        type : "POST",
	        url : '${ctx!}/front/orders/getDepartures.do',
	        data: {"tourDateId":tourdateId},
	        success : function(result) {
	        	$departureSelect.empty();
    			$.each(result,function(index,departure){
	    			var $departureOption = '<option value="' + departure.id + '">'+ departure.name + ' ' + departure.city + '</option>'
	     			$departureSelect.append($departureOption);
    			});
    			var $selectedOption = $departureSelect.find("option:selected");
    			if($selectedOption.size() > 0){
    				$departureSelect.parent().parent().show();
    			}
	        }
	    });
	    <#-- 异步获取机票价格选项 -->
	    $.ajax({
	        async: false,
	        type : "POST",
	        url : '${ctx!}/front/orders/getAirTicketPrices.do',
	        data: {"tourPriceId":tourPriceId},
	        success : function(result) {
				$flightTicks.empty();
				$.each(result,function(index,flightTickPrice){
					var $flightTicksOption = '<option value="'+ flightTickPrice.id +'" price="'+ flightTickPrice.price +'">' + flightTickPrice.departureName + ' USD' + flightTickPrice.price + '</option>';
					$flightTicks.append($flightTicksOption);
				});
				var $selectedOption = $flightTicks.find("option:selected");
				if($selectedOption.size() > 0){
					$flightTicks.parent().parent().show();
				}
	        }
	    });
	    showPrice(productIndex);
    }
    
	<#-- 重新排序房间号 -->
    var fnReorderRoomNumber = function($parent){
    	$parent.find(".left.pay_room b").each(function(index){
    		$(this).html(index + 1);
    	});
    	$parent.find(".pay_check_more").each(function(index){
			$(this).attr("roomIndex",index + 1);    	
    	});
    }
    
	<#-- 修改按钮事件 -->
	var modifyButton = function(button){
		var $currentModifyDiv = $(button).parent().parent().parent();
		$currentModifyDiv.hide();
		$currentModifyDiv.next().show();
	}

	<#-- 生成房间输入框 -->
    var fnGeneralRoomInput = function(maxRoomIndex,productIndex){
   		var content = ''
		   +'<div class="pay_room_nums_d" style="margin-bottom:5px;">'
			 +'<div class="left pay_room">房间<b class="roomNum">' + (maxRoomIndex+1) + '</b></div>'
				+'<div class="left pay_room_people">'
					+'<div class="left pay_adult_sel_nums">成人</div>'
					+'<select class="pay_adult_nums passenger_num" onchange="changeNum(this,\''+ productIndex +'\');">'
						+'<option value="1">1</option>'
						+'<option value="2" selected="">2</option>'
						+'<option value="3">3</option>'
						+'<option value="4">4</option>'
					+'</select>'
				+'</div>'
				+'<div class="left pay_room_people">'
					+'<div class="left pay_child_sel_nums">儿童</div>'
					+'<select class="pay_child_nums passenger_num"  onchange="changeNum(this,\''+ productIndex +'\');">'
						+'<option value="0">0</option>'
						+'<option value="1">1</option>'
						+'<option value="2">2</option>'
						+'<option value="3">3</option>'
					+'</select>'
				+'</div>'
				+'<div class="left">'
					+'<a href="javascript:;" productIndex ="'+ productIndex +'" roomIndex="'+ maxRoomIndex +'" class="pay_check_more">[删除]</a>'
				+'</div>'
				+'<div class="clear"></div>'
			+'</div>';
   		return $(content);
    }
    
    <#-- 确认房间住客信息 -->
    var comfirmRoomInfo = function(productIndex){
    	<#-- 根据客人数量的变化调整房间输入框的数量 -->
    	adjustRoomInput(productIndex);
		<#-- 重新显示房间住客信息 -->		
    	var totalPoeple = reloadRoomInfo(productIndex);
    	$("#simpleShow_" + productIndex).show();
    	$("#detailShow_" + productIndex).hide();
    	
    	<#-- 接送机价格  -->
    	var $selectedAirportPickUp = $("#airportPickUpSelect_" + productIndex).find("option:selected");
    	var $airportPickUp = $("#airportPickUpShow_" + productIndex);
    	if($selectedAirportPickUp.size() != 0){
			selectedAirportPickUpPriceArray[productIndex] = parseFloat($selectedAirportPickUp.attr("price")) * exchangeRate * totalPoeple;
			$airportPickUp.html($selectedAirportPickUp.html());
    	}else{
    		selectedAirportPickUpPriceArray[productIndex] = 0;
    	}
    	
    	<#-- 接送地点 -->
    	var $selectedDeparture = $("#pickUp_" + productIndex).find("option:selected");
    	var $departure = $("#departureSpan_" + productIndex);
    	if($selectedDeparture.size() != 0){
    		$departure.html($selectedDeparture.html());
    		$departure.parent().parent().show();	
    	}else{
    		$departure.parent().parent().hide();
    	}
    	
    	<#-- 机票价格 -->
    	var $selectedAirticketprice = $("#airTicketPriceSelect_" + productIndex).find("option:selected");
    	var $airticketPrice = $("#airTicketPriceSpan_" + productIndex);
    	if($selectedAirticketprice.size() != 0){
    		selectedAirTicketPriceArray[productIndex] = parseFloat($selectedAirticketprice.attr("price")) * exchangeRate * totalPoeple;
    		$airticketPrice.html($selectedAirticketprice.html());
    		$airticketPrice.parent().parent().show();
    	}else{
    		selectedAirTicketPriceArray[productIndex] = 0;
    		$airticketPrice.parent().parent().hide();
    	}
    	showPrice(productIndex);
    }
    
    <#-- 当人数发生变化时,改变容器中储存的值 -->
    var changeNum = function(select,productIndex){
    	var totalNumEachRoom = 0;
    	var $roomInfo = $(select).parent().parent().find(".passenger_num");
    	<#-- 计算每个房间的总人数 -->
    	$roomInfo.each(function(){
    		totalNumEachRoom += $(this).val() * 1;
    	});
    	if(totalNumEachRoom > 4){
    		$roomInfo.eq(1).val(4 - $roomInfo.eq(0).val() * 1);
    		alertWarn("每个房间人数不得超过4人.");
    	}
    	var roomInfoArray = new Array();
    	$(".roomInfo_" + productIndex).find(".pay_room_nums_d").each(function(index){
    		roomInfoArray[index] = new RoomInfo(index+1,$(this).find(".pay_adult_nums").val(),$(this).find(".pay_child_nums").val());
    	});
    	roomInfoTotalArray[productIndex] = roomInfoArray;
    }
    
    <#-- 根据输入字符串计算年龄 -->
	var getAge = function(str){  
        var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);    
        if(r==null)return   false;    
        var   d=   new   Date(r[1],   r[3]-1,   r[4]);    
        if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]){  
          var Y = new Date().getFullYear();  
          return (Y-r[1]);  
        }  
	}   
    
    <#-- 根据产品编号计算该产品的总价格 -->
    var resetCurrentProductPrice = function(productIndex){
    	var baseTourPrice = 0;
    	var productTips = 0;
    	
    	<#-- 团价格信息 -->
    	var tourPrice = tourPriceArray[productIndex];
    	<#-- 房间信息 -->
    	var roomInfoArray = roomInfoTotalArray[productIndex];
    	var $roomInfo = $("#roomInfo_" + productIndex);
    	
    	for(var i=0; i<roomInfoArray.length; i++){
    		roomInfo = roomInfoArray[i];
			if(roomInfo.adultNum == 2){
				baseTourPrice += tourPrice.sellingPrice * 2;
			}else if(roomInfo.adultNum == 1){
				baseTourPrice += tourPrice.sellingPrice + tourPrice.singleRoomPrice;
			}else if(roomInfo.adultNum == 3){
				baseTourPrice += tourPrice.threeSellingPrice * 3;
			}else if(roomInfo.adultNum == 4){
				baseTourPrice += tourPrice.fourSellingPrice * 4;
			}
			productTips += roomInfo.adultNum * tourlineTipArray[productIndex];
    	}
    	$roomInfo.find(".childrenRoom").each(function(){
    		var birthdayStr = $(this).find(".birthdayInput").val();
    		if(birthdayStr == ''){
    			baseTourPrice += tourPrice.extraBedPrice;
    			productTips += tourlineTipArray[productIndex];
    		}else{
    			var age = getAge(birthdayStr);
    			if(age < 2){
    				baseTourPrice += tourPrice.babyPrice; 
    			}else if(age >= 2 && age < 5){
    				baseTourPrice += tourPrice.childPrice;
    			}else if(age >= 5 && age < 11){
    				baseTourPrice += tourPrice.extraBedPrice;
    				productTips += tourlineTipArray[productIndex];
    			}
    		}
    	});
    	baseTourPriceArray[productIndex] = baseTourPrice;
    	eachProductTips[productIndex] = productTips;
    	totalProductPrice = baseTourPriceArray[productIndex] + eachProductTips[productIndex] + selectedAirportPickUpPriceArray[productIndex] + selectedAirTicketPriceArray[productIndex];
    	return Math.ceil(totalProductPrice);
    }
    
    <#-- 重新计算产品价格 -->
    var showPrice = function(productIndex){
    	productPrice = resetCurrentProductPrice(productIndex);
    	
    	var changePrice = productPrice - orderPriceArray[productIndex];
    	orderPriceArray[productIndex] = productPrice;
    	
    	var formatedProductPrice = formatPrice(productPrice);
    	$("#shouldPay_" + productIndex).html(formatedProductPrice);
    	$("#actualDelivery_" + productIndex).html(formatedProductPrice);
    	
    	<#-- 如果该产品已被选中 -->
    	if($("#checkProduct_" + productIndex).hasClass("cart_check")){
			totalPrice += changePrice;	
			$totalPriceShow.html(formatPrice(totalPrice));
    	}
    }
    
    <#-- 选中产品并计算产品数量和价格 -->
    var checkProduct = function(b,productIndex){
    	<#-- 取消产品 -->
		if($(b).hasClass("cart_check")){
			//$orderNumber.html($orderNumber.html()*1 - 1);
			totalPrice -= orderPriceArray[productIndex];
			$totalPriceShow.html(formatPrice(totalPrice));
		<#-- 选中产品 -->	
		}else{
			//$orderNumber.html($orderNumber.html()*1 + 1);
			totalPrice += orderPriceArray[productIndex];
			$totalPriceShow.html(formatPrice(totalPrice));
		}  	
    } 
    
    <#-- 儿童生日选中后计算价格  -->
    var calculatePrice = function(dp){
    	var birthDateStr = dp.cal.getNewDateStr();
    	var productIndex = $(this).parent().parent().parent().parent().attr("id").split("_")[1];
		showPrice(productIndex);  
    }
    
    <#-- 继续购物 -->
    var goShopping = function(){
    	window.location.href = "${ctx}";
    }
    
    <#-- 显示客人输入框的间隔颜色  -->
    var showColorInterval = function(){
    	$("div[id^='roomInfo_']").each(function(){
    		$(this).find("div.roomInfo").each(function(index){
    			$(this).removeClass("pay_mess_main").removeClass("pay_mess_main_2");
    			if(index % 2 == 0){
    				$(this).addClass("pay_mess_main");
    			}else{
    				$(this).addClass("pay_mess_main_2");
    			}
    		});
    	});
    }
    
    <#-- 调整客人房间输入框的数量 -->
    var adjustRoomInput = function(productIndex){
   		var $roomInfo = $("#roomInfo_" + productIndex);
    	var totalAdultNum = 0;
    	var totalChildrenNum = 0;
    	var actualAdultNum = $roomInfo.find(".adultRoom").size();
    	var actualChildrenNum = $roomInfo.find(".childrenRoom").size();
    	
    	<#-- 计算当前产品下的客人数量 -->
    	for(var i=0; i<roomInfoTotalArray[productIndex].length; i++){
    		totalAdultNum += parseInt(roomInfoTotalArray[productIndex][i].adultNum);
    		totalChildrenNum += parseInt(roomInfoTotalArray[productIndex][i].childNum);
    	}
    	var increace = 0;
    	
    	<#-- 大人数量大于实际输入框数量,在输入框后面补充-->
    	if(totalAdultNum >= actualAdultNum){
    		increace = totalAdultNum - actualAdultNum;
    		for(var i=0; i<increace; i++){
    			var $adultRoomInput = $("#adultTemplate").clone(true);
    			$roomInfo.find(".adultRoom:last").after($adultRoomInput);
    		}
    	<#-- 大人数量小于实际输入框数量,删除最后的输入框  -->
    	}else{
    		increace = actualAdultNum - totalAdultNum;
    		for(var i=0; i<increace; i++){
    			$roomInfo.find(".adultRoom:last").remove();
    		}
    	}
    	<#-- 儿童数量大于实际输入框数量,在输入框后面补充 -->
    	if(totalChildrenNum >= actualChildrenNum){
    		increace = totalChildrenNum - actualChildrenNum;
    		for(var i=0; i<increace; i++){
    			var $childrenRoomInput = $("#childrenTemplate").clone(true);
    			var $lastChildrenRoom = $roomInfo.find(".childrenRoom:last");
    			if($lastChildrenRoom.size() == 0){
    				$roomInfo.find(".adultRoom:last").after($childrenRoomInput);
    			}else{
    				$lastChildrenRoom.after($childrenRoomInput);
    			}
    		}
    	<#-- 儿童数量小于实际输入框数量,删除最后的输入框  -->		
    	}else{
    		increace = actualChildrenNum - totalChildrenNum;
    		for(var i=0; i<increace; i++){
    			$roomInfo.find(".childrenRoom:last").remove();
    		}
    	}
    	<#-- 重新排列客人编号 -->
    	$("#roomInfo_" + productIndex).find(".roomInfo").each(function(index){
    		$(this).find("i.customerNum").html(index+1);
    	});
    	<#-- 将日期传值 -->
    	$("#departureDate_" + productIndex).html($("#departureDateInput_" + productIndex).val());
    	$("#endDate_" + productIndex).html($("#endDateInput_" + productIndex).html());
    	showColorInterval();
    }
	
	<#-- 重新显示房间的住客信息 -->    
    var reloadRoomInfo = function(productIndex){
    	var $roomInfoArea = $("#roomInfoArea_" + productIndex);
    	$roomInfoArea.html('');
    	var totalPeople = 0;
    	for(var i=0; i<roomInfoTotalArray[productIndex].length; i++){
    		roomInfo = roomInfoTotalArray[productIndex][i];
    		$roomInfoTemplate = $("#roomInfoTemplate").clone(true);
    		$roomInfoTemplate.removeAttr("id");
    		$roomInfoTemplate.find(".roomNumber").html(roomInfo.roomNum);
    		$roomInfoTemplate.find(".adultNum").html(roomInfo.adultNum);
    		totalPeople += roomInfo.adultNum;
    		totalPeople += roomInfo.childNum;
    		$roomInfoTemplate.find(".childrenNum").html(roomInfo.childNum);
    		$roomInfoArea.append($roomInfoTemplate);
    	}
    	return totalPeople;
    }
    
    <#-- 日历下一月 -->
    function nextMonth(button){
    	var $root = $(button).parent().parent().parent().parent().parent();
    	var productIndex = $root.attr("id").split("_")[1];
    	var tourlineId = $root.attr("tourlineId");
		
    	var month = $root.find(".calendarMonth").val();
    	var year = $root.find(".calendarYear").val();
 		if(parseInt(month) == 12){
 			month = parseInt(1);
 			year = parseInt(year)+1;
 		}else{
 			month = parseInt(month)+1;
 			year = parseInt(year);
 		}
		getAsynchronousCalendar(year,month,tourlineId,productIndex);
    }
    
    <#-- 日历上一月 --> 
    function lastMonth(button){
    	var $root = $(button).parent().parent().parent().parent().parent();
    	var productIndex = $root.attr("id").split("_")[1];
    	var tourlineId = $root.attr("tourlineId");
    	
    	var month = $root.find(".calendarMonth").val();
    	var year = $root.find(".calendarYear").val();
 		if(parseInt(month)==1){
 			month = parseInt(12);
 			year = parseInt(year)-1;
 		}else{
 			month = parseInt(month)-1;
 			year = parseInt(year);
 		}
		getAsynchronousCalendar(year,month,tourlineId,productIndex);
    }
    
    <#-- 异步获得日历 -->
    var getAsynchronousCalendar = function(year,month,tourlineId,productIndex){
 		$.ajax({
 			type: "POST",
	   		url: "${ctx!}/front/tourlineDetails/changeCalendar.do",
	   		dataType:"html",
	   		data: {year:year,month:month,id:tourlineId},
	   		success:function(data){
	   			$("#calendar_" + productIndex).html(data);
	   		},
	   		error:function(e){
	   		}
 		})	    
    }
    
    <#-- 当用户选择积分变化时 -->
    function scoreChange(){
		var $scoreSelect = $("#scoreSelect");
    	chooseScore = $scoreSelect.find("option:selected").val();
    	exchangedPrice = chooseScore/100 * exchangeRate;
    	exchangedPrice = Math.floor(exchangedPrice);
    	var $showScore = $("#showScore");
    	var code = $showScore.attr("currency");
    	$showScore.html('-' + code + exchangedPrice);
    }
    
    <#-- 初始化选中产品
    var $car_b = $(".cart_b");
    $car_b.click();
    $car_b.addClass("cart_check");
    $("#agreement_check").click();
    -->
    <#--异步兑换优惠券--> 
    var exchange = function(){
      var money = totalPrice;
      var code = $("#uscouponse").val();
      $.ajax({
            type: "POST",
            url: "${ctx!}/front/action/Exchange.do",
            
            data: {courrysign:courrysign,code:code,totalPrice:money},
            success:function(data){
                var obj = eval(data);
                if(obj.meassage!=null&&obj.meassage!=""){
                  alertWarn(obj.meassage);
                }else{
                  totalPrice = obj.totalprice;
                  $("#uscouponseShow").html("-"+courrysign+obj.releaseprice);
                  $("#couponseUs").html(obj.releaseprice);
                  $(".actualDelivery").html(obj.totalprice);
                  $("#totalPriceShow").html(obj.totalprice);
                  $("#uscouponse").attr("readonly","readonly");
                  $("#couponseComfrim").attr('disabled',true);
                }
            },
            error:function(e){
            }
        })   

    }
    <#-- 确认订单 -->
    var confirmOrder = function(){
       var couponseCode = $("#uscouponse").val();
       
    	<#-- 用户未同意文景的客户协议 -->
		if(!$("#agreement_check").attr("checked")){	
			alertWarn('请阅读客户协议.');
			return;
		} 	
		<#-- 用户选择的产品数量为0 -->
    	if($(".cart_check").size() == 0){
    		alertWarn('请选择产品.');
    		return;
    	}
    	$form = $("#dataForm");
    	$form.empty();
    	
    	var eachProductTotalArrayIndex = 0;
    	var isError = false;
    	$(".cart_b").each(function(){
    		if($(this).hasClass("cart_check")){
    			var productIndex = $(this).attr("id").split("_")[1];
    			var $showInfoDiv = $("#simpleShow_" + productIndex);
    			<#-- 用户编辑产品信息并没有确定的情况 -->
				if($showInfoDiv.is(":visible") == false){
					alertWarn('您没有确认信息录入.');
					isError = true;
					return false;
				}
				<#-- 对每个顾客信息进行校验--> 	
    			$("#roomInfo_" + productIndex).find("input").each(function(){
    				type = $(this).attr("rule");
    				if(type == 'required'){
    					<#--
						if($(this).val() == ''){
							isError = true;
							$(this).removeClass("pay_mess_input").addClass("pay_mess_input_error");
						}
						-->						
					}else if(type == 'passportNo'){
						<#--
						if($(this).val() == ''){
							isError = true;
							$(this).removeClass("pay_mess_input").addClass("pay_mess_input_error");
						}
						-->
					}else if(type == 'number'){
						if($(this).val() != '' && !new RegExp("^[0-9]{7,20}$").test($(this).val())){
							isError = true;	
							$(this).removeClass("pay_mess_input").addClass("pay_mess_input_error");
						}						
					}
    			});
    		        	
    			<#-- 校验联系人的信息 -->
    			$("#contacterForm").find("input").each(function(){
    				type = $(this).attr("rule");
    				if(type == 'required'){
	    				if($(this).val() == ''){
	    					isError = true;
	    					$(this).removeClass("pay_mess_input").addClass("pay_mess_input_error");
	    				}
    				}else if(type == 'number'){
    					if(!new RegExp("^[0-9]{7,20}$").test($(this).val())){
    						isError = true;
    						$(this).removeClass("pay_mess_input").addClass("pay_mess_input_error");
    					}
    				}else if(type == 'mail'){
    					if(!new RegExp("^([a-zA-Z0-9\-_\.])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$").test($(this).val())){
    						isError = true;
    						$(this).removeClass("pay_mess_input").addClass("pay_mess_input_error");
    					}
    				}
    			});
    			
				<#-- 如果联系人信息有错误,不继续向下执行,并且给错误框添加获取焦点事件 -->
    			if(isError){
    				$(".pay_mess_input_error").focus(function(){
    					$(this).removeClass("pay_mess_input_error").addClass("pay_mess_input");
    				});
    				return false;
    			}
    			
    			var departureDate = $(this).parent().parent().find(".departureDate").html();
    			var endDate = $(this).parent().parent().find(".endDate").html();
    			var totalAdultNum = 0;
    			var totalChildrenNum = 0;
    			for(var i=0; i<roomInfoTotalArray[productIndex].length; i++){
		    		totalAdultNum += parseInt(roomInfoTotalArray[productIndex][i].adultNum);
		    		totalChildrenNum += parseInt(roomInfoTotalArray[productIndex][i].childNum);
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].roomInfoList['+ i +'].roomNum" value="'+ roomInfoTotalArray[productIndex][i].roomNum +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].roomInfoList['+ i +'].adultNum" value="'+ roomInfoTotalArray[productIndex][i].adultNum +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].roomInfoList['+ i +'].childNum" value="'+ roomInfoTotalArray[productIndex][i].childNum +'">');
    			}
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].shoppingCartId" value="'+ $(this).attr("shoppingCartId") +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultNum" value="'+ totalAdultNum +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenNum" value="'+ totalChildrenNum +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].currencySign" value="<#if (loadOrderInfoVO.eachProductInfoVOList[0])??>${loadOrderInfoVO.eachProductInfoVOList[0].currencySign}</#if>"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].days" value="'+ $(this).attr("days") +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].departureDate" value="'+ departureDate +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].endDate" value="'+ endDate +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].price" value="'+ tourPriceArray[productIndex].sellingPrice +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].product.id" value="'+ $(this).attr("productId") +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].totalPrice" value="'+ totalPrice +'"/>');
    			$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].tourDateId" value="'+ tourDateIdArray[productIndex] +'"/>');
    			
    			var departureId = $("#pickUp_" + productIndex).val();
    			if(typeof(departureId) != 'undefined'){
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].selectedDeparture.id" value="'+ departureId +'">');
    			} 
    			var pickUpId = $("#airportPickUpSelect_" + productIndex).val();
    			if(typeof(pickUpId) != 'undefined'){
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].selectedAirportPickUp.id" value="'+ pickUpId +'"/>');
    			}
    			var ticketPriceId = $("#airTicketPriceSelect_" + productIndex).val();
    			if(typeof(ticketPriceId) != 'undefined'){
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].selectedAirTicketPrice.id" value="'+ ticketPriceId +'"/>');
    			}
    			$("#roomInfo_" + productIndex).find(".adultRoom").each(function(index){
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].lastName" value="'+ $(this).find(".lastNameInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].firstName" value="'+ $(this).find(".firstNameInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].gender" value="'+ $(this).find(".genderSelect").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].birthday" value="'+ $(this).find(".birthdayInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].nationality" value="'+ $(this).find(".nationalityInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].passportNo" value="'+ $(this).find(".passportNoInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].passportNoExpiryDate" value="'+ $(this).find(".passportNoExpiryDateInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].adultPassengerList['+ index +'].mobileNumber" value="'+ $(this).find(".mobileNumberInput").val() +'">');
    			});
    			$("#roomInfo_" + productIndex).find(".childrenRoom").each(function(index){
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].lastName" value="'+ $(this).find(".lastNameInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].firstName" value="'+ $(this).find(".firstNameInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].gender" value="'+ $(this).find(".genderSelect").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].birthday" value="'+ $(this).find(".birthdayInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].nationality" value="'+ $(this).find(".nationalityInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].passportNo" value="'+ $(this).find(".passportNoInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].passportNoExpiryDate" value="'+ $(this).find(".passportNoExpiryDateInput").val() +'">');
    				$form.append('<input name="eachProductInfoVOList['+ eachProductTotalArrayIndex +'].childrenPassengerList['+ index +'].mobileNumber" value="'+ $(this).find(".mobileNumberInput").val() +'">');
    			});
    			eachProductTotalArrayIndex++;
    		}	
    	});
    	if(!isError){
    	   
    		$form.append('<input name="orderContacter.name" value="'+ $("#contacterName").val() +'">');
    		$form.append('<input name="orderContacter.tel" value="'+ $("#contacterMobile").val() +'">');
    		$form.append('<input name="orderContacter.email" value="'+ $("#contacterMail").val() +'">');
    	 	$form.append('<input name="totalPrice" value="'+ totalPrice +'">');
    	 	$form.append('<input name="orders.remark" value="'+ $("#orderRemark").val() +'"/>');
    	 	$form.append('<input name="exchangedScore" value="'+ conformScore +'"/>');
    	 	$form.append('<input name="couposeCode" value="'+ couponseCode +'"/>');
    		$form.submit(); 
    		<!-- alert($form.html()); -->
    	}
    }
    
    // 收缩展开效果
	$(document).ready(function(){
		$("#pay_messages_steps").click(function(){
			$(this).next("#pay_messages_list0").slideToggle("2000");
			$(".pay_messages").toggleClass("airport_ky").toggleClass("airport_ing");
		});
	});
	scoreChange();
</script>
<script type="text/javascript">
	var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
</script>
</html>