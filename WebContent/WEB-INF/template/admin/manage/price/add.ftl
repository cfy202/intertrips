<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>添加收费服务</title>
    <!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 
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
					<h1 class="title">添加价格</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
							   <li>
								<a href="${ctx!}/admin/price/list.do?productid=${(price.productid)!}">价格管理</a>
							  </li>
							<li class="active">
								<a href=""><strong>添加价格</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/price/save.do" method="post">
							
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标题</label>								
									<div class="col-sm-10">
										<input type="text" name="title" class="form-control" id="field-1" placeholder="">
									</div>
									
								</div>
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">价格</label>								
									<div class="col-sm-10">
										<div class="input-group input-group-sm input-group-minimal">
										<span class="input-group-addon">
											<i class="fa-sort-numeric-asc"></i>
										</span>
										<input type="text" class="form-control" name="price" data-mask="decimal" placeholder="Decimal" />
									</div>
									</div>
									
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">备注：</label>
									<div class="col-sm-10">
									    <textarea class="form-control" name="information" cols="5" id="field-1"></textarea>
									</div>
								</div>
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<div class="col-sm-10" align="center">
									    <input type ="hidden" name = "productid" value="${productid!}">
									    <input type ="hidden" name = "id" value="${(price.id)!}">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/price/list.do?productid=${productid!}';">
									</div>
								</div>
							</form>
						</div>
					</div>
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
	<script src="${ctx!}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
   

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>