<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>优惠券活动管理</title>
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
    <link href="${ctx!}/assets/js/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" media="screen" rel="stylesheet" type="text/css" />
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
					<h1 class="title">优惠券活动管理</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="#">优惠券活动管理</a>
						  </li>
						  <li class="active">
						     <strong>优惠券活动列表</strong>
						  </li>
						</ol>
				</div>
			</div>	
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/couponse/add.do'">新增优惠券</button>
					<button class="btn btn-info" onclick="window.location.href='javascript:myfrom.submit()'">生成页面</button>
				</div>
				<div class="panel-body">
					
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
					    var notice = '${(notice)!}';
					    if(notice!=null&&notice!=""){
					      alert(notice);
					    }
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
					<form role="forl" action="${ctx!}/admin/couponse/createHtml.do" id="myfrom" method="post">				
					<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<th>活动图片</th>
								<th>名称</th>
								<th>类型</th>
								<th>code</th>
								<th>总数量</th>
								<th>剩余数量</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>过期时间</th>
								<th>运营中心</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list couponselist as couponse >
							<tr>
							    <th class="no-sorting">
								        <#if (couponse.isCreate?? && couponse.isCreate !=0)>
									        <input type="checkbox" name = "tids" value="${(couponse.id)!}" class="cbr cbr-success">
									        <#else> 
									        <input type="checkbox" name = "tids" value="${(couponse.id)!}" class="cbr cbr-red">
									    </#if>
									
								</th>
								<td>
								   <div id="gallery">
								     <a href="${ctx!}${(couponse.pic)!}"><img src="${ctx!}${(couponse.pic)!}" height="18px" width="40px" id="img"/></a>
								   </div>
								</td>
								<#setting datetime_format="yyyy-MM-dd HH:mm"/>
								<td>${(couponse.name)!}</td>
								<td>
								<#if couponse.type??&&couponse.type=1>抽奖</#if>
								<#if couponse.type??&&couponse.type=2>会员派送</#if>
								<#if couponse.type??&&couponse.type=3>第三方合作</#if>
								</td>
								<td>${(couponse.couponseCode)!}</td>
								<td>${(couponse.amount)!}</td>
								<td>${(couponse.remaining)!}</td>
								<td>${(couponse.beginTime)!}</td>
								<td>${(couponse.overTime)!}</td>
								<td>${(couponse.expirationdate)!}</td>
								<td>${(couponse.cost.name)!}</td>
								<td>
								  <ul class="nav navbar-nav">
						
										<li class="dropdown">
											<a href="#" class="dropdown-toggle" data-toggle="dropdown">操作 <b class="caret"></b></a>
											<ul class="dropdown-menu">
												<li>
													<a href="${ctx!}/admin/couponse/update.do?id=${(couponse.id)!}">
									              	    修改优惠券
									                </a>
												</li>
												<li>
													<a href="${ctx!}/admin/couponse /delete.do?id=${(couponse.productid)!}">
									              	   删除优惠券
									                </a>
												</li>
												<li>
													<a href="${ctx!}/admin/level/list.do?couponseid=${(couponse.id)!}">
									              	   奖券等级管理
									                </a>
												</li>
												<#if couponse.type??&&couponse.type=1>
												<li>
													<a href="${ctx!}/admin/activityrules/list.do?couponseid=${(couponse.id)!}">
									              	   活动规则管理
									                </a>
												</li>
												<li>
													<a href="${ctx!}/admin/couponse/view.do?couponseid=${(couponse.id)!}" target='_black'>
									              	   页面预览
									                </a>
												</li>
												
												<li class="divider"></li>
												</#if>
												<li>
													<a href="${ctx!}/admin/couponse/realse.do?id=${(couponse.id)!}">
									              	  生成及派送
									                </a>
												</li>
												
												<li class="divider"></li>
												<li>
													<a href="${ctx!}/admin/couponse/issued.do?id=${(couponse.id)!}">
									              	  发放管理
									                </a>
												</li>
												
											</ul>
										</li>
									</ul>
									
								</td>
							</tr>	
							</#list>							
						</tbody>
					</table>	
					</from>				
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
