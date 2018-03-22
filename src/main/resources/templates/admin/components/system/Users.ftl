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
                    <span class="input-group-addon" id="basic-addon1">用户名：</span>
                    <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">用户状态：</span>
                    <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_search" type="button" class="btn" style="background-color: #3c8dbc;color: #fff;">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                <button id="btn_refresh" type="button" class="btn" style="background-color: #3c8dbc;color: #fff;">
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
    <script>

        (function ($, win) {
            function userInit() {
                searchUsers()
                searchUsersByFilters()
            }

            function searchUsers(filters) {
                $('#table_users').bootstrapTable({
                    toolbar: '#toolbar',
                    pagination: true,
                    queryParams: queryParams(),//请求服务器时所传的参数
                    sidePagination: 'server',//指定服务器端分页
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,                       //每页的记录行数（*）
                    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                    search: true,
                    method: 'get',
                    url: "../admin/users",//要请求数据的文件路径
                    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                    columns: [{
                        field: 'id',
                        visible: false
                    }, {
                        field: 'userStatusName',
                        title: '用户状态'
                    }, {
                        field: 'userName',
                        title: '用户名'
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

            function searchUsersByFilters() {
                $('#table_users').bootstrapTable('refresh', {url: '../users'});
            }

            //请求服务数据时所传参数
            function queryParams(params) {
                debugger;
                return {
                    //每页多少条数据
                    pageSize: params.limit,
                    //请求第几页
                    pageNumber: params.pageNumber
                }
            }

            win.operateEvents = {
                'click .RoleOfdelete': function (e, value, row, index) {
                    alert(row.id);
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

            userInit();


        })(jQuery, window);


    </script>

