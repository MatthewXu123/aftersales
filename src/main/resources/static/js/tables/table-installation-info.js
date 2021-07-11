$(function() {
	var oTable = new TableInit();
	oTable.Init();
	$("#btn_delete").click(function() {
		if (confirm("确认要删除安装信息以及相应issue和维修记录吗？")) {
			var ids = "";
			$("input[name='btSelectItem']:checked").each(function() {
				ids += $(this).parents("tr").attr("data-uniqueid") + ",";
			})
			$.ajax({
				type : "post",
				url : "/aftersales/dm_installationinfo/delete",
				data : {
					"ids": ids,
				},
				dataType : 'JSON',
				success : function(data, status) {
					if (status == "success") {
						alert('提交数据成功');
						$("#table").bootstrapTable('refresh');
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
				field : 'product.serialNumber',
				title : '序列号',
				sortable:true
			},
			{
				field : 'product.productCode',
				title : '产品代码'
			}, 
			{
				field : 'installerName',
				title : '安装人员',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'installerPhone',
				title : '安装人员电话',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'province',
				title : '省份',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'city',
				title : '城市',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'district',
				title : '区',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'street',
				title : '街道',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'address',
				title : '地址',
				sortable:true,
				editable : {
					type : 'text',
					mode: "inline",  
					emptytext:'暂无',
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			}, 
			{
				field : 'comment',
				title : '说明',
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
			}
			
			],
			onEditableSave:function(field, row, oldValue, $el) {
				$.ajax({
					type : "post",
					url : "/aftersales/dm_installationinfo/save",
					data: JSON.stringify(row),
					contentType: 'application/json;charset=utf-8',
					dataType : 'JSON',
					success : function(data, status) {
						if (status == "success") {
							alert('操作成功');
							$("#table").bootstrapTable('refresh');
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