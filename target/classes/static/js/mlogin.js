$(function(){
	loginCodeAutoFill();
	loginVerify();
})
function loginCodeAutoFill(){
	var loginCode = $.cookie('loginCode');
	if(isNotBlank(loginCode))
		$(".input-discode").val(loginCode);
}
function loginVerify(){
	$(".input-discode").blur(function(){
		$(this).removeClass("border-red");
	})
	$(".img-enter").click(function(){
		var loginCode = $(".input-discode").val();
		if(loginCode == null || loginCode.length == 0){
			$(".input-discode").addClass("border-red");
		}else{
			$.ajax({
				url:"/aftersales/mlogin",
				method:"POST",
				data:{
					"loginCode":loginCode,
				},
				success:function(result){
					if(result.status == 200){
						$.cookie('loginCode', loginCode, { expires: 7 });
						window.location.href = "/aftersales/mboard";
					}else{
						$(".input-discode").addClass("border-red");
					}
				}
			})
		}
		
	})
}