<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>车辆添加</title>
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
		var num = 1;
		
		var $airticketTable = $("#priceTable");
	    var $airticketTitle = $("#priceTableTitle");
	    
        //点击添加机票
    	$(".check_more_one").click(function () {
	            var trHtml = "";
	            trHtml +=  '<tr class="airticketTr">'+
							   '<td>'+
								   '<div class="col-sm-12">'+
	  							      '<select class="form-control" name="airTicketDeparture">';
				
				            trHtml += '</select>'+
					    			'</div>'+
							    '</td>'+
								'<td>'+
		    		      		   '<div class="col-sm-10">'+
						           		'<input class="form-control" type="text" name="airTicketPrice" digits="true" value="0"/>'+
					       		   '</div>'+
					    		'</td>'+
					   		    '<td>'+
				    		       '<div class="col-sm-8">'+
								         '<input class="form-control" type="text" name="sortArr" digits="true" value="'+num+'"/>'+
							       '</div>'+
					  		   '</td>'+
							   '<td>'+
						           '<a href="javascript:;" id="deleteProduct" class="btn btn-white" onclick="deleteairticketTr(this)">删除</a>'+
						       '</td>'+
					           '<input type="hidden" name="departureName" id="departureName'+num+'" value="'+data[0].name+'-'+data[0].city+'">'
					       '</tr>';
	            $airticketTitle.show();
		        $airticketTable.append(trHtml);
		        num++;
        });
	});
	 
	//图库选图
	function showImages(pageNow){
	    var costnumber = $("#costnumber").val();
	    var type = "tourline";
	    var type1 = "atractions";
	    var searcher = $("#searchbyTitle").val();
	    if(searcher=='undefined'){
	     searcher = "";
	    }else if(searcher==''){
	      searcher = "";
	    }
	        $.ajax({
	           type:"post",
	            url:"${ctx!}/admin/slider/image.do",
	            data:{pageNow:pageNow,costnumber:costnumber,usetype:type,usetype1:type1,search:searcher},
	            dataType:"json",
	            success: function(data){
	              $('#IMG').empty();   //清空里面的所有内容
	              var html = "<div class=\"input-group\">"+
							"<input type=\"text\" id=\"searchbyTitle\" placeholder=\"景点名称\" value=\"\" class=\"form-control no-right-border form-focus-purple\">"+
								"<span class=\"input-group-btn\">"+
								 "<button class=\"btn btn-purple\" onclick=\"showImages(${(page.pageNow)!})\" type=\"button\">搜索</button>"+
								"</span>"+
							"</div><br/>";
	              $.each(data.allImages, function(i, e) {
	                 html += "<div class=\"col-md-3 col-sm-4 col-xs-6\">"+
											"<div class=\"album-image\">"+
												"<a href=\"#\" class=\"thumb\" data-action=\"edit\">"+
													"<img src=\"${ctx!}"+e.url+"\" class=\"img-responsive\" />"+
													"<span>"+e.title+"</span>"+
												"</a>"+
											    "<div class=\"image-options\">"+
											    "</div>"+
											    "<div class=\"image-checkbox\">"+
												   "<input type=\"checkbox\" class=\"imgcheckbox\" name=\"checkbox\" value=\""+e.url+"\" id=\""+e.id+"\" onclick=\"selectidp(this)\"/>"+
											    "</div>"+
										    "</div>"+
									    "</div>";
				});
				var html2 = data.pageContent;
				$("#page").html(html2);
				$("#seleimg").hide();
				$("#IMG").html(html);
				
				//遍历checkbox和input,比对勾选复选框
				var imginput = $(".imginput");
				var imgcheckbox = $(".imgcheckbox");
				$.each(imgcheckbox,function(i,n){	
					$.each(imginput,function(r,m){
						if(n.id==m.id){
							$(n).prop("checked", true);
						};
					})
				});
	         }
	     });
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
					<h1 class="title">车辆添加</h1>
					<p class="description"></p>
				</div>
				<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/bus/list.do">车辆管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/bus/add.do"><strong>车辆添加</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/bus/save.do" method="post">
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">车型 ：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-1" name="busType">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">乘客数：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-2" name="passengerNumber">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-3">行李位：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-3" name="luggageNumber">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">备注：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-5" name="remark">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-7">车型介绍：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-7" name="introduce">
									</div>
								</div>
								
								<br/><div class="form-group-separator"></div>
								<div class="row">
								    <div class="col-md-12">
										<div class="form-group">
										<label class="col-sm-1 control-label">价格：</label>
										<div class="col-sm-8">
									       <a class="check_more_one" href="javascript:;">+ 添加价格</a><br/>
									    </div>
									    <div class="col-sm-10">
										     <table class="table responsive" id="priceTable">
												   <thead>
														<tr id="priceTableTitle" style="display:none">
															<th>运营中心</th>
															<th>基础价格</th>
															<th>超时价格</th>
														</tr>
													</thead>
											</table>
										</div>
									  </div>
									</div>
								</div>								
								
								<div class="form-group-separator"></div>
								<div class="row">		
										<div class="form-group">
											<label class="col-sm-2 control-label" for="field-2">上传图片预览<br/>(点击可删除):</label>
											<div class="col-sm-10" id="IMAGEURL"></div>
										</div>
										<div id="IMAGEID"></div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="row">	
										<div class="form-group">
											<label class="col-sm-2 control-label" for="field-2">选择图片：</label>									
											<div class="col-sm-10" id="seleimg">
												<input type="button" value="选择图片" onclick="showImages();" class="btn btn-turquoise"/>
											</div>
											<div class="col-sm-9 gallery-right">
											<!-- Album Images -->
												<div class="album-images row" id="IMG"></div>
												<div id="page"></div>
											</div>
										</div>
								</div>
						
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/bus/list.do';">
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
	<script src="${ctx!}/assets/js/datepicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctx!}/assets/js/ckeditor/ckeditor.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>
</body>
</html>