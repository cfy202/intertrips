$(document).ready(function(){
							
	$("#select1 dd").click(function () {
		$(this).addClass("selected");
		if ($(this).hasClass("select-all")) {
			$(this).siblings().removeClass("selected");
			$(".citySelect").remove();
		} else {
			var isExist = false;
			$(this).siblings(".select-all").removeClass("selected");
			var $copyThisA = $(this).clone();
			$(".select-result dl").find("dd").each(function(){
				if($(this).text() == $copyThisA.text()){
					isExist = true;
				}
			});
			if(!isExist){
				$(".select-result dl").append($copyThisA.attr("class", "citySelect"));		
			}
		}
	});

	$("#select2 dd").click(function () {
		$(this).addClass("selected");
		if ($(this).hasClass("select-all")) {
			$(this).siblings().removeClass("selected");
			$(".attractSelect").remove();
		} else {
			var isExist = false;
			$(this).siblings(".select-all").removeClass("selected");
			var $copyThisA = $(this).clone();
			$(".select-result dl").find("dd").each(function(){
				if($(this).text() == $copyThisA.text()){
					isExist = true;
				}
			});
			if(!isExist){
				$(".select-result dl").append($copyThisA.attr("class", "attractSelect"));		
			}
		}
	});

	$("#select3 dd").click(function () {
		$(this).addClass("selected");
		if ($(this).hasClass("select-all")) {
			$(this).siblings().removeClass("selected");
			$(".itinerarySelect").remove();
		} else {
			var isExist = false;
			$(this).siblings(".select-all").removeClass("selected");
			var $copyThisA = $(this).clone();
			$(".select-result dl").find("dd").each(function(){
				if($(this).text() == $copyThisA.text()){
					isExist = true;
				}
			});
			if(!isExist){
				$(".select-result dl").append($copyThisA.attr("class", "itinerarySelect"));		
			}
		}
	});
	
	var $citySelect = $(".select-result dl .citySelect");
	var $attractSelect = $(".select-result dl .attractSelect");
	var $itinerarySelect = $(".select-result dl .itinerarySelect");
	

	$citySelect.live("click", function () {
		var $this = $(this);
		$this.remove();
		if($(".select-result dl .citySelect").size() == 0){
			$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
		}else{
			$("#select1 dd").each(function(){
				if($this.text() == $(this).text()){
					$(this).removeClass("selected");
				}
			});
		}
	});

    $attractSelect.live("click", function () {
		var $this = $(this);
		$this.remove();
		if($(".select-result dl .attractSelect").size() == 0){
			$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
		}else{
			$("#select2 dd").each(function(){
				if($this.text() == $(this).text()){
					$(this).removeClass("selected");
				}
			});
		}
	});

	$itinerarySelect.live("click", function () {
		var $this = $(this);
		$this.remove();
		if($(".select-result dl .itinerarySelect").size() == 0){
			$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
		}else{
			$("#select3 dd").each(function(){
				if($this.text() == $(this).text()){
					$(this).removeClass("selected");
				}
			});
		}	
	});

	$(".select dd").live("click", function () {
		if ($(".select-result dd").length > 1) {
			$(".select-no").hide();
		} else {
			$(".select-no").show();
		}
	});
	
});
/*www.jq22.com*/