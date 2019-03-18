
		<div class="span4">
            <div class="uc-box uc-sub-box">
                <div class="uc-nav-box">
                    <div class="box-hd">
                        <h3 class="title">Orders Center</h3>
                    </div>
                    <div class="box-bd">
                        <ul class="uc-nav-list">
                            <li <#if active?has_content&&active='My order'>class="active"</#if>><a href="${ctx!}/member/profile/user_orders.htm?cValue=${frontCode!}">My order</a></li>
                        </ul>
                    </div>
                </div>
                <div class="uc-nav-box">
                    <div class="box-hd">
                        <h3 class="title">Personal center</h3>
                    </div>
                    <div class="box-bd">
                        <ul class="uc-nav-list">
                            <li <#if active?has_content&&active='Personal center'>class="active"</#if>><a href="${ctx!}/member/profile/index.htm?cValue=${frontCode!}">Personal center</a></li>
                            <li <#if active?has_content&&active='Coupons'>class="active"</#if>><a href="${ctx!}/member/profile/user_couponse.htm?cValue=${frontCode!}">Coupons</a></li>
                        </ul>
                    </div>
                </div>
                <div class="uc-nav-box">
                    <div class="box-hd">
                        <h3 class="title">Accounts management</h3>
                    </div>
                    <div class="box-bd">
                        <ul class="uc-nav-list">
                            <li <#if active?has_content&&active='Personal Information'>class="active"</#if>><a href="${ctx!}/member/profile/edit.htm?cValue=${frontCode!}">Personal Information</a></li>
                            <li <#if active?has_content&&active='Change password'>class="active"</#if>><a href="${ctx!}/member/profile/edit_password.htm?cValue=${frontCode!}">Change password</a></li>
                        </ul>
                    </div>
                </div>
          