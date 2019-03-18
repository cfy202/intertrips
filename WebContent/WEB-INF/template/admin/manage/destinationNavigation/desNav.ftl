<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>目的地导航管理</title>
    <!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	
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
					<h1 class="title">目的地导航管理</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/destination/list.do">目的地管理</a>
								</li>
							<li class="active">
						
										<strong>目的地导航管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>	
			<script>
				function formSubmit(){
					var checkedBox = $("input[name='destinationIdList']:checked");
					var size = checkedBox.size();
					if(size == 0){
						alert("请选择目的地");
					}else{
						$("#FORM").submit();
					}
				}
			</script>
			<div class="panel panel-default">
			    
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="formSubmit();">生成页面</button>
				</div>
				
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
					<form method="post" action="${ctx!}/admin/destination/create.do" id="FORM">
						<input type="hidden" name="costnumber" value="${(costObject.id)!}"/>					
						<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
								    <th class="no-sorting">
										<input type="checkbox" class="cbr">
									</th>
									<th>英文名称</th>
									<th>中文名称</th>
									<th>类型</th>
									<#--
									<th>隶属</th>	
									-->
									<th>链接地址</th>
									<th>销售中心</th>
									<th>操作</th>
								</tr>
							</thead>															
							<tbody>
								<#list destinationList as destination>
									<tr>
									   <th class="no-sorting">
											<input type="checkbox" class="cbr" name="destinationIdList" value="${(destination.id)!}">
										</th>
										<td>${(destination.name)!}</td>
										<td>${(destination.namecn)!}</td>
										<td>
											<#if destination.typeid?? && destination.typeid=1>洲</#if>
											<#if destination.typeid?? && destination.typeid=2>国家</#if>
											<#if destination.typeid?? && destination.typeid=3>州/省</#if>
											<#if destination.typeid?? && destination.typeid=4>城市</#if>
										</td>
										<#--
										<td>${(destination.ups)!}</td>	
										-->	
										<td>${(destination.desUrl)!}</td>
										<td>${(costObject.name)!}</td>
										<td>
											<a href="${ctx!}/admin/destination/updateUrl.do?costnumber=${costObject.id}&id=${(destination.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
												修改
											</a>									
											<a href="${ctx!}/admin/destination/preview.do?costnumber=${(costObject.id)!}&id=${(destination.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left" target="_blanck">
												预览
											</a>																		
										</td>
									</tr>
								</#list>											
							</tbody>
						</table>
					</form>					
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
	<script src="${ctx!}/assets/js/datatables/js/jquery.dataTables.min.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>