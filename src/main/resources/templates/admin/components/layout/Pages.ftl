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
    <#include "../common/TableFiles.ftl"/>
    <div style="width: 20%;flex: 5">
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
                    <select class="form-control input-form-group-value-item" id="searchPageStatusName"
                            name="pageStatus">
                        <option value=""></option>
                        <option value="100000001">启用</option>
                        <option value="100000002">禁用</option>
                    </select>
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
    <div style="width: 79%;padding-left: 10px;flex: 7">
        <div id="page_component_toolbar" class="btn-group">
            <button class="btn" style="background-color: #ffffff;color: red;">
                提示：创建页面系统会自动生成三个默认的组件，分别为 "页面名称_title、_body、_footer"。
                排序会影响页面的组件布局，排序规则为在父组件内按升序排列
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
                    <select class="form-control input-form-group-value-item" id="componentStatus"
                            name="componentStatus">
                        <option value=""></option>
                        <option value="100000001">启用</option>
                        <option value="100000002">禁用</option>
                    </select>
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
        <#include "PageSettingDialog.ftl"/>
    </div>
</div>
<script>
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
                $('#table_pages').bootstrapTable('refresh');
            });

            $('#btn_page_component_search').click(function () {
                $('#table_page_components').bootstrapTable('refresh');
            });
        }

        $("#table_pages").on('click-row.bs.table', function (e, row, element) {
            $('.success').removeClass('success');//去除之前选中的行的，选中样式
            $(element).addClass('success');//添加当前选中的 success样式用于区别
        });

        $('#btn_refresh').click(function () {
            $("#searchPageName").val(null);
            $("#searchPageStatusName").val(null)
        });

        $('#btn_page_components_refresh').click(function () {
            $("#componentName").val(null);
            $("#componentStatus").val(null)
        });

        function deletePage(row) {
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.pageName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                var param = {
                    method: 'delete', url: 'delete/' + row.id,
                    sessionId: 'change-status', message: '删除成功'
                };
                httpClient(param);
            });
        }

        function revisionSort(row, type) {
            console.log(row);
            var param = {
                method: 'post', url: '../page-component-associate/revision-sort/' + row.id + '/' + type,
                sessionId: 'change-status-component'
            };
            httpClient(param);
        }

        function deletePageComponent(row) {
            console.log(row);
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.componentImageVO.componentName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                var param = {
                    method: 'delete', url: '../page-component-associate/delete/' + row.id,
                    sessionId: 'change-status-component', message: '删除成功'
                };
                httpClient(param);
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
                    var pageStatus = $("#searchPageStatusName").val();
                    if (!isEmpty(searchPageName)) {
                        filters = 'LIKE_pageName=' + searchPageName;
                    }
                    if (!isEmpty(pageStatus)) {
                        filters += ';EQ_pageStatus=' + pageStatus;
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
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 260,
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
                detailView: true,
                onExpandRow: function (index, row, $detail) {
                    InitSubTable(index, row, $detail);
                },
                queryParams: function (params) {
                    var filters = basePageComponentAssociateFtilers + ';EQ_level=1';
                    var componentName = $("#componentName").val();
                    var componentStatus = $("#componentStatus").val();
                    if (!isEmpty(componentName)) {
                        filters += ';LIKE_componentImage.componentName=' + componentName;
                    }
                    if (!isEmpty(componentStatus)) {
                        filters += ';EQ_componentImage.componentStatus=' + componentStatus;
                    }
                    var temp = {
                        pageSize: params.limit,                         //页面大小
                        pageNumber: (params.offset / params.limit) + 1,   //页码
                        sorts: '+sorts',      //排序列名
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
                contentType: "application/x-www-form-urlencoded",
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'sorts',
                    title: '排序',
                    width: 30
                }, {
                    field: 'componentImageVO.componentSourceName',
                    title: '组件类型',
                    width: 30
                }, {
                    field: 'componentImageVO.componentName',
                    title: '组件名'
                    // formatter: function (value, row, index) {
                    //     return value + '<i class="select-component-content" style="color: #49a982;float: right;" >查看</i>';
                    // }
                }, {
                    field: 'componentImageVO.componentBodyApi',
                    title: '组件API'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 270,
                    events: operateEventsComponent,
                    formatter: operateFormatterComponent
                }]
            });
        }

        //初始化子表格(无线循环)
        function InitSubTable(index, row, $detail) {
            var parentId = row.componentImageVO.id;
            var cur_table = $detail.html('<table></table>').find('table');
            $(cur_table).bootstrapTable({
                sidePagination: 'server',//指定服务器端分页
                contentType: "application/x-www-form-urlencoded",
                method: 'get',
                url: "../page-component-associate",//要请求数据的文件路径
                queryParams: function (params) {
                    var temp = {
                        pageSize: params.limit,                         //页面大小
                        pageNumber: (params.offset / params.limit) + 1,   //页码
                        sorts: '+sorts',      //排序列名
                        filters: 'EQ_componentImagePId.id=' + parentId //排位命令（desc，asc）
                    };
                    return temp;
                },
                clickToSelect: true,
                pagination: true,
                detailView: true,//父子表
                uniqueId: "id",
                pageSize: 10,
                pageList: [10, 25],
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'sorts',
                    title: '排序',
                    width: 30
                }, {
                    field: 'componentImageVO.componentSourceName',
                    title: '组件类型',
                    width: 30
                }, {
                    field: 'componentImageVO.componentName',
                    title: '组件名'
                }, {
                    field: 'componentImageVO.componentBodyApi',
                    title: '组件API'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 270,
                    events: operateEventsComponent,
                    formatter: operateFormatterComponent
                }],
                //无线循环取子表，直到子表里面没有记录
                onExpandRow: function (index, row, $Subdetail) {
                    InitSubTable(index, row, $Subdetail);
                }
            });
        };

        function operateFormatter(value, row, index) {
            var editBtns = [
                '<button type="button" class="page-delete btn btn-delete btn-sm" style="margin-right:10px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
                '<button type="button" class="page-edit btn btn-primary btn-sm" style="margin-right:10px;"><i class="fa fa-pencil" aria-hidden="true"></i></button>',
                '<button type="button" class="page-setting btn btn-primary btn-sm" style="margin-right:10px;"><i class="fa fa-cogs" aria-hidden="true"></i></button>'
            ];
            var statusBtn = '<button type="button" class="page-status-disabled btn btn-warning btn-sm" style="margin-right:10px;">禁用</button>';
            if (row.pageStatus === 100000002) {
                statusBtn = '<button type="button" class="page-status-enable btn btn-info btn-sm" style="margin-right:10px;">启用</button>';
            }

            var createHTMLBtn = '<button type="button" class="page-create-html btn btn-sm" style="margin-right:10px;background-color: #27AE60;">生成HTML</button>';
            if (row.createStaticFile === 100000001) {
                createHTMLBtn = '<button type="button" class="page-create-html btn btn-warning btn-sm" style="margin-right:10px;">取消静态</button>';
            }

            editBtns.push(statusBtn);
            editBtns.push(createHTMLBtn);
            return editBtns.join('');
        }

        function operateFormatterComponent(value, row, index) {
            var options = $('#table_page_components').bootstrapTable('getOptions');
            var rowNumber = options.totalRows - 1;

            var editBtns = [];
            var deleteBtn = '<button type="button" class="page-component-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>';
            if (row.componentImageVO.componentSource === 100006002) {
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
            var createHTMLBtn = '<button type="button" class="page-component-add-btn btn btn-sm" style="margin-right:15px;background-color: #5cb85c;color: #ffffff">添加组件</button>';
            editBtns.push(statusBtn);
            editBtns.push(createHTMLBtn);
            return editBtns.join('');
        }

        win.operateEvents = {
            'click .page-delete': function (e, value, row, index) {
                deletePage(row);
            },
            'click .page-edit': function (e, value, row, index) {
                $("#new_page_title").text("修改页面信息");
                $('#page_modal').modal();
                win.commitPage(row);
            },
            'click .page-setting': function (e, value, row, index) {
                $("#page_setting_title").text("页面设置");
                $('#page_setting_modal').modal();
                win.commitPageSetting(row);
            },
            'click .page-status-disabled': function (e, value, row, index) {
                changeStatus(row, 100000002, '【 禁用 】 成功');
            },
            'click .page-status-enable': function (e, value, row, index) {
                changeStatus(row, 100000001, '【 启用 】 成功');
            },
            'click .page-create-html': function (e, value, row, index) {
                createHtmlFile(row);
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
                };
                win.commitAddComponents(data);
            },

            'click .page-component-down': function (e, value, row, index) {
                revisionSort(row, 'down');
            },
            'click .page-component-up': function (e, value, row, index) {
                revisionSort(row, 'up');
            }
        };

        function createHtmlFile(row) {
            var param = {
                method: 'post', url: 'setting-page-create-html',
                sessionId: 'change-status', message: '成功',
                data: {
                    id: row.id,
                    file_name: row.pageName,
                    create_static_file: (isEmpty(row.createStaticFile) || row.createStaticFile == 100000001) ? 100000002 : 100000001
                }
            };
            httpClient(param);
        }

        function changeStatus(row, status, message) {
            var param = {
                method: 'post',
                url: 'change/status',
                data: {id: row.id, page_name: row.pageName, status: status},
                sessionId: 'change-status'
            };
            httpClient(param);
        }

        function changeStatusComponent(row, status, message) {
            var param = {
                method: 'post',
                url: '../componentImages/change/status',
                data: {id: row.componentImageVO.id, status: status},
                sessionId: 'change-status-component'
            };
            httpClient(param);
        }

        pageInit();
        win.refreshPageComponent = function (data) {
            if (data.type === 'success') {
                toastr.success(data.message);
            } else {
                toastr.error(data.message);
            }
            $('#table_page_components').bootstrapTable('refresh');
        };
        win.httpClientSuccess = function (data) {
            switch (data.sessionId) {
                case 'change-status-component':
                    $('#table_page_components').bootstrapTable('refresh');
                    break;
                case 'change-status':
                    $('#table_pages').bootstrapTable('refresh');
                    break;
                case 'page-save':
                    if (data.data.success) {
                        $('#page_modal').modal('hide');
                    }
                    $('#table_pages').bootstrapTable('refresh');
                    break;
            }
            toastr.success(data.data.message);
        };
    })(jQuery, window)
</script>