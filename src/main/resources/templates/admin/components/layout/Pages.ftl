<#include "../common/TableFiles.ftl"/>
<style>
    .div-page-component-title {
        border: 1px solid #c8c8c8;
        line-height: 40px;
        background-color: #ffffff;
        padding-left: 10px;
        margin-bottom: 10px;
    }
</style>
<div style="padding: 10px 10px 10px 10px;display: flex">
    <div style="width: 20%;flex: 3">
        <div id="page_toolbar" class="btn-group">
            <button id="btn_add_show_page_dialog_click" type="button" class="btn" style="background-color: #3c8dbc;color: #fff;">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
        </div>
        <div class="div-page-component-title">
            页面管理
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入名称" id="userName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入状态" id="userStatusName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;margin-right: 20px">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <table id="table_pages" style="background-color: #FFFFFF"></table>
    </div>
    <div style="width: 79%;padding-left: 10px;flex: 9">
        <div id="component_toolbar" class="btn-group">
            <button id="btn_add_user_click" type="button" class="btn" style="background-color: #3c8dbc;color: #fff;">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
        </div>
        <div class="div-page-component-title">
            组件管理
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入名称" id="userName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入状态" id="userStatusName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;margin-right: 20px">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <table id="table_page_components" style="background-color: #FFFFFF"></table>
    </div>
    <div class="user-dialog">
        <#include "PageCreateOrModifyDialog.ftl"/>
    </div>
</div>
<script>
    (function ($, win) {

        var basePageComponentAssociateFtilers = '';

        function clicks() {
            $("#btn_add_show_page_dialog_click").click(function () {
                $("#new_page_title").text("创建新页面");
                $('#page_modal').modal();
                win.commitUser(null);
            });
        }

        function pageInit() {
            searchPages();
            clicks();
        }

        function searchPages() {
            $('#table_pages').bootstrapTable({
                toolbar: '#page_toolbar',
                pagination: true,
                queryParams: function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var filters = '';
                    return filters;
                },
                sidePagination: 'server',//指定服务器端分页
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 15,                       //每页的记录行数（*）
                pageList: [15, 30, 50, 100],        //可供选择的每页的行数（*）
                method: 'get',
                url: "../pages",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'pageName',
                    title: '页面名称'
                }, {
                    field: 'pagePath',
                    title: '页面路径'
                }, {
                    field: 'pageStatusName',
                    title: '页面状态'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 200,
                    events: operateEvents,
                    formatter: operateFormatter
                }],
                onClickRow: function (row, $element) {
                    basePageComponentAssociateFtilers = 'EQ_page.id=' + row.id;
                    $('#table_page_components').bootstrapTable('refresh');
                },
                onCheck: function (rows) {
                    basePageComponentAssociateFtilers = 'EQ_page.id=' + rows.id;
                    $('#table_page_components').bootstrapTable('refresh');
                    searchPageComponents();
                },
                onLoadSuccess: function (data) {
                    $('#table_pages').bootstrapTable('check', 0);
                }
            });
        }

        function searchPageComponents() {
            $('#table_page_components').bootstrapTable({
                toolbar: '#component_toolbar',
                pagination: true,
                queryParams: function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var filters = '';
                    var temp = {
                        pageSize: params.limit,                         //页面大小
                        pageNumber: (params.offset / params.limit) + 1,   //页码
                        sorts: '-createDate',      //排序列名
                        filters: basePageComponentAssociateFtilers //排位命令（desc，asc）
                    };
                    return temp;
                },
                sidePagination: 'server',//指定服务器端分页
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 15,                       //每页的记录行数（*）
                pageList: [15, 30, 50, 100],        //可供选择的每页的行数（*）
                method: 'get',
                url: "../page-component-associate",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'sorts',
                    title: '排序'
                }, {
                    field: 'componentVO.componentName',
                    title: '组件名'
                }, {
                    field: 'componentVO.componentStatusName',
                    title: '组件状态',
                }, {
                    field: 'componentVO.componentBodyApi',
                    title: '组件API',
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

        pageInit();
    })(jQuery, window)
</script>