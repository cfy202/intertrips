<#--
<div class="text-center">
    <ul class="page-numbers">
    	<li><a class="next page-numbers" href="">Previous</a></li>
        <li><span class="page-numbers current">1</span></li>
        <li><a class="page-numbers" href="">2</a></li>
        <li><a class="next page-numbers" href="">Next</a></li>
    </ul>
</div>		
-->
<#if page??>
<div class='text-center'>
			<ul class="pagination">
				<#if page.pageNow gt 1>
				     <li>
						<a href="javascript:goPage(${page.pageNow - 1})">
						 <i class='fa fa-angle-left'></i>
						</a>
					 </li>
					
				<#else>
					<li class='disabled'>
						 <a href='#'>
						 <i class='fa fa-angle-left'></i>
						</a>
					 </li>
				</#if>
				<#assign start = 1>
					<#if (page.pageNow - 2 > 0)>
						<#assign start=(page.pageNow - 2)>
					</#if>
				<#assign end = (start + 4)>
					<#if (end > page.totalPageCount)>
						<#assign end = page.totalPageCount>
					</#if>
					<#if page.totalPageCount == 0>
						<#assign end = 1>
					</#if>
				<#if (page.pageSize > 4) && (start > page.pageSize-4)>
					<li class="paginate_button" aria-controls="example-3" tabindex="0">
						<a href="javascript:goPage(1)">1</a>
					</li>
					<li id="example-3_ellipsis" class="paginate_button disabled" aria-controls="example-3" tabindex="0">
						<a href="#">…</a>
					</li>
				</#if>
				<#list start..end as i>
					<#if (page.pageNow == i)>
						<li class="paginate_button active" aria-controls="example-3" tabindex="0">
							<a href="javascript:goPage(${i})">${i}</a>
						</li>
					<#else>
						<#if (end > 4) && (i= end - 1)>
							<li class="paginate_button" aria-controls="example-3" tabindex="0">
								<a href="javascript:goPage(${i})">${i}</a>
							</li>
							<li id="example-3_ellipsis" class="paginate_button disabled" aria-controls="example-3" tabindex="0">
								<a href="#">…</a>
							</li>
						<#else>
							<#if i = end>
								<li class="paginate_button" aria-controls="example-3" tabindex="0">
									<a href="javascript:goPage(${page.totalPageCount})">${page.totalPageCount}</a>
								</li>
							<#else>
								<li class="paginate_button" aria-controls="example-3" tabindex="0">
									<a href="javascript:goPage(${i})">${i}</a>
								</li>
							</#if>
						</#if>
				    </#if>
				</#list>
				<#if page.pageNow lt page.totalPageCount>
					<li>
						<a href="javascript:goPage(${page.pageNow + 1})">
						<i class='fa fa-angle-right'></i>
						</a>
					</li>
				<#else>
					<li class='disabled'>
						<a>
						<i class='fa fa-angle-right'></i>
						</a>
					</li>
				</#if>
			</ul>
		</div>
</#if>
