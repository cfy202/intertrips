<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>评论添加</title>
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
<script>
	$(function(){
		$("#costNumber").change(function(){
			var conum = $("#costNumber").val();
			$.ajax({
			type: "POST",
			url: "${ctx!}/admin/review/selectCost.do",
			data: "conum=" + conum,
			success: function(data) {
				$("#TOURLINE").empty();
				$.each(data,function(i,n){
					$("#TOURLINE").append("<option value=\"" + n.productid + "\">" + n.tourname + "</option>");
				});
			},
			error: function(e) {
				alert(e);
			},
			});
		
		})
	})
</script>
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
					<h1 class="title">评论添加</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/review/list.do">评论管理</a>
								</li>
							<li class="active">
						
										<strong>评论添加</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/review/addSave.do" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label">销售中心</label>									
									<div class="col-sm-10">
										<select class="form-control" name="costNumber" id="costNumber">
											<#list costlist as cost>
            									<option value="${(cost.id)!}">${(cost.name)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">线路名称</label>									
									<div class="col-sm-10">
										<select class="form-control" name="productId" id="TOURLINE">
											<#list tourline as tourline>
            									<option value="${(tourline.productid)!}">${(tourline.tourname)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#TOURLINE").select2({
										//		placeholder: '请选择产品',
												allowClear: false
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
								</script>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">评论标题</label>									
									<div class="col-sm-10">
										<input type="text" name="title" class="form-control" id="field-2" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">评论内容</label>									
									<div class="col-sm-10">
										<textarea class="form-control" name="content"  id="field-5"></textarea>
									</div>
								</div> 
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">旅行团评分星级</label>									
									<div class="col-sm-10">
										<select class="form-control" name="tourGroupScore">
        									<option value="5">5</option>
        									<option value="4">4</option>
        									<option value="3">3</option>
        									<option value="2">2</option>
        									<option value="1">1</option>
										</select>
									</div>
								</div> 
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">文景评分星级</label>									
									<div class="col-sm-10">
										<select class="form-control" name="wenjingScore">
        									<option value="5">5</option>
        									<option value="4">4</option>
        									<option value="3">3</option>
        									<option value="2">2</option>
        									<option value="1">1</option>
										</select>
									</div>
								</div> 
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">是否显示</label>
									<div class="col-sm-10">
										<label>
											<input type="checkbox" name="isshow" value="1" class="form-control" id="field-2" placeholder="">
										</label>											
									</div>
								</div>	
								
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input name="id" type="hidden" value="${id!}">
										<input type="hidden" name="status" value="1"/>
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/review/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
									</div>								
								</div>     		
							</form>
						</div>
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