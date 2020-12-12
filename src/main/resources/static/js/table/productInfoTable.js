$(function() {
	var oTable = new TableInit();
	oTable.Init();
});
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#table').bootstrapTable({
			url : '/aftersales/productInfo/list', // 请求后台的URL（*）
			editable: true,
			method : 'get', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100], // 可供选择的每页的行数（*）
			search : true,// 是否显示表格搜索，此搜索是客户端搜索,也可以是服务端检索
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			clickToSelect : true, // 是否启用点击选中行
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			columns : [ 
			{
				field : 'description',
				title : '说明',
				sortable:true,
				editable : {
					type : 'text',
					emptytext:'暂无',
					mode: "inline",  
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			},
			{
				field : 'type',
				title : '类型',
				sortable:true,
				editable : {
					type : 'text',
					title : '暂无',
					emptytext:'',
					mode: "inline",  
					validate : function(v) {
						if (!v)
							return '不能为空';
					}
				}
			},
			
			],
			
			onEditableSave:function(field, row, oldValue, $el) {
				$.ajax({
					type : "post",
					url : "/aftersales/productInfo/update",
					data:JSON.stringify(row),
					contentType:"application/json",
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
	};
	return oTableInit;
};

