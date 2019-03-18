<!DOCTYPE html>
<html lang="en">
	<head>
		<#assign ctx = request.contextPath />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="Xenon Boostrap Admin Panel" />
		<meta name="author" content="" />
		<title>线路产品系统信息</title>
			<!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic"> -->
		<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
		<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	    <link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
		<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
		<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
		<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
		<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
		<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
		<link rel="stylesheet" href="${ctx!}/assets/js/daterangepicker/daterangepicker-bs3.css">
		<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js"/></script>
		<script src="${ctx!}/assets/js/handlebars.min.js"></script>
		<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
		<script src="${ctx!}/assets/js/xenon-custom.js"></script>
			<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
			<!--[if lt IE 9]> <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script> <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script> <![endif] -->
	</head>
	
	<body class="page-body">
		<div class="page-container">
			<#include "/admin/include/left.ftl"/>
			<div class="main-content">
				<!-- User Info, Notifications and Menu Bar -->
				<#include "/admin/include/man.ftl"/>
				<div class="page-title">
					<div class="title-env">
						<h1 class="title">线路产品系统信息</h1>
					</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
							<li>
								<a href="${ctx!}/admin/list.do">
									<i class="fa-home"></i>
									首页
								</a>
							</li>
							<li>
								<a href="javascript:">线路管理</a>
							</li>
							<li class="active">
								<strong>线路列表</strong>
							</li>
						</ol>
					</div>
				</div>
				<div class="panel panel-default">
					<nav class="navbar navbar-inverse" role="navigation">
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<#--
							<ul class="nav navbar-nav navbar-left">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										销售中心
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<#list costList as cost>
											<li>
												<a href="${ctx!}/admin/orders/list.do?costId=${(cost.id)!}">${(cost.name)!}</a>
											</li>
										</#list>
									</ul>
								</li>
							</ul>
							-->
						</div>
					</nav>
					<div class="panel-body">
						<script type="text/javascript">
						jQuery(document).ready(function($)
						{
							$("#example-3").dataTable({
							    'bStateSave': true,
							    'bLengthChange': true,
							    'bFilter':false,
							    'bPaginate': false,
							    'bInfo': false,
							    'bSort': false,
							    //'sScrollX': "100%",
								//'sScrollXInner': "101%",
								//'bScrollCollapse': true,
								//'sScrollY': 800,//竖向滚动条 tbody区域的高度    
								
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
										<th>产品Code</th>
										<th>产品名称</th>
										<th>系统Code</th>
										<th>系统名称</th>
										<th>销售中心</th>
									</tr>
								</thead>
								<tbody>
								  <#list productList as product>
								   <tr>
										<td>${(product.code)!}</td>
										<td>${(product.name)!}</td>
										<td>${(product.productNo)!}</td>
										<td><#if (product.productNo)??>${(product.briefinfo)!}</#if></td>
										<td>${(product.costnumberids)!}</td>
									</tr>
								  </#list>
								</tbody>
							</table>
					</div>
					<#include "/admin/include/pagination.ftl"/>
				</div>
				<footer class="main-footer sticky footer-type-1">
					<div class="footer-inner">
						<div class="footer-text">
						&copy;
							2015
							<a href="" target="_blank" title="淘游网路">西安淘游网络科技有限公司</a>
						</div>
						<div class="go-up">
							<a href="#" rel="go-top">
								<i class="fa-angle-up"></i>
							</a>
						</div>
					</div>
				</footer>
			</div>
		</div>
	</body>
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
	<script type="text/javascript">
	//分页
	function goPage(pageNow){
		window.location.href="${ctx!}/admin/tourline/showProduct.do?pageNow="+pageNow;
	}
	</script>
</html>