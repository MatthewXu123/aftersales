$(function(){
	//菜单点击
	$(".J_menuItem").on('click', function() {
		var url = $(this).attr('href');
		$("#J_iframe").attr('src', url);
		return false;
	});
	$('input[id=uploadFile]').change(function() {
		$('#fileCover').val($(this).val());
	});
	$(".btn-submit").click(function() {
		if ($('input[id=uploadFile]').val() == ""){
			addNotification('btn-danger','请上传文件',true);
			return;
		}
		var formData = new FormData();
		formData.append("file", $('#uploadFile')[0].files[0]);
		addNotification('btn-info','文件上传中',false);
		$.ajax({
			url : $(this).parent().attr("action"),
			type : "POST",
			data : formData,
			contentType : false,
			processData : false,
			dataType : 'JSON',
			success : function(result) {
				if(result.status == 200){
					addNotification('btn-success','上传成功',true);
					$('#fileCover').val("");
				}
			}
		})
	});
});


function addNotification(btnStyle,message,removed){
	$('.div-alert').html("");
	var notification = '<button class="btn ' + btnStyle + '" type="button">' + message + '</button>';
	$('.div-alert').html(notification);
	if(removed){
		setTimeout(function() {
			$('.div-alert').html("");
		}, 2000);
	}
}