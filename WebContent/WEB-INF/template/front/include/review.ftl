<#assign ctx = request.contextPath />
<#list reviewList as review>
  <#--
  <div>
      <div class="left" style="width:71px;">
           <div class="ac"><img style="height:71px; width:71px;" src="${ctx!}/assets-web/images/no_avatar.png"></div>
           <div class="ff14 ac mt5">
           <#if review.member??>
           ${(review.member.account)!}
           <#else>
                               游客
           </#if>
           </div>
      </div>
      <div class="right customer_ev_right">
             <div class="">
                <div>
                   <div class="left ff14">${(review.title)!}&nbsp;&nbsp;&nbsp;${(review.createDate)?string("yyyy-MM-dd HH:mm:ss")}</div>  
                   <div class="left ml10" style="font-size:12px;">旅行团评分 <img class="ml5" src="${ctx!}/assets-web/images/icon_stars_${(review.tourGroupScore)!}.png"></div>
                   <div class="left ml10" style="font-size:12px;">文景假期评分 <img class="ml5" src="${ctx!}/assets-web/images/icon_stars_${(review.wenjingScore)!}.png"></div>
                   <div class="clear">${(review.content)!}</div>
                </div>
                <div class="customer_ev_main"></div>
             </div>
      </div>
      <div class="clear"></div>
  </div>
  <div class="customer_ev_line"></div>
  -->
  <div class="tour-reviews-item-container">
	  <div class="tour-reviews-item-info"> <img alt="" <#if (review.member.memberinformation.imageurl)??>src="${ctx!}${(review.member.memberinformation.imageurl)!}"<#else>src="${ctx!}/assets-web/images/people.jpg"</#if> class="avatar avatar-122 photo" height="122" width="122">
	    <div class="tour-reviews-item-name"><span itemprop="name"><#if review.member??>${(review.member.account)!}<#else>traveler</#if></span></div>
	  </div>
	  <div class="tour-reviews-item-content">
	    <div class="tour-reviews-item-content-top">
	      <div class="tour-reviews-item-rating">
	      	 <#list 1..review.wenjingScore as i>
	      	 <i class="fa fa-star voted"></i>
	      	 </#list> 
	      </div>
	      <div class="tour-reviews-item-date">${(review.createDateString)!}</div>
	    </div>
	    <div class="tour-reviews-item-text" itemprop="reviewBody">
	      <p>${(review.content)!}</p>
	    </div>
	    <div class="clear"></div>
	  </div>
	</div>
</#list>