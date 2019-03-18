<div class="atgrid-item-rating">
	 <#list  1..starNumber as i>
	 <i class="fa fa-star voted"></i>
	 </#list> 
	 <#if reviewNumber = 1>
	 <a href="" class="">(${reviewNumber} review)</a>
	 </#if>
	 <#if reviewNumber gt 1>
	 <a href="" class="">(${reviewNumber} reviews)</a>
	 </#if>
</div>