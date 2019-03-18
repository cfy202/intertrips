<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<#assign ctx = request.contextPath />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Xenon Boostrap Admin Panel" />
<meta name="author" content="" />
<title>退出</title>
<!-- 
<link rel="stylesheet" href="https://fonts.useso.com/css?family=Arimo:400,700,400italic">
 -->

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
</head>
<body class="page-body lockscreen-page">
<div class="login-container">
  <div class="row">
    <div class="col-sm-7"> 
<script type="text/javascript">
jQuery(document).ready(function($)
{
	
	
	// Reveal Login form
	setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
	
	
	// Clicking on thumbnail will focus on password field
	$(".user-thumb a").on('click', function(ev)
	{
		ev.preventDefault();
		$("#passwd").focus();
	});
	
	
	// Form validation and AJAX request
	$(".lockcreen-form").validate({
		rules: {
			passwd: {
				required: true
			}
		},
		
		messages: {
			passwd: {
				required: 'Please enter your password.'
			}
		},
		
		submitHandler: function(form)
		{
			show_loading_bar(70); // Fill progress bar to 70% (just a given value)
			
			var $passwd = $(form).find('#passwd'),
			
				opts = {
					"closeButton": true,
					"debug": false,
					"positionClass": "toast-top-full-width",
					"onclick": null,
					"showDuration": "300",
					"hideDuration": "1000",
					"timeOut": "5000",
					"extendedTimeOut": "1000",
					"showEasing": "swing",
					"hideEasing": "linear",
					"showMethod": "fadeIn",
					"hideMethod": "fadeOut"
				};
			
			$.ajax({
				url: "${ctx!}/admin/login.do",
				method: 'POST',
				
				data: {
					do_login: true,
					username: $("#username").text(),
					passwd: $passwd.val(),
				},
				success: function(resp)
				{
					show_loading_bar({
						delay: .5,
						pct: 100,
						finish: function(){
							
							if(resp)
							{
								// Redirect after successful login page (when progress bar reaches 100%)
								window.location.href = '${ctx!}/admin/list.do';
							}
							else
							{
								toastr.error("密码不正确, 请重新输入)", "登陆", opts);
								$passwd.select();
							}
						}
					});
				}
			});
		}
	});
	
	// Set Form focus
	$("form#lockscreen .form-group:has(.form-control):first .form-control").focus();
});
</script>
      <form id="lockscreen" class="lockcreen-form fade-in-effect">
        <div class="user-thumb"> <a href="#"> <img src="../assets/images/user-5.png" class="img-responsive img-circle" /> </a> </div>
        <div class="form-group">
          <h3>欢迎<span id = "username" >${username!}</span>回来 </h3>
          <p>请输入密码登陆 <span><a href="../admin/login.html" style="color:#f2f2f2;text-decoration: underline;">更换用户登陆</a></span></p>
          <div class="input-group">
            <input type="password"  class="form-control input-dark" name="password" id="passwd" placeholder="Password" />
            
            <span class="input-group-btn">
            <button type="submit" class="btn btn-primary">登陆</button>
            </span> </div>
        </div>
      </form>
    </div>
  </div>
</div>
<!-- Bottom Scripts --> 
<script src="${ctx!}/assets/js/bootstrap.min.js"></script> 
<script src="${ctx!}/assets/js/TweenMax.min.js"></script> 
<script src="${ctx!}/assets/js/resizeable.js"></script> 
<script src="${ctx!}/assets/js/joinable.js"></script> 
<script src="${ctx!}/assets/js/xenon-api.js"></script> 
<script src="${ctx!}/assets/js/xenon-toggles.js"></script> 
<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 
<script src="${ctx!}/assets/js/toastr/toastr.min.js"></script> 
<!-- JavaScripts initializations and stuff --> 
<script src="${ctx!}/assets/js/xenon-custom.js"></script>
</body>
</html>