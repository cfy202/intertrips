<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "https://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="https://www.w3.org/1999/xhtml">
<head>
<#assign ctx = request.contextPath />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>密码修改</title>
<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function() {
		 $("form#resetForm").validate({
		     rules: {
		        account: {
		           required: true,
				   email: true,
				   remote: {
	                    type: "post",
	                    url: "${ctx!}/register/isExist.do",
	                    data: {
	                        account: function() {
	                            return $("#account").val();
	                        }
	                    },
	                    dataType: "json",
	                    dataFilter: function(data,type) {
				              if(data=="true"){  
				                  return true;
				               }else{  
				                  return false;
				               }   
	                    }
				  }
		        }
		     },
		     
		     messages: {
				account: {
					required: "请输入Email地址",
	                email: "请输入正确的email地址",
	                remote: "注册邮箱不存在"
				},
			},
		 });
	}); 
</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-67304615-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body>
	<div class="container member">
		<div class="span18 last">
			<div class="input">
				<div class="title">
				     <h3>密保找回</h3>
				</div>
				<form id="resetForm" action="reset_question.do" method="post">
					<table class="input">
						<tr>
							<th>
								注册邮箱: 
							</th>
							<td>
								<input type="text" id="account" name="account" class="text" maxlength="20" placeholder="请输入注册邮箱账号"/>
							</td>
						</tr>
						<tr>
							<th>
								&nbsp;
							</th>
							<td>
								<input type="submit" class="button" value="下一步" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>