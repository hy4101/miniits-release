<div style="padding: 10px 10px 10px 10px">
    <#include "../common/TableFiles.ftl"/>
    <div class="row">
        <div class="col-lg-4">
            <div class="input-group">
                <span class="input-group-addon">链接名称：</span>
                <input type="text" class="form-control" placeholder="请输入链接名称" id="linkName"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-lg-4">
            <div class="input-group">
                <span class="input-group-addon"> URL ：</span>
                <input type="text" class="form-control" placeholder="请输入链接" id="url"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-lg-4">
            <button id="btn_link_search" type="button" class="btn"
                    style="background-color: #27AE60;color: #fff;width: 80px;margin-right: 20px">
                <i class="fa fa-search" aria-hidden="true"></i>
            </button>
            <button id="btn_refresh" type="button" class="btn" style="background-color: #ff754e;color: #fff;">
                <i class="fa fa-refresh" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <div id="links_toolbar" class="btn-group">
        <button id="btn_add_link_click" type="button" class="btn"
                style="background-color: #3c8dbc;color: #fff;">
            <i class="fa fa-plus" aria-hidden="true"></i>
        </button>
    </div>
    <table id="table_links" style="background-color: #FFFFFF"></table>
</div>
<script>
    (function ($, win) {

        function initLinks() {
            searchLinks();
        }

        $("#btn_link_search").click(function () {
            $('#table_links').bootstrapTable('refresh');
        });

        $("#btn_refresh").click(function () {
            $("#linkName").val(null);
            $("#url").val(null);
        });

        $("#btn_add_link_click").click(function () {
            var linkName = $("#linkName").val();
            var url = $("#url").val();
            if (isEmpty(linkName)) {
                return toastr.error('请输入链接名称');
            }
            if (isEmpty(url)) {
                return toastr.error('请输入链接URL');
            }
            $.ajax({
                type: 'post',
                url: '../links',
                data: {
                    linkName: linkName,
                    url: url
                },
                datatype: 'json',
                success: function (data) {
                    toastr.success('链接添加成功');
                    $('#table_links').bootstrapTable('refresh');
                },
                error: function (data) {
                    toastr.success(data.message);
                }
            });
        });

        function searchLinks() {
            $('#table_links').bootstrapTable({
                toolbar: '#links_toolbar',
                pagination: true,
                queryParams: function (params) {
                    var filters = '';
                    var linkName = $("#linkName").val();
                    var url = $("#url").val();
                    if (!isEmpty(linkName)) {
                        filters = 'LIKE_linkName=' + linkName;
                    }
                    if (!isEmpty(url)) {
                        filters += ';LIKE_url=' + url;
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
                url: "../links",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'linkName',
                    title: '链接名称'
                }, {
                    field: 'url',
                    title: '链接URL'
                }, {
                    field: 'createDate',
                    title: '添加时间'
                }, {
                    field: 'statusName',
                    title: '状态'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 200,
                    events: operateEvents,
                    formatter: operateFormatter
                }]
            });
        }


        function operateFormatter(value, row, index) {
            var editBtns = [
                '<button type="button" class="link-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
            ];
            var statusBtn = '<button type="button" class="link-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">禁用</button>';
            if (row.status === 100002002) {
                statusBtn = '<button type="button" class="link-status-enable btn btn-info btn-sm" style="margin-right:15px;">启用</button>';
            }
            editBtns.push(statusBtn);
            return editBtns.join('');
        }

        win.operateEvents = {
            'click .link-delete': function (e, value, row, index) {
                deleteLink(row);
            },
            'click .link-status-disabled': function (e, value, row, index) {
                changeStatus(row, 100002002, '【 禁用 】 成功');
            },
            'click .link-status-enable': function (e, value, row, index) {
                changeStatus(row, 100002001, '【 启用 】 成功');
            }
        };

        function deleteLink(row) {
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.linkName + "</b> ？",
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
                        $('#table_links').bootstrapTable('refresh');
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
                    $('#table_links').bootstrapTable('refresh');
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

        initLinks();

    })(jQuery, window)
</script>