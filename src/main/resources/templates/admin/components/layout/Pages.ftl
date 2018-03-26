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
    <div style="width: 20%;flex: 4">
        <div id="page_toolbar" class="btn-group">
            <button id="btn_add_show_page_dialog_click" type="button" class="btn"
                    style="background-color: #3c8dbc;color: #fff;">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
        </div>
        <div class="div-page-component-title">
            页面管理
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入名称" id="searchPageName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入状态" id="searchPageStatusName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                <button id="btn_refresh" type="button" class="btn" style="background-color: #ff754e;color: #fff;">
                    <i class="fa fa-refresh" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <table id="table_pages" style="background-color: #FFFFFF"></table>
    </div>
    <div style="width: 79%;padding-left: 10px;flex: 8">
        <div id="page_component_toolbar" class="btn-group">
            <button class="btn" style="background-color: #ffffff;color: red;">
                提示：创建页面系统会自动生成三个默认的组件，分别为 "文件名称_title", "文件名称_body", "文件名称_footer",
                不能删除。排序会影响页面的组件布局，排序规则为在父组件内按升序排列
            </button>
        </div>
        <div class="div-page-component-title">
            组件管理
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入名称" id="componentName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入状态" id="componentStatusName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_page_component_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;margin-right: 20px">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                <button id="btn_page_components_refresh" type="button" class="btn"
                        style="background-color: #ff754e;color: #fff;">
                    <i class="fa fa-refresh" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <table id="table_page_components" style="background-color: #FFFFFF"></table>
    </div>
    <div class="user-dialog">
        <#include "PageCreateOrModifyDialog.ftl"/>
        <#include "PageComponentsCreateOrModifyDialog.ftl"/>
    </div>
</div>
<script>
    toastr.options.positionClass = 'toast-top-center';
    (function ($, win) {

        var thisClickPage = null;

        var basePageComponentAssociateFtilers = '';

        function clicks() {
            $("#btn_add_show_page_dialog_click").click(function () {
                $("#new_page_title").text("创建新页面");
                $('#page_modal').modal();
                win.commitPage(null);
            });

            $('#btn_search').click(function () {
                searchPagesByFilters();
            });

            $('#btn_page_component_search').click(function () {
                $('#table_page_components').bootstrapTable('refresh');
            });
        }

        $('#btn_refresh').click(function () {
            $("#searchPageName").val(null);
            $("#searchPageStatusName").val(null)
        });

        $('#btn_page_components_refresh').click(function () {
            $("#componentName").val(null);
            $("#componentStatusName").val(null)
        });

        function deletePage(row) {
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.pageName + "</b> ？",
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
                        toastr.success('删除成功');
                        searchPagesByFilters()
                    },
                    error: function (data) {
                        console.log(data)
                    }
                });
            });
        }

        function deletePageComponent(row) {
            console.log(row);
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.pageName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: 'delete',
                    url: '../page-component-associate/delete/' + row.id,
                    datatype: 'json',
                    success: function (data) {
                        toastr.success('删除成功');
                        searchPagesByFilters()
                    },
                    error: function (data) {
                        console.log(data)
                    }
                });
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
                    var filters = '';
                    var searchPageName = $("#searchPageName").val();
                    var searchPageStatusName = $("#searchPageStatusName").val();
                    if (!isEmpty(searchPageName)) {
                        filters = 'LIKE_pageName=' + searchPageName;
                    }
                    if (!isEmpty(searchPageStatusName)) {
                        filters += ';LIKE_pageStatusName=' + searchPageStatusName;
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
                    field: 'pageStatusName',
                    title: '页面状态',
                    width: 60
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 200,
                    events: operateEvents,
                    formatter: operateFormatter
                }],
                onClickRow: function (row, $element) {
                    thisClickPage = row;
                    basePageComponentAssociateFtilers = 'EQ_page.id=' + row.id;
                    $('#table_page_components').bootstrapTable('refresh');
                },
                onCheck: function (rows) {
                    thisClickPage = rows;
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
                toolbar: '#page_component_toolbar',
                pagination: true,
                queryParams: function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var filters = basePageComponentAssociateFtilers;
                    var componentName = $("#componentName").val();
                    var componentStatusName = $("#componentStatusName").val();
                    if (!isEmpty(componentName)) {
                        filters += ';LIKE_componentImage.componentName=' + componentName;
                    }
                    if (!isEmpty(componentStatusName)) {
                        filters += ';LIKE_componentImage.componentStatusName=' + componentStatusName;
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
                url: "../page-component-associate",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'sorts',
                    title: '排序',
                    width: 30
                }, {
                    field: 'componentImagePIdVO.componentName',
                    title: '父组件',
                    width: 30
                }, {
                    field: 'componentImageVO.componentTypeName',
                    title: '组件类型',
                    width: 30
                }, {
                    field: 'componentImageVO.componentName',
                    title: '组件名'
                }, {
                    field: 'componentImageVO.componentStatusName',
                    title: '组件状态'
                }, {
                    field: 'componentImageVO.componentBodyApi',
                    title: '组件API'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 220,
                    events: operateEventsComponent,
                    formatter: operateFormatterComponent
                }],
                onClickRow: function (row, $element) {
                }
            });
        }

        function searchPagesByFilters() {
            $('#table_pages').bootstrapTable('refresh');
        }

        function operateFormatter(value, row, index) {
            var editBtns = [
                '<button type="button" class="user-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
                '<button type="button" class="user-edit btn btn-primary btn-sm" style="margin-right:15px;"><i class="fa fa-pencil" aria-hidden="true"></i></button>'
            ];
            var statusBtn = '<button type="button" class="user-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">禁用</button>';
            if (row.pageStatus === 100000002) {
                statusBtn = '<button type="button" class="user-status-enable btn btn-info btn-sm" style="margin-right:15px;">启用</button>';
            }
            var createHTMLBtn = '<button type="button" class="user-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">生成页面</button>';
            editBtns.push(statusBtn);
            editBtns.push(createHTMLBtn);
            return editBtns.join('');
        }

        function operateFormatterComponent(value, row, index) {
            var editBtns = [];
            var deleteBtn = '<button type="button" class="page-component-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>';
            if (row.componentImageVO.componentType === 100006002) {
                editBtns.push(deleteBtn);
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
        win.operateEventsComponent = {
            'click .page-component-delete': function (e, value, row, index) {
                deletePageComponent(row);
            },
            'click .page-component-status-disabled': function (e, value, row, index) {
                changeStatusComponent(row, 100000002, '【 禁用 】 成功');
            },
            'click .page-component-status-enable': function (e, value, row, index) {
                changeStatusComponent(row, 100000001, '【 启用 】 成功');
            },
            'click .page-component-add-btn': function (e, value, row, index) {
                $("#new_page_components_title").text("添加新组件");
                $('#page_components_modal').modal();
                var data = {
                    componentImagePId: row,
                    page: thisClickPage
                }
                win.commitAddComponents(data);
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
                    searchPagesByFilters();
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

        function changeStatusComponent(row, status, message) {
            $.ajax({
                type: 'post',
                url: '../componentImages/change/status',
                datatype: 'json',
                data: {
                    id: row.componentImageVO.id,
                    status: status
                },
                success: function (data) {
                    toastr.success(message);
                    $('#table_page_components').bootstrapTable('refresh');
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
        pageInit();
        win.refreshPage = function (data) {
            if (data.type === 'success') {
                toastr.success(data.message);
            } else {
                toastr.error(data.message);
            }
            searchPagesByFilters();
        };
        win.refreshPageComponent = function (data) {
            if (data.type === 'success') {
                toastr.success(data.message);
            } else {
                toastr.error(data.message);
            }
            $('#table_page_components').bootstrapTable('refresh');
        };
    })(jQuery, window)
</script>