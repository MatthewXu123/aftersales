$(function(){
	resizeFontSize();
	$(window).resize(function(){
		resizeFontSize();
    })
//    $(".icon-return").click(function(){
//        window.history.go(-1);
//    })
    $(".form-notblank").blur(function(){
    	var val = $(this).val();
    	if(val == "" || val.length == 0){
    		$(this).addClass("border-red");
    	}else{
    		$(this).removeClass("border-red");
    	}
    })
})

function resizeFontSize(){
	var html = document.documentElement;
	var clientWidth = html.getBoundingClientRect().width;
	html.style.fontSize = 12 * (clientWidth / 750) + 'px';// 1rem = 12px
}
function isNotBlank(str){
	return str != undefined && str != null && str.length != 0;
}
function encrypt(data) {
    var key  = CryptoJS.enc.Utf8.parse('1234567890000000');
    var iv   = CryptoJS.enc.Utf8.parse('1234567890000000');
    return CryptoJS.AES.encrypt(data, key, {iv:iv,mode:CryptoJS.mode.CBC,padding:CryptoJS.pad.ZeroPadding}).toString();
}
function photoUpload(){
	$('.input-photo').change(
		function() {
			var filePath = $(this).val();
			fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
			src = window.URL.createObjectURL(this.files[0]);
			if (!fileFormat.match(/.png|.jpg|.jpeg/)) {
				alert('png/jpg/jpeg');
				return;
			}
			var img = $(this).prev();
			img.attr('src', src);
			img.css('width', '100%');
			img.css('height', '100%');
		});
}
function photoDeleteInit() {
	$(".img-delete").css("display","none");
	$(".img-delete").click(function(){
		var $imgp = $(this).next(".img-plus");
		var id = $imgp.attr("id");
		$(this).hide();
		$imgp.attr("src","/aftersales/static/icon/plus-icon.svg");
		$imgp.css("width","3.5rem");
	});
	$(".img-plus").each(function(){
		var $imgp = $(this);
		$imgp.on({
		touchstart : function(e){
			timeOutEvent = setTimeout(function(){
				timeOutEvent = 0;
			}, 400);
		},
		touchmove : function(){
			clearTimeout(timeOutEvent);
			timeOutEvent = 0;
		},
		touchend : function(ev){
			clearTimeout(timeOutEvent);
			var target = ev.target.className;
			if (timeOutEvent != 0) {
				if(target == "img-delete"){
					$imgp.siblings(".img-delete").click();
				}
				else{
					if($imgp.attr("src").indexOf("plus-icon") != -1)
						$imgp.siblings(".input-photo").click();
				}
			}else{
				$imgp.siblings(".img-delete").show();
			}
			return false;
		}
	})
	})
}
function photoVal(){
	$(".img-plus").each(function(){
		var $imgp = $(this);
		var $input = $imgp.next(".input-photo");
		var val = $input.val();
		if($imgp.attr("src").indexOf("plus-icon") != -1 
				&& ($input.val() == undefined || $input.val() == null || $input.val() == "")){
			$imgp.siblings(".input-photoval").val(1);
		}else{
			$imgp.siblings(".input-photoval").val(0);
		}
			
	})
}
/**
 * 
 * @param {*} tabIdArray For example, ['div-subtab-maintenance','div-subtab-process']
 * @param {*} contentIdArray For example, ['div-main-maintenance','div-main-process']
 * @param {*} tabStyle 
 */
function tabSwitch(tabIdArray, contentIdArray, tabStyle){
    // ['div-subtab-maintenance','div-subtab-process']
    for(var i = 0; i < tabIdArray.length; i++){
        // ['div','tab']
        var tabIdArraySplit = tabIdArray[i].split("-");
        // 'div-tab'
        var tabClass = tabIdArraySplit[0] + "-" + tabIdArraySplit[1];
         // '.div-tab'
        var tabClassSelector = "." + tabClass;
        $(tabClassSelector).click(function(){
            $(tabClassSelector).each(function(){
                $(this).removeClass(tabStyle);
            })
            $(this).addClass(tabStyle);
            var idSuffix = $(this).attr("id").split("-")[2];
            // ['div-main-maintenance','div-main-process']
            for(var j = 0; j < contentIdArray.length; j++){
                // ['div','main','maintenance']
                var contentIdArraySplit = contentIdArray[j].split("-");
                // '#div-main-maintenance'
                var otherContentIdSelector = "#" +contentIdArray[j];
                // '#div-main-maintenance'
                var contentIdSelector = "#" + contentIdArraySplit[0] + "-" + contentIdArraySplit[1] + "-" + idSuffix;

                if(otherContentIdSelector == contentIdSelector){
                    $(otherContentIdSelector).css("display", "");
                }else{
                    $(otherContentIdSelector).css("display", "none");
                }

            }
        })
    }
}