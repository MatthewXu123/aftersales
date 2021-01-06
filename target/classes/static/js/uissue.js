$(function() {
	var evaluationLevel = "";
	if(issueHalarmId != null){
		$(".option-alarmCode").each(function(){
			if($(this).val() == issueHalarmId)
				$(this).attr("selected",true);
		})
	}
	tabInit();
	formSubmit();
	photoUpload();
	photoRetrieve();
	photoDeleteInit();
	evaluationSelect();
})

function tabInit() {
	$(".span-tab1").click(function(){
		if(issueStatus != 'PENDING'){
			$(".div-submit").css("display", "none");
		}else{
			$(".div-submit").css("display", "");
		}
	});
	$(".span-tab2").click(function(){
		if(issueStatus != 'FINISHED'){
			$(".div-submit").css("display", "none");
		}else{
			$(".div-submit").css("display", "");
		}
	});
	$(".span-tab1").click();
	if(issueStatus != undefined && issueStatus != null){
		if(issueStatus != 'FINISHED'){
			$("#div-main-process").css("height","25%");
			$(".div-evaluation").css("display", "none");
			$(".span-issue2").css("display", "none");
			$(".div-evaluation-description").css("display", "none");
		}else if(issueStatus == 'FINISHED'){
			$(".span-hint").css("display", "none");
		}
		
		$("#div-subtab-maintenance").addClass("border-selected");
		$("#div-main-process").css("display", "none");
		tabSwitch([ 'div-subtab-maintenance', 'div-subtab-process' ], [
				'div-main-maintenance', 'div-main-process' ], "border-selected");
	}
}

function formSubmit(){
	$("#btn_form").click(function(){
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
			if(issueId == null || issueId == undefined || $("#div-subtab-maintenance").hasClass("border-selected")){
				photoVal();
				$(".input-alarmCode").val($(".select-cause").find("option:selected").val());
				$("#form_main").submit();
			}
		}
		
		if($("#div-subtab-process").hasClass("border-selected")){
			$.ajax({
				url:"/aftersales/ueva/" + issueId,
				method:"POST",
				data:{
					"evaluationLevel":evaluationLevel,
					"comment":$("#textarea_eva").val()
				},
				dataType:"json",
				success:function(result){
					if(result.status == 200){
						if(i18nVal == 'zh-CN' || i18nVal == undefined){
							$("#btn_form").text('已提交，即将跳转');
						}else{
							$("#btn_form").text('Submitting...');
						}
						setTimeout(function(){
							window.location.href = "/aftersales/uboard";
						},2000);
					}
				},
				error:function(e){
					console.log(e.status);
				}
			})
		}
	});
}

function photoRetrieve(){
	if(issueId != null){
		$(".img-plus").each(function(){
			var $img = $(this);
			var id = $img.attr("id");
			$.ajax({
				url: "/aftersales/uissue/" + issueId + "/" + id,
				method : "GET",
				success:function(result){
					if(result != null && result.length != 0){
						$img.attr("src","/aftersales/uissue/" + issueId + "/" + id);
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

function evaluationSelect(){
	$(".img-evaluation").click(function(){
		var $img = $(this);
		var url = $(this).attr("src").split(".svg")[0];
		if(url.indexOf("2") != -1)
			url = url.substring(0,url.length-1);
		$(".img-evaluation").each(function(){
			var url = $(this).attr("src").split(".svg")[0];
			if(url.indexOf("2") != -1)
				url = url.substring(0,url.length-1);
			$(this).attr("src", url + ".svg");
		})
		$img.attr("src", url + "2.svg");
		evaluationLevel = $img.attr("value");
	})
}



