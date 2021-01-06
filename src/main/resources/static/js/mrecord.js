$(function(){
	photoUpload();
	photoDeleteInit();
	photoRetrieve();
	formSubmit();
	if(mrecordId!=null){
    	$(".option-alarmCode").each(function(){
			if($(this).val() == mrecordAlarmCode)
				$(this).attr("selected",true);
		})
		$(".textarea-description").val(mrecordComment);
    }
})

function formSubmit(){
	$(".span-submit").click(function(){
		var flag = true;
		$(".form-notblank").each(function(){
			var val = $(this).val();
	    	if(val == "" || val.length == 0){
	    		$(this).addClass("border-red");
	    		flag = false;
	    	}else{
	    		$(this).removeClass("border-red");
	    	}
		})
		if(flag){
			photoVal();
			$(".input-alarmCode").val($(".select-cause").find("option:selected").val());
			$("#processStatus").val($(this).attr("status"));
			$("#form_record").submit();
		}
	})
		
}
function photoRetrieve(){
	if(mrecordId != undefined && mrecordId != null){
		$(".img-plus").each(function(){
			var $img = $(this);
			var id = $img.attr("id");
			$.ajax({
				url: "/aftersales/mrecord/" + mrecordId + "/" + id,
				method : "GET",
				success:function(result){
					if(result != null && result.length != 0){
						$img.attr("src","/aftersales/mrecord/" + mrecordId + "/" + id);
						$img.css("width","100%");
						$img.css("height","100%");
					}
				},
				error:function(e){
					console.log(e.status);
				}
			})
		})
	}
}