$(function() {
	$(".span-home-system").css("display","none");
	// Verify the SN
	var sn = $.cookie('sn');
	/*var uri = document.referrer;*/
	/*if(uri.indexOf("board") != -1 || uri.indexOf("mlogin") != -1){
		$(".display-none").css("display","none");
		$("#div_home").attr("class", "fade-in-section.is-visible");
		$(".span-home-system").css("display","");
	}*/
	if (sn != undefined && sn.length != 0) {
		$(".input-sn").val(sn);
	}
	$(".img-verify").click(function() {
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
					$(".display-none").css("display","none");
					$("#div_home").attr("class", "fade-in-section.is-visible");
					$(".span-home-system").css("display","");
				} else {
					$(".input-sn").addClass("border-red");
				}
			}
		})
	})
})