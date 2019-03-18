<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	 <METAHTTP-EQUIV="Pragma"CONTENT="no-cache">

	<METAHTTP-EQUIV="Cache-Control"CONTENT="no-cache">
	
	<METAHTTP-EQUIV="Expires"CONTENT="0">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>线路管理</title>
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
	
	<script type="text/javascript">
	$(function(){
	 var message =  getCookie("message");
	 if(message!=null&&message!=''){
	 alert(message);
	 addCookie("message",null,{path:"/"});	
	 }			
	});
   </script>
	<script type="text/javascript">
	//异步处理产品是否热推
    function updateIshot(memberid,pageId){
        var costnumberId = '${costIdo!}';
	    var ishot = $('#ishot'+memberid).text();
	    var is = 0;
	    if(ishot=='热推'){
	    is =1;
	    }
       $.ajax({
          type: "post",
          url: "${ctx!}/admin/tourline/updateishot.do",
          data:{tourlineId:memberid, ishot:is,pageId:pageId,costnumberId:costnumberId},
          datatype:"json",
          success:function(data){
            var name = "";
             if(data==1){
             name ="热推";
             }else{
             name ="不热推"
             }
             
             $('#ishot'+memberid).text(name);
          }
         
       });
      
    }
    //异步处理产品是否显示
    function updateIsshow(memberid,pageId){
       var costnumberId = '${costIdo!}';
        var ishot = $('#isshow'+memberid).text().trim();
	    var is = 0;
	    if(ishot=='显示'){
	    is =1
	    }
       $.ajax({
          type: "post",
          url: "${ctx!}/admin/tourline/updateisshow.do",
          data:{tourlineId:memberid, isshow:is,pageId:pageId,costnumberId:costnumberId},
          datatype:"json",
          success:function(data){
              var name = "";
             if(data==1){
             name ="显示";
             }else{
             name = "不显示"
             }
             $('#isshow'+memberid).text(name);
          }
       });
    }
    //异步处理产品是否首页显示
    function updateIsindexshow(memberid,pageId){
        var costnumberId = '${costIdo!}';
        var ishot = $('#isindexshow'+memberid).text().trim();
	    var is = 0;
	    if(ishot=='首页显示'){
	    is =1
	    }
       $.ajax({
          type: "post",
          url: "${ctx!}/admin/tourline/updateindexshow.do",
          data:{tourlineId:memberid, isindexshow:is,pageId:pageId,costnumberId:costnumberId},
          datatype:"json",
          success:function(data){
             var name = "";
             if(data==1){
             name ="首页显示";
             }else{
             name = "首页不显示"
             }
             $('#isindexshow'+memberid).text(name);
          }
       });
    }
 // 获取Cookie
	function getCookie(name) {
	    if (name != null) {
	        var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
	        return value ? decodeURIComponent(value[1]) : null;
	    }
	}

    
  // 添加Cookie
	function addCookie(name, value, options) {
	    if (arguments.length > 1 && name != null) {
	        if (options == null) {
	            options = {};
	        }
	        if (value == null) {
	            options.expires = -1;
	        }
	        if (typeof options.expires == "number") {
	            var time = options.expires;
	            var expires = options.expires = new Date();
	            expires.setTime(expires.getTime() + time * 1000);
	        }
	        document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path: "") + (options.domain ? "; domain=" + options.domain: ""),
	        (options.secure ? "; secure": "");
	    }
	}
    
    function realse(){
      addCookie("Tsearch",null,{path:"/"});
    }
    
    //清除所有参数
	var clearAllParam = function(){
	    addCookie('costId',null,{path:"/"});
		addCookie('TpageNow',null,{path:"/"});
		addCookie('TpageSize',null,{path:"/"});
		addCookie('Tsearch',null,{path:"/"});
		addCookie('tourIndexShow',null,{path:"/"});
		addCookie('tourIsshow',null,{path:"/"});
		addCookie('tourIshot',null,{path:"/"});
		$("#searchForm").find(".searchInput").val('');
		$("#pageNowInput").val('');
		window.location.href="${ctx!}/admin/tourline/list.do";
	}
	//清除cookie中存在的销售中心
	var clearTCostNum = function(){
		// addCookie('costId',null,{path:"/"});
		addCookie('TpageNow',null,{path:"/"});
		addCookie('TpageSize',null,{path:"/"});
		addCookie('Tsearch',null,{path:"/"});
		addCookie('tourIndexShow',null,{path:"/"});
		addCookie('tourIsshow',null,{path:"/"});
		addCookie('tourIshot',null,{path:"/"});
		$("#searchForm").find(".searchInput").val('');
		$("#pageNowInput").val('');
		window.location.href = "${ctx!}/admin/tourline/list.do";
	}
	
	//清除首页显示、显示、热推cookie
	function clearIsShow(status){
	    if(status==1){
	        addCookie('tourIndexShow',null,{path:"/"});
	        addCookie('tourIshot',null,{path:"/"});
	        window.location.href="${ctx!}/admin/tourline/list.do?isshow=1";
	    } else if(status==2){
	        addCookie('tourIndexShow',null,{path:"/"});
	     	addCookie('tourIsshow',null,{path:"/"});
	        window.location.href="${ctx!}/admin/tourline/list.do?ishot=1";
	    } else if(status==3){
	        addCookie('tourIshot',null,{path:"/"});  
	        addCookie('tourIsshow',null,{path:"/"});
	        window.location.href="${ctx!}/admin/tourline/list.do?indexShow=1";
	    } else{
		    addCookie('tourIndexShow',null,{path:"/"});
			addCookie('tourIsshow',null,{path:"/"});
			addCookie('tourIshot',null,{path:"/"});
			window.location.href="${ctx!}/admin/tourline/list.do";
	    }
	}
</script>
<script type="text/javascript">
		//分页
		function goPage(pageNow){
		    window.location.href="${ctx!}/admin/tourline/list.do?TpageNow="+pageNow;
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
					<h1 class="title">线路管理</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
						<li>
							<a href="${ctx!}/admin/tourline/list.do">线路管理</a>
						</li>
						
						<li class="active">
							<strong>线路列表${message!}</strong>
						</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/tourline/add.do'">添加线路</button>
					 <#if costNow??&&costNow=costIdo||admin.username='admin'>
					 <button class="btn btn-info" onclick="window.location.href='javascript:myfrom.submit()'" >生成页面</button>
					 </#if>
					 <button class="btn btn-info" onclick="window.location.href='${ctx!}/admin/tourline/showProduct.do'" >查看线路产品系统信息</button>
				     <script type="text/javascript">
							jQuery(document).ready(function($)
							{   
								$("#s2example-2").select2({
									placeholder: '选择标签',
									allowClear: true
								}).on('select2-open', function()
								{
									// Adding Custom Scrollbar
									$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
								});
								
								
							});
							function bachTags(){
							  $("#tagids").val($("#s2example-2").val());
							  $("#costnumberId").val('${costIdo!}')
							  myfrom.action="${ctx!}/admin/tourline/addTags.do";
							  myfrom.submit()
							}
			          </script>
	                   <#if costNow??&&costNow=costIdo>
							<div class="col-sm-3">
							   <div style="float:left">
									<select class="form-control"  name = "tourlineC"  id="s2example-2" multiple>
									    <option value="remove">移除标签</option>
									    <#list taglist as taglist >
									    <option value="${(taglist.id)!}">${(taglist.name)!}</option>
									    </#list>
									</select>
								</div>
								<div style="float:left">
							       <button  class="btn btn-info" onclick="bachTags()" >批量添加标签或更新标签</button>
							    </div>
							</div>
						<#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>	
							<div class="col-sm-3">
							   <div style="float:left">
									<select class="form-control"  name = "tourlineC"  id="s2example-2" multiple>
									  <option value="remove">移除标签</option>
									  <#list taglist as taglist >
									    <option value="${(taglist.id)!}">${(taglist.name)!}</option>
									  </#list>
									</select>
								</div>
								<div style="float:left">
							       <button  class="btn btn-info" onclick="bachTags()" >批量添加标签或更新标签</button>
							    </div>
							</div>	
				      </#if>
				      <script type="text/javascript">
							jQuery(document).ready(function($)
							{   
								$("#s2example-3").select2({
									placeholder: '选择视频',
									allowClear: true
								}).on('select2-open', function()
								{
									// Adding Custom Scrollbar
									$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
								});
								
								
							});
							function bachVideos(){
							  $("#videoids").val($("#s2example-3").val());
							  $("#costnumberId").val('${costIdo!}')
							  myfrom.action="${ctx!}/admin/tourline/addVideos.do";
							  myfrom.submit()
							}
			          </script>
	                   <#if costNow??&&costNow=costIdo>
							<div class="col-sm-3">
							   <div style="float:left">
									<select class="form-control"  name = "tourlineC"  id="s2example-3" multiple>
									    <option value="remove">移除视频</option>
									    <#list videolist as videolist >
									    <option value="${(videolist.id)!}">${(videolist.title)!}</option>
									    </#list>
									</select>
								</div>
								<div style="float:left">
							       <button  class="btn btn-info" onclick="bachVideos()" >批量添加视频或更新视频</button>
							    </div>
							</div>
						<#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>	
							<div class="col-sm-3">
							   <div style="float:left">
									<select class="form-control"  name = "tourlineC"  id="s2example-3" multiple>
									    <option value="remove">移除视频</option>
									    <#list videolist as videolist >
									    <option value="${(videolist.id)!}">${(videolist.title)!}</option>
									    </#list>
									</select>
								</div>
								<div style="float:left">
							       <button  class="btn btn-info" onclick="bachVideos()" >批量添加视频或更新视频</button>
							    </div>
							</div>
				      </#if> 
				</div>
				<nav class="navbar navbar-inverse" role="navigation">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
						<span class="sr-only">Toggle navigation</span>
						<i class="fa-bars"></i>
					</button>
					   <#--
					   <a class="navbar-brand" href="javascript:clearAllParam();">全部</a>
					   -->
				</div>
			
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
					<form class="navbar-form navbar-left" action="${ctx!}/admin/tourline/list.do" method="POST" role="search">
						<div class="form-group">
							<input type="text"  class="form-control" onFocus="realse()" placeholder="线路名称、编号、天数、分类" id="tsearch" value="${Tsearch!}" name="Tsearch">
						    <input type="hidden" name ="TpageNow" value="${(page.pageNow)!}">
						    <input type="hidden" name ="costId" id="costId" value="${costIdo!}">
						</div>
						<button type="submit" class="btn btn-white">搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-left">
						<li class="dropdown">
						    <#--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">销售中心 <b class="caret"></b></a>
						    -->
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
							   <#if costIdo?has_content>
							       <#list costlists as costlists>
										<#if costIdo=costlists.id>
										   ${(costlists.name)!}
										   <#break>
										</#if>
								   </#list>
								<#else>
								   销售中心（全部）
							    </#if>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu dropdown-primary">
							   <li>
								   <a href="javascript:clearAllParam();">销售中心（全部）</a>
							   </li>
							  <#list costlists as costlists>
							    <li>
									<a href="${ctx!}/admin/tourline/list.do?costId=${(costlists.id)!}">${(costlists.name)!}</a>
								</li>
							  </#list>
							</ul>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">每页显示${page.pageSize}条记录 <b class="caret"></b></a>
							<ul class="dropdown-menu dropdown-primary">
							   <li>
									<a href="${ctx!}/admin/tourline/list.do?pageSize=10">10</a>
								</li>
							    <li>
									<a href="${ctx!}/admin/tourline/list.do?pageSize=20">20</a>
								</li>
							    <li>
									<a href="${ctx!}/admin/tourline/list.do?pageSize=50">50</a>
								</li>
								<li>
									<a href="${ctx!}/admin/tourline/list.do?pageSize=100">100</a>
								</li>
								<li>
									<a href="${ctx!}/admin/tourline/list.do?pageSize=${(page.totalCount)?c}">全部</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</nav>
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
						    //'bSort': false,
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
					<#if costIdo?has_content>
					<div>
					    <label>
					        <input type="radio" onclick="location.href='javascript:clearTCostNum();'" <#if !isshow?? && !ishot?? && !indexShow??>checked</#if>/> 全部 
					   </label>&nbsp;&nbsp;
					   <label>
					        <input type="radio" onclick="location.href='javascript:clearIsShow(1);'" <#if isshow?? && isshow==1>checked</#if>/> 显示 
					   </label>&nbsp;&nbsp;
					   <label>
					        <input type="radio" onclick="location.href='javascript:clearIsShow(2);'" <#if ishot?? && ishot==1>checked</#if>/> 热推 
					   </label>&nbsp;&nbsp; 
					   <label>
					        <input type="radio" onclick="location.href='javascript:clearIsShow(3);'" <#if indexShow?? && indexShow==1>checked</#if>/> 首页显示 
					   </label> 
					</div>	
					</#if>
					
					<form role="forl" action="${ctx!}/admin/tourline/create.do" id="myfrom" method="post">
						<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
								    <#if costNow??&&costNow=costIdo||admin.username='admin'>
								    <th class="no-sorting">
										<input type="checkbox" class="cbr">
									</th>
									</#if>
									<#if costNow??&&costNow=costIdo>
									    <th>标签</th>
									  <#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>  
									    <th>标签</th>
									</#if>
									<#if costNow??&&costNow=costIdo>
									    <th>视频</th>
									  <#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>  
									    <th>视频</th>
									</#if>
									<th>封面</th>
									<th>名称</th>
									<th>编号</th>
									<th>天数</th>
									<th>分类</th>
									<#if costNow??&&costNow=costIdo>
									    <th>状态</th>
									  <#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>  
									    <th>状态</th>
									</#if>
									<th>操作</th>
								</tr>
							</thead>
						
							
						
							<tbody>
							  <#list Alltourline as tourlinelist>
							   <tr>
							       <#if costNow??&&costNow=costIdo>
							         <th class="no-sorting">
								      <#if (isCreate?? && isCreate?index_of("${(tourlinelist.id)!}")!=-1)>
								        <input type="checkbox" name = "tids" value="${(tourlinelist.id)!}" class="cbr cbr-success">
								        <#else> 
								        <input type="checkbox" name = "tids" value="${(tourlinelist.id)!}" class="cbr cbr-red">
								      </#if>
										
									</th>
									<#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>
									  <th class="no-sorting">
								      <#if (isCreate?? && isCreate?index_of("${(tourlinelist.id)!}")!=-1)>
								        <input type="checkbox" name = "tids" value="${(tourlinelist.id)!}" class="cbr cbr-success">
								        <#else> 
								        <input type="checkbox" name = "tids" value="${(tourlinelist.id)!}" class="cbr cbr-red">
								      </#if>
									<#else>	
									 <#if admin.username??&&admin.username=='admin'>
									     </th>
										  <th class="no-sorting">
										   <input type="checkbox" name = "tids" value="${(tourlinelist.id)!}">
										  </th>
									 </#if>
									  
							       </#if>
								   <#if costNow??&&costNow=costIdo>
									<td>
									<div class="vertical-top">
									<#if tourlinelist.productTagList?has_content>
									    <#list tourlinelist.productTagList as tagnames>
									      <button class="btn" style="background-color:#${(tagnames.tagTagid.bgcolor)!}; color:#${(tagnames.tagTagid.textcolor)!}">${(tagnames.tagTagid.name)!}</button>
									    </#list>					
									</#if>	
								    </div>
									</td>
									<#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>
									 <td>
										<div class="vertical-top">
										<#if tourlinelist.productTagList?has_content>
										    <#list tourlinelist.productTagList as tagnames>
										      <button class="btn" style="background-color:#${(tagnames.tagTagid.bgcolor)!}; color:#${(tagnames.tagTagid.textcolor)!}">${(tagnames.tagTagid.name)!}</button>
										    </#list>					
										</#if>	
									    </div>
									</td>
									</#if>
									 <#if costNow??&&costNow=costIdo>
									<td>
									<div class="vertical-top">
									<#if tourlinelist.productVideoList?has_content>
									    <#list tourlinelist.productVideoList as tagnames>
									      <button class="btn" style="background-color:#fa6400; color:#ffffff;">${(tagnames.videoVideoid.title)!}</button>
									    </#list>					
									</#if>	
								    </div>
									</td>
									<#elseif admin.username??&&admin.username=='admin' && costIdo?has_content>
									 <td>
										<div class="vertical-top">
										<#if tourlinelist.productVideoList?has_content>
										    <#list tourlinelist.productVideoList as tagnames>
										      <button class="btn" style="background-color:#fa6400; color:#ffffff;" >${(tagnames.videoVideoid.title)!}</button>
										    </#list>					
										</#if>	
									    </div>
									</td>
									</#if>
									<td>
									   <div id="gallery">
									    <#if tourlinelist.productProductid.imageurl?has_content>
										    <#list tourlinelist.productProductid.imageurl?split(",") as urls>
										     <#if urls_index=0>
										     <a href="${ctx!}/admin/tourline/tourlinePc.do?id=${(tourlinelist.id)!}" ><img src="${ctx!}${urls}" height="18px" width="40px" id="img"/></a>
										     </#if>
										    </#list>
									   </#if>
									 </div>
									</td>
									<td>${(tourlinelist.productProductid.name)!}</td>
									<td>${(tourlinelist.productProductid.code)!}</td>
									<td>${(tourlinelist.days)!}</td>
									<td>${(tourlinelist.region.name)!}</td>
								  <#if costNow??&&costNow=costIdo>
									      <td>
										    <div class="btn-group" width="270">
										              
										                  <#if (hott?? && hott?index_of("${(tourlinelist.id)!}")!=-1)>
														    <button type="button" id="ishot${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIshot('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-red btn-sm">热推</button>
															 <#else>
															<button type="button" id="ishot${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIshot('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-red btn-sm">不热推</button>
														</#if>
														 <#if (show?? && show?index_of("${(tourlinelist.id)!}")!=-1)>
														    <button type="button" id="isshow${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIsshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-blue btn-sm">显示</button>
															 <#else>
															<button type="button" id="isshow${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIsshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-blue btn-sm">不显示</button>
														</#if>
														 <#if (index?? && index?index_of("${(tourlinelist.id)!}")!=-1)>
														    <button type="button" id="isindexshow${(tourlinelist.id)!}" style="width:80px;"
														    onclick="updateIsindexshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-success btn-sm">首页显示</button>
															 <#else>
															<button type="button" id="isindexshow${(tourlinelist.id)!}" style="width:80px;"
														    onclick="updateIsindexshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-success btn-sm">首页不显示</button>
														</#if>
										            </div>
									            </td>
									        <#elseif admin.username??&&admin.username=='admin' && costIdo?has_content> 
									        <td>
										    <div class="btn-group" width="270">
										              
										                  <#if (hott?? && hott?index_of("${(tourlinelist.id)!}")!=-1)>
														    <button type="button" id="ishot${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIshot('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-red btn-sm">热推</button>
															 <#else>
															<button type="button" id="ishot${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIshot('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-red btn-sm">不热推</button>
														</#if>
														 <#if (show?? && show?index_of("${(tourlinelist.id)!}")!=-1)>
														    <button type="button" id="isshow${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIsshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-blue btn-sm">显示</button>
															 <#else>
															<button type="button" id="isshow${(tourlinelist.id)!}" style="width:60px;"
														    onclick="updateIsshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-blue btn-sm">不显示</button>
														</#if>
														 <#if (index?? && index?index_of("${(tourlinelist.id)!}")!=-1)>
														    <button type="button" id="isindexshow${(tourlinelist.id)!}" style="width:80px;"
														    onclick="updateIsindexshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-success btn-sm">首页显示</button>
															 <#else>
															<button type="button" id="isindexshow${(tourlinelist.id)!}" style="width:80px;"
														    onclick="updateIsindexshow('${(tourlinelist.id)!}','${(tourlinelist.productProductid.pagePageid.id)!}');" class="btn btn-success btn-sm">首页不显示</button>
														</#if>
										            </div>
									            </td>      
										</#if>
											
											<!--<button type="button" id="isshow${(tourlinelist.productid)!}" style="width:60px;"
											onclick="updateIsshow('${(tourlinelist.productid)!}');" class="btn btn-blue btn-sm">
											<#if (tourlinelist.productProductid.isshow)=1>显示<#else>不显示</#if></button>
											
											<button type="button" id="isindexshow${(tourlinelist.productid)!}" style="width:80px;"
											 onclick="updateIsindexshow('${(tourlinelist.productid)!}');" class="btn btn-success btn-sm">
											 <#if (tourlinelist.productProductid.indexShow)=1>首页显示<#else>首页不显示</#if> </button>
										  -->
										
									<td>
									  <ul class="nav navbar-nav">
							
											<li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown">操作 <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li>
														<a href="${ctx!}/admin/tourline/update.do?tourlineId=${(tourlinelist.id)!}">
										              	    修改线路
										                </a>
													</li>
													<#if admin.username??&&admin.username=='admin' && costIdo?has_content>
													   <li>
															<a href="${ctx!}/admin/tourline/delete.do?tourlineId=${(tourlinelist.id)!}&productId=${(tourlinelist.productProductid.id)!}&pageId=${(tourlinelist.productProductid.pageid)!}" onClick="return confirm('确定要删除?');">
											              	   删除线路
											                </a>
														</li>
														<li>
															<a href="javascript:;" onclick="showAjaxModal('${(tourlinelist.costnumber)!}','${(tourlinelist.id)!}');">复制线路</a>
														</li>	 
													</#if>
													 <#if (show?? && show?index_of("${(tourlinelist.id)!}")==-1)>
													   <li>
														<a href="${ctx!}/admin/tourline/remove.do?tourlineId=${(tourlinelist.id)!}&productId=${(tourlinelist.productProductid.id)!}&pageId=${(tourlinelist.productProductid.pageid)!}&costnumberId=${costIdo!}" >
										              	   线路下线
										                </a>
													   </li>
													 </#if>
													<li>
														<a href="${ctx!}/admin/tourline/view.do?id=${(tourlinelist.id)!}&costIdo=${costIdo!}" target="_black">
										              	   线路预览
										                </a>
													</li>
													<li class="divider"></li>
													<li>
														<a href="${ctx!}/admin/tourdate/list.do?productId=${(tourlinelist.productProductid.id)!}">
										              	   价格管理
										                </a>
													</li>
													
													<li class="divider"></li>
													<li>
														<a href="${ctx!}/admin/itinerary/list.do?tourlineId=${(tourlinelist.id)!}">
										              	  行程管理
										                </a>
													</li>
													<li>
														<a href="${ctx!}/admin/map/showDestinationMap.do?tourlineId=${(tourlinelist.id)!}">
														   查看地图
														</a>
													</li>
													<#if tourlinelist.productProductid.productNo != '' && tourlinelist.productProductid.isSynchronizedToERP = 0>
													<li>
														<a href="javascript:void(0);" onclick="synchronizeToERP(this,'${(tourlinelist.productid)!}');">
														  同步到ERP
														</a>
													</li>
													</#if>													
												</ul>
											</li>
										</ul>
										
									</td>
								</tr>
							  </#list>
							
								
							</tbody>
						</table>
						<input type="hidden" name ="costids" value="${costNow!}">
						<input type="hidden" name ="tagids" id="tagids">
						<input type="hidden" name ="videoids" id="videoids">
						<input type="hidden" name ="costnumberId" id="costnumberId">
				   </from>
				 <#include "/admin/include/pagination.ftl"/>
				</div>
		     
			
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			
		</div>
		<footer class="main-footer sticky footer-type-1">
				
				<div class="footer-inner">
				
					<!-- Add your copyright text here -->
					<div class="footer-text">
						&copy; 2015
					   <a href="#" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
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
   <!-- 显示需要替换的出发地  -->
	<!-- Modal 6 (Long Modal)-->
	<div class="modal fade" id="modal-6">
		<div class="modal-dialog">
			<div class="modal-content">
			   <form action="${ctx!}/admin/attraction/replaceAttraction.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body"></div>
					<div  id="regionid"></div>
					<div class="modal-footer">
					    <input type="hidden" id="tourlineId" class="tourlineId"/>
						<button type="button" class="btn btn-info" onclick="submitYouFrom()">提交</button>
						<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
			
		</div>
	</div>	
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			<#if noticeMessage??>
			alert("${noticeMessage}");
			</#if>
		});
		//删除景点js
		function showAjaxModal(costnumberToa,tourlineId) {
		   
			$.ajax({
			    type: "post",
	            url: "${ctx!}/admin/tourline/Tocostnumber.do",
	            data: {costnumberToa:costnumberToa},
	            dataType: "json",
				success: function(data) {
				     $('#modal-6 .modal-body').empty();
				     var html ="";
				     if(data!=null){
				         html = "<div class=\"row\">"+
				                    "<div class=\"col-md-12\">"+
									"<div class=\"form-group\"><label for=\"replaceId\" class=\"control-label\">目标销售中心：</label>";	
	                                 
	                     $.each(data, function(commentIndex, costTo){
	                          html +="<input type=\"radio\" class=\"replaceId\" name=\"replaceId\" value=\""+costTo.id+"\"/>" + costTo.name;
	                        
	                     });
	                     html +="</div></div></div>";
	                     $('#modal-6').modal('show', {backdrop: 'static'});
	                     $(".modal-title").text("复制线路！");
	                     $("#tourlineId").val(tourlineId);
	                     $('#modal-6 .modal-body').html(html);
				     }
				     $(".replaceId").change(function(){
				        var val = $("input[name='replaceId']:checked").val();//获得选中的radio的值 
				        updateRegion(val);
				       
				     });
			     
				     
				     
				}
			});
		}
		 
		 function updateRegion(costnumber){
		 
		   $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/updateRegion.do",
			data: "costnumber="+costnumber+"&type=1",
			cache:false,
			success: function(data) {
			   $("#regionid").empty();
			    var html ="";
			     html = "<div class=\"row\">"+
				                    "<div class=\"col-md-12\">"+
									"<div class=\"form-group\"><label for=\"regionids\" class=\"control-label\">请选择分类：</label>"+	
	                                   "<select class=\"form-control\" name=\"regionids\" id=\"regionids\">";
			   $("#regionid").append("<option value=-1>请选择</option>");
			   $.each(data, function(i, des) {
                html +="<option url ='"+des['url']+"'  value='"+des['id']+"'>" + des['levelstr'] + ""+des['name']+"</option>";
                
			});
			 html +="</select></div></div></div>";
	                    
	                     $('#regionid').html(html);
			},
			error: function(e) {
				alert(e);
			},
		});
	 }
	 function submitYouFrom(){
	   var costnumberc = $("input[name='replaceId']:checked").val();//获得选中的radio的值 
	   var tourlinId = $("#tourlineId").val();
	   var regionId = $("#regionids").val();
		 $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/coptTourline.do",
			data: {replaceId:costnumberc,tourlineId:tourlinId,regionids:regionId},
			cache:false,
			success: function(data) {
			  
			    alert(data.noticeMessage);
			},
			error: function(e) {
				alert(e);
			},
		});
	}
	
	</script>
	
    
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
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>
	
	


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>
	<script>
		var synchronizeToERP = function(aButton,productId){
			$.post("${ctx!}/admin/tourline/synchronizeToERP.do",{productId:productId},function(result){
				if(result == 1){
					alert('同步成功！');	
					$(aButton).parent().remove();
				}else{
					alert('同步失败');
				}
			});
		}		
	</script>
</body>
</html>