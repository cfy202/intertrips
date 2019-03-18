<div id="pt_header">
<div class="header_top width100 left">
        <div class="widthCenter">
            <div class="header_top_left left">
	              <#if frontCode??>
				    <#if frontCode="CNY">
				        <span id="hello" class="top_welcome">您好，中国文景假期欢迎您！</span>
			        <#elseif frontCode="CAD">
			            <span id="hello" class="top_welcome">您好，加拿大文景假期欢迎您！</span>
			        <#elseif frontCode="AUD">
			            <span id="hello" class="top_welcome">您好，澳大利亚文景假期欢迎您！</span>
			        <#elseif frontCode="EUR">
			            <span id="hello" class="top_welcome">您好，欧洲文景假期欢迎您！</span>
			        <#else>
				        <span id="hello" class="top_welcome">您好，美国文景假期欢迎您！</span>
				    </#if> 
				 <#else>
				     <span id="hello" class="top_welcome">您好，文景假期欢迎您！</span>
				 </#if> 
                <div class="right">
                    <span id="showLogin" class="top_welcome">
                       <a id="headerLogin" class="theme-login" href="javascript:;">登录</a>
                       <a id="headerRegister" class="theme-register" href="javascript:;">注册</a>
                    </span>
                    <span id="headerUsername" class="top_welcome">您好，<a href="${ctx!}/member/profile/index.htm" id="wjuser"></a></span>
                    <a id="headerLogout" class="a1 top_welcome" href="${ctx!}/logout.htm">退出</a>
                </div>
            </div>
            <div class="header_top_right right">
                <div class="header_top_sel left">
                    <div class="header_title">
                        <span class="left"><a href="${ctx!}/member/profile/index.htm" class="a1">我的文景</a></span>
                        <div class="header_title_bg left"></div>
                    </div>
                </div>
                <div class="header_top_sel left">
                    <div class="header_title">
                        <div class="header_title_bg1 left"></div>
                        <span id="shoppingCartButton" class="left">购物车(<font id="shoppingCartNum" class="colorOrange">0</font>)</span>
                    </div>
                </div>
                <#--
                <div class="header_top_sel left">
                    <div class="header_title">
                        <span class="left">语言</span>
                        <div class="header_title_bg left"></div>
                    </div>
                </div>
                <div class="header_top_sel left">
                    <div class="header_title">
                        <span class="left">人民币</span>
                        <div class="header_title_bg left"></div>
                    </div>
                </div>
                -->
               <div class="header_top_sel nationalflag left" style="position:relative;">
                    <div class="header_title">
                        <span class="choose_gq left"><img class="img_gq" id="img_gq" src="${ctx!}/assets-web/images/china.jpg"><img src="${ctx!}/assets-web/background/icon_bg_bz.png" style="vertical-align:middle;"></span>
                        <div class="clear"></div>
                    </div>
                    <ul class="brand_op" style="display:none;" tabindex="-1">
                        <li>
                            <a href="javascript:void(0);" onclick="setCurrency('CNY')"><img src="${ctx!}/assets-web/images/china.jpg"> 中国</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="setCurrency('USD')"><img src="${ctx!}/assets-web/images/united_states.jpg"> 美国</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="setCurrency('CAD')"><img src="${ctx!}/assets-web/images/canada.jpg"> 加拿大</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="setCurrency('AUD')"><img src="${ctx!}/assets-web/images/australia.jpg"> 澳大利亚</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="setCurrency('EUR')"><img src="${ctx!}/assets-web/images/oumeng.jpg"> 欧洲</a>
                        </li>
                        <div class="clear"></div>
                    </ul>
                    <div class="clear"></div>

                </div>
                <div class="header_top_sel left">
				    <div class="header_title">
				        <span class="left"><a href="https://b2b.intertrips.com" class="a1" style="background:#f69650 url(${ctx!}/assets-web/images/icon.png) 10px 5px no-repeat;color: #fff;padding:2px 9px 2px 24px;border-radius: 3px;" target="_blank">同业登陆</a></span>
				    </div>
				</div>
                <#--
                 <div class="header_top_sel left">
                    <div class="header_title" id="bizhong">
		   	            <#list currencies as currency>
				             <span class="left" val="${currency.code}" onclick="setCurrency('${currency.code}')">${currency.name}</span>
				             <#if currency_has_next><span class="left">&nbsp;|&nbsp;</span></#if>
			             </#list>
                    </div>
                </div>
                -->
            </div>
        </div>
    </div>
    <div class="logoAndseek width100 left">
        <div class="widthCenter">
            <div class="logo left">
                <a href="${ctx!}/"><img src="${ctx!}/assets-web/logo/logo.png" /></a>
            </div>
            <div class="seek left">
            	<form action="${ctx!}/search.htm" method="get">
	                <img class="left" src="${ctx!}/assets-web/images/seek.png" />
	                <input class="seek_text left" type="text" name="keyword" placeholder="可输入目的地名称 景点名称 产品编号"/>
	                <input class="seek_sub left" type="submit" value="搜索" />
                </form>
            </div>
            <div class="right">
            	<div class="weixin left">
                    <img class="left" src="${ctx!}/assets-web/logo/logo-2.png" style="width:150px;" />
                </div>
                <div class="tell left" id="phone_lists">
                     <#if frontCode??>
					    <#if frontCode="CNY">
					        <div>
						        <p>服务热线</p>
						        <p class="colorOrange tell_number" id="tell_number">400-071-0197</p>
					        </div>
					        <div class="arrows off">
							    <img src="${ctx!}/assets-web/images/arrow_tel.png">
							</div>
						    <div style="display: none;" class="tel_list fs1">
						        <div class="tel_list_d cny">
	                                <b>北京：</b>
	                                <span>010-65206136</span>
                                </div>
                                <div class="tel_list_d cny">
	                                <b>西安：</b>
	                                <span>029-82224852</span>
                                </div>
                                 <div class="tel_list_d cny">
	                                <b>苏州：</b>
	                                <span>0512-67600096</span>
                                </div>
                            </div>
				        <#elseif frontCode="CAD">
				            <div>
					            <p>服务热线</p>
					            <p class="colorOrange tell_number" id="tell_number">604-800-6411</p>
				            </div>
				            <div class="arrows off">
							    <img src="${ctx!}/assets-web/images/arrow_tel.png">
							</div>
				            <div style="display: none;" class="tel_list fs1">
					            <div class="tel_list_d cny">
	                                <b>加拿大：</b>
	                                <span>604-800-6411</span>
                                </div>
                            </div>
				        <#elseif frontCode="AUD">
				        
				        <#elseif frontCode="EUR">
				           <div>
					            <p>服务热线</p>
					            <p class="colorOrange tell_number" id="tell_number">34-913088695</p>
				            </div>
				        <#else>
				           <div style="height: 36px; text-align: center;">
					            <p>服务热线</p>
					            <img src="${ctx!}/assets-web/images/tell.png">
						      <!--  <p class="colorOrange tell_number" id="tell_number">888-736-4685</p> -->
					        </div>
					        <div class="arrows off">
							    <img src="${ctx!}/assets-web/images/arrow_tel.png">
							</div>
				            <div style="display: none;" class="tel_list fs1">
				            <#--
					            <div class="tel_list_d">
	                                <b>洛杉矶钻石吧：</b>
	                                <span>+1 626-377-9888</span>
                                </div>
                            -->
                                <div class="tel_list_d">
	                                <b>洛杉矶圣盖博：</b>
	                                <span>+1 626-576-9688</span>
                                </div>
                                 <div class="tel_list_d">
	                                <b>洛杉矶哈仙达冈：</b>
	                                <span>+1 626-768-9865</span>
                                </div>
                                <div class="tel_list_d">
	                                <b>旧金山市中心：</b>
	                                <span>+1 415-876-7888</span>
                                </div>
                                <div class="tel_list_d">
	                                <b>旧金山喜悦城：</b>
	                                <span>+1 925-521-6999</span>
                                </div>
                                 <div class="tel_list_d">
	                                <b>纽约法拉盛：</b>
	                                <span>+1 718-412-0111</span>
                                </div>
                                <div class="tel_list_d">
	                                <b>纽约布鲁克林：</b>
	                                <span>+1 718-412-0108</span>
                                </div>
                                 <div class="tel_list_d">
	                                <b>芝加哥中国城：</b>
	                                <span>+1 312-854-2388</span>
                                </div>
                            </div>
					    </#if> 
					 <#else>
					      <div>
					          <p>服务热线</p>
					          <p class="colorOrange tell_number" id="tell_number">400-071-0197</p>
					      </div>
					 </#if> 
                </div>
                
                <#--
                <div class="weixin left">
                    <#if frontCode??>
					    <#if frontCode="CNY">
					        <img class="left" id="top_sweep_img" src="${ctx!}/assets-web/images/weixin_2.jpg" width="49px" hight="49px"/>
				        <#elseif frontCode="CAD">
				           <img class="left" id="top_sweep_img" src="${ctx!}/assets-web/images/user_head1.gif" width="49px" hight="49px"/>
				        <#elseif frontCode="AUD">
				            <img class="left" id="top_sweep_img" src="${ctx!}/assets-web/images/aud_sweep.jpg" width="49px" hight="49px"/>
				        <#else>
					       <img class="left" id="top_sweep_img" src="${ctx!}/assets-web/images/weixin_1.jpg" width="49px" hight="49px"/>
					    </#if> 
					 <#else>
					     <img class="left" id="top_sweep_img" src="${ctx!}/assets-web/images/weixin_2.jpg" width="49px" hight="49px"/>
					 </#if> 
                    <div class="weixin_p colorGray left">
                        <p>扫一扫</p>
                        <p>更多服务</p>
                        <p>更多优惠</p>
                    </div>
                </div>
                -->
                
                
            </div>
        </div>
    </div>
    <div class="private_tour_nav width100 left">
        <div class="widthCenter">
            <div class="private_tour_nav_left left">
                <div class="private_tour_nav_left_center">
                    <span class="left">热门旅游产品</span>
                    <div class="private_tour_nav_left_icon right"></div>
                </div>
                <#if region?has_content>
                <ul class="private_tour_nav_left_pos" style="display:none">
						<#assign idd = "">
						<#assign nn = 0>
						<#assign i = 0>
						<#assign j = 0>
						<#assign currentroot = 0>	<!--当前一级分类index-->
						<#assign max = 0>
                    	<#list region as reg>
	                        <#if 'root'==reg.upid >	                        	
	                        	<#if idd!="" || j!=0 >
	                        	</div>
	                        		<#list region[currentroot..reg_index] as rp>
	                        			<#if (rp.level)=1>
	                        	<div class="private_tour_nav_left_more">
		                            		
	                            	<div class="private_tour_nav_left_inner">
                                		<div class="private_tour_nav_left_panel">
                                    		<h4>
                                      			 ${(region[currentroot].name)!}
                                    		</h4>
                                    		<#assign regionTh=region[currentroot+1..reg_index-1]><!--当前二级分类-->
		                                    <dl class="private_tour_nav_left_dl">
                                           <#list regionTh as re>	
	                                    		<#if re_index !=0 >
			                                    <div class="clear"></div>		
			                                    </dl>
			                                    <dl class="private_tour_nav_left_dl">
	                                    		</#if>
		                                        <dt><a href="${ctx!}${(re.url)!}">${(re.name)!}</a></dt>
		                                        <#assign destinationArray = "${(re.destinationShow)!}"?split(",")>
		                                        <#list destinationArray as destination>
		                                        	<#if destination_index lt 8>
		                                        <dd><a href="${ctx!}${(re.url)!}?searchdestination=${destination!}">${destination!}</a></dd>
		                                        	</#if>
		                                        </#list>
		                                    </#list> 
		                                    
		                                        <div class="clear"></div>
		                                    </dl>
                                		</div>
                            		</div>
		                            		<#assign currentroot = reg_index>
                            		
	                        	</div>
	                        			<#break>
	                        			</#if>
	                        		</#list>
                    		</li>
	                        	</#if>
	                        <li>
	                        	<#assign nn = nn+1>
		                        <div class="private_tour_nav_left_top left">
		                            <div class="private_tour_icon_bg private_tour_icon_bg_${nn!}"></div>
		                            <a href="${ctx!}${(reg.url)!}">${(reg.name)!}</a>
		                            <div class="private_tour_icon_bg_td right"></div>
		                        </div>
		                        <div class="private_tour_nav_left_down right">
		                        <#assign j = j + 1> 
	                        </#if>
	                        <#if 'root'!=reg.upid && reg.level=1>
	                                <#assign i = i + 1 >
		                            <a href="${ctx!}${(reg.url)!}">${(reg.name)!}</a>
		                            <#assign idd = reg.id >
	                        </#if>
	                    	<#assign max = max+1>	
                        </#list>
                        		</div>
                        		<#list region[currentroot..(max-1)] as rp>
                        			<#if (rp.level)=1>
                        		<div class="private_tour_nav_left_more">
		                         
	                            	<div class="private_tour_nav_left_inner">
                                		<div class="private_tour_nav_left_panel">
                                    		<h4>
                                      			 ${(region[currentroot].name)!} 
                                    		</h4>
                                    		<#assign regionTh=region[currentroot+1..(max-1)]><!--当前二三级分类-->
		                                    <dl class="private_tour_nav_left_dl">
                                           <#list regionTh as re>	
	                                    		<#if re_index !=0 >
			                                    <div class="clear"></div>		
			                                    </dl>
			                                    <dl class="private_tour_nav_left_dl">
	                                    		</#if>
		                                        <dt><a href="${ctx!}${(re.url)!}">${(re.name)!}</a></dt>
		                                        <#assign destinationArray = "${(re.destinationShow)!}"?split(",")>
		                                        <#list destinationArray as destination>
		                                        <dd><a href="${ctx!}${(re.url)!}?searchdestination=${destination!}">${destination!}</a></dd>
		                                        </#list>
		                                    </#list> 
	                                      	
		                                        <div class="clear"></div>
		                                    </dl>
                                		</div>
                            		</div>
                        		</div>
                        				<#break>
                        			</#if>
                        		</#list>
                    		</li>
                    		
                    		<#--单独处理中国出发特卖-->
                    		<#if frontCode="CNY">
		                    <li>
		                        <div class="private_tour_nav_left_top left">
		                            <div class="private_tour_icon_bg icon_bg_3"></div>
		                            <a href="${ctx!}/china-depart-tours.htm">中国出发特卖</a>
		                            <div class="icon_bg_td right"></div>
		                        </div>
		                        <div class="private_tour_nav_left_down right">
		                            <a href="${ctx!}/china-depart-tours.htm?searchdestination=北京">北京</a>
		                            <a href="${ctx!}/china-depart-tours.htm?searchdestination=西安">西安</a>
		                            <a href="${ctx!}/china-depart-tours.htm?searchdestination=上海">上海</a>
		                            <a href="${ctx!}/china-depart-tours.htm?searchdestination=广州">广州</a>
		                        </div>
		                        <div class="private_tour_nav_left_more" >
		                            <div class="private_tour_nav_left_inner">
		                                <div class="private_tour_nav_left_panel">
		                                    <h4>
		                                		中国出发特卖
		                                    </h4>
		                                    <dl class="private_tour_nav_left_dl">
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=重庆">重庆</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=成都">成都</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=昆明">昆明</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=杭州">杭州</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=贵阳">贵阳</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=武汉">武汉</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=沈阳">沈阳</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=兰州">兰州</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=广州">广州</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=长沙">长沙</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=深圳">深圳</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=南京">南京</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=南昌">南昌</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=银川">银川</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=南宁">南宁</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=郑州">郑州</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=洛阳">洛阳</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=哈尔滨">哈尔滨</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=长春">长春</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=大连">大连</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=宁波">宁波</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=合肥">合肥</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=南阳">南阳</a></dd>
		                                        <dd><a href="${ctx!}/china-depart-tours.htm?searchdestination=鞍山">鞍山</a></dd>
		                                        <div class="clear"></div>
		                                    </dl>
		                                </div>
		                            </div>
			                    </div>
		                    </li>
		                    </#if>
                    		<#--中国出发特卖-->
                    		
                </ul>
                </#if>
            </div>
            <ul class="private_tour_nav_ul left">
            <#--
            	<#list navigation as navigation>
	                <li>
	                    <a href="${ctx!}${(navigation.url)!}">
	                    <#if navigation.name?? && navigation.name="中国出发特卖">
	                    <img src="${ctx!}/assets-web/images/icon/hot.png" style="width:40px; position:absolute;top:-15px;right:0"/>
	                    </#if>
	                    ${(navigation.name)!}
	                    </a>
	                </li>
                </#list>
            --> 
            	<#list navigation as ng> 
            		<#if ng.tagList?has_content>
                <li>
                	<a href="${ctx!}${(ng.url)!}" class="private_tour_nav_link private_tour_nav_ul_bg">
	                    <#if ng.name?? && ng.name="中国出发特卖">
	                    <img src="${ctx!}/assets-web/images/icon/hot.png" style="width:40px; position:absolute;top:-15px;right:0"/>
	                    </#if>
	                    ${(ng.name)!}
                    </a>
                    
                    <div class="width100 private_tour_sub_nav_new" style="display:none;">
                    	<div class="private_tour_sub_in">
	                    	<div class="private_tour_sub_left">
	                    	<#list ng.tagList as tag>                   		
	                            <a href="${ctx!}${(ng.url)!}?tagName=${(tag.name)!}">${(tag.name)!}</a>                            
	                        </#list>
	                        </div>
                        </div>
                    </div>
                    
                    <#--
                    <div class="private_tour_sub_in" style="display:none;">
                        	<div>
                        	    <#list ng.tagList as tag>                   		
	                            <a href="${ctx!}${(ng.url)!}?tagName=${(tag.name)!}"><span class="private_tour_list_cont_discount" style="background:#${(tag.bgcolor)!};color:#ffffff">${(tag.name)!}</a>                            
	                            </#list>
                            	</div>
                    </div>
                    -->
                </li>
                	<#else>	
                <li>
                    <a href="${ctx!}${(ng.url)!}" class="private_tour_nav_link">
	                    <#if ng.name?? && ng.name="中国出发特卖">
	                    <img src="${ctx!}/assets-web/images/icon/hot.png" style="width:40px; position:absolute;top:-15px;right:0"/>
	                    </#if>
	                    ${(ng.name)!}
                   </a>
                </li>
            		</#if>
                </#list>
            </ul>
        </div>
    </div>
    <div class="clear"></div>
 </div>
    <script type="text/javascript" src="${ctx!}/assets-web/js/shoppingCart.js"></script>
    <script>
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
    </script>