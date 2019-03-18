<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>添加门票</title>

	<!--
	 <link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">
	-->
  	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css"/>
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
	    var costnumber = $("#costnumber").val();
	     updateRegion(costnumber);
	  
		//$("#IMAGEURL").empty();
		//$("#IMAGEID").empty();
		$("#uploadify1").uploadify({
			'uploader': '${ctx!}/assets/script/swf/uploadify.swf',
			// uploadify.swf 文件的相对路径
			'script': "${ctx!}/upload.do?path=images/admissionticket",
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
			'auto': false,
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
		//更新分类选项
       $("#costnumber").change(function(){
			var costnumber = $("#costnumber").val();
			updateRegion(costnumber);
		}); 
	});

	//上传图片后返回并添加图片路径和id
	function saveUserPictproal(response) {
	 var costnumber = $("#costnumber").val();
		$.ajax({
			type: "POST",
			url: "${ctx!}/admin/admissionticket/savepic.do",
			data: "picaddress=" + response +"&costnumber="+costnumber,
			cache:false,
			success: function(data) {
			//	var num = 0;
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
	
	function updateRegion(costnumber){
		   $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/updateRegion.do",
			data: "costnumber="+costnumber+"&type="+10,
			cache:false,
			success: function(data) {
			   $.each(data, function(i, des) {
			    $("#regionid").append("<option url ='"+des['url']+"'  value='"+des['id']+"'>"+des['name']+"</option>");
			});
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
	function selectidp(obj){
		var imgid = $(obj).attr("id");
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
    var useType = "visa";
     var searcher = $("#searchbyTitle").val();
		    if(searcher=='undefined'){
		     searcher = "";
		    }else if(searcher==''){
		      searcher = "";
		   }
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/image.do",
            data:{pageNow:pageNow,costnumber:costnumber,usetype:useType,search:searcher},
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
 <script type="text/javascript">
		$(function(){
		            //获取选中值
		        $('#rootwizard').submit(function(){
		           //处理是否热推
		            if($("input[name='ishot']").is(':checked')){
		               $("#pishot").val(1);
		            }
		            //处理是否显示
		             if($("input[name='isshow']").is(':checked')){
		               $("#pisshow").val(1);
		            }
		            //处理是否首页显示
		             if($("input[name='indexShow']").is(':checked')){
		               $("#pindexShow").val(1);
		            }
		        
		          
		    })
</script>    
<script type="text/javascript"> 
//校验产品编号是否存在			
	 function validate(){
	 
	  var numbers = $("#door_code").val();
		   $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/validateCode.do",
			data: "numbers="+numbers,
			dataType: "json",
			cache:false,
			success: function(data) {
			  if(data){
			    $("#mess").text("该产品编号没有被占用！");	
			  }else{
			    $("#mess").text("该产品编号已经存在！");
			    $("#door_code").empty();
			    $("#door_code").focus();
			  }
			
			},
			error: function(e) {
				alert(e);
			},
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
					<h1 class="title">门票添加</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/admissionticket/list.do">门票管理</a>
								</li>
							<li class="active">
						
										<strong>添加门票</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			
			
			
			<div class="vspacer v3"></div>
			
			
			<!-- Form wizard with validation starts here -->
	        <script type="text/javascript">
				function pushine(){
				 var name = $("#prname").val();
				 $("#admissionticketname").val(name);
				 $("#metatitle").val(name);
				}
			</script>
			
			
			<form role="forl" action="${ctx!}/admin/admissionticket/save.do" id="rootwizard" method="post" class="form-wizard validate">
				
				<ul class="tabs">
					<li class="active">
						<a href="#fwv-1" data-toggle="tab">
							产品信息
							<span>1</span>
						</a>
					</li>					
					<li>
						<a href="#fwv-2" data-toggle="tab">
							门票信息
							<span>2</span>
						</a>
					</li>
					
					<li>
						<a href="#fwv-3" data-toggle="tab">
							页面信息
							<span>3</span>
						</a>
					</li>
				</ul>
				
				<div class="progress-indicator">
					<span></span>
				</div>
				
				<div class="tab-content no-margin">
					
					<!-- Tabs Content -->
					<div class="tab-pane with-bg active" id="fwv-1">
					<div class="row">
							
							<div class="col-md-8">
								<div class="form-group">
									<label class="control-label" for="street">产品名称</label>
									<input class="form-control" name="name"  onBlur="pushine()" id="prname" data-validate="required" placeholder="请填写产品名称" />
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label" for="door_no">产品编号</label><i class="fa-bell-o"></i><span id="mess"></span>
									<input class="form-control" name="code" id="door_code" onBlur="validate()" data-validate="required" digits="true" minlength="2" maxlength="8" placeholder="请填写产品编号" />
								</div>
							</div>
							
						</div>
						
						<div class="row">
						<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="door_no">产品积分:</label><i class="fa-bell-o"></i><span id="mess"></span>
									<input class="form-control" name="score"  data-validate="required" digits="true"  placeholder="请填写产品积分" />
								</div>
						</div>
						<#if (cost?size==1)>
						<input type="hidden" name="costnumber" id="costnumber" value="${cost[0].id}">
						<#else>
						    <div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="state">选择运营中心</label>
									<select name="costnumber" id="costnumber" class="selectboxit">
										   <#list cost as cost>
										    <option value="${cost.id}">${cost.name}</option>
										   </#list>
									</select>
								</div>
							</div>
						 </#if>
						</div>
						
						<div class="row">
							
							<div class="col-md-5">
								<div class="form-group">
									<label class="control-label" for="city">最低价格</label>
									<input class="form-control" name="minprice" value= "0" id="minprice" data-validate="number" placeholder="输入最低价格" />
								</div>
							</div>
							
							
						</div>
						
						<div class="row">
							
							<div class="col-md-6">	
							<div class="form-group">
									<label class="col-sm-2 control-label" for="field-4">上传图片</label>
									<div class="col-sm-10">
									   <div id="fileQueue"></div>
									   <input type="file" class="col-sm-2 control-label" name="uploadify1" id="uploadify1" />
						        		<p>
	      									<a href="javascript:$('#uploadify1').uploadifyUpload()">上传</a>| 
	      									<a href="javascript:$('#uploadify1').uploadifyClearQueue()">取消上传</a>
						        		</p>
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
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">是否热推</label>
									
									<br />
									
								    <input type="checkbox" name ="ishot" value="" id="pishot" class="iswitch iswitch-red" checked >
								</div>	
								
								<div class="form-group">
									<label class="control-label">是否显示</label>
									
									<br />
									
								    <input type="checkbox" name ="isshow" value="" id="pisshow" class="iswitch iswitch-purple" checked>
								</div>
								
								<div class="form-group">
									<label class="control-label">
										是否首页显示 
										<span class="label label-warning">new</span>
									</label>
									
									<br />
									
								    <input type="checkbox" name ="indexShow" value="" id="pindexShow" class="iswitch iswitch-secondary" checked>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" for="about">产品描述</label>
									<textarea class="form-control autogrow" name="briefinfo" id="metadescription" data-validate="minlength[10]" rows="5" placeholder="填写产品描述" ></textarea>
								</div>
							</div>
			
						</div>
						<script type="text/javascript">
								jQuery(document).ready(function($){
									$("#regionid").select2({
											//placeholder: '请选择产品',
												allowClear: false
									}).on('select2-open', function(){
											// Adding Custom Scrollbar
											$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
									});
									$("#regionid").change(function(){
									  var tourcode = $("#door_code").val();
									  var url = $("#regionid").find("option:selected").attr("url").replace(".htm","tour"+tourcode+".htm");
									  $("#filepath").val(url);
									});
										
								});
							</script>
					</div>
					
					<div class="tab-pane with-bg" id="fwv-2">
							<div class="row">
								
									<div class="form-group">
											<label class="col-sm-2 control-label">门票分类</label>
											
											<div class="col-sm-10">
												<select class="form-control" id = "regionid" name = "regionid">
												 <option value=-1>请选择</option>
												 </select>
											</div>
										</div>
								 </div>   
							<br />
						<div class="form-group-separator"></div>
					<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="admissionticketname">英文名称</label>								
									<div class="col-sm-10">
										<input type="text" name="ticketname" class="form-control" id="admissionticketname" placeholder="">
									</div>
								</div>
							
						</div>
						<br />
						<div class="form-group-separator"></div>
						<div class="row">
						
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">中文名称</label>									
									<div class="col-sm-10">
										<input type="text" name="ticketcn" class="form-control" id="field-2" placeholder="">
									</div>
								</div>
							</div>
						 <br />
						<div class="form-group-separator"></div>
						
						<div class="row">
						
								<div class="form-group">
									<label class="col-sm-2 control-label">城市</label>									
									<div class="col-sm-10">
										<select class="form-control" name="attractionid" id="UPID" >
											<#list attraction as attraction>
            									<option value="${(attraction.id)!}">${(attraction.name)!}-${(attraction.namecn)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#UPID").select2({
										//		placeholder: '请选择产品',
												allowClear: false
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
								</script>
							</div>
						 <br />
						<div class="form-group-separator"></div>
						
						<div class="row">
							<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">包含</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="ticketinclude"  cols="5" id="field-5"></textarea>
									</div>
								</div>
						</div>
						
						<br />
						<div class="row">
							<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">不包含</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="notinclude"  cols="5" id="field-5"></textarea>
									</div>
								</div>
						</div>
						
						<br />
						<div class="row">
							<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">描述</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="ticketdescription"  cols="5" id="field-5"></textarea>
									</div>
								</div>
						</div>
						
						<br />
						
						
						<div class="row">
							<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">辅助说明</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="ticketamenities"  cols="5" id="field-5"></textarea>
									</div>
								</div>
						</div>
						
						<br />
						
						
						<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">排序</label>									
									<div class="col-sm-10">
										<input type="text" name="sort" value="${(admissionticket.sort)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div> 
							
						</div>
					</div>
					
					<div class="tab-pane with-bg" id="fwv-3">
						<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="full_name">页面标题</label>
									<input class="form-control" name="metatitle" id="metatitle"  placeholder="请填写页面标题" />
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="birthdate">搜索关键字</label>
									<input class="form-control" name="metakeywords" id="metakeywords"   placeholder="请填写页面搜索关键字" />
								</div>
							</div>
							
						</div>
						
						<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="full_name">页面路径</label>
									<input class="form-control" name="filepath" id="filepath"  placeholder="请填写页面访问路径" />
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="birthdate">模板路径</label>
									<input class="form-control" name="templateUrl" id="templateUrl"  placeholder="请填写页面模板路径" />
								</div>
							</div>
							
						</div>
						
						<div class="row">
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" for="about">页面描述</label>
									<textarea class="form-control autogrow" name="metadescription" id="metadescription" data-validate="minlength[10]" rows="5" placeholder="填写页面描述" ></textarea>
								</div>
							</div>
			
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" for="about">页面内容</label>
									<textarea class="form-control autogrow" name="content" id="content" data-validate="minlength[10]" rows="5" placeholder="填写页面内容"></textarea>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
								    <input name="id" type="hidden" id="id" value="${(admissionticket.id)!}">
								    <input name="type" type="hidden" id="type" value="2">
									<button type="submit" class="btn btn-primary">完成提交</button>
								</div>
							</div>
						</div>
					</div>
					
					
					
					
					<!-- Tabs Pager -->
					
					<ul class="pager wizard">
						<li class="previous">
							<a href="#">上一步<i class="entypo-left-open"></i> </a>
						</li>
						
						<li class="next">
							<a href="#">下一步 <i class="entypo-right-open"></i></a>
						</li>
					</ul>
					
				</div>
				
			</form>
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
		
			
		<!-- start: Chat Section -->
		<div id="chat" class="fixed">
			
			<div class="chat-inner">
			
				
				<h2 class="chat-header">
					<a href="#" class="chat-close" data-toggle="chat">
						<i class="fa-plus-circle rotate-45deg"></i>
					</a>
					
					Chat
					<span class="badge badge-success is-hidden">0</span>
				</h2>
				
				<script type="text/javascript">
				// Here is just a sample how to open chat conversation box
				jQuery(document).ready(function($)
				{
					var $chat_conversation = $(".chat-conversation");
					
					$(".chat-group a").on('click', function(ev)
					{
						ev.preventDefault();
						
						$chat_conversation.toggleClass('is-open');
						
						$(".chat-conversation textarea").trigger('autosize.resize').focus();
					});
					
					$(".conversation-close").on('click', function(ev)
					{
						ev.preventDefault();
						$chat_conversation.removeClass('is-open');
					});
				});
				</script>
				
				
				<div class="chat-group">
					<strong>Favorites</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Catherine J. Watkins</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Nicholas R. Walker</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Susan J. Best</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Fernando G. Olson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Brandon S. Young</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Work</strong>
					
					<a href="#"><span class="user-status is-busy"></span> <em>Rodrigo E. Lozano</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert J. Garcia</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Daniel A. Pena</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Other</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Dennis E. Johnson</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Stuart A. Shire</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Janet I. Matas</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Mindy A. Smith</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Herman S. Foltz</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Gregory E. Robie</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Nellie T. Foreman</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>William R. Miller</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Vivian J. Hall</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Melinda A. Anderson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Gary M. Mooneyham</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert C. Medina</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Dylan C. Bernal</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Marc P. Sanborn</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Kenneth M. Rochester</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Rachael D. Carpenter</em></a>
				</div>
			
			</div>
			
			<!-- conversation template -->
			<div class="chat-conversation">
				
				<div class="conversation-header">
					<a href="#" class="conversation-close">
						&times;
					</a>
					
					<span class="user-status is-online"></span>
					<span class="display-name">Arlind Nushi</span> 
					<small>Online</small>
				</div>
				
				<ul class="conversation-body">	
					<li>
						<span class="user">Arlind Nushi</span>
						<span class="time">09:00</span>
						<p>Are you here?</p>
					</li>
					<li class="odd">
						<span class="user">Brandon S. Young</span>
						<span class="time">09:25</span>
						<p>This message is pre-queued.</p>
					</li>
					<li>
						<span class="user">Brandon S. Young</span>
						<span class="time">09:26</span>
						<p>Whohoo!</p>
					</li>
					<li class="odd">
						<span class="user">Arlind Nushi</span>
						<span class="time">09:27</span>
						<p>Do you like it?</p>
					</li>
				</ul>
				
				<div class="chat-textarea">
					<textarea class="form-control autogrow" placeholder="Type your message"></textarea>
				</div>
				
			</div>
			
		</div>
		<!-- end: Chat Section -->
		
		
	</div>
	
	
	




	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
    <link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
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


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${ctx!}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
	<script src="${ctx!}/assets/js/formwizard/jquery.bootstrap.wizard.min.js"></script>
	<script src="${ctx!}/assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.js"></script>
	<script src="${ctx!}/assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="${ctx!}/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 
    <script type="text/javascript" src="${ctx!}/assets/js/jquery-validate/localization/messages_zh.js"></script>
	<script src="${ctx!}/assets/js/jquery-validate/jquery.metadata.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>