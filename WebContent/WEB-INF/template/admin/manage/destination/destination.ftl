<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>目的地管理</title>
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
	 var message =  getCookie("Dcreatemessage");
	 if(message!=null&&message!=''){
	 alert(message);
	 addCookie("Dcreatemessage",null,{path:"/"});	
	 }			
	});
   </script>
	<script type="text/javascript">

	//分页
	function goPage(pageNow){
		window.location.href="${ctx!}/admin/destination/list.do?DpageNow="+pageNow;
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
      addCookie("Dsearch",null,{path:"/"})
    }
    
    // 获取Cookie
	function getCookie(name) {
	    if (name != null) {
	        var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
	        return value ? decodeURIComponent(value[1]) : null;
	    }
	}
	
	//清除cookie中存在的销售中心
	var clearQnaCostNum = function(){
		addCookie('desCostId',null,{path:"/"});
		window.location.href = "${ctx!}/admin/destination/list.do";
	}
	</script>
	<script>
		function updateShow(obj){
			var showStatus = $(obj).attr("showStatus");
			var showType = $(obj).attr("showType");
			var destinationId = $(obj).attr("destinationId");
			var costNumber = getCookie("desCostId");
//			alert(showStatus);
//			alert(showType);
//			alert(destinationId);
//			alert(costNumber);
	    	$.ajax({
		   		type: "POST",
		   		url: "${ctx!}/admin/destination/changeindexshow.do",
		   		data: {destinationId:destinationId,costNumber:costNumber,showType:showType,showStatus:showStatus},
		   		success:function(data){
		   			if(showType=="1"){
		   				if(showStatus == "show"){
		   					$(obj).attr("showStatus","noneShow");
		   					$(obj).text("上不显示");
		   				}else{
		   					$(obj).attr("showStatus","show");
		   					$(obj).text("上显示");
		   				}
		   			}
		   			if(showType=="2"){
		   				if(showStatus == "show"){
		   					$(obj).attr("showStatus","noneShow");
		   					$(obj).text("下不显示");
		   				}else{
		   					$(obj).attr("showStatus","show");
		   					$(obj).text("下显示");
		   				}
		   			}
		   		},
		   		error:function(e){
		   		},
    		});
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
					<h1 class="title">目的地管理</h1>
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
						
										<strong>目的地管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>	
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/destination/add.do'">添加目的地</button>
					<#if (costNumList?size=1)>
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/destination/desnav.do?costnumber=${costNumList[0]}'">目的地导航管理</button>
					</#if>
				</div>
				<nav class="navbar navbar-inverse" role="navigation">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
						<span class="sr-only">Toggle navigation</span>
						<i class="fa-bars"></i>
					</button>
					
					   <a class="navbar-brand" href="${ctx!}/admin/destination/list.do">所有目的地</a>	 
					
					
				</div>
			
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
					
					<form class="navbar-form navbar-left" action="${ctx!}/admin/destination/list.do" method="POST" role="search">
						<div class="form-group">
							<input type="text"  class="form-control" onFocus="realse()" placeholder="Search..." id="Dsearch" value="${Dsearch!}" name="search">
						    <input type="hidden" name ="DpageNow" value="${(page.pageNow)!}">
						    <input type="hidden" name ="costId" id="costId" <#if (costNumList?size>1)>value=""<#else>value="${costNumList[0]}"</#if>>
						</div>
						<button type="submit" class="btn btn-white">搜索</button>
					</form>
					
					<#if (cost?size>1)>
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
										<a href="${ctx!}/admin/destination/list.do?costId=${(cost.id)!}">${(cost.name)!}</a>
									</li>
								</#list>
							</ul>
						</li>
					</ul>
					</#if>
					
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">每页显示${(page.pageSize)!}条记录 <b class="caret"></b></a>
							<ul class="dropdown-menu dropdown-primary">
							   <li>
									<a href="${ctx!}/admin/destination/list.do?pageSize=10">10</a>
								</li>
							    <li>
									<a href="${ctx!}/admin/destination/list.do?pageSize=20">20</a>
								</li>
							    <li>
									<a href="${ctx!}/admin/destination/list.do?pageSize=50">50</a>
								</li>
								<li>
									<a href="${ctx!}/admin/destination/list.do?pageSize=100">100</a>
								</li>
								<li>
									<a href="${ctx!}/admin/destination/list.do?pageSize=${((page.totalCount)?c)!}">全部</a>
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
								<#--
								<th>排序</th>
								-->
								<th>英文名称</th>
								<th>中文名称</th>
								<th>类型</th>
								<th>隶属</th>	
								<th>热点</th>
								<#if (costNumList?size=1)>
								<th>状态</th>
								</#if>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
							<#list Alldestion as destination>
								<tr>
								   <th class="no-sorting">
										<input type="checkbox" class="cbr">
									</th>
									<#--
									<td>${((destination.sort)?c)!}</td>
									-->
									<td>${(destination.name)!}</td>
									<td>${(destination.namecn)!}</td>
									<td>
										<#if destination.typeid?? && destination.typeid=1>洲</#if>
										<#if destination.typeid?? && destination.typeid=2>国家</#if>
										<#if destination.typeid?? && destination.typeid=3>州/省</#if>
										<#if destination.typeid?? && destination.typeid=4>城市</#if>
									</td>
									<td>${(destination.ups)!}</td>		
									<td>
										<#if destination.ishot?? && destination.ishot=1>hot</#if>
										<#if destination.ishot?? && destination.ishot=0></#if>
									</td>
									<#if (costNumList?size=1)>
								    <td>
							    		<div class="btn-group" width="270">
							                <#if (upTourlineId?? && upTourlineId?index_of("${(destination.id)!}")!=-1)>
											    <button type="button" destinationId="${(destination.id)!}" style="width:80px;" showType="1" showStatus="show"
											    onclick="updateShow(this);" class="btn btn-red btn-sm">上显示
											    </button>
										 		<#else>
												<button type="button" destinationId="${(destination.id)!}" style="width:80px;" showType="1" showStatus="noneShow"
											    onclick="updateShow(this);" class="btn btn-red btn-sm">上不显示
											    </button>
											</#if>
											
										   <#if (downTourlineId?? && downTourlineId?index_of("${(destination.id)!}")!=-1)>
										    <button type="button" destinationId="${(destination.id)!}" style="width:80px;" showType="2" showStatus="show"
										    onclick="updateShow(this);" class="btn btn-blue btn-sm">下显示
										    </button>
									 		<#else>
											<button type="button" destinationId="${(destination.id)!}" style="width:80px;" showType="2" showStatus="noneShow"
										    onclick="updateShow(this);" class="btn btn-blue btn-sm">下不显示
										    </button>
											</#if>
							            </div>
						            </td>
						            </#if>
									<td>
										<a href="${ctx!}/admin/map/addDestinationCoordinate.do?id=${destination.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											指定坐标
										</a>
										<a href="${ctx!}/admin/map/showAttractionMap.do?destinationId=${destination.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											查看景点地图
										</a>
										<a href="${ctx!}/admin/destination/update.do?id=${destination.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											修改
										</a>	
										<#--								
										<a href="${ctx!}/admin/destination/delete.do?id=${destination.id}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
											删除
										</a>
										-->
										<a href="javascript:;" onclick="showAjaxModal('${(destination.id)!}');" class="btn btn-danger btn-sm btn-icon icon-left">删除</a>
										<a href="${ctx!}/admin/destination/createTour.do?id=${destination.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											生成线路页面
										</a>
										<#--
										<#if (costNumList?size=1)>
										<a href="${ctx!}/admin/destination/preview.do?name=${(destination.name)!}&costnumber=${costNumList[0]}" class="btn btn-secondary btn-sm btn-icon icon-left">
											预览列表页面
										</a>
										</#if>
										-->
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
		//删除目的地js
		function showAjaxModal(destinationId) {
			$.ajax({
			    type: "post",
	            url: "${ctx!}/admin/destination/isUse.do",
	            data: {destinationId:destinationId},
	            dataType: "json",
				success: function(data) {
				     $('#modal-6 .modal-body').empty();
				     var html ="";
				     if(data.flag){
				         html = "<div class=\"row\">"+
				                    "<div class=\"col-md-12\">"+
									"<div class=\"form-group\"><label for=\"replaceId\" class=\"control-label\">请选择替换此目的地：</label>"+	
	                                   "<select class=\"form-control\" name=\"replaceId\" id=\"replaceId\">";
	                     $.each(data.destinationList, function(commentIndex, destination){
	                         if(destination.id != data.destinationId){
	                          html +="<option value=\""+destination.id+"\" >" + destination.name + "-" + destination.namecn + "</option>";
	                         }
	                     });
	                     html +="</select></div></div></div>";
	                     $('#modal-6').modal('show', {backdrop: 'static'});
	                     $(".modal-title").text("该目的地正在使用！");
	                     $(".destinationId").val(data.destinationId);
	                     $('#modal-6 .modal-body').html(html);
				     }else{
				        if(confirm('确定要删除?')){
				          window.location.href="${ctx!}/admin/destination/delete.do?id="+data.destinationId;
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
			    <form action="${ctx!}/admin/destination/replaceDestination.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
					    <input type="hidden" name="destinationId" class="destinationId"/>
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