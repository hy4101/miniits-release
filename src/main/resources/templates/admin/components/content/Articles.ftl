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
        <table id="table_articles" style="background-color: #FFFFFF"></table>
    </div>
    <div style="padding-left: 10px;flex: 5">
        <div class="div-articles-component-title">
            评论管理
        </div>
        <table id="table_comments" style="background-color: #FFFFFF"></table>
    </div>
</div>
<script>
    toastr.options.positionClass = 'toast-top-center';
    (function ($, win) {

        function articlesInit() {
            searchArticles();
            searchPageComponents();
            clicks();
        }

        function clicks() {
        }

        function searchArticles() {
            $('#table_articles').bootstrapTable({
                toolbar: '#articles_toolbar',
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
                    field: 'titleName',
                    title: '标题'
                }, {
                    field: 'statusName',
                    title: '状态'
                }, {
                    field: 'sourceName',
                    title: '来源'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 260
                    // events: operateEvents,
                    // formatter: operateFormatter
                }],
                // onClickRow: function (row, $element) {
                //     thisClickPage = row;
                //     basePageComponentAssociateFtilers = 'EQ_page.id=' + row.id;
                //     $('#table_page_components').bootstrapTable('refresh');
                // },
                // onCheck: function (rows) {
                //     thisClickPage = rows;
                //     basePageComponentAssociateFtilers = 'EQ_page.id=' + rows.id;
                //     $('#table_page_components').bootstrapTable('refresh');
                //     searchPageComponents();
                // },
                // onLoadSuccess: function (data) {
                //     $('#table_pages').bootstrapTable('check', 0);
                // }
            });
        }

        function searchPageComponents() {
            $('#table_comments').bootstrapTable({
                toolbar: '#articles_comments_toolbar',
                pagination: true,
                queryParams: function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var filters = basePageComponentAssociateFtilers + ';EQ_level=1';
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
                    width: 270,
                    // events: operateEventsComponent,
                    // formatter: operateFormatterComponent
                }]
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
        articlesInit();

    })(jQuery, window)
</script>