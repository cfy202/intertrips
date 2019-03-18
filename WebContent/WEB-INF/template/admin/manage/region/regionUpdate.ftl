<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>产品分类修改</title>
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
	<style>
		form label.error{
		margin-top: 10px;
		top: 10px;
		right: -110px;
		border-radius: 4px;
		color: #f60;
		width: 95px;
		z-index: 7;
		font-size:12px;
	  }
	</style>
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

	});
	
	//上传图片后返回并添加图片路径和id
	function saveUserPictproal(response) {
		var costnumber = $("#costnumber").val();
		var namecn = $("#namecn").val();
		$.ajax({
			type: "POST",
			url: "${ctx!}/admin/region/savepic.do",
			data: "picaddress=" + response+"&namecn="+namecn,
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
		var costnumber = $("#costnumber").val();
		var type = "atractions";
		 var searcher = $("#searchbyTitle").val();
		    if(searcher=='undefined'){
		     searcher = "";
		    }else if(searcher==''){
		      searcher = "";
		    }
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/image.do",
            data:{pageNow:pageNow,costnumber:costnumber,usetype:type,search:searcher},
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
			var attractionid = $("#attractionid").val();
			var imgclass = $(obj).attr("class");
			$.ajax({
				type:"post",
				url:"${ctx!}/admin/attraction/deletebyimageid.do",
				data:"url="+url+"&attractionid="+attractionid,
				success:function(){
					$("."+imgclass).remove();
				},
				error:function(e){
					alert("error");
				},
			});
		};
	}
</script>		
	
	<script type="text/javascript">
		$(function(){
			//选择不同类型,异步查询对应类型下的分类
			<#--
			  $('#type').bind('change', function () {
			       $.ajax({
		             type: "post",
		             url: "${ctx!}/admin/region/getsort.do",
		             data: {
		                  type:$("#type").val()
		                  },
		             dataType: "json",
		             success: function(data){
		                         $('#upid').empty();   //清空upid里面的所有内容
		                         var html = "<option></option><optgroup label=\"英文名称 - 中文名称\"><option value=\"root\">根目录 </option>"; 
		                         $.each(data.regions, function(commentIndex, region){
		                             html +="<option value=\""+region.id+"\" >" + region.levelstr + "" + region.name + "</option>";
		                         });
		                         html+="</optgroup>";
		                         $('#upid').html(html);
		                      }
		         });
			  });
			  -->
	         //获取选中值
	        $('#regionForm').submit(function(){
	           var checkText=$("#upid").find("option:selected").text();
	           checkText = checkText.replace(/[ ]/g, "");
	           checkText = checkText.replace(/\|--/g, "").trim();
			   $("#pname").val(checkText);
			   return true;
			});
			
			  //校验
			 $("form#regionForm").validate({
				rules: {
					upid: {
						required: true,
					},
					 url: {
					    required: true,
					    remote: {
		                      type: "post",
		                      url:  "${ctx!}/admin/region/isExistsUrl.do",
		                      data: {
		                        url: function() {
		                            return $("#url").val();
		                        },
		                        id: function() {
		                            return $("#id").val();
		                        }
		                  },
		                }
					 }
				},	
				messages: {
					upid: {
						required: "请选择上级目录"
					},
					url: {
					    required: "请输入访问路径",
					    remote:"访问路径已存在"
					 }
				},
			  });
      })
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
					<h1 class="title">产品分类修改</h1>
					<p class="description"></p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/region/list.do">产品分类管理</a>
								</li>
							<li class="active">
						
										<strong>产品分类修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" id="regionForm" action="${ctx!}/admin/region/save.do" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label">类型：</label>									
									<div class="col-sm-10">
									    <#if region.type?? && region.type = 1>
									       <label class="col-sm-0 control-label">线路</label>	
									    <#elseif region.type?? && region.type = 2>
									       <label class="col-sm-0 control-label">签证</label>	
									    <#elseif region.type?? && region.type = 3>
									       <label class="col-sm-0 control-label">游船</label>	
									    <#elseif region.type?? && region.type = 4>
									       <label class="col-sm-0 control-label">酒店</label>	
									    <#elseif region.type?? && region.type = 5>
									       <label class="col-sm-0 control-label">机票</label>	
									     <#elseif region.type?? && region.type = 6>
									       <label class="col-sm-0 control-label">目的地</label>	
									    
									    <#elseif region.type?? && region.type = 7>
									       <label class="col-sm-0 control-label">美食</label>	
									    
									    <#elseif region.type?? && region.type = 8>
									       <label class="col-sm-0 control-label">导游</label>	
									    
									    <#elseif region.type?? && region.type = 9>
									       <label class="col-sm-0 control-label">租车</label>	
									    <#elseif region.type?? && region.type = 10>
									       <label class="col-sm-0 control-label">门票</label>	
									    </#if>
									    <input type="hidden" name="type" value="${(region.type)!}">
									    <#--
										<select class="form-control" name="type" id="type">
										    <option value="1" <#if region.type?? && region.type = 1>selected="selected"</#if>>线路</option>
											<option value="2" <#if region.type?? && region.type = 2>selected="selected"</#if>>签证</option>
											<option value="3" <#if region.type?? && region.type = 3>selected="selected"</#if>>游船</option>
											<option value="4" <#if region.type?? && region.type = 4>selected="selected"</#if>>酒店</option>
											<option value="5" <#if region.type?? && region.type = 5>selected="selected"</#if>>机票</option>
										</select>
										-->
									</div>
								</div>
							    <div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="namepy">英文名称：</label>								
									<div class="col-sm-10">
										<input type="text" name="namepy" class="form-control" id="namepy" value="${(region.namepy)!}">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">中文名称：</label>								
									<div class="col-sm-10">
										<input type="text" name="name" value="${(region.name)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								 <#if (cost?size==1)>
									<input type="hidden" name="costnumber" id="costnumber" value="${cost[0].id}">
								<#else>
								 
									    <div class="form-group">
										<label class="col-sm-2 control-label" for="costnumber">销售中心：</label>
											<div class="col-sm-10">
											    <select name="costnumber" class="form-control" id="costnumber">
											       <#list cost as cost>
											          <option value="${(cost.id)!}" <#if (cost.id)=(region.costnumber)>selected="selected"</#if>>${cost.name} - ${cost.code} - ${cost.sign}</option>
											       </#list>
											    </select>
									        </div>
								        </div> 
								
						        </#if>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">上级目录：</label>			
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#upid").select2({
												placeholder: '请选择上级目录',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
									</script>							
									<div class="col-sm-10">
										<select class="form-control" name="upid" id="upid">
										  <option></option>
										  <optgroup label="英文名称 - 中文名称">
										     <option value="root" <#if region.upid?? && region.upid == "root">selected="selected"</#if>>根目录</option>
											 <#list regionlist as regionlist>
            									<option value="${(regionlist.id)!}" <#if region.upid?? && region.upid = regionlist.id>selected="selected"</#if>>
            									  ${(regionlist.levelstr)!}${(regionlist.namepy)!} - ${(regionlist.name)!}</option>
            								 </#list>
            							  </optgroup>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态：</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="checkbox" name="isShow" value="1"<#if region.isShow?? && region.isShow=1>checked="checked"</#if>>显示
											</label>											
										</p>
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
									<label class="col-sm-2 control-label" for="field-4">上传图片</label>
									<div class="col-sm-10">
										<div id="fileQueue"></div>
										<input type="file" class="col-sm-2 control-label" name="uploadify1" id="uploadify1" />
										<#--
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
									<label class="col-sm-2 control-label" for="url">访问路径：</label>								
									<div class="col-sm-10">
										<input type="text" name="url" class="form-control" id="url" value="${(region.url)!}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="metatitle">页面标题：</label>								
									<div class="col-sm-10">
										<input type="text" name="metatitle" class="form-control" id="metatitle" value="${(region.page.metatitle)!}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="metakeywords">搜索关键字：</label>								
									<div class="col-sm-10">
										<input type="text" name="metakeywords" class="form-control" id="metakeywords" value="${(region.page.metakeywords)!}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="url">页面描述：</label>								
									<div class="col-sm-10">
										<textarea class="form-control" name="metadescription" id="metadescription">${(region.page.metadescription)!}</textarea>
									</div>
								</div>
								<#--
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="url">页面内容：</label>								
									<div class="col-sm-10">
										<textarea class="form-control" name="content" id="content">${(region.page.content)!}</textarea>
									</div>
								</div>
								-->
								<div class="form-group-separator"></div>
								<div class="form-group" align="center">
									<div class="col-sm-10">
									   <#if img?? && img=1>
											<#assign a=0>
											<#list imgurl as imgurl>
												<#assign a=a+1>
												<input name="imageurl" type="hidden"  value="${imgurl!}"  class="img${a}"/>	
											</#list>
										</#if>
										<input name="imageid" type="hidden" id="imageid" value=""/>
										<input name="id" type="hidden" id="id" value="${(region.id)!}">
										<input name="pageid" type="hidden" id="pageid" value="${(region.page.id)!}">
										<input name="pname" type="hidden" id="pname" value="">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/region/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
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
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>