<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>出发日期及价格添加</title>
  	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css"/>
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			   $("#groupdate").click(function(){
	                 $("#week").show();	
	                 $("#san").show();
	                 $("#groupdate").hide();
			   });
			   $("#san").click(function(){
			         $("input[name='dateweek']").each(function(){
					     $(this).attr("checked",false);
					 });  
					 $("#files").attr("checked",false);
	                 $("#week").hide();	
	                 $("#san").hide();
	                 $("#groupdate").show();
			   });
			   $('#files').click(function(){
					var obj = this;
					$('input[name="dateweek"]').each(function(index, element) {
						this.checked = obj.checked;
					});
	           });
	           
	           //根据运营中心异步查询orderid和出发地
			  $('#costnumber').bind('change', function () {
			       $.ajax({
		             type: "post",
		             url: "${ctx!}/admin/tourdate/getsort.do",
		             data: {costnumber:$("#costnumber").val(),tourlineid:$("#tourlineid").val()},
		             dataType: "json",
		             success: function(data){
		                 $('#sort').val(data.maxSort);
		                 <#--
		                     $('#departure').empty();   //清空upid里面的所有内容
	                         var html = ""; 
	                         $.each(data.departures, function(commentIndex, departure){
				        		html += '<div class="col-sm-2">'+
												'<div class="form-block">'+
												       '<label>'+
														  '<input type="checkbox" name="departureid" value="'+departure.id+'"/>&nbsp&nbsp'+departure.name+''+
													   '</label>'+
													   '<br />'+
												'</div>'+
											'</div>';
	                         });
	                          $('#departure').html(html);
	                     -->
		             }
		            });
		         });
		         
		         var num = 1;
		        //点击添加机票
		    	$(".check_more_one").click(function () {
		    	      var $airticketTable = $("#airticketTable");
				      var $airticketTitle = $("#airticketTitle");
				      $.ajax({
			             type: "post",
			             url: "${ctx!}/admin/tourdate/getDeparture.do",
			             data: {costnumber:$("#costnumber").val()},
			             dataType: "json",
			             success: function(data){
			                var trHtml = "";
			                trHtml +=  '<tr class="airticketTr">'+
										   '<td>'+
											   '<div class="col-sm-12">'+
				  							      '<select class="form-control" name="airTicketDeparture" onchange="setDepartureName(this,'+num+');">';
							$.each(data, function(commentIndex, departure){					       
								 trHtml +='<option value="'+departure.id+'">'+departure.name+'-'+departure.city+'</option>';
						    });
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
			             }
		             })
		        });
		        
	       });   
	       
	       
	       function setDepartureName(obj,n) {
	          $('#departureName'+n).val($(obj).find('option:selected').text());
	       }
	       
	        //删除机票
			function deleteairticketTr(nowtr){
			     if(confirm("确定删除吗？")){
				     $(nowtr).closest("tr").remove();
					if ($("#airticketTable").find("tr.airticketTr").size() <= 0) {
				    	$("#airticketTitle").hide();
					}
			     }
			}
	          
	        //自定义验证
	        <#--
            $.validator.addMethod("checkDate",function(value,element){
                var DATE_FORMAT = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}~[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
                return this.optional(element) || (DATE_FORMAT.test(value));
            },"<font color='red'>您输入的日期格式有误，正确格式应为：2015-05-01~2015-05-01</font>");
            
            $("form#loginForm").validate({
		        rules: {
					datelimit: {
						required: true,
						checkDate: true,
					},
				},	
				messages: {
					datelimit: {
						required: "<br/> 请填写出发日期 ",
					},
				},
            });
            -->
		
		// 计算剩余人数
		function getremainNum(){
		   var totalNum = $("#totalnum").val();
	       var soldNum = $("#soldnum").val();
	       var remainNum = parseInt(totalNum)-parseInt(soldNum);	  
           $("#remainnum").val(remainNum);
		}
		// 计算售出人数
		function getsoldNum(){
		   var totalNum = $("#totalnum").val();
		   var remainNum =  $("#remainnum").val();
	       var soldNum = parseInt(totalNum)-parseInt(remainNum);
           $("#soldnum").val(soldNum);
		}
		
		//校验日期和组团类型
		$(function(){
		    $('#tourDateForm').submit(function(){
			      var datelimit = $("#datelimit").val();
			      var dateArr = new Array();
			      dateArr = datelimit.split("~");
			      if(dateArr[0]==dateArr[1]){
			         if($("input[name='dateweek']:checked").length>0){
			           alert("请选择正确出发日期和组团类型！");
			           return false;
			         }
			         if($("#days").val()>0){
			           alert("请选择正确出发日期");
			           return false;
			         }
			      }else{
			         return true;
			      }
		    });
		})
		
		//计算标价
		function getMarkedPrice(){
			var sellingPrice = $("#sellingprice").val();
			var markedPrice = parseInt(parseInt(sellingPrice)/0.55);
			$("#markedprice").val(markedPrice);
		}
	</script>
</head>
<body class="page-body">
<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
			<#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">${(tourline.tourname)!}</h1>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						   <li>
							  <a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						   </li>
						   <li>
							   <a href="${ctx!}/admin/tourdate/list.do?productId=${productId!}">出发日期及价格管理</a>
						   </li>
						   <li class="active">
							  <a href="${ctx!}/admin/tourdate/add.do?productId=${productId!}"><strong>出发日期及价格添加</strong></a>
						   </li>
						</ol>
				   </div>
			</div>
			
			<div class="vspacer v3"></div>
			
			<!-- Form wizard with validation starts here -->
			
			<script type="text/javascript">
				jQuery(document).ready(function($)
				{
					$(".multi-select").multiSelect({
						afterInit: function()
						{
							// Add alternative scrollbar to list
							this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
						},
						afterSelect: function()
						{
							// Update scrollbar size
							this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
						}
					});
					
					$(".selectboxit").selectBoxIt().on('open', function()
					{
						// Adding Custom Scrollbar
						$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
					});
				});
			</script>
			
			<form role="form" id="tourDateForm" action="${ctx!}/admin/tourdate/save.do" method="post" class="form-wizard validate">
				 <ul class="tabs">
					<li class="active">
					</li>					
				</ul>				
				<div class="tab-content no-margin">
					
					<!-- Tabs Content -->
					<div class="tab-pane with-bg active" id="fwv-1">
					    <#if (cost?size==1)>
							<input type="hidden" name="costnumber" id="costnumber" value="${cost[0].id}">
						<#else>
						 <div class="row">
					        <div class="col-md-8">
							    <div class="form-group">
								<label class="col-sm-2 control-label" for="costnumber">销售中心：</label>
									<div class="col-sm-5">
									    <select name="costnumber" class="form-control" id="costnumber">
									       <#list cost as cost>
									          <option value="${(cost.id)!}">${cost.name} - ${cost.code} - ${cost.sign}</option>
									       </#list>
									    </select>
							        </div>
						        </div> 
							</div> 
						</div> 
						    <br/><div class="form-group-separator"></div>
						</#if>
						<!--
					    <#if (cost?size==1)>
						   <input type="hidden" name="currencyid" value="${costCurrency.currency.id}"/>
						<#else>
						   <div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="currencyid">币种（默认）：</label>
									<div class="col-sm-5">
									    <select name="currencyid" class="form-control" id="currencyid">
									       <#list currencies as currency>
									          <option value="${(currency.id)!}">${currency.name} - ${currency.code} - ${currency.sign}</option>
									       </#list>
									    </select>
									</div>
								</div>
							</div>
						  </div>
						   <br/><div class="form-group-separator"></div>
						</#if>
					    -->
					    <div class="row">
					        <div class="col-md-8">
							    <div class="form-group">
									<label class="col-sm-2 control-label" for="datelimit">出发日期：</label>
										<div class="col-sm-5">
											<input class="form-control daterange" id="datelimit" name="datelimit" data-format="YYYY-MM-DD" data-start-date="${nowtime}" data-end-date="${nowtime}" data-separator="~" data-validate="required">
										</div>
								</div> 
							</div> 
						</div> 
						
						<br/><div class="form-group-separator"></div>
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label  class="col-sm-2 control-label" for="sealGroupDate">封团天数 ：<br/>&nbsp;&nbsp;（天）：</label>
									<div class="col-sm-2">
									   <input class="form-control" name="sealGroupDate" id="sealGroupDate" number="true" data-validate="required" value="45"/>
									</div>
								</div>
							</div>
						</div>
						
						<br/><div class="form-group-separator"></div>
						    <div class="row">
					            <div class="col-md-8">
									<div class="form-group">
										<label class="col-sm-2 control-label">组团类型：</label>
										<div class="col-sm-2">
										   <input type="button" class="btn btn-turquoise" id="groupdate" value="定期发团">
										</div>
										<label class="col-sm-2 control-label"></label>
										<div class="col-sm-10" style="display:none" id="week">
										    <label><input type="checkbox" name="files" id="files">&nbsp;全选&nbsp;&nbsp;&nbsp;</label><br/> 
										    <label><input name="dateweek" type="checkbox" value="1" />&nbsp;周一&nbsp;&nbsp;&nbsp;</label> 
											<label><input name="dateweek" type="checkbox" value="2" />&nbsp;周二&nbsp;&nbsp;&nbsp;</label> 
											<label><input name="dateweek" type="checkbox" value="3" />&nbsp;周三 &nbsp;&nbsp;&nbsp;</label> 
											<label><input name="dateweek" type="checkbox" value="4" />&nbsp;周四 &nbsp;&nbsp;&nbsp;</label> 
											<label><input name="dateweek" type="checkbox" value="5" />&nbsp;周五 &nbsp;&nbsp;&nbsp;</label>
											<label><input name="dateweek" type="checkbox" value="6" />&nbsp;周六 &nbsp;&nbsp;&nbsp;</label>
											<label><input name="dateweek" type="checkbox" value="7" />&nbsp;周日 &nbsp;&nbsp;&nbsp;</label>
										</div>
										<label class="col-sm-2 control-label"></label>
										<div class="col-sm-2">
										   <input type="button" class="btn btn-turquoise" style="display:none" id="san" value="散拼团">
										</div>
								</div>
							</div>
						 </div>
						 
						<br/><div class="form-group-separator"></div>
						<div class="row" class="col-md-12">
							<div class="col-md-3">
								<div class="form-group">
									<label  class="col-sm-5 control-label" for="days">隔天发团<br/>&nbsp;&nbsp;（天）：</label>
									<div class="col-sm-5">
									   <input class="form-control" name="days" id="days" number="true" data-validate="required" value="0"/>
									</div>
								</div>
							</div>
						</div>
						 
						<br/><div class="form-group-separator"></div>
						<div class="row" class="col-md-12">
							<div class="col-md-3">
								<div class="form-group">
									<label  class="col-sm-5 control-label" for="markedprice">市场价：</label>
									<div class="col-sm-5">
									   <input class="form-control" name="markedprice" id="markedprice" number="true" data-validate="required" value="0" readonly="readonly"/>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-6 control-label" for="sellingprice">两人一间价<br/>&nbsp;&nbsp;（成人）：</label>
									<div class="col-sm-5">
									  <input class="form-control"  name="sellingprice" id="sellingprice" number="true" data-validate="required" value="0" onblur="getMarkedPrice()"/>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-5 control-label" for="threesellingprice">三人一间价<br/>（成人）：</label>
									<div class="col-sm-5">
									   <input class="form-control"  name="threesellingprice" id="threesellingprice" number="true" data-validate="required" value="0"/>
								    </div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-5 control-label" for="foursellingprice">四人一间价<br/>（成人）：</label>
									<div class="col-sm-5">
								        <input class="form-control"  name="foursellingprice" id="foursellingprice" number="true" data-validate="required" value="0"/>
								    </div>
								</div>
							</div>
						</div>
						
						<br/><div class="form-group-separator"></div>
						<div class="row">
						    <div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-5 control-label" for="babyPrice">&nbsp;&nbsp;婴儿价<br/>（0-2岁）：</label>
									<div class="col-sm-5">
									   <input class="form-control" name="babyPrice" id="babyPrice" number="true" data-validate="required" value="0"/>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-5 control-label" for="childPrice">&nbsp;&nbsp;小童价<br/>（2-5岁）：</label>
									<div class="col-sm-5">
									   <input class="form-control" name="childPrice" id="childPrice" number="true" data-validate="required" value="0"/>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-5 control-label" for="extrabedprice">小孩占床价格<br/>&nbsp;&nbsp;（5-11岁）：</label>
									<div class="col-sm-5">
									   <input class="form-control" name="extrabedprice" id="extrabedprice" number="true" data-validate="required" value="0"/>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class=" col-sm-6 control-label" for="nobedprice">小孩不占床价格<br/>&nbsp;&nbsp;（5-11岁）：</label>
									<div class="col-sm-5">
								   	   <input class="form-control" name="nobedprice" id="nobedprice" number="true" data-validate="required" value="0"/>
									</div>
								</div>
							</div>
						</div>
						
						<br/><div class="form-group-separator"></div>
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label class="col-sm-5 control-label" for="singleroomprice">单房差：</label>
									<div class="col-sm-5">
									   <input class="form-control" name="singleroomprice" id="singleroomprice" number="true" data-validate="required" value="0"/>
									</div>
								</div>
							</div>
						</div>
						
						<br/><div class="form-group-separator"></div>
						<div class="row">
						    <div class="col-md-12">
								<div class="form-group">
									<label class="col-sm-1 control-label">出发地：</label>
								    <div class="col-sm-10" id="departure">
									    <#list departures as departure>
											<div class="col-sm-3">
												<div class="form-block">
											       <label>
		     											<input type="checkbox"  class="cbr cbr-success" name="departureid" value="${departure.id}"/>${departure.name} - ${departure.city}
												   </label>
												   <br />
												</div>
											</div>
										</#list>
									</div>
							  </div>
							</div>
						</div>
						<br/><div class="form-group-separator"></div>
						<div class="row">
						    <div class="col-md-12">
								<div class="form-group">
								<label class="col-sm-1 control-label">机票：</label>
								<div class="col-sm-8">
							       <a class="check_more_one" href="javascript:;">+ 添加机票</a><br/>
							    </div>
							    <div class="col-sm-10">
								     <table class="table responsive" id="airticketTable">
										   <thead>
												<tr id="airticketTitle" style="display:none">
													<th>出发地</th>
													<th>价格</th>
													<th>排序</th>
													<th>操作</th>
												</tr>
											</thead>
									</table>
								</div>
							  </div>
							</div>
						</div>
						
						 <br/><div class="form-group-separator"></div>
						 <div class="row">
					          <div class="col-md-3">
							     <div class="form-group">
									<label class="col-sm-5 control-label" for="totalnum">成团人数：</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="totalnum" name="totalnum" value="9" number="true" data-validate="required" onBlur="getremainNum();" onkeyup="getremainNum()">
									</div>
								 </div>
							  </div>
							  
							  <div class="col-md-3">
								 <div class="form-group">
									<label class="col-sm-5 control-label" for="soldnum">售出人数：</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="soldnum" name="soldnum" value="0" number="true" data-validate="required" onBlur="getremainNum();" onkeyup="getremainNum()">
									</div>
								 </div>
							  </div>
							  
							  <div class="col-md-3">
								 <div class="form-group">
									<label class="col-sm-5 control-label" for="remainnum">剩余人数：</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="remainnum" name="remainnum" value="9" number="true" data-validate="required" onBlur="getsoldNum();" onkeyup="getsoldNum()">
									</div>
								</div>
							  </div>
						 </div>
						 
						 <br/><div class="form-group-separator"></div>
						 <div class="row">
					          <div class="col-md-4">
								 <div class="form-group">
									<label class="col-sm-4 control-label">状态：</label>
										<div class="checkbox">
											<label>
												<input type="checkbox" class="cbr cbr-success" name="isshow" value="1" checked="checked"> 显示
											</label>
										</div>
								</div>
							  </div>
							  
							  <div class="col-md-3">
								 <div class="form-group">
										<div class="checkbox">
											<label>
												<input type="checkbox" class="cbr cbr-success" name="ishot" value="1"> 热推
											</label>
										</div>
								</div>
							  </div>
							  
							   <div class="col-md-3">
								 <div class="form-group">
										<div class="checkbox">
											<label>
												<input type="checkbox" onclick="chooseCall(this);" class="cbr cbr-success" name="iscall" value="1"> 电话联系
											</label>
										</div>
								</div>
							  </div>
						 </div>
						 
					   <div class="form-group-separator"></div>
					   <div class="row" id="callNumberDiv" style="display:none;">
					          <div class="col-md-7">
								 <div class="form-group">
									<label class="col-sm-2 control-label" for="callNumber">Call Number:</label>
									<div class="col-sm-10">
									    <input id="callNumber" class="form-control" name="callNumber" ></input>
									</div>
								</div>
							  </div>
						 </div>
						
						<br/><div class="form-group-separator"></div>
						<div class="row">
					          <div class="col-md-7">
								 <div class="form-group">
									<label class="col-sm-2 control-label" for="sort">排序：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="sort" name="sort" value="${maxSort?c}" data-validate="number"> 
									</div>
								</div>
							  </div>
						 </div>
						 
						 <br/><div class="form-group-separator"></div>
						 <div class="row">
					          <div class="col-md-7">
								 <div class="form-group">
									<label class="col-sm-2 control-label" for="remark">备注：</label>
									<div class="col-sm-10">
									    <textarea class="form-control" name="remark" cols="6" id="remark"></textarea>
									</div>
								</div>
							  </div>
						 </div>
						 
						  <div class="row">
							  <br/><div class="form-group-separator"></div>
							  <div class="form-group">
								<div class="col-sm-10" align="center">
								    
								    <input type="hidden" name="productid" value="${product.id}">
									<input type="submit" value="提交" class="btn btn-success">
									<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/tourdate/list.do?productId=${productId!}';">
								</div>
							 </div>
						</div>
					</div>
				</div>
			</form>	
			<footer class="main-footer sticky footer-type-1">
				<div class="footer-inner">
					<div class="footer-text">
						&copy; 2015
					   <a href="" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
					</div>
					<div class="go-up">
					
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>
						
					</div>
					
				</div>
				
			</footer>
		</div>
	</div>

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
    <link rel="stylesheet" href="${ctx!}/assets/js/select2/select2.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2-bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/daterangepicker/daterangepicker-bs3.css">
    
	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>
    <script src="${ctx!}/assets/js/moment.min.js"></script>

	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${ctx!}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
	<script src="${ctx!}/assets/js/formwizard/jquery.bootstrap.wizard.min.js"></script>
	<script src="${ctx!}/assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="${ctx!}/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
    <script src="${ctx!}/assets/js/ckeditor/ckeditor.js"></script>
	<script src="${ctx!}/assets/js/ckeditor/adapters/jquery.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>
	<script src="${ctx!}/assets/js/daterangepicker/daterangepicker.js"></script>
	
	<!-- JavaScripts initializations and stuff -->
    <script src="${ctx!}/assets/js/xenon-custom.js"></script>
    <script>
		var chooseCall = function(isCall){
			if($(isCall).is(":checked")){
				$("#callNumberDiv").show();
			}else{
			    $("#callNumberDiv").hide();
			}
		}    
    </script>
</body>
</html>