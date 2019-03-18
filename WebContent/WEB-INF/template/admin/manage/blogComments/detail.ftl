<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>blog评论详情</title>
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
					<h1 class="title">blog评论详情</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
										<a href="${ctx!}/admin/blogComments/list.do">blog评论管理</a>
								</li>
							<li class="active">
										<strong>blog评论详情</strong>
								</li>
								</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
						   <div class="row">
								<div class="col-sm-12">
								    <table class="table responsive">
										<thead>
											<tr>
												<th>
													博客title    	
												</th>
												<th>
													${(commentDetails.blog.tittle)!}
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
											    <td>评论内容</td>
											    <td>
											    	${(commentDetails.content)!}
											    </td>
											</tr>
										    <tr>
												<td>父级评论</td>
												<td>${(commentDetails.parent.content)!}</td>
											</tr>
											<tr>
												<td>评论时间</td> 
												<td>${commentDetails.createTime?string('yyyy-MM-dd')}</td>
											</tr>
											<tr>
												<td>评论人</td>
												<td>${commentDetails.member.account}</td>
											</tr>
											<tr>
												<td>是否显示</td>
												<td>
													<div class="form-group">
														<div class="col-sm-10">
															<div class="checkbox">
																<label>
																	<input id="isShow" type="checkbox" name="isshow" value="1"/>显示
																</label>
															</div>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td>审核状态</td>
												<td>
													<div class="form-group">
														<div class="col-sm-10">
															<div class="checkbox">
																<label>
																	<input id="statusCheck" type="checkbox" name="status" value="1"/>审核通过
																</label>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="form-group">
										<button type="button" id="confirmButton" class="btn btn-info btn-single">确定</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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
			var $isShow = $("#isShow");
			var $status = $("#statusCheck");
			
			<#if commentDetails.isShow = 1>
				$isShow.click();						
			</#if>
			<#if commentDetails.status = 1>
				$status.click();
			</#if>
			
			$("#confirmButton").click(function(){
				var isShow = 0;
				var status = 0;
			
				if($isShow.is(":checked")){
					isShow = 1;	
				}
				if($status.is(":checked")){
					status = 1;
				}
				$.post("${ctx!}/admin/blogComments/updateComments.do",{id:"${commentDetails.id}",status:status,isShow:isShow},function(data){
					if(data == 'success'){
						alert('更新成功');			
					}							
				});			
			});
		});
	</script>
</body>
</html>