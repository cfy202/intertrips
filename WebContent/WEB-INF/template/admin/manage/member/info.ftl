<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>会员详细信息</title>
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
<script type="text/javascript">
	var basePath = "${ctx!}";
    var Country = "${(info.country)!}";
	var Province = "${(info.province)!}";
    var City = "${(info.city)!}";
	var Nationality = "${(info.nationality)!}";
	
	var $$ = function(func){  
            if (document.addEventListener) {  
                window.addEventListener("load", func, false);  
            }  
            else if (document.attachEvent) {  
                window.attachEvent("onload", func);  
            }  
        }  
     $$(function(){  
            disableOcx();  
        })  
    //window.onload = disableOcx;//当页面加载的时候执行此函数
	
	//使页面不可编辑
	function disableOcx() {
		var form = document.forms[0];
		for ( var i = 0; i < form.length; i++) {
			var element = form.elements[i];
			//部分元素可以编号 element.name 是元素自定义 name
			if (element.name != "updateForm" &&　element.name != "back") {
			   element.disabled = "true";
			}
		}
	}
	
	//取消不可编辑
	function noDisable() {
		var form = document.forms[0];
		for ( var i = 0; i < form.length; i++) {
		var element = form.elements[i];
		element.disabled = false;
	   }
	} 
	$(document).ready(function(){
	  $("form#user_profileForm").validate({
				rules: {
					usertel: {
		                number : true
					},
					usermobile: {
					    number : true
					},
					postalcode: {
					    number: true
					}
				},
					
				messages: {
					usertel: {
		                number : "* 请输入正确电话号码"
					},
					usermobile: {
					    number : "* 请输入正确手机号码"
					},
					postalcode: {
					    number: "* 请输入正确邮编"
					}
				},
			})
	
	  $(".checkImage").click(function(){
			$("#userImage").attr("src",$(this).attr("src"));
			$("#changephoto").val($(this).attr("src"));
		});
	})
</script>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
			<!-- User Info, Notifications and Menu Bar -->
			 <#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">会员详细信息</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/member/list.do">会员列表</a>
						  </li>
							<li class="active">
								 <strong>会员详细信息</strong>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="user_profileForm" action="${ctx!}/admin/member/save.do" method="post">
							    <div class="row" class="col-md-12">
							       <div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="imageurl">头像：</label>
											<div class="col-sm-8">
											    <table width="800" border="0" cellspacing="1" cellpadding="0">
					                                 <tr>
					                                    <td>
					                                      <#if info.imageurl??>
					                                      <img id="userImage" src="${(info.imageurl)!}" class="img_userface left">
					                                      <#else>
					                                      <img id="userImage" src="${ctx!}/assets-web/images/default_bg.jpg" class="img_userface left" width="100" height="100">
					                                      </#if>
					                                      <input type="hidden" name="imageurl" id="changephoto" value="${(info.imageurl)!}"/>
					                                      <div class="div_facelist left">
					                                      	<img class="checkImage" src="${ctx!}/assets-web/images/user_head1.gif" alt="" width="50" height="50">		                               			
					                                        <img class="checkImage" src="${ctx!}/assets-web/images/user_head2.gif" alt="" width="50" height="50">         						        											                                       
					                                        <img class="checkImage" src="${ctx!}/assets-web/images/user_head3.gif" alt="" width="50" height="50">							                                        
					                                        <img class="checkImage" src="${ctx!}/assets-web/images/user_head4.gif" alt="" width="50" height="50">
					                                      </div>
					                                      <div class="clear"></div>
					                                    </td>
					                                  </tr>          
					                              </table>
											</div>
										</div>
									</div>
									<div class="col-md-8">
										<div class="form-group">
										   <div class="col-sm-10 control-label">
											  <input type="button" name="updateForm" value="编辑资料" class="btn btn-success" onClick="noDisable();">
										   </div>
										</div>
									</div>
							    </div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-5">
										<div class="form-group">
											<label class="col-sm-5 control-label">注册时间：${(info.member.createtime)!}</label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-6">最后登录时间：${(info.member.lasttime)!}</label>
										</div>
									</div>
								</div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-5">
										<div class="form-group">
											<label class="col-sm-5 control-label">注册地址：${(info.member.ip)!}</label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-6">状态：${(info.member.userstatus=1)?string("激活","未激活")}</label>
										</div>
									</div>
							    </div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="email">邮箱：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="email" name="email" value="${(info.email)!}" >
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="userlevel">用户等级：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="userlevel" name="userlevel" value="${(info.userlevel)?c}" >
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="score">积分：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="score" name="score" value="${(info.score)?c}" >
											</div>
										</div>
									</div>
							    </div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
							         <div class="col-md-4">
									     <div class="form-group">
											<label class="col-sm-3 control-label" for="lastName">姓：</label>
											<div class="col-sm-8">
											   <input type="text" class="form-control" id="lastName" name="lastName" value="${(info.lastName)!}" >
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="firstName">名：</label>
											<div class="col-sm-8">
											   <input type="text" class="form-control" id="firstName" name="firstName" value="${(info.firstName)!}" >
											</div>
										</div>
									</div>
							    </div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="usersex">性别：</label>
											<div class="col-sm-8">
											    <select class="form-control" name="usersex">
			                                        <option value="0" <#if (info.usersex)?? && info.usersex="0">selected="selected"</#if>>保密</option>
			                                        <option value="1" <#if (info.usersex)?? && info.usersex="1">selected="selected"</#if>>男</option>
			                                        <option value="2" <#if (info.usersex)?? && info.usersex="2">selected="selected"</#if>>女</option>
			                                    </select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="birthday">生日：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="birthday" name="birthday" value="${(info.birthday?string("yyyy-MM-dd"))!}" onClick="WdatePicker();">
											</div>
										</div>
									</div>
							    </div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="nationality">国籍：</label>
											<div class="col-sm-8">
												<select class="form-control" name="nationality" id="nationality">
                                                </select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="passportNo">护照号：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="passportNo" name="passportNo" value="${(info.passportNo)!}" >
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-4 control-label" for="passportValid">护照有效期：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="passportValid" name="passportValid" value="${(info.passportValid?string("yyyy-MM-dd"))!}" onClick="WdatePicker();">
											</div>
										</div>
									</div>
							    </div>
							    <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label">地址：</label>
											<div class="col-sm-8">
												<select class="form-control" name="country" id="sCountry"></select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<div class="col-sm-8">
			                                    <select class="form-control" name="province" id="sProvince"></select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<div class="col-sm-8">
				                                <select class="form-control" name="city" id="sCity"></select>
											</div>
										</div>
									</div>
								</div>
								<div class="row" class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="useraddr">详细地址：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="useraddr" name="useraddr" value="${(info.useraddr)!}" >
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-4 control-label" for="postalcode">邮编：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="postalcode" name="postalcode" value="${(info.postalcode)!}" >
											</div>
										</div>
									</div>
							    </div>
							     <div class="form-group-separator"></div>
							    <div class="row" class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="usertel">电话号码：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="usertel" name="usertel" value="${(info.usertel)!}" >
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="col-sm-3 control-label" for="usermobile">手机号码：</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="usermobile" name="usermobile" value="${(info.usermobile)!}" >
											</div>
										</div>
									</div>
							    </div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
									    <input type="hidden" name="id" value="${(info.id)!}">
									    <input type="hidden" name="memberid" value="${(info.memberid)!}">
										<input type="submit" value="修改提交" class="btn btn-success">
										<input type="button" name="back" value="返回" class="btn btn-success" onClick="location.href='${ctx!}/admin/member/list.do';">
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
	<script type="text/javascript" src="${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/location.js"></script>
    <script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>