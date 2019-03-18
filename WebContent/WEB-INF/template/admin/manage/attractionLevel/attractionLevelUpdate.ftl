<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>景点等级修改</title>
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
					<h1 class="title">景点等级修改</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
										<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/attractionLevel/list.do">景点等级管理</a>
								</li>
							<li class="active">
						
										<strong>景点等级修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/attractionLevel/save.do" method="post">
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">等级</label>								
									<div class="col-sm-10">
										<select class="form-control" name="level" id="level">
											<option value="1" <#if attractionLevel.level?? && attractionLevel.level=1>selected="selected"</#if>>1</option>
            								<option value="2" <#if attractionLevel.level?? && attractionLevel.level=2>selected="selected"</#if>>2</option>
            								<option value="3" <#if attractionLevel.level?? && attractionLevel.level=3>selected="selected"</#if>>3</option>
            								<option value="4" <#if attractionLevel.level?? && attractionLevel.level=4>selected="selected"</#if>>4</option>
            								<option value="5" <#if attractionLevel.level?? && attractionLevel.level=5>selected="selected"</#if>>5</option>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">说明</label>									
									<div class="col-sm-10">
										<input type="text" name="description" value="${(attractionLevel.description)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input name="id" type="hidden" id="id" value="${(attractionLevel.id)!}">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/attractionLevel/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
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
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>