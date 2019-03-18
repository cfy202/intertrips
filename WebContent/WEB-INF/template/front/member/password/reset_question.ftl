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
		 $("form#questionForm").validate({
		     rules: {
		       password:{
		         required: true,
		       },
		       confirm_password:{
		         required: true,
		         equalTo: "#password"
		       }
		     },
		     
		     messages: {
				password:{
				  required:"请输入新密码"
				},
				confirm_password:{
				 required:"请输入确认密码",
				 equalTo: "两次密码输入不一致"
				}
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
				     <h3>密保找回密码</h3>
				</div>
				<form id="questionForm" action="question_submit.do" method="post">
						用户名：<input type="text" id="answer" name="account" class="text" value="${info.email}" readonly="readonly"/><br/><br/>
						您的密保问题：<input type="text" id="answer" name="account" class="text"  value="${info.question}" readonly="readonly"/><br/><br/>
						您的答案：<input type="text" id="answer" name="answer" class="text"/><br/><br/>
						输入新密码：<input type="password" id="password" name="password" class="text"/><br/><br/>
						确定新密码：<input type="password" id="confirm_password" name="confirm_password"/><br/><br/>
						<input type="hidden" id="id" name="id" value="${info.id}"/><br/><br/>
						<input type="submit" value="提交"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>