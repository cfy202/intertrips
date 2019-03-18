<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>用户评价管理</title>
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	   //异步修改处理状态
	   $(document).ready(function(){
	       $(".dispose").click(function () {
	            var reviewId = $(this).attr("val");
	            $.ajax({
			          type: "post",
			          url: "${ctx!}/admin/review/status.do",
			          data:{id:reviewId},
			          datatype:"json",
			          success:function(data){
			            
			          }
			       });
			     $(this).text("已处理");
			     $(this).removeClass("dispose");
                 $(this).removeAttr("style");
            });
	   });
	   
	   //异步修改是否显示
	    function updateIsshow(reviewId){
	       $.ajax({
	          type: "post",
	          url: "${ctx!}/admin/review/isshow.do",
	          data:{id:reviewId,isshow:$("#"+reviewId).val()},
	          datatype:"json",
	          success:function(data){
	            $("#reviewId").val(data);
	            $("#status"+reviewId).text("已处理");
	            $("#status"+reviewId).removeClass("dispose");
	            $("#status"+reviewId).removeAttr("style"); 
	          }
	       });
	    }
	</script>
	<style>
	  .list_cont_message {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		    width: 128px;
		    float: left;
		}
	</style>
</head>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
		    <#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">用户评价管理</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
							<li>
							   <a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						    </li>
							<li>
								<a href="#">用户评价管理</a>
							</li>
							<li class="active">
								<strong>评价列表</strong>
							</li>
						</ol>
				</div>
			</div>	
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/review/add.do'">添加评价</button>
				</div>
			    <nav class="navbar navbar-inverse" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle " data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span>
								<i class="fa-bars"></i>
							</button>
							<a class="navbar-brand" href="javascript:clearAllParam();">全部</a>
						</div>
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<form class="navbar-form navbar-left" id="searchForm" action="${ctx!}/admin/review/list.do" method="POST" role="search">
								<div class="form-group">
									<input type="text" class="form-control searchInput" style="width:350px;" placeholder="线路名称、标题" name="reviewSearch" value="${reviewSearch!}">
								    <input type="hidden" id="pageNowInput" name="pageNow" value="${(page.pageNow)!}">
								</div>
								<button type="submit" class="btn btn-white">搜索</button>
							</form>
							<ul class="nav navbar-nav navbar-left">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									    <#if (costNumList?size>1)>
									                   销售中心（全部）
									    <#else>
									       <#list cost as cost>
												<#if cost.id=costNumList[0]>
												   ${(cost.name)!}
												   <#break>
												</#if>
										   </#list>
									    </#if>
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
									    <li>
										   <a href="javascript:clearQnaCostNum();">销售中心（全部）</a>
									    </li>
										<#list cost as cost>
											<li>
												<a href="${ctx!}/admin/review/list.do?costId=${(cost.id)!}">${(cost.name)!}</a>
											</li>
										</#list>
									</ul>
								</li>
							</ul>
							<ul class="nav navbar-nav navbar-left">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<#if status?? && status == 2>
										        处理状态（全部） 
										<#elseif status?? && status == 0>
										          未处理
										<#elseif status?? && status == 1> 
										          已处理            
										</#if>
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<li>
											<a href="javascript:clearQnaStatus();">处理状态（全部）</a>
										</li>
										<li>
											<a href="${ctx!}/admin/review/list.do?status=1">已处理</a>
										</li>
										<li>
											<a href="${ctx!}/admin/review/list.do?status=0">未处理</a>
										</li>
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
											<a href="${ctx!}/admin/review/list.do?pageSize=10">10</a>
										</li>
										<li>
											<a href="${ctx!}/admin/review/list.do?pageSize=20">20</a>
										</li>
										<li>
											<a href="${ctx!}/admin/review/list.do?pageSize=50">50</a>
										</li>
										<li>
											<a href="${ctx!}/admin/review/list.do?pageSize=100">100</a>
										</li>
										<li>
											<a href="${ctx!}/admin/review/list.do?pageSize=${(page.totalCount)?c}">全部</a>
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
					<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<th>线路名称</th>
								<th>订单号</th>
								<th>标题</th>
								<th>内容</th>
								<th>评分</th>
								<th>是否显示</th>
								<th>状态</th>
								<th>创建日期</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list reviews as review>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<td>${(review.tourline.tourname)!}</td>
								<td>
								   <a href="${ctx!}/admin/orders/showOrders.do?orderNo=${(review.orderNo)!}" target="_blank">
								     ${(review.orderNo)!}
								   </a>
								</td>
								<td>
								   <span class="list_cont_message" title="${(review.title)!}">${(review.title)!}</span>
								</td>
								<td>
								    <span class="list_cont_message" title="${(review.content)!}">${(review.content)!}</span>
								</td>
								<td>
								  <span>文景评分：${review.wenjingScore}</span><br/>
								  <span>旅行团评分：${review.tourGroupScore}</span>
								</td>
								<td>
								   <input type="checkbox" id="${(review.id)!}" value="${review.isshow}" onclick="updateIsshow('${(review.id)!}');" <#if review.isshow=1>checked="checked"</#if>><label for="${(review.id)!}">&nbsp;&nbsp;显示</label> 
								</td>
								<td>
								    ${(review.status=1)?string('<span>已处理</span>','<span style="color:red;cursor:pointer;" id="status${(review.id)!}" class="dispose" val="${(review.id)!}">未处理</span>')}
								</td>
								<td>${(review.createDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
								<td>
									<a href="${ctx!}/admin/review/details.do?id=${(review.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										详情
									</a>
									<a href="${ctx!}/admin/review/delete.do?id=${(review.id)!}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
										删除
									</a>
								</td>
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
						&copy; 2015
					   <a href="#" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
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

	<script type="text/javascript">
		$(function(){
			$("#searchForm").on('focus','input',function(){
				var cookieName = $(this).attr("name");
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
			window.location.href="${ctx!}/admin/review/list.do?pageNow="+pageNow;
		}
		
		//清除所有参数
		var clearAllParam = function(){
		    addCookie('reviewCostId',null,{path:"/"});
			addCookie('reviewStatus',null,{path:"/"});
			addCookie('reviewPageNow',null,{path:"/"});
			addCookie('reviewPageSize',null,{path:"/"});
			addCookie('reviewSearch',null,{path:"/"});
			$("#searchForm").find(".searchInput").val('');
			$("#pageNowInput").val('');
			window.location.href="${ctx!}/admin/review/list.do";
		}
		
		//清除cookie中存在的状态
		var clearQnaStatus = function(){
			addCookie('reviewStatus',null,{path:"/"});
			window.location.href = "${ctx!}/admin/review/list.do";
		}
		
		//清除cookie中存在的销售中心
		var clearQnaCostNum = function(){
			addCookie('reviewCostId',null,{path:"/"});
			window.location.href = "${ctx!}/admin/review/list.do";
		}
	</script>
</body>
