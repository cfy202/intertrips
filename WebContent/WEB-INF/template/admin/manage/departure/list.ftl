<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>出发地管理</title>
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
		$(document).ready(function() {
		   <#if message??>
		      alert("${message}");
		   </#if>
		}); 
    </script>
</head>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
			<#include "/admin/include/man.ftl"/>
			<div class="page-title">
				
				<div class="title-env">
					<h1 class="title">出发地管理</h1>
					<p class="description"></p>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
				    	<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
						<li>
							<a href="#">产品管理</a>
						</li>
						<li class="active">
								<strong>出发地管理</strong>
						</li>
					</ol>
				</div>
			</div>	
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/departure/add.do'">添加出发地</button>
					<!--<button class="btn btn-info">生成页面</button>-->
				</div>
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
								<th>名称</th>
								<th>所在城市</th>
								<th>地址</th>
								<th>价格</th>
								<th>说明</th>
								<!--
								<#if (cost?size>1)>
								<th>运营中心</th>
								</#if>
								-->
								<th>排序</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list departures as departure>
							<tr>
							    <th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<td>${(departure.name)!}</td>
								<td>${(departure.city)!}</td>
								<td>
								   <span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 128px;float: left;" title="${(departure.address)!}">${(departure.address)!}</span>
								</td>
								<td>${((departure.price)?c)!}</td>
								<td>${(departure.remark)!}</td>
								<!--
								<#if (cost?size>1)>
								<td>${(departure.cost.name)!}</td>
								</#if>
								-->
								<td>${(departure.sort)?c}</td>
								<td>
									<a href="${ctx!}/admin/departure/update.do?id=${(departure.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										修改
									</a>
									<#--
									<a href="${ctx!}/admin/departure/delete.do?id=${(departure.id)!}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
										删除
									</a>
									-->
									<a href="javascript:;" onclick="showAjaxModal('${(departure.id)!}');" class="btn btn-danger btn-sm btn-icon icon-left">删除</a>
								</td>
							</tr>	
							</#list>							
						</tbody>
					</table>					
				</div>
			</div>
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
			
	<script type="text/javascript">
		function showAjaxModal(departureId) {
			$.ajax({
			    type: "post",
	            url: "${ctx!}/admin/departure/isUse.do",
	            data: {departureId:departureId},
	            dataType: "json",
				success: function(data) {
				     $('#modal-6 .modal-body').empty();
				     var html ="";
				     if(data.flag){
				         html = "<div class=\"row\">"+
				                    "<div class=\"col-md-12\">"+
									"<div class=\"form-group\"><label for=\"replaceId\" class=\"control-label\">请选择替换此出发地：</label>"+	
	                                   "<select class=\"form-control\" name=\"replaceId\" id=\"replaceId\">";
	                     $.each(data.departures, function(commentIndex, departure){
	                         if(departure.id != data.departureId){
	                          html +="<option value=\""+departure.id+"\" >" + departure.name + " —— " + departure.city + "</option>";
	                         }
	                     });
	                     html +="</select></div></div></div>";
	                     $('#modal-6').modal('show', {backdrop: 'static'});
	                     $(".modal-title").text("该出发地正在使用！");
	                     $(".departureId").val(data.departureId);
	                     $('#modal-6 .modal-body').html(html);
				     }else{
				        if(confirm('确定要删除?')){
				          window.location.href="${ctx!}/admin/departure/delete.do?id="+data.departureId;
				        }
				     }
				}
			});
		}
	</script>
	
    <!-- 显示需要替换的出发地  -->
	<!-- Modal 6 (Long Modal)-->
	<div class="modal fade" id="modal-6">
		<div class="modal-dialog">
			<div class="modal-content">
			    <form action="${ctx!}/admin/departure/replaceDeparture.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
					
					</div>
					<div class="modal-footer">
					    <input type="hidden" name="departureId" class="departureId"/>
						<button type="submit" class="btn btn-info">提交</button>
						<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
					</div>
				</form>
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
