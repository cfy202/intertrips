<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>后台管理首页</title>

	<!--<link rel="stylesheet" href="https://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">

	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	
</head>
<body class="page-body">

	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
			
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<#include "/admin/include/left.ftl"/>

		
		<div class="main-content">
					
			<!-- User Info, Notifications and Menu Bar -->
			<#include "/admin/include/man.ftl"/>
			
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">欢迎使用Intertrips网站后台管理系统！</h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-3">
					
					<div class="xe-widget xe-counter" data-count=".num" data-from="0" data-to="99.9" data-suffix="%" data-duration="2">
						<div class="xe-icon">
							<i class="linecons-cloud"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${ordersCount!}</strong>
							<span>最新订单</span>
						</div>
					</div>
					
				</div>
				<div class="col-sm-3">
					
					<div class="xe-widget xe-counter xe-counter-blue" data-count=".num" data-from="1" data-to="117" data-suffix="k" data-duration="3" data-easing="false">
						<div class="xe-icon">
							<i class="linecons-user"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${members!}</strong>
							<span>会员数量</span>
						</div>
					</div>
				
				</div>
				<div class="col-sm-3">
					
					<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="1000" data-to="2470" data-duration="4" data-easing="true">
						<div class="xe-icon">
							<i class="linecons-camera"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${tourlineCount!}</strong>
							<span>线路数量</span>
						</div>
					</div>
				
				</div>
				<div class="col-sm-3">
					
					<div class="xe-widget xe-counter xe-counter-red" data-count=".num" data-from="0" data-to="57" data-prefix="-," data-suffix="%" data-duration="5" data-easing="true" data-delay="1">
						<div class="xe-icon">
							<i class="linecons-lightbulb"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${attrCount!}</strong>
							<span>景点数量</span>
						</div>
					</div>
				
				</div>
			</div>
			<h3 class="text-gray">
				
				<small class="text-muted">线路统计</small>
			</h3>
			
			<!-- Xenon Conversations Widget -->
			<div class="row">
			
				<div class="col-md-12">
					
					<ul class="nav nav-tabs nav-tabs-justified">
						<li class="active">
							<a href="#home-3" data-toggle="tab">
								<span class="visible-xs"><i class="fa-home"></i></span>
								<span class="hidden-xs">即将过期的线路</span>
							</a>
						</li>
						<li>
							<a href="#messages-3" data-toggle="tab">
								<span class="visible-xs"><i class="fa-envelope-o"></i></span>
								<span class="hidden-xs">没有价格的线路</span>
							</a>
						</li>
						
					</ul>
					
					<div class="tab-content">
						<div class="tab-pane active" id="home-3">
						
							<div class="panel-body">
					
									<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#example-1").dataTable({
											aLengthMenu: [
												[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
											]
										});
										// Replace checkboxes when they appear
										var $state = $("#example-1 thead input[type='checkbox']");
										
										$("#example-1").on('draw.dt', function()
										{
											cbr_replace();
											
											$state.trigger('change');
										});
										
										// Script to select all checkboxes
										$state.on('change', function(ev)
										{
											var $chcks = $("#example-1 tbody input[type='checkbox']");
											
											if($state.is(':checked'))
											{
												$chcks.prop('checked', true).trigger('change');
											}
											else
											{
												$chcks.prop('checked', false).trigger('change');
											}
										});
									
									});
									</script>					
									<table id="example-1" class="table table-striped table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
											    <th class="no-sorting">
													<input type="checkbox" class="cbr">
												</th>
												<#--
												<th>封面图</th>
												-->
												<th>编号</th>
												<th>名称</th>
											</tr>
										</thead>															
										<tbody>
											<#list tourlist as tourlist>
										    <tr>
												   <th class="no-sorting">
														<input type="checkbox" class="cbr">
													</th>
													<#--
													<td>
													 <#if tourlist.productProductid.imageurl?has_content>
														<#list tourlist.productProductid.imageurl?split(",") as urls>
															<#if urls_index=0>
															   <a href="${ctx!}/admin/tourdate/list.do?tourlineId=${(tourlist.id)!}" class="xe-user-img">
																<img src="${ctx!}${urls}" class="img-circle" width="40" />
																</a>
															   </#if>
															</#list>
													    </#if>
												      </td>
												      -->
													<td><a href="${ctx!}/admin/tourdate/list.do?tourlineId=${(tourlist.id)!}">${(tourlist.productProductid.code)!}</a></td>
													<td>${(tourlist.productProductid.name)!}</td>
												</tr>
											  </#list>	
										</tbody>
									</table>					
								</div>
							</div>
							<div class="tab-pane " id="messages-3">
							<div class="panel-body">
					
									<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#example-3").dataTable({
											aLengthMenu: [
												[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
											]
										});
										// Replace checkboxes when they appear
										var $state = $("#example-3 thead input[type='checkbox']");
										
										$("#example-3").on('draw.dt', function()
										{
											cbr_replace();
											
											$state.trigger('change');
										});
										
										// Script to select all checkboxes
										$state.on('change', function(ev)
										{
											var $chcks = $("#example-3 tbody input[type='checkbox']");
											
											if($state.is(':checked'))
											{
												$chcks.prop('checked', true).trigger('change');
											}
											else
											{
												$chcks.prop('checked', false).trigger('change');
											}
										});
									
									});
									</script>					
									<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
											    <th class="no-sorting">
													<input type="checkbox" class="cbr">
												</th>
												<#--
												<th>封面图</th>
												-->
												<th>编号</th>
												<th>名称</th>
											</tr>
										</thead>															
										<tbody>
										    <#list tourlineNoprice as tourlist>
										    <tr>
												   <th class="no-sorting">
														<input type="checkbox" class="cbr">
													</th>
													<#--
													<td>
													 <#if tourlist.productProductid.imageurl?has_content>
														<#list tourlist.productProductid.imageurl?split(",") as urls>
															<#if urls_index=0>
															   <a href="${ctx!}/admin/tourdate/list.do?tourlineId=${(tourlist.id)!}" class="xe-user-img">
																<img src="${ctx!}${urls}" class="img-circle" width="40" />
																</a>
															   </#if>
															</#list>
													    </#if>
												      </td>
												      -->
													<td><a href="${ctx!}/admin/tourdate/list.do?tourlineId=${(tourlist.id)!}">${(tourlist.productProductid.code)!}</a></td>
													<td>${(tourlist.productProductid.name)!}</td>
												</tr>
											  </#list>
												
										</tbody>
									</table>					
								</div>
						</div>
						
						
					</div>
					
					
				</div>
			</div>
				
			
			
			
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">
				
				<div class="footer-inner">
				
					<!-- Add your copyright text here -->
					<div class="footer-text">
						 西安淘游网络科技有限公司 
					</div>
					
					
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
					
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>
						
					</div>
					
				</div>
				
			</footer>
		</div>
		
			
		
		<!-- end: Chat Section -->
		
		
	</div>
	
	
	
    <!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">


	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/js/jquery.dataTables.min.js"></script>
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
    

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>