/*******************************************/
/**      author:  hoho                   **/
/**      http://www.thinkcart.net        **/
/******************************************/

function getSwf(movieName) {
    if (window.document[movieName]) {
        return window.document[movieName];
    } else if (navigator.appName.indexOf("Microsoft") == -1) {
        if (document.embeds && document.embeds[movieName]) return document.embeds[movieName];
    } else {
        return document.getElementById(movieName);
    }
}

award_id = 0; //奖品ID
award_name = ''; //中奖名称

function start_lottery() {
	var id = $("#couponseId").val();
	alert(id);
    $.ajax({
        url: 'front/action/activity.do?couponseid='+id,
        type: "GET",
        data: null,
        dataType: "json",
        timeout: 20000,
        cache: false,
        beforeSend: function() { // 提交之前
        },
        error: function() { //出错
            getSwf('lottery').reset_egg(); //恢复金蛋点击事件
            alert('服务端出错！');
        },
        success: function(res) { //成功
            var obj = eval(res);
            if (obj != null) {
                if (obj.name == "start") {
                    alert('The activity will be started on Dec 22');
                } else {
                	if(obj.name == "end"){
                		alert('抽奖活动已经结束');	
                	}else{
                		
                		award_id = res.award_id; //得到奖品ID
                		award_name = res.award_name; //得到奖品名称
                		if (obj.disType == '$') {
                			$("#won").text("您获得了 -" + obj.disvalue + " & 优惠券");
                		} else {
                			$("#won").text("您获得了 " + obj.disvalue + "" + obj.distype + "折扣券");
                		}
                		$("#duijiangId").val(obj.id);
                		alert(obj.id);
                		//$("#CouponCode").text("测试请复制code Number:"+obj.name);
                		getSwf('lottery').break_egg(1); //展现金蛋被砸碎
                		//effect.zoomIn('award_1',1);
                		setTimeout(function() {
                			effect.zoomIn('award_1', 1);
                		},
                		500);
                	}

                }
            } else {
                getSwf('lottery').reset_egg(); //恢复金蛋点击事件
                alert('您的抽奖机会已经使用!');
            }
        }
    });
}

function reset(id, mask, demo) {
    if (mask) effect.mask(0);
    id = dom.ID(id);
    demo = dom.ID(demo);
    //每隔10毫秒执行一次。要在0.1秒内执行完。用高度除以10得出每次要减少多少像素
    var ele = {
        'height': id.clientHeight,
        'width': id.clientWidth
    }; //元素对象的宽度和高度
    var height_step = Math.ceil(ele.height / 10);
    var width_step = Math.ceil(ele.width / 10);
    var height_start = ele.height;
    var width_start = ele.width;
    var opacity = 100;
    var height_over = false;
    var width_over = false;
    var timer = setInterval(function() {
        height_start -= height_step;
        width_start -= width_step;
        dom.setOpacity(id, opacity);
        opacity -= 10;
        if (height_start < 0) {
            id.style.height = 0;
            height_over = true;
        } else {
            id.style.height = height_start + 'px';
        }
        if (width_start < 0) {
            id.style.width = 0;
            width_over = true;
        } else {
            id.style.width = width_start + 'px';
        }
        position.center(id);
        if (height_over && width_over) {
            id.style.display = 'none';
            clearInterval(timer);
            id.style.width = ele.width + 'px'; //将width和height还原，以便让它在接下来出现
            id.style.height = ele.height + 'px';
        }
    },
    10);
    demo.style.display = 'none';
}