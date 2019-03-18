<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>回复详情</title>
	
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">

	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
</head>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
	    <#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">问题回复详情</h1>
					<p class="description"></p>
				</div>
				<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
							<li>
								<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
							</li>
							<li>
								<a href="${ctx!}/admin/qna/list.do">QA管理</a>
							</li>
							<li>
								<a href="${ctx!}/admin/qna/answer.do?id="${answer.questionId}">问题回复列表</a>
							</li>
							<li class="active">
								<strong>问题回复详情</strong>
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
								    <form role="form" class="form-horizontal" action="${ctx!}/admin/qna/answer/save.do" method="post">
									    <table class="table responsive">
											<thead>
												<tr>
													<th>
													        会员：<#if answer.member??>
													        ${(answer.member.account)!}
													        <#else>
													                        游客
													        </#if>
													</th>
													<th>处理状态：${(answer.status=1)?string('已处理','<span style="color:red">未处理</span>')}</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<tr>
												    <td>姓名：${(answer.name)!}</td>
												    <td>邮箱：${(answer.email)!}</td>
												    <td>回复时间：${(answer.createDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
												</tr>
												<tr>
													<td colspan=3>回复内容：${(answer.content)!}</td> 
												</tr>
												<tr>
													<td>所在地：${(answer.ip)!}</td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td>是否显示：
													   <input type="checkbox" name="isshow" id="isshow" value="1" <#if answer.isshow=1>checked="checked"</#if>><label for="isshow">&nbsp;&nbsp;显示</label> 
													</td>
													<td></td>
													<td></td>
												</tr>
											</tbody>
										</table>
										<div class="form-group">
											<div class="col-sm-10" align="center">
											    <input type="hidden" name="status" value="1"/>
											    <input type="hidden" name="questionId" value="${(answer.questionId)!}"/>
											    <input type="hidden" name="id" value="${(answer.id)!}"/>
												<input type="submit" value="提交" class="btn btn-success">
												<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/qna/answer.do?id=${answer.questionId}';">
											</div>
										</div>
									</form>
								</div>
							</div>
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