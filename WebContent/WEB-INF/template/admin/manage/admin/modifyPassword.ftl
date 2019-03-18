<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>密码修改</title>
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
	    $("form#modifypassword").validate({
		rules: {
		    oldpassword: {
				required: true,
			},
		
			newpassword: {
				required: true,
			},
			
			confirm_password: {
                required: true,
                equalTo: "#newpassword"
           },
		},
		
		messages: {
		    oldpassword: {
				required: "请输入修改密码",
			},
		
			newpassword: {
				required: "请输入新密码",
			},
			
			confirm_password: {
                required: "请输入确认密码",
                equalTo: "两次输入密码不一致"
           },
		},
		
		submitHandler: function(form) {
				$.ajax({
						url: $("#modifypassword").attr("action"),
						type: "POST",
						data: {
							oldpassword: $("#oldpassword").val(),
							newpassword: $("#newpassword").val()
						},
						dataType: "json",
						cache: false,
						complete: function(message) {
							var result = eval("(" + message.responseText + ")");  
				              if(result.success) {  
				                 alert(result.message);
				                 window.location.href="${ctx!}/admin/loginout.do";
				                } else {  
				                  alert(result.message);  
				                }   
						}
				});
		 }
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
					<h1 class="title">管理员管理</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/manager/list.do">管理员管理</a>
								</li>
							<li class="active">
						
										<strong>密码修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">密码修改</h3>
							
						</div>
						<div class="panel-body">
							<form role="form" id="modifypassword" class="form-horizontal" action="${ctx!}/admin/passwordsave.do" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">用户名：</label>								
									<div class="col-sm-10">
										<input type="text" name="username" class="form-control" id="field-1" readonly="readonly" value="${username}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="oldpassword">原密码：</label>
									<div class="col-sm-10">
										<input type="password" name="oldpassword" class="form-control input-dark" id="oldpassword" autocomplete="off">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="password">新密码：</label>
									<div class="col-sm-10">
									    <input type="password" class="form-control input-dark" name="newpassword" id="newpassword" autocomplete="off"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="confirm_password">确认密码：</label>
									<div class="col-sm-10">
										<input type="password" name="confirm_password" class="form-control input-dark" id="confirm_password" autocomplete="off">
									</div>
								</div>
								 
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/list.do';">
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
	

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
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
    <script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
    <script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>