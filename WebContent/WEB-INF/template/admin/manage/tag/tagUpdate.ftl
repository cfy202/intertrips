<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>系统参数修改</title>
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
	
		$(document).ready(function () {

			$(".pick-a-color").pickAColor({
			    showSpectrum            : true,
				showSavedColors         : true,
				saveColorsPerElement    : true,
				fadeMenuToggle          : true,
				showAdvanced			: true,
				showBasicColors         : true,
				showHexInput            : true,
				allowBlank				: true,
				inlineDropdown			: true
			});
			
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
					<h1 class="title">产品标签修改</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/tag/list.do">产品标签管理</a>
						  </li>
							<li class="active">
								<a href="#"><strong>产品标签修改</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/tag/save.do?id=${(tag.id)!}" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-1" name="name" value="${(tag.name)!}">
									</div>
								</div>
								
								<#--
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label">标签字体颜色</label>
									<div class="col-sm-2">
										<div class="input-group">
										    <input type="text" value="${(tag.textcolor)!}" name="textcolor" class="pick-a-color form-control">
										</div>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label">标签背景颜色</label>
									<div class="col-sm-2">
										<div class="input-group">
											 <input type="text" value="${(tag.bgcolor)!}" name="bgcolor" class="pick-a-color form-control">
										</div>
									</div>
								</div>
								-->
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标签背景颜色：</label>
									<div class="col-sm-10">
									    <label>  
									       <input type="radio" value="ffae2f" name="bgcolor" <#if tag.bgcolor??&&tag.bgcolor=="ffae2f">checked</#if>>&nbsp;&nbsp;&nbsp;
									       <span style="background:#ffae2f; display:inline-block; width:25px; height:25px;"></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>  
									       <input type="radio" value="50b5f7" name="bgcolor" <#if tag.bgcolor??&&tag.bgcolor=="50b5f7">checked</#if>>&nbsp;&nbsp;&nbsp;
									       <span style="background:#50b5f7; display:inline-block; width:25px; height:25px;"></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>  
									       <input type="radio" value="ff5500" name="bgcolor" <#if tag.bgcolor??&&tag.bgcolor=="ff5500">checked</#if>>&nbsp;&nbsp;&nbsp;
										   <span style="background:#ff5500; display:inline-block; width:25px; height:25px;"></span>
									    </label>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
									    <input type="hidden" value="${(tag.textcolor)!}" name="textcolor">
									    <input type="hidden" value="${(tag.type)!}" name="type">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/tag/list.do';">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${ctx!}/assets/js/colorpicker/pick-a-color-1.2.3.min.css">
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
    <script src="${ctx!}/assets/js/colorpicker/tinycolor-0.9.15.min.js"></script>
    <script src="${ctx!}/assets/js/colorpicker/pick-a-color-1.2.3.min.js"></script>

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>