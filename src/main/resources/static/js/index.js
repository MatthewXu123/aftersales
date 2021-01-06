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
	//$('#side-menu').metisMenu();
    // 打开右侧边栏
    $('.right-sidebar-toggle').click(function () {
        $('#right-sidebar').toggleClass('sidebar-open');
    });
    // 菜单切换
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });
    // 侧边栏高度
    function fix_height() {
        var heightWithoutNavbar = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
    }
    fix_height();

    $(window).bind("load resize click scroll", function () {
        if (!$("body").hasClass('body-small')) {
            fix_height();
        }
    });

    //侧边栏滚动
    $(window).scroll(function () {
        if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
            $('#right-sidebar').addClass('sidebar-top');
        } else {
            $('#right-sidebar').removeClass('sidebar-top');
        }
    });
    $('#side-menu>li').click(function () {
        if ($('body').hasClass('mini-navbar')) {
            NavToggle();
        }
    });
    $('#side-menu>li li a').click(function () {
        if ($(window).width() < 769) {
            NavToggle();
        }
    });

    $('.nav-close').click(NavToggle);
    //ios浏览器兼容性处理
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        $('#content-main').css('overflow-y', 'auto');
    }
});

$(window).bind("load resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('mini-navbar');
        $('.navbar-static-side').fadeIn();
    }
});

function NavToggle() {
    $('.navbar-minimalize').trigger('click');
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 300);
    } else {
        $('#side-menu').removeAttr('style');
    }
}



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
//animation.css
function animationHover(element, animation) {
    element = $(element);
    element.hover(
        function () {
            element.addClass('animated ' + animation);
        },
        function () {
            //动画完成之前移除class
            window.setTimeout(function () {
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

