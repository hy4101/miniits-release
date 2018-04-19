<div style="padding: 10px 10px 10px 10px;">
    <#include "../common/TableFiles.ftl"/>
    <div class="row" style="margin-bottom: 10px">
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入名称" id="componentName">
            </div>
        </div>
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入状态" id="componentStatusName">
            </div>
        </div>
        <div class="col-lg-4">
            <button type="button" class="btn" id="btn_components_search"
                    style="background-color: #27AE60;color: #fff;">
                <i class="fa fa-search" aria-hidden="true"></i>
            </button>
            <button type="button" class="btn" style="background-color: #ff754e;color: #fff;" id="btn_components_refresh">
                <i class="fa fa-refresh" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <table id="table_components" style="background-color: #FFFFFF"></table>
</div>
<script>
    (function ($, win) {
        function initComponents() {
            searchComponents();
        }
        $("#btn_components_search").click(function () {
            $('#table_components').bootstrapTable('refresh');
        });

        $("#btn_components_refresh").click(function () {
            $("#componentName").val(null);
            $("#componentStatusName").val(null);
        });

        function searchComponents() {
            $('#table_components').bootstrapTable({
                pagination: true,
                queryParams: function (params) {
                    var filters = '';
                    var componentName = $("#componentName").val();
                    var componentStatusName = $("#componentStatusName").val();
                    if (!isEmpty(componentName)) {
                        filters = 'LIKE_componentName=' + componentName;
                    }
                    if (!isEmpty(componentStatusName)) {
                        filters += ';LIKE_componentStatusName=' + componentStatusName;
                    }
                    var temp = {
                        pageSize: params.limit,                         //页面大小
                        pageNumber: (params.offset / params.limit) + 1,   //页码
                        sorts: '-createDate',      //排序列名
                        filters: filters //排位命令（desc，asc）
                    };
                    return temp;
                },
                sidePagination: 'server',//指定服务器端分页
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 15,                       //每页的记录行数（*）
                pageList: [15, 30, 50, 100],        //可供选择的每页的行数（*）
                method: 'get',
                url: "../components",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'componentName',
                    title: '组件名称'
                }, {
                    field: 'componentId',
                    title: '组件ID'
                }, {
                    field: 'componentStatusName',
                    title: '组件状态'
                }, {
                    field: 'componentBodyApi',
                    title: '组件API'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 260,
                    events: operateEvents,
                    formatter: operateFormatter
                }]
            });
        }

        function operateFormatter(value, row, index) {
            var editBtns = [
                '<button type="button" class="components-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
                '<a href="modify-development/' + row.id + '"><button type="button" class="btn btn-primary btn-sm" style="margin-right:15px;"><i class="fa fa-pencil" aria-hidden="true"></i></button></a>'
            ];
            var statusBtn = '<button type="button" class="components-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">禁用</button>';
            if (row.componentStatus === 100000002) {
                statusBtn = '<button type="button" class="components-status-enable btn btn-info btn-sm" style="margin-right:15px;">启用</button>';
            }
            editBtns.push(statusBtn);
            return editBtns.join('');
        }

        win.operateEvents = {
            'click .components-delete': function (e, value, row, index) {
                deleteArticle(row);
            },
            'click .components-status-disabled': function (e, value, row, index) {
                changeStatus(row, 100000002, '【 禁用 】 成功<h4>提示:当该组件已被页面所应用时，你的禁用操作不影响页面的展示！</h4>');
            },
            'click .components-status-enable': function (e, value, row, index) {
                changeStatus(row, 100000001, '【 启用 】 成功');
            }
        };

        function deleteArticle(row) {
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.componentName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: 'delete',
                    url: row.id,
                    success: function (data) {
                        toastr.success(data.message);
                        $('#table_components').bootstrapTable('refresh');
                    },
                    error: function (data) {
                        console.log(data)
                    }
                });
            });
        }


        function changeStatus(row, status, message) {
            $.ajax({
                type: 'post',
                url: 'change/status',
                datatype: 'json',
                data: {
                    id: row.id,
                    status: status
                },
                success: function (data) {
                    toastr.success(message);
                    $('#table_components').bootstrapTable('refresh');
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

        initComponents();

    })(jQuery, window)
</script>