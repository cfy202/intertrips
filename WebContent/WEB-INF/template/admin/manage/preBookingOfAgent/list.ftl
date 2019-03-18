<!DOCTYPE html>
<html lang="en">
	<head>
		<#assign ctx = request.contextPath />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="Xenon Boostrap Admin Panel" />
		<meta name="author" content="" />
		<title>Agent预订管理</title>
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
						<h1 class="title">Agent预订管理</h1>
						<p style="margin-top:10px; color:#2c2e2f;">
							<span>预订总额：${totalAndNumber.total!'0'}</span> 
							<span>预订数量：${totalAndNumber.pax!'0'}</span> 
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
								<strong>Agent预订管理</strong>
							</li>
						</ol>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<button class="btn btn-info" onclick="exportExcel();">导出Excel</button>
						<button class="btn btn-info" onclick="synchorizeToErp();">同步到ERP</button>
					</div>			
					<nav class="navbar navbar-inverse" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle " data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span>
								<i class="fa-bars"></i>
							</button>
							<a class="navbar-brand" href="javascript:clearAllParam();">所有预订</a>
						</div>
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<form class="navbar-form navbar-left" id="searchForm" action="${ctx!}/admin/prebookingofagent/list.do" method="POST" role="search">
								<div class="form-group">
									<input type="text" class="form-control searchInput" cookieName="bookingName" style="width:100px;" placeholder="booking No." name="name" value="${(bookingQueryVO.name)!}">
									<input type="text" class="form-control searchInput" cookieName="bookingProductNameOrCode" style="width:100px;" placeholder="productCode、productName" name="productNameOrCode" value="${(bookingQueryVO.productNameOrCode)!}"> 
									<input type="text" class="form-control searchInput" cookieName="bookingGateWay" style="width:100px;" placeholder="gateway" name="gateWay" value="${(bookingQueryVO.gateWay)!}"> 
									<input type="text" class="form-control searchInput" cookieName="bookingAgentCode" style="width:100px;" placeholder="agentCode" name="agentCode" value="${(bookingQueryVO.agentCode)!}"> 						
									<input type="text" id="departureDateBeforeLimitInput" cookieName="bookingDepartureDateBeforeLimit" class="searchInput Wdate" placeholder="departure date from" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'departureDateAfterLimitInput\')}'});" name="departureDateBeforeLimit" value="${(bookingQueryVO.departureDateBeforeLimit?string('yyyy-MM-dd'))!}">
									<input type="text" id="departureDateAfterLimitInput" cookieName="bookingDepartureDateAfterLimit" class="searchInput Wdate" placeholder="departure date to" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'departureDateBeforeLimitInput\')}'});" name="departureDateAfterLimit" value="${(bookingQueryVO.departureDateAfterLimit?string('yyyy-MM-dd'))!}">
									<input type="text" id="bookingTimeBeforeLimitInput" cookieName="preBookingTimeBeforeLimit" class="searchInput Wdate" placeholder="booking time from" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'bookingTimeAfterLimitInput\')}'});" name="bookingTimeBeforeLimit" value="${(bookingQueryVO.bookingTimeBeforeLimit?string('yyyy-MM-dd'))!}">
									<input type="text" id="bookingTimeAfterLimitInput" cookieName="preBookingTimeAfterLimit" class="searchInput Wdate" placeholder="booking time to" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'bookingTimeBeforeLimitInput\')}'});" name="bookingTimeAfterLimit" value="${(bookingQueryVO.bookingTimeAfterLimit?string('yyyy-MM-dd'))!}">				
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
										<#list costList as cost>
											<#if cost.id = bookingQueryVO.costId>
												 ${cost.name}
											</#if>
										</#list>
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<#list costList as cost>
										<li>
											<a href="${ctx!}/admin/prebookingofagent/list.do?costId=${cost.id}">${cost.name}</a>
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
											<a href="${ctx!}/admin/prebookingofagent/list.do?pageSize=10">10</a>
										</li>
										<li>
											<a href="${ctx!}/admin/prebookingofagent/list.do?pageSize=20">20</a>
										</li>
										<li>
											<a href="${ctx!}/admin/prebookingofagent/list.do?pageSize=50">50</a>
										</li>
										<li>
											<a href="${ctx!}/admin/prebookingofagent/list.do?pageSize=100">100</a>
										</li>
										<li>
											<a href="${ctx!}/admin/prebookingofagent/list.do?pageSize=${(page.totalCount)?c}">全部</a>
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
						<form id="exportIdsForm" action="${ctx!}/admin/prebookingofagent/exportExcel.do">
						<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><input type="checkbox" class="cbr"></th>
									<th>预订号</th>
									<th>名称</th>
									<th>人数</th>
									<th>产品CODE</th>
									<th>出发地</th>
									<th>出发日期</th>
									<th>下单时间</th>
									<th>Agent code</th>
									<th>是否同步</th>
									<th>是否在线支付</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<#list preBookingOfAgentList as preBookingOfAgent>
								<tr id="${preBookingOfAgent.id}">
									<td>
										<input type="checkbox" name="ids" class="cbr cbr-success" value="${preBookingOfAgent.id}">
									</td>
									<td>
										${preBookingOfAgent.bookingNo}
									</td>
									<td>
										${preBookingOfAgent.name}
									</td>
									<td>
										${preBookingOfAgent.pax}
									</td>
									<td>
										${preBookingOfAgent.productcode}
									</td>
									<td>
										${(preBookingOfAgent.gateway)!}
									</td>
									<td>
										${preBookingOfAgent.departuredate?string('yyyy-MM-dd')}
									</td>
									<td> 
										${preBookingOfAgent.createtime?string('yyyy-MM-dd HH:mm:ss')}
									</td>
									<td>
										${preBookingOfAgent.agentcode}
									</td>
									<td>
										<#if preBookingOfAgent.isSynchronize>
											是
										<#else>
											否
										</#if>
									</td>
									<td>
										<#if preBookingOfAgent.isOnlinePay?? && preBookingOfAgent.isOnlinePay>
											是
										<#else>
											否
										</#if>
									</td>
									<td>
									    <ul class="nav navbar-nav">
											<li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown">操作 <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li>
														<a href="${ctx!}/admin/prebookingofagent/showDetail.do?id=${preBookingOfAgent.id}">
															预订详情
														</a>
													</li>
													<#if !preBookingOfAgent.isSynchronize>
													<li>
														<a href="javascript:cancel('${preBookingOfAgent.id}');">
														  	取消预订
														</a>
													</li>	
													</#if>
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
			var cookieName = $(this).attr("cookieName");
			addCookie(cookieName,null,{path:"/"});	
			$("#pageNowInput").val('1');
		});
	});
	
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
		window.location.href="${ctx!}/admin/prebookingofagent/list.do?pageNow="+pageNow;
	}
	
	//清除所有参数
	var clearAllParam = function(){
		addCookie('bookingCostId',null,{path:"/"});
		addCookie('bookingName',null,{path:"/"});
		addCookie('bookingProductNameOrCode',null,{path:"/"});
		addCookie('bookingGateWay',null,{path:"/"});
		addCookie('bookingAgentCode',null,{path:"/"});
		addCookie('bookingDepartureDateBeforeLimit',null,{path:"/"});
		addCookie('bookingDepartureDateAfterLimit',null,{path:"/"});
		addCookie('preBookingTimeBeforeLimit',null,{path:"/"});
		addCookie('preBookingTimeAfterLimit',null,{path:"/"});
		$("#searchForm").find(".searchInput").val('');
		$("#pageNowInput").val('');
		window.location.href="${ctx!}/admin/prebookingofagent/list.do";
	}
	
	//导出excel
	var exportExcel = function(){
	    var $exportsForm = $("#exportIdsForm");
	    var checkedlength = $exportsForm.find("input[name='ids']:checked").size();
	    if(checkedlength == 0){
	    	alert('请选择导出记录.');
	    	return;
	    }
	    $exportsForm.submit();
	}
	
	//导入到erp
	var synchorizeToErp = function(){
		var $exportsForm = $("#exportIdsForm");
		$exportsForm.removeAttr("action");
	    var checkedlength = $exportsForm.find("input[name='ids']:checked").size();
	    if(checkedlength == 0){
	    	alert('请选择同步记录.');
	    	return;
	    }
		$.post("${ctx!}/admin/prebookingofagent/synchorizeToErp.do",$exportsForm.serialize(),function(result){
			if(result == 1){
			   alert('同步成功！');
			   window.location.href = "${ctx!}/admin/prebookingofagent/list.do";
			}else if(result == 0){
			   alert('同步完成,但系统有产品不存在！');
			   window.location.href = "${ctx!}/admin/prebookingofagent/list.do";
			}
		}); 
	}
	
	//取消预订
	var cancel = function(id){
		if(confirm('您确定取消这个预订吗？')){
			$.get("${ctx!}/admin/prebookingofagent/cancel.do?id=" + id,function(result){
				if(result == 1){
					alert('取消成功！');
					$("tr#" + id).remove();
				}
			}); 
		}
	}
	</script>
</html