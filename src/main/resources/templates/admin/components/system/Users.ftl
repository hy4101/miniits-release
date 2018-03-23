<link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/table/bootstrap-table.css">
<script src="/static/bootstrap-3.3.7-dist/table/bootstrap-table.js"></script>
<script src="/static/bootstrap-3.3.7-dist/table/bootstrap-table-zh-CN.js"></script>
<link href="/static/toastr/toastr.css" rel="stylesheet"/>
<script src="/static/toastr/toastr.min.js"></script>
<div style="padding: 10px 10px 10px 10px">
    <div>
        <div id="toolbar" class="btn-group">
            <button id="btn_add_user_click" type="button" class="btn" style="background-color: #3c8dbc;color: #fff;">
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
    <div class="user-dialog">
        <#include "UserCreateOrModifyDialog.ftl"/>
    </div>
</div>
<script>
    toastr.options.positionClass = 'toast-top-center';
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

        function operateFormatter(value, row, index) {
            var editBtns = [
                '<button type="button" class="user-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
                '<button type="button" class="user-edit btn btn-primary btn-sm" style="margin-right:15px;"><i class="fa fa-pencil" aria-hidden="true"></i></button>'
            ];
            var statusBtn = '<button type="button" class="user-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">禁用</button>';
            if (row.userStatusCode === 100000002) {
                statusBtn = '<button type="button" class="user-status-enable btn btn-info btn-sm" style="margin-right:15px;">启用</button>';
            }

            editBtns.push(statusBtn);
            return editBtns.join('');
        }

        win.operateEvents = {
            'click .user-delete': function (e, value, row, index) {
                deleteUser(row);
            },
            'click .user-edit': function (e, value, row, index) {
                $("#new_user_title").text("修改用户信息");
                $('#user_modal').modal();
                win.commitUser(row);
            },
            'click .user-status-disabled': function (e, value, row, index) {
                changeStatus(row, 100000002, '【 禁用 】 成功');
            },
            'click .user-status-enable': function (e, value, row, index) {
                changeStatus(row, 100000001, '【 启用 】 成功');
            }
        };

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
                    searchUsersByFilters();
                },
                error: function (data) {
                    console.log(data)
                }
            });
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
                        toastr.warning(row.userName + ' 删除成功！');
                        searchUsersByFilters();
                    },
                    error: function (data) {
                        console.log(data)
                    }
                });
            });
        }

        $("#btn_add_user_click").click(function () {
            $("#new_user_title").text("创建新用户");
            $('#user_modal').modal();
            win.commitUser(null);
        });
        userInit();
        //对外曝光刷新方法
        win.refreshUser = function (data) {
            if (data.type === 'success') {
                toastr.success(data.message);
            } else {
                toastr.error(data.message);
            }
            return searchUsersByFilters();
        };
    })(jQuery, window);

</script>
