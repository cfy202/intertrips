<!doctype html>
<html>
<head>
    <meta charset="utf-8">
   <#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link type="text/css" media="all" href="${ctx!}/assets-web/css/index.css" rel="stylesheet" />
	<title>Change Password</title>
	<link rel="shortcut icon" href="${ctx!}/favicon.ico">
	<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
	<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
	<script type='text/javascript' src='${ctx!}/assets-web/js/jquery-1.10.2.min.js'></script>
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
    <script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-67304615-1', 'auto');
	  ga('send', 'pageview');
	
	</script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		 $("form#resetForm").validate({
		    errorElement: "div",
            errorClass:"err-tip",
			rules: {
				password: {
					required: true,
					minlength: 6,
					maxlength: 20
				},
				rePassword: {
					required: true,
					equalTo: "#password"
				}
			},
			messages: {
				password: {
					required: "Please enter a new password",
					minlength: "Password length must be between 6-20 characters.",
					maxlength: "Password length must be between 6-20 characters."
				},
				rePassword: {
					required: "Please enter a confirmation password",
					equalTo: "Two input passwords are not consistent"
				}
			},
			submitHandler: function(form) {
        	$.ajax({
                url: $("#resetForm").attr("action"),
                type: "POST",
                data: {
                    password: $("#password").val(),
                    id: $("#memberid").val()
                },
                dataType: "json",
                cache: false,
                beforeSend: function() {
                    $('#sub-reset-password').attr("disabled", true);
                    $("#loading_hint").show();
                    $("#hint").text("Please wait a moment...");
                },
                complete: function(message) {
                	$("#loading_hint").hide();
                	<#--
                	$("#loading_hint").show();
                	$("#hint").text("修改成功...");
                	setTimeout(function(){
                		 $("#loading_hint").hide();
               	    },1500);
               	    -->
                    $('#sub-reset-password').attr("disabled", false);
                    var result = eval("(" + message.responseText + ")");
                    if(result.success){
                      alert("To modify the success, please login！");
                      location.href = "${ctx!}/login.htm";
                    }else{
                      alert(result.message);
                    }
                }
            });
        }
		});

  })
</script>
<body class="blog"> 
<div class="wrapper">
  <div class="container"> 
  	<a href="${ctx!}/" title="Back Home" class="logo"></a>
    <div class="signup-forms">
      <div class="signup-box" >
        <div class="add-info">
          <div class="add-hd">Change password</div>
          <form action="reset_submit.do" method="post" id="resetForm">
            <input type="hidden" name="token" value="">
            <div class="form-field">
              <#if message?? && message!="">
		            <div class="alert alert-success">
			            <div class="mt5">
			                ${message}<br>
			             </div>
		            </div>
		       </#if>
              <input name="password" id="password" type="password" placeholder="new password" autocomplete="off"  class="" value="">
            </div>
            <div class="form-field">
              <input name="rePassword" id="rePassword" type="password" placeholder="confirmation password" autocomplete="off"  class="" value="">
            </div>
            <div class="submit-btn">
              <input type="hidden" name="id" id="memberid" value="${member.id}" />
              <button type="submit" id="sub-reset-password">submit</button>
            </div>
            <div class="form-bottom">
            	<a href="${ctx!}/login.htm" class="form-field-a left">back to login</a>
                <div class="clear"></div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


</body>


</html>