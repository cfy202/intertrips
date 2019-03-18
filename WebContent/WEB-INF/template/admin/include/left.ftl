<#assign ctx = request.contextPath />
<div class="sidebar-menu toggle-others fixed">
			
			<div class="sidebar-menu-inner">	
				
				<header class="logo-env">
					
					<!-- logo -->
					<div class="logo">
						<a href="${ctx!}/admin/list.do" class="logo-expanded">
							<img src="${ctx!}/assets/images/logo-wenjing.png"  alt="" />
						</a>
						
						<a href="${ctx!}/admin/list.do" class="logo-collapsed">
							<img src="${ctx!}/assets/images/logo-wenjing-small.png" width="68" alt="" />
						</a>
					</div>
					
					<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
					<div class="mobile-menu-toggle visible-xs">
						<a href="#" data-toggle="user-info-menu">
							<i class="fa-bell-o"></i>
							<span class="badge badge-success">7</span>
						</a>
						
						<a href="#" data-toggle="mobile-menu">
							<i class="fa-bars"></i>
						</a>
					</div>
					
					<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
					
					
								
				</header>
						
				
						
				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
					
					<li>
					<#assign treeid = "">
					<#assign treeid2 = "">
					<#assign n = 0 >
					<#list tree as tree >					
					    <#if 'root' == tree.parentid >					    
					    <#if treeid != "" >
					    <#if n != 0 >
					    </ul>
					    </#if>
					</li>
					<li>
					    </#if>
						<a href="#">
							<i class="linecons-cog"></i>
							<span class="title">${tree.name}</span>
						</a>
						<#assign treeid = tree.id >
						<#assign treeid2 = "">
						<#assign n = 0 >
						</#if>
						<#if 'root' != tree.parentid >
						<#assign n = n + 1 >
						<#if treeid2 == "" >
						 <#if dids?? && dids=tree.parentid>
						   <ul style="display: block;">
						  <#else>
						  <ul>
						 </#if>
						</#if>
						   <#if tid?? && tid=tree.id>
							<li style=" background-color:#4a4d4f">
								<a href="${ctx!}${tree.url}?did=${tree.parentid}&tid=${tree.id}">
									<span class="title">${tree.name}</span>
								</a>
							</li>
						   <#else>
						   <li>
								<a href="${ctx!}${tree.url}?did=${tree.parentid}&tid=${tree.id}">
									<span class="title">${tree.name}</span>
								</a>
							</li>
						   </#if>												
						<#assign treeid2 = tree.id >						
						</#if>					
					</#list>
					</li>
				</ul>	
			</div>			
		</div>