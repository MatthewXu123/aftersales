$(function(){
    //tabInit();
    formSubmit();
    if(installStreet != null){
        $("#textarea_address").val(installStreet);
    }
    if(installComment != null){
    	$("#textarea_comment").val(installComment);
    }
})

function tabInit(){
    $("#div-subtab-install").addClass("border-selected");
    $("#div-main-issue").css("display","none");
    tabSwitch(['div-subtab-install','div-subtab-issue'],['div-main-install','div-main-issue'],"border-selected");
}
function formSubmit() {
	$("#btn_form").click(function() {
		var flag = true;
		$(".form-notblank").each(function() {
			var val = $(this).val();
			if (val == "" || val.length == 0) {
				$(this).addClass("border-red");
				flag = false;
			} else {
				$(this).removeClass("border-red");
			}
		});

		if (flag) {
			var province = $("#select_province").val();
			var city = $("#select_city").val();
			var district = $("#select_district").val();
			var street = $("#textarea_address").val();
			$.ajax({
				url : window.location.href,
				method : "post",
				data : {
					"installerName":$("#input_installerName").val(),
					"installerPhone":$("#input_installerPhone").val(),
					"province":province,
					"city":city,
					"district":district,
					"street":street,
					"address" : province + city + district + street,
					"comment" : $("#textarea_comment").val()
				},
				success : function(result) {
					window.location.href="/aftersales/minstall";
				}
			})
		}
	})
}
