<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>successful</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/login.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
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
<div class="wrapper">
  <div class="container"> 
  	<a href="${ctx!}/" title="Back Home" class="logo"></a>
    <div class="signup-forms">
      <div class="signup-box" >
        <div class="add-info" id="add-sessucess" style="display:none">
            <div class="form-field">
                <p>Mail has been sent to your email address.</p>
                <p class="add-success-email" id="add-success-email"></p>
                <p>Please click on the verification link in the mailbox to complete the verification.</p>
                
            </div>
            <div class="form-field">
                <p class="add-success-down-title">------------Did not receive the message?------------</p>
                <p>Try again<a href="javascript:;" onclick="activate();" style="color:#ff872f;">Application to send</a>Verification link</p>
            </div>
          </div>
      </div>
    </div>
  </div>
</div>
<div id="div_bg" >
   <img id="img_bg" src="${ctx!}/assets-web/picture/1.jpg" style="width: 1920px; height: auto; margin-top: -218px;">
</div>
<div id="loading_hint" class="loading_hint" >
   <img src="${ctx!}/assets-web/background/loading.gif" style="width:32px; height:32px; margin-left:25px;"/>
   <span id="hint">login...</span>
</div>


</body>
<script type="text/javascript">
var n = parseInt(Math.ceil(Math.random()*3));
document.getElementById("div_bg").style.backgroundImage="url(${ctx!}/assets-web/picture/"+n+".jpg)"; 
document.getElementById('img_bg').src ="${ctx!}/assets-web/picture/"+ n + ".jpg";
</script>

</html>