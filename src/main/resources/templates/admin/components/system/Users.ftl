<link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/table/bootstrap-table.css">
<script src="/static/bootstrap-3.3.7-dist/table/bootstrap-table.js"></script>
<script src="/static/bootstrap-3.3.7-dist/table/bootstrap-table-zh-CN.js"></script>
<div style="padding: 10px 10px 10px 10px">
    <div>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn" style="background-color: #3c8dbc;color: #fff;">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="input-group">
                    <span class="input-group-addon">用户名：</span>
                    <input type="text" class="form-control" placeholder="请输入用户名" id="userName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <span class="input-group-addon">用户状态：</span>
                    <input type="text" class="form-control" placeholder="请输入用户状态" id="userStatusName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;width: 80px;margin-right: 20px">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                <button id="btn_refresh" type="button" class="btn" style="background-color: #ff754e;color: #fff;">
                    <i class="fa fa-refresh" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <table id="table_users" style="background-color: #FFFFFF"></table>
        <div>
            <nav aria-label="...">
                <ul class="pagination pagination-sm">...</ul>
            </nav>
        </div>
    </div>
</div>
<script>

    (function ($, win) {
        function userInit() {
            searchUsers();
            searchUsersByFilters();
        }

        function searchUsers() {
            $('#table_users').bootstrapTable({
                toolbar: '#toolbar',
                pagination: true,
                queryParams: function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var filters = '';
                    if (!isEmpty($("#userName").val())) {
                        filters = 'LIKE_userName=' + $("#userName").val();
                    }
                    if (!isEmpty($("#userStatusName").val())) {
                        filters = filters += 'LIKE_userStatusName=' + $("#userStatusName").val();
                    }
                    var temp = {
                        pageSize: params.limit,                         //页面大小
                        pageNumber: (params.offset / params.limit) + 1,   //页码
                        sort: '-createDate',      //排序列名
                        filters: filters //排位命令（desc，asc）
                    };
                    return temp;
                },
                sidePagination: 'server',//指定服务器端分页
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 15,                       //每页的记录行数（*）
                pageList: [15, 30, 50, 100],        //可供选择的每页的行数（*）
                search: true,
                method: 'get',
                url: "../admin/users",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'userName',
                    title: '用户名'
                }, {
                    field: 'userStatusName',
                    title: '用户状态'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 200,
                    events: operateEvents,
                    formatter: operateFormatter
                }],
                data: [{
                    id: 1,
                    userStatusName: 'Item 1',
                    userName: '$1'
                }, {
                    id: 2,
                    userStatusName: 'Item 2',
                    userName: '$2'
                }],
                onClickRow: function (row, $element) {
                }
            });
        }

        //查询按钮事件
        $('#btn_search').click(function () {
            searchUsersByFilters();
        });
        //查询按钮事件
        $('#btn_refresh').click(function () {
            $("#userName").val(null);
            $("#userStatusName").val(null)
        });

        function searchUsersByFilters() {
            $('#table_users').bootstrapTable('refresh', {url: '../users'});
        }

        //请求服务数据时所传参数
        function queryParams(params) {
            return {
                //每页多少条数据
                pageSize: 1,
                //请求第几页
                pageNumber: 2
            }
        }

        win.operateEvents = {
            'click .RoleOfdelete': function (e, value, row, index) {
                deleteUser(row);
            },
            'click .RoleOfedit': function (e, value, row, index) {
                $("#editModal").modal('show');
            }
        };

        function operateFormatter(value, row, index) {
            return [
                '<button type="button" class="RoleOfdelete btn btn-delete  btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',

                '<button type="button" class="RoleOfedit btn btn-warning  btn-sm" style="margin-right:15px;"><i class="fa fa-pencil" aria-hidden="true"></i></button>'
            ].join('');
        }

        function isEmpty(str) {
            if (str === '' || str == null || str === undefined) {
                return true;
            }
            if (str instanceof Array) {
                if (str == null || str.length <= 0) {
                    return true;
                }
            }
            return false;
        };

        function deleteUser(row) {
            Ewin.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.userName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: 'delete',
                    url: 'delete/' + row.id,
                    datatype: 'json',
                    success: function (data) {
                        debugger;
                        searchUsersByFilters();
                    },
                });
            });
        }

        userInit();
    })(jQuery, window);
</script>
