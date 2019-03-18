<div class="cd-popup" role="alert">
    <div class="cd-popup-container">
        <p id="warnNotice" class="cd-popup-container-p"></p>
        <input class="cd-popup-btn" type="button" value="confirm">
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>        
</div>
<script type="text/javascript">
	$(function(){
	    //close popup
		$('.cd-popup').on('click', function(event){
			if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
				event.preventDefault();
				$(this).removeClass('is-visible');
			}
		});
		$('.cd-popup-btn').click(function(){
			$(this).parent().parent().removeClass('is-visible');		
		});	
	});
    var alertWarn = function(warnNotice){
    	$("#warnNotice").html(warnNotice);
    	$('.cd-popup').addClass('is-visible');
    }
</script>