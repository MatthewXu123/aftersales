$(function() {
	var oTable = new TableInit();
	oTable.Init();
	$("#btn_delete").click(function() {
		if (confirm("确认要删除吗？")) {
			var ids = "";
			$("input[name='btSelectItem']:checked").each(function() {
				ids += $(this).parents("tr").attr("data-uniqueid") + ",";
			})
			$.ajax({
				type : "post",
				url : "/watchDog/dsites/delete",
				data : {
					"ids": ids,
				},
				dataType : 'JSON',
				success : function(data, status) {
					if (status == "success") {
						alert('提交数据成功');
					}
				},
				error : function() {
					alert('编辑失败');
				},
				complete : function() {

				}

			});

		}
	});
});
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#table').bootstrapTable({
			url : '/aftersales/dm_installationinfo/list', // 请求后台的URL（*）
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			editable: true,
			method : 'get', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100], // 可供选择的每页的行数（*）
			search : true,// 是否显示表格搜索，此搜索是客户端搜索,也可以是服务端检索
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			clickToSelect : true, // 是否启用点击选中行
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			showExport: true,                     //是否显示导出
            exportDataType: "basic",              //basic', 'all', 'selected'.
			columns : [ 
			{
				checkbox : true
			}, 
			{
				field : 'pk.productCode',
				title : '产品料号',
				sortable:true
			},
			{
				field : 'pk.partCode',
				title : '备件料号'
			}, 
			{
				field : 'description',
				title : '说明',
				sortable:true,
				editable : {
					type : 'text',
					title : '说明',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'alternativeDescription',
				title : '备用说明',
				sortable:true,
				editable : {
					type : 'text',
					title : '备用说明',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'requiredNum',
				title : '1台所需数量',
				sortable:true,
				editable : {
					type : 'number',
					title : '1台所需数量',
					mode: "inline",  
					emptytext:'暂无',
					validate: function (v) {
						if (isNaN(v)) return '数量必须是数字';
						var age = parseInt(v);
						if (age <= 0) return '数量必须是正整数';
					}
				}
			}
			
			],
			onEditableSave:function(field, row, oldValue, $el) {
				// 可进行异步操作
				$.ajax({
					type : "post",
					url : "/aftersales/sparepart/update",
//					data : {
//						"sparePart": JSON.stringify(row),
//					},
					data: JSON.stringify(row),
					contentType: 'application/json;charset=utf-8',
					//contentType : 'application/json',
					dataType : 'JSON',
					success : function(data, status) {
						if (status == "success") {
							alert('操作成功');
						}
					},
					error : function() {
						alert('编辑失败');
					},
					complete : function() {

					}

				});
			}

		});
	};

	return oTableInit;
};


$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};