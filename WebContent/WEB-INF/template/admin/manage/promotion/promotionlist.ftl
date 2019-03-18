<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>促销活动管理</title>
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
	<script src="${ctx!}/assets/js/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js" type="text/javascript"></script>
    <link href="${ctx!}/assets//js/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" media="screen" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
		$(function() {
		$('#gallery a').lightBox();
		});
   </script>
	
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
					<h1 class="title">促销活动管理</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="#">营销活动管理</a>
								</li>
							<li class="active">
						
										<strong>促销活动管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>	
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/promotion/add.do'">添加活动</button>
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
					<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<th>标题</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<#--
								<th>折扣</th>
								<th>消费额满</th>
								<th>价格减</th>
								<th>描述</th>
								-->
								<th>是否显示</th>
								<th>生成页面</th>
								<th>图片</th>
								<#--
								<th>排序</th>
								-->
								<#if (cost?size>1)>
								<th>运营中心</th>
								</#if>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list promotions as promotion>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<td>${(promotion.title)!}</td>
								<td>${(promotion.beginDate)!}</td>
								<td>${(promotion.endDate)!}</td>
								<#--
								<td>
								   ${(promotion.discount)!}
								</td>
								<td>
								   <#if promotion.full??>
								   ${(promotion.currency.sign)!}
								   </#if>
								   ${(promotion.full)!}
								 </td>
								<td>
								   <#if promotion.reduce??>
								   ${(promotion.currency.sign)!}
								   </#if>
								   ${(promotion.reduce)!}
								</td>
								<td>${promotion.description}</td>
								-->
								<td>${(promotion.isshow=1)?string('是','<span style="color:red">否</span>')}</td>
								<td>${(promotion.isCreate=1)?string('是','<span style="color:red">否</span>')}</td>
								<td>
								  <div id="gallery">
								     <a href="${ctx!}${(promotion.imageurl)!}"><img src="${ctx!}${(promotion.imageurl)!}" height="18px" width="40px" id="img"/></a>
								  </div>
								</td>
								<#--
								<td>${(promotion.sort)!}</td>
								-->
								<#if (cost?size>1)>
								<td>${(promotion.cost.name)!}</td>
								</#if>
								<td>
									<a href="${ctx!}/admin/promotion/update.do?id=${(promotion.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										修改
									</a>
									<a href="${ctx!}/admin/promotion/delete.do?id=${(promotion.id)!}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
										删除
									</a>
									<a href="${ctx!}/admin/promotion/view.do?id=${(promotion.id)!}&costnumber=${(promotion.costnumber)!}" target="_black" class="btn btn-secondary btn-sm btn-icon icon-left">
										预览
									</a>
									<a href="${ctx!}/admin/promotion/create.do?id=${(promotion.id)!}&costnumber=${(promotion.costnumber)!}&filePath=${(promotion.filePath)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										静态化
									</a>
								</td>
							</tr>	
							</#list>							
						</tbody>
					</table>					
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
						&copy; 2015
					   <a href="" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
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
