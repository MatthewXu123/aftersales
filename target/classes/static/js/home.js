$(function() {
	$("#div_home").css("display","none");
	$(".div-home2").css("display","none");
	// Verify the SN
	var sn = $.cookie('sn');
	/*var uri = document.referrer;
	var name = window.name;
	window.onload = function(){
		uri = "";
	}
	if(uri.indexOf("board") != -1 || uri.indexOf("mlogin") != -1){
		$(".display-none").css("display","none");
		$("#div_home").attr("class", "fade-in-section.is-visible");
		$(".span-home-system").css("display","");
	}*/
	if (sn != undefined && sn.length != 0) {
		$(".input-sn").val(sn);
	}
	$(".img-enter").click(function() {
		var sn = $(".input-sn").val();
		if (sn == undefined || sn.length == 0) {
			$(".input-sn").addClass("border-red");
			return;
		}
		$.ajax({
			url : "/aftersales/home/sn_verify",
			method : "POST",
			data : {
				"sn" : sn,
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 200) {
					$.cookie('sn', sn);
					//$(".display-none").css("display","none");
					//$("#div_home").attr("class", "fade-in-section.is-visible");
					/*$("#div_home").css("display", "");
					$(".div-home2").css("display","");
					$(".div-home1").css("display","none");
					$(".select-i18n").css("display","none");
					$(".div-home-bg").css("height","85%");*/
					window.location.href = "/aftersales/home/role"
				} else {
					$(".input-sn").addClass("border-red");
				}
			}
		})
	})
})