<!-- 分页功能 start -->
<div class="row">
	<div class="col-xs-6">
		<div id="example-3_info" class="dataTables_info" role="status" aria-live="polite">从 ${page.startPos} 到 ${page.endPos} 共计 ${page.totalCount} 条记录</div>
	</div>
	<div class="col-xs-6">
		<div id="example-3_paginate" class="dataTables_paginate paging_simple_numbers">
			<ul class="pagination">
				<#if page.pageNow gt 1>
					<li id="example-3_previous" class="paginate_button previous" aria-controls="example-3" tabindex="0">
						<a href="javascript:goPage(${page.pageNow - 1})">上一页</a>
					</li>
				<#else>
					<li id="example-3_previous" class="paginate_button previous disabled" aria-controls="example-3" tabindex="0">
						<a>上一页</a>
					</li>
				</#if>
				<#assign start = 1>
					<#if (page.pageNow - 2 > 0)>
						<#assign start=(page.pageNow - 2)>
					</#if>
				<#assign end = (start + 6)>
					<#if (end > page.totalPageCount)>
						<#assign end = page.totalPageCount>
					</#if>
					<#if page.totalPageCount == 0>
						<#assign end = 1>
					</#if>
				<#if (page.pageSize > 6) && (start > page.pageSize-6)>
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
						<#if (end > 6) && (i= end - 1)>
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
					<li id="example-3_next" class="paginate_button next " aria-controls="example-3" tabindex="0">
						<a href="javascript:goPage(${page.pageNow + 1})">下一页</a>
					</li>
				<#else>
					<li id="example-3_next" class="paginate_button next disabled" aria-controls="example-3" tabindex="0">
						<a>下一页</a>
					</li>
				</#if>
			</ul>
		</div>
	</div>
</div>
<!-- 分页功能 End -->