<div id="top" class="navbar-wrapper">
  <header class="navbar navbar-default navbar-fixed-top" id="MainMenu" role="navigation">
    <div class="navbar-extra-top clearfix">
      <div class="navbar container-fluid">
        <ul id="menu-top-bar-menu" class="nav navbar-nav navbar-left">
          <li id="menu-item-715" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-715"><a href="${ctx!}/${(costFresh.code)!}/contactus.htm" title="Contact Us"><i class="fa fa-envelope"></i> Contact Us</a></li>
          <li id="menu-item-312" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-312"><a href="javascript:goProfile();" title="Sign in"><i class="fa fa-sign-in"></i> Sign in </a></li>
          <li id="logoutLi" style="display:none;" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-312"><a href="javascript:logout();" class="theme-logout" title="Log Out"><i class="fa fa-sign-in"></i> Log Out </a></li>
         
         <#--
          <#if page.tel !=null && page.tel?has_content >
          <li id="menu-item-312" class="menu-item menu-item-type-custom menu-item-phone"><i class="fa fa-phone"></i> ${(page.tel)!}</li>
          </#if>
          -->
          
        </ul>
        <div class="navbar-top-right">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="javascript: void(window.open('https://www.facebook.com/IntertripsTravel/'));" target="_blank"><i class="fa fa-facebook fa-fw"></i></a></li>
            <li><a href="javascript: void(window.open('https://plus.google.com/share?url='.concat(encodeURIComponent(document.title)) .concat(' ') .concat(encodeURIComponent(location.href))));" target="_blank"><i class="fa fa-google-plus fa-fw"></i></a></li>
            <li><a href="javascript: void(window.open('https://twitter.com/Inter_trips'));" target="_blank"><i class="fa fa-twitter fa-fw"></i></a></li>
          </ul>
       
          <form class="navbar-form navbar-right navbar-search" role="search" method="get" action="${ctx!}/search.htm">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search" id = "SearchMore" value="" name="keyword" title="Search for:">
              <input type ="hidden" name = "costnumberF" value="${(costFresh.id)!}">
           
            </div>
            <button type="submit" class="btn btn-default"><span class="fa fa-search"></span></button>
          </form>
        </div>
      </div>
    </div>
    <div class="container-fluid collapse-md" id="navbar-main-container">
      <div class="navbar-header"> 
      	<a href="${ctx!}/${(costFresh.code)!}" title="" rel="home" class="navbar-brand"> <img src="${ctx!}/assets-web/images/logo.png" alt=""> </a>
      	<a href="javascript:goProfileMobile();" class="navbar-login-icon"><i class="fa fa-user"></i></a>
      	<ul class="navbar-flag" id="flagselect" actTry="<#if frontCode??&&frontCode='USD'>USD<#else>AUD</#if>">
      	     
        	<li><a  href="https://www.intertrips.com"><img <#if frontCode??&&frontCode='USD'>class="navbar-flag-current"</#if>  src="${ctx!}/assets-web/images/united_states.jpg"></a></li>
            <li><a href="http://www.intertrips.com.au"><img <#if frontCode??&&frontCode='AUD'>class="navbar-flag-current"</#if> src="${ctx!}/assets-web/images/australia.jpg"></a></li>
            <li><a href="http://www.wenjing.com"><img src="${ctx!}/assets-web/images/wenjing.jpg"></a></li>
        </ul>
        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      </div>
      <nav class="navbar-collapse collapse" id="navbar-main">
        <ul id="menu-main-menu" class="nav navbar-nav navbar-right">
        <#list navigation as nav>
        	<#if nav.level = 0>
        		<#if nav_has_next && navigation[nav_index+1].level=1>
          <li id="menu-item-114" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-114 dropdown show-on-hover">
          	<a href="${ctx!}${(nav.url)!}" data-toggle="dropdown" class="dropdown-toggle" aria-haspopup="true">${(nav.name)!}<span class="caret"></span></a>
            <ul role="menu" class=" dropdown-menu">
            		<#list navigation as nav2>
            			<#if nav2.level = 1 && nav2.upid = nav.id>
              <li id="menu-item-285" class="menu-item menu-item-type-post_type menu-item-object-destination menu-item-285"><a href="${ctx!}${(nav2.url)!}">${(nav2.name)!}</a></li>
              			</#if>
              		</#list>
            </ul>
          </li>
          		<#else>
      	  <li id="menu-item-138" class="menu-item menu-item-type-post_type menu-item-object-page current_page_parent menu-item-138">
          	<a href="${ctx!}${(nav.url)!}">${(nav.name)!}</a>
          </li>
        		</#if>
        	</#if>
        </#list>
        
        </ul>
      </nav>
    </div>
  </header>
</div>