<#if reviewNumber gt 0>
<div class="tour-reviews-rating-total">
  <div class="tour-reviews-rating-total-stars">
  	  <#list 1..starNumber as i> 
  		<i class="fa fa-star voted"></i>
  	  </#list> 
  </div>
  <#if reviewNumber = 1>
  <div class="tour-reviews-rating-total-text">${avgScore} based on ${reviewNumber} review</div>  
  </#if>
  <#if reviewNumber gt 1>
  <div class="tour-reviews-rating-total-text">${avgScore} based on ${reviewNumber} reviews</div>	
  </#if>
</div>
</#if>