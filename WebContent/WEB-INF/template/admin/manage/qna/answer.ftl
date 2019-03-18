<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>回复内容管理</title>
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	  //异步修改处理状态
	   $(document).ready(function(){
	       $(".dispose").click(function () {
	            var answerId = $(this).attr("val");
	            $.ajax({
			          type: "post",
			          url: "${ctx!}/admin/qna/answer/status.do",
			          data:{id:answerId},
			          datatype:"json",
			          success:function(data){
			            
			          }
			       });
			     $(this).text("已处理");
                 $(this).removeAttr("style");
            });
	   });
	
	    //异步修改是否显示
	    function updateIsshow(answerId){
	       $.ajax({
	          type: "post",
	          url: "${ctx!}/admin/qna/answer/isshow.do",
	          data:{id:answerId,isshow:$("#"+answerId).val()},
	          datatype:"json",
	          success:function(data){
	            $("#answerId").val(data);
	            $("#status"+answerId).text("已处理");
	            $("#status"+answerId).removeAttr("style"); 
	          }
	       });
	    }
   </script>
   <style>
	  .list_cont_message {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		    width: 128px;
		    float: left;
		}
	</style>
</head>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
			 <#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">回复内容管理</h1>
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
						<li class="active">
						    <strong>回复内容管理</strong>
						</li>
					</ol>
			    </div>
			</div>	
			<div class="panel panel-default">
				<div class="panel-body">
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#example-3").dataTable({
						   'bStateSave': true,
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
								<th>姓名</th>
								<th>回复内容</th>
								<th>回复时间</th>
								<th>是否显示</th>
								<th>状态</th>
								<th>会员</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list anList as answer>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<td>${(answer.name)!}</td>
								<td>
								   <span class="list_cont_message" title="${(answer.content)!}">${(answer.content)!}</span>
								</td>
								<td>${(answer.createDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
								<td>
								    <input type="checkbox" id="${(answer.id)!}" value="${answer.isshow}" onclick="updateIsshow('${(answer.id)!}');" <#if answer.isshow=1>checked="checked"</#if>><label for="${(answer.id)!}">&nbsp;&nbsp;显示</label> 
								</td>
								<td>
								   ${(answer.status=1)?string('<span>已处理</span>','<span style="color:red;cursor:pointer;" id="status${(answer.id)!}" class="dispose" val="${(answer.id)!}">未处理</span>')}
								</td>
								<td>
								   <#if answer.member??>
							           ${(answer.member.account)!}
							        <#elseif answer.name="文景假期">
							                                文景假期
	                                <#else>
	                               		 游客
							        </#if>
								</td>
								<td>
									<a href="${ctx!}/admin/qna/answer/details.do?id=${(answer.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										详情
									</a>
									<a href="${ctx!}/admin/qna/answer/delete.do?id=${(answer.id)!}&qid=${(answer.questionId)!}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
										删除
									</a>
								</td>
							</tr>	
							</#list>							
						</tbody>
					</table>					
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
						&copy; 2015
					   <a href="" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
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
