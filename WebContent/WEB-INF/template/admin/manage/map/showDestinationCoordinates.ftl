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
						查看目的地
						线路：${tourline.tourname}
					</h1>
				</div>
			</div>	
			<div class="panel panel-default">
				<div id="googleMap" class="panel-body" style="width:1000px;height:400px">
					
				</div>
			</div>
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
	    var map;
		var center = new google.maps.LatLng(${avgXAxis},${avgYAxis});
		var pointArray = new Array();
		<#list coordinateList as coordinate>
			pointArray[${coordinate_index}] = new google.maps.LatLng(${coordinate.xAxis},${coordinate.yAxis});
		</#list>
		
		// 初始化地图
		function initialize(){
		  var mapDiv = document.getElementById('googleMap');
		  var myOptions = {
		    zoom: 5,
		    center: center,
		    mapTypeId: google.maps.MapTypeId.ROADMAP
		  }
		  map = new google.maps.Map(mapDiv, myOptions); 
		  
		  <#list coordinateList as coordinate>
			  var marker = new google.maps.Marker({
				position: pointArray[${coordinate_index}],
		      });
			  marker.setMap(map);
		  </#list>
		  
		  var myTrip=[
		  <#list coordinateList as coordinate>
		  	<#if coordinate_has_next>
		  		pointArray[${coordinate_index}],
		  	<#else>
		  		pointArray[${coordinate_index}]
		  	</#if>
		  </#list>];
		  var flightPath = new google.maps.Polyline({
			  path:myTrip,
			  strokeColor:"#0000FF",
			  strokeOpacity:0.8,
			  strokeWeight:2
		  });
          flightPath.setMap(map);
		}
		
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
</body>
</html>