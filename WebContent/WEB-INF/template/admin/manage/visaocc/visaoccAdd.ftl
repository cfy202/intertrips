<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>添加签证资料</title>
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
	$(document).ready(function() {
		 //校验
		 $("form#loginForm").validate({
			rules: {
				price: {
					number: true
				}
			},	
			messages: {
				price: {
					number: "请输入合法的数字(如：100/99.9)"
				}
			},
		  });
		  
		  //根据运营中心异步查询orderid和上级
		  $('#costnumber').bind('change', function () {
		       $.ajax({
	             type: "post",
	             url: "${ctx!}/admin/departure/getsort.do",
	             data: {costnumber:$("#costnumber").val()},
	             dataType: "json",
	             success: function(data){
	                         $('#sort').val(data);
	                      }
	         });
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
					<h1 class="title">添加签证资料</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/visacc/list.do">签证管理—职业列表</a>
						  </li>
							<li class="active">
								<a href=""><strong>添加签证资料</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/visaocc/save.do" method="post" id="loginForm">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="title">标题：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="title" name="title">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">职业分类</label>									
									<div class="col-sm-10">
										<select class="form-control" name="typeName" id="destinationid">
											<option value="在职人员">在职人员</option>
											<option value="自由职业">自由职业</option>
											<option value="在校学生">在校学生</option>
											<option value="退休">退休</option>
											<option value="学龄前儿童">学龄前儿童</option>
										</select>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="passport">内容：</label>
									<div class="col-sm-10">
									    <textarea class="form-control" name="content" cols="5" rows="6" id="passport"></textarea>
									</div>
								</div>
								
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sort">排序：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="sort" name="sort" value="${visaoccupation.sort?c}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
									    <input type="hidden" name = "id" value="${(visaoccupation.id)!}" >
									    <input type="hidden" name = "visaId" value="${visaId!}" >
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/visaocc/list.do?visaId=${visaId!}';">
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
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>