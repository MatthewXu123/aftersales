$(function() {
	// Verify the SN
	var sn = $.cookie('sn');
	if (sn != undefined && sn.length != 0) {
		$(".input-sn").val(sn);
	}
	$(".img-enter").click(function() {
		var sn = $(this).prev().val();
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
				} else {
					$(".input-sn").addClass("border-red");
				}
			}
		})
	})
})