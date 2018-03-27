<div style="padding: 10px 10px 10px 10px;">
    <#include "../common/TableFiles.ftl"/>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <nav class="navbar navbar-default nofollow" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">切换导航</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a rel="nofollow" class="navbar-brand" href="#">导航</a>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <a rel="nofollow" href="#">链接</a>
                            </li>
                            <li>
                                <a rel="nofollow" href="#">链接</a>
                            </li>
                            <li class="dropdown">
                                <a rel="nofollow" href="#" class="dropdown-toggle" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a rel="nofollow" href="#">列表一</a>
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">列表二</a>
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">列表三</a>
                                    </li>
                                    <li class="divider">
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">更多列表</a>
                                    </li>
                                    <li class="divider">
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">更多列表</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" />
                            </div> <button type="submit" class="btn btn-default">搜索</button>
                        </form>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a rel="nofollow" href="#">链接</a>
                            </li>
                            <li class="dropdown">
                                <a rel="nofollow" href="#" class="dropdown-toggle" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a rel="nofollow" href="#">列表一</a>
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">列表二</a>
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">列表三</a>
                                    </li>
                                    <li class="divider">
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">更多列表</a>
                                    </li>
                                    <li class="divider">
                                    </li>
                                    <li>
                                        <a rel="nofollow" href="#">更多列表</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                </nav>
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