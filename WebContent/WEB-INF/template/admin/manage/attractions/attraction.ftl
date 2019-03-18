<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>景点管理</title>
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
	<script type="text/javascript">
	$(function(){
	 var message =  getCookie("Acreatemessage");
	 if(message!=null&&message!=''){
	 alert(message);
	 addCookie("Acreatemessage",null,{path:"/"});	
	 }			
	});
   </script>
	<script type="text/javascript">

		//分页
		function goPage(pageNow){
			window.location.href="${ctx!}/admin/attraction/list.do?AtpageNow="+pageNow;
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
	        document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path: "") + (options.domain ? "; domain=" + options.domain: ""),
	        (options.secure ? "; secure": "");
	    }
	}
    
    function realse(){
      addCookie("Atsearch",null,{path:"/"})
    }
    
    // 获取Cookie
	function getCookie(name) {
	    if (name != null) {
	        var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
	        return value ? decodeURIComponent(value[1]) : null;
	    }
	
	}
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
					<h1 class="title">景点管理</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="#">资源管理</a>
								</li>
							<li class="active">
						
										<strong>景点管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>	
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/attraction/add.do'">添加景点</button>
				</div>
				<nav class="navbar navbar-inverse" role="navigation">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
						<span class="sr-only">Toggle navigation</span>
						<i class="fa-bars"></i>
					</button>
					
					   <a class="navbar-brand" href="${ctx!}/admin/attraction/list.do">所有景点</a>	 
					
					
				</div>
			
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
					
					<form class="navbar-form navbar-left" action="${ctx!}/admin/attraction/list.do" method="POST" role="search">
						<div class="form-group">
							<input type="text"  class="form-control" onFocus="realse()" placeholder="Search..." id="Asearch" value="${Atsearch!}" name="search">
						    <input type="hidden" name ="AtpageNow" value="${(page.pageNow)!}">
						</div>
						<button type="submit" class="btn btn-white">搜索</button>
					</form>
					
					<ul class="nav navbar-nav navbar-right">
						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">每页显示${(page.pageSize)!}条记录 <b class="caret"></b></a>
							<ul class="dropdown-menu dropdown-primary">
							   <li>
									<a href="${ctx!}/admin/attraction/list.do?pageSize=10">10</a>
								</li>
							    <li>
									<a href="${ctx!}/admin/attraction/list.do?pageSize=20">20</a>
								</li>
							    <li>
									<a href="${ctx!}/admin/attraction/list.do?pageSize=50">50</a>
								</li>
								<li>
									<a href="${ctx!}/admin/attraction/list.do?pageSize=100">100</a>
								</li>
								<li>
									<a href="${ctx!}/admin/attraction/list.do?pageSize=${(page.totalCount)?c}">全部</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
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
								<th>排序</th>
								<th>英文名称</th>
								<th>中文名称</th>
								<th>热点</th>
								<th>所属目的地</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
							<#list Allattraction as attraction>
								<tr>
								   <th class="no-sorting">
										<input type="checkbox" class="cbr">
									</th>
									<td>${((attraction.sort)?c)!}</td>
									<td>${(attraction.name)!}</td>
									<td>${(attraction.namecn)!}</td>
									<td>
										<#if attraction.ishot?? && attraction.ishot=1>hot</#if>
									</td>
									<td>${(attraction.destinationDestinationid.namecn)!}</td>
									<td>
										<a href="${ctx!}/admin/map/addAttractionCoordinate.do?id=${attraction.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											指定坐标
										</a>
										<a href="${ctx!}/admin/attraction/update.do?id=${attraction.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											修改
										</a>
										<#--									
										<a href="${ctx!}/admin/attraction/delete.do?id=${attraction.id}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
											删除
										</a>
										-->
										<a href="javascript:;" onclick="showAjaxModal('${(attraction.id)!}');" class="btn btn-danger btn-sm btn-icon icon-left">删除</a>
										<a href="${ctx!}/admin/attraction/createTour.do?id=${attraction.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											生成线路页面
										</a>																		
									</td>
								</tr>
							</#list>											
						</tbody>
					</table>	
					<#include "/admin/include/pagination.ftl"/>				
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
			
	<script type="text/javascript">
		$(document).ready(function(){
			<#if noticeMessage??>
			alert("${noticeMessage}");
			</#if>
		});
		//删除景点js
		function showAjaxModal(attractionId) {
			$.ajax({
			    type: "post",
	            url: "${ctx!}/admin/attraction/isUse.do",
	            data: {attractionId:attractionId},
	            dataType: "json",
				success: function(data) {
				     $('#modal-6 .modal-body').empty();
				     var html ="";
				     if(data.flag){
				         html = "<div class=\"row\">"+
				                    "<div class=\"col-md-12\">"+
									"<div class=\"form-group\"><label for=\"replaceId\" class=\"control-label\">请选择替换此景点：</label>"+	
	                                   "<select class=\"form-control\" name=\"replaceId\" id=\"replaceId\">";
	                     $.each(data.attractionList, function(commentIndex, attraction){
	                         if(attraction.id != data.attractionId){
	                          html +="<option value=\""+attraction.id+"\" >" + attraction.name + "-" + attraction.namecn + "</option>";
	                         }
	                     });
	                     html +="</select></div></div></div>";
	                     $('#modal-6').modal('show', {backdrop: 'static'});
	                     $(".modal-title").text("该景点正在使用！");
	                     $(".attractionId").val(data.attractionId);
	                     $('#modal-6 .modal-body').html(html);
				     }else{
				        if(confirm('确定要删除?')){
				          window.location.href="${ctx!}/admin/attraction/delete.do?id="+data.attractionId;
				        }
				     }
				     
			     	//增加搜索功能js
			     	$("#replaceId").select2({
					//	placeholder: '请选择产品',
					allowClear: false
					}).on('select2-open', function()
					{
						// Adding Custom Scrollbar
						$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
					});
				     
				     
				}
			});
		}
	</script>
	
    <!-- 显示需要替换的出发地  -->
	<!-- Modal 6 (Long Modal)-->
	<div class="modal fade" id="modal-6">
		<div class="modal-dialog">
			<div class="modal-content">
			    <form action="${ctx!}/admin/attraction/replaceAttraction.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
					    <input type="hidden" name="attractionId" class="attractionId"/>
						<button type="submit" class="btn btn-info">提交</button>
						<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>	
	
	
	
	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2-bootstrap.css">

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
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>