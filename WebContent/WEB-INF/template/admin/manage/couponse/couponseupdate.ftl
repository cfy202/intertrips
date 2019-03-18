<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>修改优惠券</title>
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
	<script type="text/javascript">
	$(document).ready(function() {
	    var couponseCode  = $("#couponseCode").val();
	     
		     if(couponseCode!=''&&couponseCode==3){
		         $("#showCode").show();
		         $("#chooseF").hide();
		      }
		      if(couponseCode!=''&&couponseCode==2){
		        $("#chooseF").hide();
		        $("#showCode").hide();
		      }
		     if(couponseCode!=''&&couponseCode==1) {
		      
		       $("#showCode").hide();
		       $("#chooseF").show();
		     }
	    
		$("#uploadify").uploadify({
				        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf',
				        'script' : '${ctx!}/upload.do?path=images/promotion',
				        'cancelImg' :'${ctx!}/assets/script/cancel.png',
				        'queueID' : 'fileQueue',
				        'queueSizeLimit'  :10,
				        'fileDesc' : 'jpg、gif、swf文件、png',            
				        'fileExt' : '*.jpg;*.gif;*.swf;*.png',
				        'auto' : true,
				        'multi' : false,
				        'simUploadLimit' : 10,
				        'buttonText' : 'upload',
				        'displayData' : 'percentage',
				        onComplete: function (evt, queueID, fileObj, response, data) {
				         $("#changephoto").val(response);
				          $("#imgshow").attr("src","${ctx!}"+response);
				       }
		}); 
		
		   //手动输入优惠券
		  $('#couponseCode').bind('change', function () {
		     var couponseCode = $("#couponseCode").val();
		     
		   if(couponseCode!=''&&couponseCode==3){
		         $("#showCode").show();
		         $("#chooseF").hide();
		      }
		      if(couponseCode!=''&&couponseCode==2){
		        $("#chooseF").hide();
		        $("#showCode").hide();
		      }
		     if(couponseCode!=''&&couponseCode==1) {
		      
		       $("#showCode").hide();
		       $("#chooseF").show();
		     }
			  
		  });

		  
		 //产品选择
		  $('#s2example-1').bind('change', function () {
		      var $productTable = $("#productTable");
		      var $productTitle = $("#productTitle");
			  var trHtml = 
					"<tr class=\"productTr\">"+
						"<td>"+
							"<input type=\"hidden\" name=\"productIds\" value=\""+ $(this).val() +"\" \/>"+
							"&nbsp;"+
						"<\/td>"+
						"<td>"+
							"<span title=\"\">"+ $('#s2example-1 option:selected').text() +"<\/span>"+
						"<\/td>"+
						"<td>"+
						<#--
							"<a href=\"\" target=\"_blank\" class=\"btn btn-white\">查看<\/a>"+
						-->
							"<a href=\"javascript:;\" id=\"deleteProduct\" class=\"btn btn-white\" onclick=\"deleteProduct(this)\">删除<\/a>"+
						"<\/td>"+
					"<\/tr>";
				  $productTitle.show();
				  $productTable.append(trHtml);
		  });
	  
	}); 
	
	 function deleteProduct(nowtr){
	           if(confirm("确定删除吗？")){
				$(nowtr).closest("tr").remove();
				if ($("#productTable").find("tr.productTr").size() <= 0) {
					$("#productTitle").hide();
				}
	           }
	     }
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
					<h1 class="title">优惠券修改</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/couponse/list.do">优惠券管理</a>
						  </li>
							<li class="active">
								<a href=""><strong>优惠券修改</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/couponse/save.do" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">运营中心：</label>
									<div class="col-sm-10">
									    <input type="text"  value="${(costs.name)!}" class="form-control" readonly="readonly"/>
										<input type="hidden" name="costnumber" id="costnumber" value="${(couponse.costnumber)!}">
									</div>
								</div>
							
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称</label>								
									<div class="col-sm-10">
										<input type="text" name="name" value="${(couponse.name)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
									<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">优惠券类型：</label>
									<div class="col-sm-10">
									    <select name="type" class="form-control" id="couponseCode">
									          <option value="1" <#if couponse.type??&&couponse.type=1>selected="selected"</#if>>站内</option>
									          <option value="2" <#if couponse.type??&&couponse.type=2>selected="selected"</#if>>会员派送</option>
									          <option value="3" <#if couponse.type??&&couponse.type=3>selected="selected"</#if>>第三方合作</option>
									    </select>
									</div>
								</div>
							
								<div class="form-group-separator"></div>
								<div style="display:none" class="form-group" id="showCode" >
									<label class="col-sm-2 control-label" for="costnumber">优惠券编号：</label>
									<div class="col-sm-10">
									    <input type="text" name="couponseCode" value="${(couponse.couponseCode)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
							
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">图片：</label>
									<div class="col-sm-10">
									    <img src="${ctx!}${(couponse.pic)!}" id="imgshow"><br/></br>
										<input type="file" class="form-control" id="uploadify" name="uploadify">
										<input type="hidden" name="pic" id="changephoto" value="${(couponse.pic)!}"/>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">开始日期：</label>
									<div class="col-sm-4">
									      <input type="text" id="beginTime" name="beginTime" class="text Wdate" value="${(couponse.beginTime)!}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">结束日期：</label>
									<div class="col-sm-4">
								          <input type="text" id="overTime" name="overTime" class="text Wdate" value="${(couponse.overTime)!}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" />
									</div>
								</div>
								<div class="form-group-separator"></div>
						<div id="chooseF">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">描述：</label>
									<div class="col-sm-10">
									    <textarea class="form-control ckeditor" name="infor" id="metadescription" rows="5" placeholder="填写活动描述" >${(couponse.infor)!}</textarea>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">生成路径</label>								
									<div class="col-sm-10">
										<input type="text" name="filePath" value="${(couponse.filePath)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
							</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">总数量</label>								
									<div class="col-sm-10">
										<div class="input-group input-group-sm input-group-minimal">
										<span class="input-group-addon">
											<i class="fa-sort-numeric-asc"></i>
										</span>
										<input type="text" class="form-control" name="amount" value="${(couponse.amount)!}" data-mask="decimal" placeholder="Decimal" />
									</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">剩余数量</label>								
									<div class="col-sm-10">
										<div class="input-group input-group-sm input-group-minimal">
										<span class="input-group-addon">
											<i class="fa-sort-numeric-asc"></i>
										</span>
										<input type="text" class="form-control" name="remaining" value="${(couponse.remaining)!}" data-mask="decimal" placeholder="Decimal" />
									</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
							<div class="form-group">
									<label class="col-sm-2 control-label">允许参加的产品：</label>
									
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#s2example-1").select2({
												placeholder: '请选择产品',
												allowClear: false
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
									</script>
									<div class="col-sm-10">
										<select class="form-control" id="s2example-1">
											<option></option>
											<optgroup label="产品编号-名称">
											    <#list products as product>
											       <option value="${product.id}">${(product.code)!}-${(product.name)!}</option>
											    </#list>
											</optgroup>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label"></label>
									<div class="row">
										<div class="col-sm-8">
											<table class="table responsive">
											   <thead>
												<tr id="productTitle" <#if !promotionproduct?has_content>style="display:none"</#if>>
													<th>&nbsp;</th>
													<th>产品名称</th>
													<th>操作</th>
												</tr>
												</thead>
												<tbody id="productTable">
												<#list promotionproduct as p>
												   <tr class="productTr">
														<td>
															<input type="hidden" name="productIds" value="${p.id}" />
														</td>
														<td>
															<span title="${(p.code)!}-${(p.name)!}">${(p.code)!}-${(p.name)!}</span>
														</td>
														<td>
															<#--
															<a href="#" target="_blank" class="btn btn-white">查看</a>
															-->
							                                <a href="javascript:;" id="deleteProduct" onclick="deleteProduct(this)" class="btn btn-white">删除</a>
														</td>
													</tr>
												</#list>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">有效期：</label>
									<div class="col-sm-8">
										<input type="text" id="beginDate" name="expirationdate" class="text Wdate" value="${(couponse.expirationdate)!}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" />
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">排序</label>									
									<div class="col-sm-10">
										<input type="text" name="sort" value="${(couponse.sort)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div> 
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<div class="col-sm-10" align="center">
									    <input type = "hidden" name = "id" value="${(couponse.id)!}" >
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/couponse/list.do';">
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
	<script src="${ctx!}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>
    <script src="${ctx!}/assets/js/ckeditor/ckeditor.js"></script>
	<script src="${ctx!}/assets/js/ckeditor/adapters/jquery.js"></script>
   <script type="text/javascript" src="${ctx!}/assets/js/datepicker/WdatePicker.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>