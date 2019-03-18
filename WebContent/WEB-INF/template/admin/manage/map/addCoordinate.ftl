<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>Xenon - Data Tables</title>
	
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
					<h1 class="title">
						指定坐标
					    <#if destination??>
					    	${destination.name}
					    	${destination.namecn}
					    </#if>
					     <#if attraction??>
					    	${attraction.name}
					    	${attraction.namecn}
					    </#if>
					</h1>
				</div>
			</div>	
			<div class="panel panel-default">
				<div id="googleMap" class="panel-body" style="width:1000px;height:400px">
					
				</div>
				<button onclick="submit();" >保存坐标</button>
			</div>
			<form id="coordinateForm" method="post" action="saveCoordinate.do" style="display:none">
				<input name="actionDestination" value="${actionDestination}">
				<#if exist??>
				<input name="id" value="${coordinate.id}">
				<input id="xAxis" name="xAxis" value="${coordinate.xAxis}">
				<input id="yAxis" name="yAxis" value="${coordinate.yAxis}">
				<#else>
				<input id="xAxis" name="xAxis">
				<input id="yAxis" name="yAxis">
				</#if>
				<#if flag == 1>
				<input id="destinationId" name="destinationId" value="${id}">
				<#else>
				<input id="attractionId" name="attractionId" value="${id}">
				</#if>
			</form>
			<footer class="main-footer sticky footer-type-1">				
				<div class="footer-inner">				
					<!-- Add your copyright text here -->
					<div class="footer-text">
					 西安淘游网络科技有限公司 
					</div>
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
			</footer>
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
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
	<script>
	    var	lastPoint;
	    var map;
		var center = new google.maps.LatLng(${coordinate.xAxis},${coordinate.yAxis});
		
		// 初始化地图
		function initialize(){
		  var mapDiv = document.getElementById('googleMap');
		  var myOptions = {
		    zoom: 12,
		    center: center,
		    mapTypeId: google.maps.MapTypeId.ROADMAP
		  }
		  map = new google.maps.Map(mapDiv, myOptions); 
		  
		  <#if exist??>
		  var marker=new google.maps.Marker({
			  position:center,
	      });
		  marker.setMap(map);
		  lastPoint = marker;
		  </#if>
			//鼠标点击地图事件
			google.maps.event.addListener(map, 'click', function(event) {
				placeMarker(event.latLng);
		    });
		}
		
		//鼠标点击地图事件,删除上个坐标点,显示当前的坐标,把当前坐标记录下来
		function placeMarker(location){
			if(lastPoint != null){
				lastPoint.setMap(null);
			}
			var marker = new google.maps.Marker({
			   position: location,
			   map: map,
			});
			lastPoint = marker;
			$("#xAxis").val(location.lat());
			$("#yAxis").val(location.lng());
		}
			
		google.maps.event.addDomListener(window, 'load', initialize);
		
		//提交坐标
		function submit(){
			if($("#xAxis").val() == ''){
				alert('请选择坐标.');
			}else{
				$("#coordinateForm").submit();
			}
		}
	</script>
</body>
</html>