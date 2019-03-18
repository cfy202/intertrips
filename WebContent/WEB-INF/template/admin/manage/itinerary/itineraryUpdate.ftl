<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>行程修改</title>
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
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
	<link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function() {
		//$("#IMAGEURL").empty();
		//$("#IMAGEID").empty();
		$("#uploadify1").uploadify({
			'uploader': '${ctx!}/assets/script/swf/uploadify.swf',
			// uploadify.swf 文件的相对路径
			'script': "${ctx!}/upload.do?path=images",
			//要提交到处理文件上传的Controller
			'cancelImg': '${ctx!}/assets/script/cancel.png',
			//选择文件到文件队列中后的每一个文件上的关闭按钮图标
			'queueID': 'fileQueue',
			//文件队列的ID，该ID与存放文件队列的div的ID一致
			'queueSizeLimit': 10,
			//队列中同时存在的文件个数限制 
			'fileDesc': 'jpg、gif、swf文件、png',
			//如果配置了以下的'fileExt'属性，那么这个属性是必须的           
			'fileExt': '*.jpg;*.gif;*.swf;*.png',
			//允许的格式 
			'auto': true,
			//是否自动开始  
			'multi': true,
			//是否支持多文件上传
			'simUploadLimit': 10,
			//一次同步上传的文件数目 
			'buttonText': 'upload',
			//按钮上的文字  
			'displayData': 'percentage',
			//进度条的显示方式 
			onComplete: function(evt, queueID, fileObj, response, data) {
				//    	aturl+=response+",";
				//    	alert(aturl);	
				saveUserPictproal(response);
			}
		});
		
		//产品选择
		  $('#itintarySids').bind('change', function () {
		      var $productTable = $("#productTable");
		      var $productTitle = $("#productTitle");
			  var trHtml = 
					"<tr class=\"productTr\">"+
						"<td>"+
							"<input type=\"hidden\" name=\"itintarySids\" value=\""+ $(this).val() +"\" \/>"+
							"&nbsp;"+
						"<\/td>"+
						"<td>"+
							"<span title=\"\">"+ $('#itintarySids option:selected').text() +"<\/span>"+
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

	//上传图片后返回并添加图片路径和id
	function saveUserPictproal(response) {
//		var costnumber = $("#costnumber").val();
		$.ajax({
			type: "POST",
			url: "${ctx!}/admin/itinerary/savepic.do",
			data: "picaddress=" + response,
			cache:false,
			success: function(data) {
				var imid = data.id;
				var atturl = data.url;
				$("#IMAGEID").append("<input type=\"hidden\" id=\"imageid\" name=\"imageid\" value=\"" + imid + "\" class=\"" + imid + "\"/>");
				$("#IMAGEURL").append("<img src=\"${ctx!}" + atturl + "\" alt=\"\" height=\"100\" width=\"150\" onclick=\"cancel(this)\" class=\"" + imid + "\">"+"&nbsp;");
				$("#IMAGEID").append("<input type=\"hidden\" id=\"imageurl\" name=\"imageurl\" value=\"" + atturl + "\" class=\"" + imid + "\"/>");
			},
			error: function(e) {
				alert(e);
			},
		});
	}
	
	//传完图片后点击图片移除图片路径和id
	function cancel(obj){
		if(confirm("确定删除图片？")){
			var classvalue = $(obj).attr("class");
			$("."+classvalue).remove();
		};
	}
	
	//选择图片时添加或移除相应图片id
	function selectid(obj){
		var imgid = $(obj).attr("imgid");
		var url = $(obj).attr("value");
		var im = $(obj).prop('checked');
		if(im==true){
//			alert(imgid);
			$("#IMAGEID").append("<input type=\"hidden\" class=\"imginput\" name=\"imageid\" value=\"" + imgid + "\" id=\"" + imgid + "\"/>");
			$("#IMAGEID").append("<input type=\"hidden\" class=\"" + imgid + "\" name=\"imageurl\" value=\"" + url + "\"/>");
		}else{
			$("#"+imgid).remove();
			$("."+imgid).remove();
		}
	}
		

//分页函数
      function goPage(pageNow){
        showImages(pageNow);
     }
     
//图库选图
function showImages(pageNow){
//		var costnumber = $("#costnumber").val();
		var content = $("#content").val();
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/itinerary/image.do",
            data:{pageNow:pageNow,content:content},
            dataType:"json",
            success: function(data){
              $('#IMG').empty();   //清空里面的所有内容
              var html = "";
              $.each(data.allImages, function(i, e) {
                 html += "<div class=\"col-md-3 col-sm-4 col-xs-6\">"+
										"<div class=\"album-image\">"+
											"<a href=\"#\" class=\"thumb\" data-action=\"edit\">"+
												"<img src=\"${ctx!}"+e.url+"\" class=\"img-responsive\" />"+
											"</a>"+
										    "<div class=\"image-options\">"+
										    "</div>"+
										    "<div class=\"image-checkbox\">"+
											   "<input type=\"checkbox\" class=\"imgcheckbox\" name=\"checkbox\" value=\""+e.url+"\" imgid=\""+e.id+"\" onclick=\"selectid(this)\"/>"+
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
				var idd = $(n).attr("imgid");	
				$.each(imginput,function(r,m){
					if(idd==m.id){
						$(n).prop("checked", true);
					};
				})
			});
            }
        });
	}	 

	//点击图片并删除
	function deleteimg(obj){
		if(confirm("确定删除图片？")){
			var url = $(obj).attr("url");
			var itineraryid = $("#itineraryid").val();
	//		alert(itineraryid);
			var imgclass = $(obj).attr("class");
			$.ajax({
				type:"post",
				url:"${ctx!}/admin/itinerary/deletebyimageid.do",
				data:"url="+url+"&itineraryid="+itineraryid,
				success:function(){
					$("."+imgclass).remove();
				},
				error:function(e){
					alert("error");
				},
			});
		};
	}
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
					<h1 class="title">${tourname!}</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/itinerary/list.do?tourlineId=${tourlineId!}">行程管理</a>
								</li>
							<li class="active">
						
										<strong>行程修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/itinerary/updatesave.do" method="post">
							
								<div class="form-group">
									<label class="col-sm-2 control-label">第几天</label>									
									<div class="col-sm-10">
										<select class="form-control" name="day" id="day">
											<#list daylist as daylist>
            									<option value="${daylist}" <#if itinerary.day?? && itinerary.day=daylist>selected="selected"</#if>>${daylist}</option>
            								</#list>
										</select>	
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标题</label>								
									<div class="col-sm-10">
										<input type="text" name="title" value="${(itinerary.title)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">内容</label>									
									<div class="col-sm-10">
										<textarea class="form-control" name="content" cols="5" rows="13" id="content">${(itinerary.content)!}</textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">选择酒店：</label>
									
									<div class="col-sm-10">
										<select class="form-control" name="hotelId">
										       <option value="" >选择酒店</option>
											   <#if hotels?has_content>
													<#list hotels as hotels>
													   <option value="${(hotels.id)!}" <#if itinerary.hotelId ?? && itinerary.hotelId?index_of("${(hotels.id)!}")!=-1>selected</#if>>${(hotels.hotelname)!}</option>
													</#list>
												</#if>		
										</select>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">酒店说明：</label>									
									<div class="col-sm-10">
									    <input type="text"  name="hotelRemark" value="${(itinerary.hotelRemark)!}" class="form-control" id="field-2" placeholder="酒店说明">
									</div>
								</div>
								
								

								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">经历城市：</label>									
									<div class="col-sm-10">
										<select class="form-control" name="itintaryDids" id = "itintaryDids" multiple >
										  <#if destination?has_content>
											<#list destination as destination>
											        <option value="${(destination.id)!}" <#if itinerary.itintaryDids ?? && itinerary.itintaryDids?index_of("${(destination.id)!}")!=-1>selected</#if>>${(destination.name)!}</option>
											</#list>
											</#if>
										</select>
									</div>
								</div>
								
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#itintaryDids").select2({
										//		placeholder: '请选择离团城市',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											$("#itintaryDids").change(function(){
											  var ids = $("#itintaryDids").val();
											  var tourlineId = '${tourlineId!}';
											  updateattraction(ids,tourlineId);
											  updateselfpayTo(ids,tourlineId);
											});
										});
										function updateattraction(ids,tourlineId){
										  $.ajax({
											   type: "POST",
											   url: "${ctx!}/admin/itinerary/getByDestinationids.do",
											   dataType:"json",
											   data: "destinationIds=" + ids+"&tourlineId="+tourlineId ,
											   success: function(msg){
											   var attrids = $("#itintaryCids").val();
											   $("#itintaryCids").empty();
											   $("#itintaryCids").empty(""	);
										         $.each(msg, function (index, entry) {
										            if(attrids!=null && attrids.indexOf(entry['id'])!=-1 ){
										            $("#itintaryCids").append("<option  value='"+entry['id']+"' selected>"+entry['name']+"</option>");
										            }else{
										            $("#itintaryCids").append("<option  value='"+entry['id']+"'>"+entry['name']+"</option>");
										            } 
										            
										         });
											   }
											}); 
										}
										
										function updateselfpayTo(ids){
										  $.ajax({
											   type: "POST",
											   url: "${ctx!}/admin/itinerary/getSelfByDestinationids.do",
											   dataType:"json",
											   data: "destinationIds=" + ids ,
											   success: function(msg){
											   var selfpayids = $("#itintarySids").val();
											   $("#itintarySids").empty();
										         $.each(msg, function (index, entry) {
										            if(selfpayids!=null && selfpayids.indexOf(entry['id'])!=-1 ){
										            $("#itintarySids").append("<option  value='"+entry['id']+"' selected>"+entry['name']+"--"+entry['price']+"--"+entry['destinationName']+"</option>");
										            }else{
										            $("#itintarySids").append("<option  value='"+entry['id']+"'>"+entry['name']+"--"+entry['price']+"--"+entry['destinationName']+"</option>");
										            } 
										            
										         });
											   }
											}); 
										}
								</script>
								
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">经历景点：</label>									
									<div class="col-sm-10">
										<select class="form-control" name="itintaryCids" id = "itintaryCids" multiple >
										 <#if attractions?has_content>
											<#list attractions as attractions>
											        <option value="${(attractions.id)!}" <#if itinerary.itintaryCids ?? && itinerary.itintaryCids?index_of("${(attractions.id)!}")!=-1>selected</#if>>${(attractions.name)!}</option>
											</#list>
											</#if>
										</select>
									</div>
								</div>
								
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#itintaryCids").select2({
										//		placeholder: '请选择离团城市',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
										
								</script>
								
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">自选项：</label>
									
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
										<select class="form-control" id="itintarySids">
											<option></option>
											<optgroup label="产品编号-名称">
											<#if selfpays?has_content>
											    <#list selfpays as selfpays>
											       <option value="${selfpays.id}">${(selfpays.name)!}--${(selfpays.price)!}--${(selfpays.destinationName)!}</option>
											    </#list>
											</#if>
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
												<tr id="productTitle" <#if !selfpays?has_content>style="display:none"</#if>>
													<th>&nbsp;</th>
													<th>产品名称</th>
													<th>操作</th>
												</tr>
												</thead>
												<tbody id="productTable">
											<#if selfpays?has_content>
												<#list selfpays as selfpays>
												  <#if itinerary.itintarySids ?? && itinerary.itintarySids?index_of("${(selfpays.id)!}")!=-1>
												  
												   <tr class="productTr">
														<td>
															<input type="hidden" name="itintarySids" value="${(selfpays.id)!}" />
														</td>
														<td>
															<span title="${(selfpays.id)!}-${(selfpays.name)!}">${(selfpays.name)!}--${(selfpays.price)!}--${(selfpays.destinationName)!}</span>
														</td>
														<td>
															<#--
															<a href="#" target="_blank" class="btn btn-white">查看</a>
															-->
							                                <a href="javascript:;" id="deleteProduct" onclick="deleteProduct(this)" class="btn btn-white">删除</a>
														</td>
													</tr>
												  
												  </#if>
												</#list>
											</#if>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">交通</label>									
									<div class="col-sm-10">
										<#list ["飞机","火车","轮船","汽车"] as trafficlist>
											<input type="checkbox" name="traffic" value="${trafficlist}" <#if "${(itinerary.traffic)!}"?index_of("${trafficlist}")!=-1>checked="checked"</#if>/>
											${trafficlist}&nbsp;&nbsp;
										</#list>
									</div>
								</div> 
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">餐饮</label>								
									<div class="col-sm-10">
										<textarea class="form-control" name="meal" cols="5" rows="1" id="field-5">${(itinerary.meal)!}</textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">提示</label>								
									<div class="col-sm-10">
										<textarea class="form-control" name="reminder" cols="5" rows="3" id="field-5">${(itinerary.reminder)!}</textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">图片预览<br/>(点击可删除)</label>
									<#if img?? && img=1>									
										<div class="col-sm-10">
										    <#assign a=0>
											<#list imgurl as imgurl>
											     <#assign a=a+1>
												<img src="${ctx!}${imgurl!}" height="100px" width="150px" class="img${a}" url="${imgurl!}" onclick="deleteimg(this)"/>
											</#list>
										</div>
									</#if>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">选择图片</label>									
									<div class="col-sm-10" id="seleimg">
										<input type="button" value="选择图片" onclick="showImages();" class="btn btn-turquoise"/>
									</div>
									<div class="col-sm-9 gallery-right">
									<!-- Album Images -->
										<div class="album-images row" id="IMG"></div>
										<div id="page"></div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-4">上传图片</label>
									<div class="col-sm-10">
									   <div id="fileQueue"></div>
									   <input type="file" class="col-sm-2 control-label" name="uploadify1" id="uploadify1" />
									   <!--
						        		<p>
	      									<a href="javascript:$('#uploadify1').uploadifyUpload()">上传</a>| 
	      									<a href="javascript:$('#uploadify1').uploadifyClearQueue()">取消上传</a>
						        		</p>
						        		-->
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">上传图片预览<br/>(点击可删除)</label>
									<div class="col-sm-10" id="IMAGEURL"></div>
								</div>
								<div id="IMAGEID"></div>
								
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<#if img?? && img=1>
											<#assign a=0>
											<#list imgurl as imgurl>
												<#assign a=a+1>
												<input name="imageurl" type="hidden"  value="${imgurl!}"  class="img${a}"/>	
											</#list>
										</#if>
										<input name="imageid" type="hidden" id="imageid" value=""/>
								<!--	<input name="costnumber" type="hidden" id="costnumber" value="${costnumber!}"/>
								-->
										<input name="tourlineid" type="hidden" id="id" value="${(itinerary.tourlineid)!}">
										<input name="id" type="hidden" id="itineraryid" value="${(itinerary.id)!}">
										<input type="submit" name="button" id="button" value="完成" class="btn btn-secondary btn-sm btn-icon icon-left">
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
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.js"></script>
	


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>