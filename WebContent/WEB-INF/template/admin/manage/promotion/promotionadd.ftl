<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>促销活动添加</title>
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
				         $("#imgshow").attr("style","display:block");
				        }
			}); 
		 //校验
		 $("form#loginForm").validate({
			rules: {
				discount: {
					number: true
				},
				
				full: {
					number: true
				},
				
			    reduce: {
	                number: true
	           },
	            sort: {
	                required: true,
	                number: true
	           },
	            title: {
	                required: true
	           }
			},	
			messages: {
				discount: {
					number: "请输入合法的数字(如：0.9)"
				},
				
				full: {
					number: "请输入合法的数字(如：500)"
				},
				
	           reduce: {
	                number: "请输入合法的数字(如：100)"
	           },
	           sort: {
	                required: "排序不能为空",
	                number: "请输入合法的数字(如：1)"
	           },
               title: {
	                required: "请填写相应活动标题"
	           }
			},
		  });
		  
//		   //根据运营中心异步获取当前运营中心排序最大值和产品列表
//		  $('#costnumber').bind('change', function () {
//		       $.ajax({
//	             type: "post",
//	             url: "${ctx!}/admin/promotion/getsort.do",
//	             data: {costnumber:$("#costnumber").val()},
//	             dataType: "json",
//	             success: function(data){
//	                        $('#s2example-1').empty();   //清空s2example-1里面的所有内容
//	                         var html = "<option></option>"+
//											"<optgroup label=\"产品编号   - 名称\">"; 
//	                         $.each(data.products, function(commentIndex, product){
//	                             html +="<option value=\""+product.id+"\">"+product.code+"-"+product.name+"</option>";
//	                         });
//	                         html += "</optgroup>";
//	                         $('#s2example-1').html(html);
//	                         $('#sort').val(data.sort);
//	                      }
//	           });
//		  });
		  
//		  //产品选择
//		  $('#s2example-1').bind('change', function () {
//		      var $productTable = $("#productTable");
//		      var $productTitle = $("#productTitle");
//			  var trHtml = 
//					"<tr class=\"productTr\">"+
//						"<th>"+
//							"<input type=\"hidden\" name=\"productIds\" value=\""+ $(this).val() +"\" \/>"+
//							"&nbsp;"+
//						"<\/th>"+
//						"<td>"+
//							"<span title=\"\">"+ $('#s2example-1 option:selected').text() +"<\/span>"+
//						"<\/td>"+
//						"<td>"+
//							"<a href=\"javascript:;\" id=\"deleteProduct\" class=\"btn btn-white\" onclick=\"deleteProduct(this)\">删除<\/a>"+
//						"<\/td>"+
//					"<\/tr>";
//				  $productTitle.show();
//				  $productTable.append(trHtml);
//		  });
	}); 
	  
	  //删除选中的线路
      function deleteProduct(nowtr){
           if(confirm("确定删除吗？")){
			$(nowtr).closest("tr").remove();
			if ($("#productTable").find("tr.productTr").size() <= 0) {
				$("#productTitle").hide();
			}
           }
     }
	
	//选择促销活动类型     
	$(function(){
	   $("#promotionType").change(function(){
	   		var type = $("#promotionType").val();
	   		if(type == 1){
	   			$("#Discount").show();
	   			$("#Reduce").hide();
	   		}
	   		if(type == 2){
	   			$("#Discount").hide();
	   			$("#Reduce").show();
			}
	   })
	   
	})
   </script>
<script>
  $(function(){
  	  //刚进入页面查询线路
  	  queryTourline();
  	  
  	  //根据运营中心异步获取当前运营中心排序最大值和产品列表
	  $('#costnumber,#pageSize').bind('change', function () {
			queryTourline();
	  });
	  
  }) 
  
  	//搜索线路
  	function queryTourline(){
  		var tourCode = $("#tourCode").val();
  		var tourName = $("#tourName").val();
  		var	costnumber = $("#costnumber").val();
  		var regionName = $("#regionName").val();
  		var pageSize = $("#pageSize").val();
        $.ajax({
         type: "post",
         url: "${ctx!}/admin/promotion/queryTourline.do",
         data: {costnumber:costnumber,tourName:tourName,tourCode:tourCode,regionName:regionName,pageSize:pageSize},
         dataType: "json",
         success: function(data){
                     $('#sort').val(data.sort);
                     $('#ProductSelect').html(data.productContent);
                     $('#PAGE').html(data.pageContent);
                  }
       });
  	}
  	
  	
	//下一页
	function goPage(pageNow){
		showTourline(pageNow);
	};
	
	//产品分页查询
	function showTourline(pageNow){
    	var tourCode = $("#tourCode").val();
  		var tourName = $("#tourName").val();
  		var	costnumber = $("#costnumber").val();
  		var regionName = $("#regionName").val();
  		var pageSize = $("#pageSize").val();
        $.ajax({
         type: "post",
         url: "${ctx!}/admin/promotion/queryTourline.do",
         data: {costnumber:costnumber,tourName:tourName,tourCode:tourCode,regionName:regionName,pageSize:pageSize,pageNow:pageNow},
         dataType: "json",
         success: function(data){
                     $('#sort').val(data.sort);
                     $('#ProductSelect').html(data.productContent);
                     $('#PAGE').html(data.pageContent);
                  }
       });
	}
	
	//添加线路
	function insertProduct(){
		var $productTable = $("#productTable");
  		var $productTitle = $("#productTitle");
  		var selectedP = $("input[name='eachCheckbox']:checked");
  		var trHtml = "";
  		selectedP.each(function(){
  			trHtml +=
  			"<tr class=\"productTr\">"+
				"<th>"+
					"<input type=\"hidden\" name=\"productIds\" value=\""+ $(this).val() +"\" \/>"+
					"&nbsp;"+
				"<\/th>"+
				"<td>"+
					"<span title=\"\">"+$(this).attr("tourCode")+"-"+$(this).attr("tourName") +"<\/span>"+
				"<\/td>"+
				"<td>"+
					"<a href=\"javascript:;\" id=\"deleteProduct\" class=\"btn btn-white\" onclick=\"deleteProduct(this)\">删除<\/a>"+
				"<\/td>"+
			"<\/tr>";
  		})
		$productTitle.show();
	  	$productTable.append(trHtml);
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
					<h1 class="title">促销活动添加</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/promotion/list.do">促销活动管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/promotion/add.do"><strong>促销活动添加</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/promotion/save.do" method="post" id="loginForm">
							
								<div class="form-group">
									<label class="col-sm-2 control-label">活动类型：</label>									
									<div class="col-sm-10">
										<select class="form-control" name="type" id="promotionType">
        									<option value="1" selected="selected">打折</option>
        									<option value="2">减价</option>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">活动标题：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="title" name="title"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">开始日期：</label>
									<div class="col-sm-8">
										<input type="text" id="beginDate" name="beginDate" class="text Wdate" value="${nowhour}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">结束日期：</label>
									<div class="col-sm-4">
										<input type="text" id="endDate" name="endDate" class="text Wdate" value="${nowhour}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" />
									</div>
								</div>
								
								<#--
								 <div class="form-group-separator"></div>
								 <div class="form-group">
							   	     <label class="col-sm-2 control-label" for="totalnum">最大人数：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="totalnum" name="totalnum" value="0" data-validate="number">
										</div>
							     </div>
									 
								<div class="form-group-separator"></div>
								 <div class="form-group">
									<label class="col-sm-2 control-label" for="soldnum">售出人数：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="soldnum" name="soldnum" value="0" data-validate="number">
									</div>
								 </div>
								-->
									 
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-8">币种：</label>
									<div class="col-sm-10">
									    <#assign code = "${costs[0].code}"/>
										<select name="currencyid" class="form-control" id="field-8">
									       <#list currencies as currency>
									          <option value="${(currency.id)!}" 
									                   <#if (costs?size==1)&&code==(currency.code)>selected="selected"</#if>
									              >${(currency.sign)!} - ${(currency.name)!}</option>
									          
									       </#list>
									    </select>
									</div>
								</div>
								
								<div id="Discount">
									<div class="form-group-separator"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="discount">折扣：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="discount" id="discount" placeholder="如：0.9"/>
										</div>
									</div>
								</div>
								
								<div id="Reduce" style="display:none">
									<div class="form-group-separator"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="full">消费额满：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="full" id="full"  placeholder="如：500"/>
										</div>
									</div>
									
									<div class="form-group-separator"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="reduce">价格减：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="reduce" id="reduce" placeholder="如：100"/>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">活动描述：</label>
									<div class="col-sm-10">
									    <textarea class="form-control" name="description" cols="5" id="field-5"></textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">页面路径：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="filePath" name="filePath"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">是否显示：</label>
									<div class="col-sm-10">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="isshow" value="1"/>显示
											</label>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">图片：</label>
									<div class="col-sm-10">
									    <img src="" id="imgshow"  style="display:none"><br/>
										<input type="file" class="form-control" id="uploadify" name="uploadify">
										<input type="hidden" name="imageurl" id="changephoto" value="${(promotion.imageurl)!}"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sort">排序：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="sort" name="sort" value="${sort?c}">
									</div>
								</div>
								
								<#if (costs?size==1)>
								<input type="hidden" name="costnumber" id="costnumber" value="${costs[0].id}">
								<#else>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">销售中心：</label>
									<div class="col-sm-10">
									    <select name="costnumber" class="form-control" id="costnumber">
									       <#list costs as cost>
									          <option value="${(cost.id)!}">${cost.name}</option>
									       </#list>
									    </select>
									</div>
								</div>
								</#if>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sort">筛选条件：</label>
									<div class="col-sm-10">
										<input type="text" class=""   placeholder="线路编号" id="tourCode" value="${qnaScode!}">&nbsp;&nbsp;
										<input type="text" class=""   placeholder="线路名称" id="tourName" value="${qnaStourname!}">&nbsp;&nbsp;
										<input type="text" class=""   placeholder="线路分类" id="regionName" value="${qnaStitle!}">&nbsp;&nbsp;
										<label>每页显示
											<select id="pageSize">
												<option value="10">10</option>
												<option value="25">25</option>
												<option value="50">50</option>
												<option value="100">100</option>
												<option value="0">All</option>
											</select>条记录
										</label>
										<button type="button" class="btn btn-black" onclick="queryTourline()">搜索</button>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sort">
										<button class="btn btn-turquoise" type="button" onclick="insertProduct()">添加线路</button>
									</label>
									<div class="col-sm-10">
										<div id="ProductSelect">	<#--线路表格-->
											<#--
											<div class="panel-body">
												<script type="text/javascript">
												jQuery(document).ready(function($)
													{
														$("#example-3").dataTable({
														    'bStateSave': true,
														    'bLengthChange': true,
														    'bFilter':false,
														    'bPaginate': false,
														    'bInfo': false,
														    'bSort': false,
														    //'sScrollX': "100%",
															//'sScrollXInner': "101%",
															//'bScrollCollapse': true,
															//'sScrollY': 800,//竖向滚动条 tbody区域的高度    
															
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
															<th>线路编号</th>
															<th>名称</th>
														</tr>
													</thead>															
													<tbody>
														<tr>
														    <th class="no-sorting">
																<input type="checkbox" class="cbr">
															</th>
															<td> 
															</td>
															<td> 
															</td>
														</tr>	
													</tbody>
												</table>					
											</div>
											-->
										</div>
										<div id="PAGE"></div>	<#--线路分页-->
									</div>
								</div>
								<#--
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
											<optgroup label="产品编号   - 名称">
											    <#list products as product>
											       <option value="${product.id}">${(product.code)!}-${(product.name)!}</option>
											    </#list>
											</optgroup>
										</select>
									</div>
								</div>
								-->
								<div class="form-group">
									<label class="col-sm-2 control-label"></label>
									<div class="row">
										<div class="col-sm-8">
											<table class="table responsive" id="productTable">
											   <thead>
												<tr id="productTitle" style="display:none">
													<th>&nbsp;</th>
													<th>产品名称</th>
													<th>操作</th>
												</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/promotion/list.do';">
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
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>
	
	<script type="text/javascript" src="${ctx!}/assets/js/datepicker/WdatePicker.js"></script>
		
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>