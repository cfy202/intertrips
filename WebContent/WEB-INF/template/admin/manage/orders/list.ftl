<!DOCTYPE html>
<html lang="en">
	<head>
		<#assign ctx = request.contextPath />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="Xenon Boostrap Admin Panel" />
		<meta name="author" content="" />
		<title>订单管理</title>
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
						<h1 class="title">订单管理</h1>
						<p style="margin-top:10px; color:#2c2e2f;">
							<span>收款总额：${totalCollection!'0'}</span> 
							<span>收客总人数：${totalPeople!'0'}</span> 
						</p>
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
								<a href="javascript:">订单管理</a>
							</li>
							<li class="active">
								<strong>订单列表</strong>
							</li>
						</ol>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<button id="batchSynchorizeOrders" class="btn btn-info" onclick="batchSynchorizeOrders();">同步订单到ERP</button>
					</div>
					<nav class="navbar navbar-inverse" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle " data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span>
								<i class="fa-bars"></i>
							</button>
							<a class="navbar-brand" href="javascript:clearAllParam();">所有订单</a>
						</div>
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<form class="navbar-form navbar-left" id="searchForm" action="${ctx!}/admin/orders/list.do" method="POST" role="search">
								<div class="form-group">
									<input type="text" class="form-control searchInput" style="width:100px;" placeholder="订单编号" name="orderNo" value="${(orderQueryVO.orderNo)!}">
									<input type="text" class="form-control searchInput" style="width:100px;" placeholder="产品编号、产品CODE" name="productNoOrCode" value="${(orderQueryVO.productNoOrCode)!}"> 
									<input type="text" class="form-control searchInput" style="width:100px;" placeholder="联系人名称" name="contacterName" value="${(orderQueryVO.contacterName)!}">
									<input type="text" id="bookingTimeBeforeLimitInput" class="searchInput Wdate" placeholder="下单日期的最小日期" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'bookingTimeAfterLimitInput\')}'});" name="bookingTimeBeforeLimit" value="${(orderQueryVO.bookingTimeBeforeLimit?string('yyyy-MM-dd'))!}">
									<input type="text" id="bookingTimeAfterLimitInput" class="searchInput Wdate" placeholder="下单日期的最大日期" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'bookingTimeBeforeLimitInput\')}'});" name="bookingTimeAfterLimit" value="${(orderQueryVO.bookingTimeAfterLimit?string('yyyy-MM-dd'))!}">
									<input type="text" id="departureDateBeforeLimitInput" class="searchInput Wdate" placeholder="出发日期的最小日期" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'departureDateAfterLimitInput\')}'});" name="departureDateBeforeLimit" value="${(orderQueryVO.departureDateBeforeLimit?string('yyyy-MM-dd'))!}">
									<input type="text" id="departureDateAfterLimitInput" class="searchInput Wdate" placeholder="出发日期的最大日期" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'departureDateBeforeLimitInput\')}'});" name="departureDateAfterLimit" value="${(orderQueryVO.departureDateAfterLimit?string('yyyy-MM-dd'))!}">
									<input type="hidden" id="pageNowInput" name="pageNow" value="${(page.pageNow)!}">
								</div>
								<button type="submit" class="btn btn-white">搜索</button>
							</form>
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
							<ul class="nav navbar-nav navbar-left">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<#list orderStatusList as orderStatus>
											<#if orderStatusId?? && orderStatus.id = orderStatusId>
												${orderStatus.name}
												<#assign isChoose = true>
											</#if>
										</#list>
										<#if !(isChoose??)>
										支付状态
										</#if>
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<li>
											<a href="javascript:clearOrderStatus();">所有状态</a>
										</li>
										<#list orderStatusList as orderStatus>
										<li>
											<a href="${ctx!}/admin/orders/list.do?orderStatusId=${orderStatus.id}">${orderStatus.name}</a>
										</li>
										</#list>
									</ul>
								</li>
							</ul>
							<ul class="nav navbar-nav navbar-left">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<#list costList as cost>
											<#if cost.id = orderQueryVO.costId>
												 ${cost.name}
											</#if>
										</#list>
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<#list costList as cost>
										<li>
											<a href="${ctx!}/admin/orders/list.do?costId=${cost.id}">${cost.name}</a>
										</li>
										</#list>
									</ul>
								</li>
							</ul>
							<ul class="nav navbar-nav navbar-right">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										每页显示${page.pageSize}条记录
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<li>
											<a href="${ctx!}/admin/orders/list.do?pageSize=10">10</a>
										</li>
										<li>
											<a href="${ctx!}/admin/orders/list.do?pageSize=20">20</a>
										</li>
										<li>
											<a href="${ctx!}/admin/orders/list.do?pageSize=50">50</a>
										</li>
										<li>
											<a href="${ctx!}/admin/orders/list.do?pageSize=100">100</a>
										</li>
										<li>
											<a href="${ctx!}/admin/orders/list.do?pageSize=${(page.totalCount)?c}">全部</a>
										</li>
									</ul>
								</li>
							</ul>
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
						<form id="synchonizeOrderForm" >
						<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><input type="checkbox" class="cbr"></th>
									<th>订单号</th>
									<th>产品名称</th>
									<th>产品CODE</th>
									<th>商品数量</th>
									<th>总价</th>
									<th>联系人名称</th>
									<th>下单时间</th>
									<th>订单状态</th>
									<th>支付状态</th>
									<th>订单详情</th>
								</tr>
							</thead>
							<tbody>
								<#list ordersList as order>
								<tr>
									<td>
										<#if order.orderStatus.code == 3 && order.isSynchronized == 0 && order.orderType == 1>
										<input type="checkbox" name="orderIds" class="cbr cbr-success" value="${order.id}">
										</#if>
									</td>
									<td>
										<a href="showOrders.do?ordersId=${order.id}">${order.orderno}</a>
									</td>
									<td>
										${order.productName}
									</td>
									<td>
										${order.productCode}
									</td>
									<td>${order.quantity}</td>
									<td>${order.cost.code} ${order.totalprice}</td>
									<td>${order.firstName} ${order.lastName}</td>
									<td><#if (order.createtime)??>${order.createtime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
									<td>
									  <#if (order.timeLineStatus??)>
									    <select class="timeLineStatus" id="${order.orderno}" onchange="updateTimeLineStatus('${order.orderno}')">
											<option  value="New Order" <#if order.timeLineStatus="New Order">selected="selected"</#if> >New Order</option>
											<option  value="Order Confirmed" <#if order.timeLineStatus="Order Confirmed">selected="selected"</#if> >Order Confirmed</option>
											<option  value="Traveler's Info" <#if order.timeLineStatus="Traveler's Info">selected="selected"</#if>>Traveler's Info</option>
											<option  value="Tickets" <#if order.timeLineStatus="Tickets">selected="selected"</#if>>Tickets</option>
											<option  value="VISA" <#if order.timeLineStatus="VISA">selected="selected"</#if>>VISA</option>
											<option  value="Final" <#if order.timeLineStatus="Final">selected="selected"</#if>>Final</option>
											<option  value="Review" <#if order.timeLineStatus="Review">selected="selected"</#if>>Review</option>
										</select>
									  </#if>
									</td>
									<td>${order.orderStatus.name}</td>
									<#--
									<td>
										<a href="showOrders.do?id=${order.id}">订单详情</a>&nbsp;
										<a href="showOrderBills.do?ordersId=${order.id}">账单信息</a>
									</td>
									-->
									<td>
									    <ul class="nav navbar-nav">
											<li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown">操作 <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li>
														<a href="${ctx!}/admin/orders/showOrders.do?ordersId=${order.id}">
															订单详情
														</a>
													</li>
													<li>
														<a href="${ctx!}/admin/orders/showOrderBills.do?ordersId=${order.id}">
														  	账单信息
														</a>
													</li>
													<#if  order.orderStatus.code == 1 && order.orderType == 1 && !((order.occupyTime)??) >
													<li>
														<a href="${ctx!}/admin/orders/cancelOrder.do?ordersId=${order.id}"">
														  	取消订单
														</a>
													</li>	
													</#if>
													<#if  order.orderStatus.code == 4 && order.orderType == 1>
													<li>
														<a href="${ctx!}/admin/orders/showCancelRecord.do?ordersId=${order.id}">
														  	查看取消记录
														</a>
													</li>	
													</#if>													
													<#--
													<li>
														<a href="${ctx!}/admin/orders/invoice.do?ordersId=${order.id}">
															Invoice
														</a>
													</li>
													-->
												</ul>
											</li>
										</ul>
									</td>
								</tr>
								</#list>
							</tbody>
						</table>
						</form>
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
	$(function(){
		$("#searchForm").on('focus','input',function(){
			var cookieName = $(this).attr("name");
			addCookie(cookieName,null,{path:"/"});	
			$("#pageNowInput").val('1');
		});
		
	});
	
		//点击添加接送机
    function updateTimeLineStatus(orderNo) {
    	      var timeLineStatus = $("#"+orderNo+"").val();
				$.ajax({
					type: "POST",
					url: "${ctx!}/admin/orders/updateTimeLineStatus.do",
					data: "orderNo=" + orderNo+"&timeLineStatus="+timeLineStatus,
					success: function(data) {
					},
					error: function(e) {
						alert(e);
					},
				});
        }
	
	// 添加Cookie
	function addCookie(name, value, options) {
		if (arguments.length > 1 && name != null) {
			if (options == null) {
				options = {};
			}
			if (value == null) {
				options.expires = -1;
			}
			if (typeof options.expires == "number") {
				var time = options.expires;
				var expires = options.expires = new Date();
				expires.setTime(expires.getTime() + time * 1000);
			}
			document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path="
			 + options.path:"") + (options.domain ? "; domain=" + options.domain: ""),(options.secure ? "; secure": "");
		}
	}
	
	//分页
	function goPage(pageNow){
		window.location.href="${ctx!}/admin/orders/list.do?pageNow="+pageNow;
	}
	
	//清除所有参数
	var clearAllParam = function(){
		addCookie('orderNo',null,{path:"/"});
		addCookie('productNoOrCode',null,{path:"/"});
		addCookie('contacterName',null,{path:"/"});
		addCookie('orderStatusId',null,{path:"/"});
		addCookie('departureDateBeforeLimit',null,{path:"/"});
		addCookie('departureDateAfterLimit',null,{path:"/"});
		addCookie('bookingTimeBeforeLimit',null,{path:"/"});
		addCookie('bookingTimeAfterLimit',null,{path:"/"});
		addCookie('costId',null,{path:"/"});
		$("#searchForm").find(".searchInput").val('');
		$("#pageNowInput").val('');
		window.location.href="${ctx!}/admin/orders/list.do";
	}
	
	//清除cookie中存在的订单状态,加载状态的订单
	var clearOrderStatus = function(){
		addCookie('orderStatusId',null,{path:"/"});
		window.location.href = "${ctx!}/admin/orders/list.do";
	}
	
	//同步订单
	var synchronizeOrder = function(abutton,ordersId){
		$.post("${ctx!}/admin/orders/synchronizeOrder.do",{ordersId:ordersId},function(result){
			if(result == 'ok'){
				alert('同步成功！');
				$(abutton).parent().remove();
			}else if(result == 'notExists'){
				alert('该产品在erp系统中不存在！');
			}else{
				alert('同步出错！');
			}		
		});
	}
	
	//批量同步订单
	var batchSynchorizeOrders = function(){
		$("#batchSynchorizeOrders").attr("disabled",true);
	    var $chooseOrders = $("#example-3 tbody input[type='checkbox']:checked");
		if($chooseOrders.size() == 0){
			$("#batchSynchorizeOrders").attr("disabled",false);
			return;
		}
		$.post("${ctx!}/admin/orders/synchronizeOrders.do",$("#synchonizeOrderForm").serialize(),function(result){
			if(result == 1){
				alert('同步成功！');
				window.location.href = window.location.href;
			}else if(result == 0){
				alert('同步完成,但系统有产品不存在！');
				window.location.href = window.location.href;
			}
s		}); 	
	}
	</script>
</html>