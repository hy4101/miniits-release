<div style="padding: 10px 10px 10px 10px;display: flex">
    <#include "../common/TableFiles.ftl"/>
    <style>
        .div-articles-component-title {
            border: 1px solid #c8c8c8;
            line-height: 40px;
            background-color: #ffffff;
            padding-left: 10px;
            margin-bottom: 10px;
        }

    </style>
    <div style="flex: 7">
        <div class="div-articles-component-title">
            文章管理
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入名称" id="titleName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入状态" id="statusName"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-lg-4">
                <button id="btn_articles_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                <button id="btn_articles_refresh" type="button" class="btn"
                        style="background-color: #ff754e;color: #fff;">
                    <i class="fa fa-refresh" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <table id="table_articles" style="background-color: #FFFFFF"></table>
    </div>
<#--<div style="padding-left: 10px;flex: 5">-->
<#--<div class="div-articles-component-title">-->
<#--评论管理-->
<#--</div>-->
<#--<div class="row">-->
<#--<div class="col-lg-4">-->
<#--<div class="input-group">-->
<#--<input type="text" class="form-control" placeholder="请输入名称" id="titleName"-->
<#--aria-describedby="basic-addon1">-->
<#--</div>-->
<#--</div>-->
<#--<div class="col-lg-4">-->
<#--<div class="input-group">-->
<#--<input type="text" class="form-control" placeholder="请输入状态" id="statusName"-->
<#--aria-describedby="basic-addon1">-->
<#--</div>-->
<#--</div>-->
<#--<div class="col-lg-4">-->
<#--<button id="btn_articles_search" type="button" class="btn"-->
<#--style="background-color: #27AE60;color: #fff;">-->
<#--<i class="fa fa-search" aria-hidden="true"></i>-->
<#--</button>-->
<#--<button id="btn_articles_refresh" type="button" class="btn"-->
<#--style="background-color: #ff754e;color: #fff;">-->
<#--<i class="fa fa-refresh" aria-hidden="true"></i>-->
<#--</button>-->
<#--</div>-->
<#--</div>-->
<#--<table id="table_comments" style="background-color: #FFFFFF"></table>-->
<#--</div>-->
</div>
<script>
    (function ($, win) {

        var baseFilers = null;
        var thisClickRows = null;

        function articlesInit() {
            searchArticles();
            clicks();
        }

        function clicks() {
            $("#btn_articles_search").click(function () {
                $('#table_articles').bootstrapTable('refresh');
            });

            $("#btn_articles_refresh").click(function () {
                $("#titleName").val(null);
                $("#statusName").val(null);
            })

        }

        function searchArticles() {
            $('#table_articles').bootstrapTable({
                toolbar: '#articles_toolbar',
                pagination: true,
                queryParams: function (params) {
                    var filters = '';
                    var titleName = $("#titleName").val();
                    var statusName = $("#statusName").val();
                    if (!isEmpty(titleName)) {
                        filters = 'LIKE_titleName=' + titleName;
                    }
                    if (!isEmpty(statusName)) {
                        filters += ';LIKE_statusName=' + statusName;
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
                url: "${request.contextPath}/admin/contents/article",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'titleName',
                    title: '标题'
                }, {
                    field: 'statusName',
                    title: '状态'
                }, {
                    field: 'hits',
                    title: '访问量'
                }, {
                    field: 'sourceName',
                    title: '来源'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 260,
                    events: operateEvents,
                    formatter: operateFormatter
                }],
                onClickRow: function (row, $element) {
                    thisClickRows = row;
                    baseFilers = 'EQ_page.id=' + row.id;
                    $('#table_comments').bootstrapTable('refresh');
                },
                onCheck: function (rows) {
                    thisClickRows = rows;
                    baseFilers = 'EQ_page.id=' + rows.id;
                    $('#table_page_components').bootstrapTable('refresh');
                    searchPageComponents();
                },
                onLoadSuccess: function (data) {
                    $('#table_articles').bootstrapTable('check', 0);
                }
            });
        }

        function searchPageComponents() {
            $('#table_comments').bootstrapTable({
                toolbar: '#articles_comments_toolbar',
                pagination: true,
                queryParams: function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var filters = baseFilers + ';EQ_level=1';
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
                    field: 'componentImageVO.componentTypeName',
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
                    width: 270
                    // events: operateEventsComponent,
                    // formatter: operateFormatterComponent
                }]
            });
        }

        function operateFormatter(value, row, index) {
            var editBtns = [
                '<button type="button" class="article-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
                '<a href="publish/init?id=' + row.id + '"><button type="button" class="btn btn-primary btn-sm" style="margin-right:15px;"><i class="fa fa-pencil" aria-hidden="true"></i></button></a>'
            ];
            var statusBtn = '<button type="button" class="article-status-disabled btn btn-warning btn-sm" style="margin-right:15px;">隐藏</button>';
            if (row.status === 100002002) {
                statusBtn = '<button type="button" class="article-status-enable btn btn-info btn-sm" style="margin-right:15px;">显示</button>';
            }
            editBtns.push(statusBtn);
            return editBtns.join('');
        }

        win.operateEvents = {
            'click .article-delete': function (e, value, row, index) {
                deleteArticle(row);
            },
            'click .article-status-disabled': function (e, value, row, index) {
                changeStatus(row, 100002002, '【 禁用 】 成功');
            },
            'click .article-status-enable': function (e, value, row, index) {
                changeStatus(row, 100002001, '【 启用 】 成功');
            }
        };

        function deleteArticle(row) {
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.titleName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                var param = {
                    method: 'delete', url: "${request.contextPath}/admin/contents/article/" + row.id,
                    sessionId: 'articles-refresh'
                };
                httpClient(param);
            });
        }

        function changeStatus(row, status, message) {
            var param = {
                method: 'post', url: '${request.contextPath}/admin/contents/article/change/status', data: {
                    id: row.id,
                    status: status
                },
                sessionId: 'articles-refresh'
            };
            httpClient(param);
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
        articlesInit();

        win.httpClientSuccess = function (data) {
            switch (data.sessionId) {
                case 'articles-refresh':
                    $('#table_articles').bootstrapTable('refresh');
                    break;
            }
            toastr.success(data.data.message);
        };

    })(jQuery, window)
</script>