<div style="padding: 10px 10px 10px 10px;">
    <#include "../common/TableFiles.ftl"/>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="navbar">
                            <div class="navbar-inner">
                                <div class="container-fluid">
                                    <a class="btn btn-navbar" data-target=".navbar-responsive-collapse" data-toggle="collapse"></a> <a class="brand" href="http://www.miniits.com">Miniits</a>
                                    <div class="nav-collapse collapse navbar-responsive-collapse">
                                        <ul class="nav">
                                            <li class="active">
                                                <a href="#">主1页</a>
                                            </li>
                                            <li>
                                                <a href="#">链2接</a>
                                            </li>
                                            <li>
                                                <a href="#">链3接</a>
                                            </li>
                                            <li class="dropdown">
                                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">下拉4菜单</a>
                                                <ul class="dropdown-menu">
                                                    <li>
                                                        <a href="#">下拉1导航111</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">下拉导航223</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">其他213</a>
                                                    </li>
                                                    <li class="divider">
                                                    </li>
                                                    <li class="nav-header">
                                                        标签1
                                                    </li>
                                                    <li>
                                                        <a href="#">链接11</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">链接232</a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                        <ul class="nav pull-right">
                                            <li>
                                                <a href="#">右边链接</a>
                                            </li>
                                            <li class="divider-vertical">
                                            </li>
                                            <li class="dropdown">
                                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">下拉菜单</a>
                                                <ul class="dropdown-menu">
                                                    <li>
                                                        <a href="#">下拉导航1</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">下拉导航2</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">其他</a>
                                                    </li>
                                                    <li class="divider">
                                                    </li>
                                                    <li>
                                                        <a href="#">链接3</a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="components_toolbar" class="btn-group">
        <button type="button" class="btn"
                style="background-color: #3c8dbc;color: #fff;">
            <i class="fa fa-plus" aria-hidden="true"></i>
        </button>
    </div>
    <div class="row">
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入名称"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入状态"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-lg-4">
            <button type="button" class="btn"
                    style="background-color: #27AE60;color: #fff;">
                <i class="fa fa-search" aria-hidden="true"></i>
            </button>
            <button type="button" class="btn" style="background-color: #ff754e;color: #fff;">
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

        function searchComponents() {
            $('#table_components').bootstrapTable({
                toolbar: '#components_toolbar',
                pagination: true,
                // queryParams: function (params) {
                //     var filters = '';
                //     var searchPageName = $("#searchPageName").val();
                //     var searchPageStatusName = $("#searchPageStatusName").val();
                //     if (!isEmpty(searchPageName)) {
                //         filters = 'LIKE_pageName=' + searchPageName;
                //     }
                //     if (!isEmpty(searchPageStatusName)) {
                //         filters += ';LIKE_pageStatusName=' + searchPageStatusName;
                //     }
                //     var temp = {
                //         pageSize: params.limit,                         //页面大小
                //         pageNumber: (params.offset / params.limit) + 1,   //页码
                //         sorts: '-createDate',      //排序列名
                //         filters: filters //排位命令（desc，asc）
                //     };
                //     return temp;
                // },
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
                    title: '页面名称',
                    width: 100
                }, {
                    field: 'pagePath',
                    title: '页面路径'
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

        win.operateEvents = {
            'click .user-delete': function (e, value, row, index) {
                deletePage(row);
            },
            'click .user-edit': function (e, value, row, index) {
                $("#new_page_title").text("修改页面信息");
                $('#page_modal').modal();
                win.commitPage(row);
            },
            'click .user-status-disabled': function (e, value, row, index) {
                changeStatus(row, 100000002, '【 禁用 】 成功');
            },
            'click .user-status-enable': function (e, value, row, index) {
                changeStatus(row, 100000001, '【 启用 】 成功');
            }
        };

        function operateFormatter(value, row, index) {
            var options = $('#table_page_components').bootstrapTable('getOptions');
            var rowNumber = options.totalRows - 1;

            var editBtns = [];
            var deleteBtn = '<button type="button" class="page-component-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>';
            if (row.componentImageVO.componentType === 100006002) {
                editBtns.push(deleteBtn);
            }
            var revisionSortDownBtn = '<button type="button" class="page-component-down btn btn-default btn-sm" style="margin-right:15px;"><i class="fa fa-arrow-down" aria-hidden="true"></i></button>';
            var revisionSortUpBtn = '<button type="button" class="page-component-up btn btn-default btn-sm" style="margin-right:15px;"><i class="fa fa-arrow-up" aria-hidden="true"></i></button>';

            if (row.sorts !== 1 && index != 0) {
                editBtns.push(revisionSortUpBtn);
            }

            if (rowNumber != index) {
                editBtns.push(revisionSortDownBtn);
            }

            var statusBtn = '<button type="button" class="page-component-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">禁用</button>';
            if (row.componentImageVO.componentStatus === 100000002) {
                statusBtn = '<button type="button" class="page-component-status-enable btn btn-info btn-sm" style="margin-right:15px;">启用</button>';
            }
            var createHTMLBtn = '<button type="button" class="page-component-add-btn btn btn-warning btn-sm" style="margin-right:15px;">添加组件</button>';
            editBtns.push(statusBtn);
            editBtns.push(createHTMLBtn);
            return editBtns.join('');
        }

        initComponents();

    })(jQuery, window)
</script>