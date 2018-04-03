<div style="padding: 10px 10px 10px 10px">
    <#include "../common/TableFiles.ftl"/>
    <div>
        <div class="row" style="margin-bottom: 10px">
            <div class="col-lg-6">
                <input type="text" class="form-control" placeholder="请输入名称" id="categoryName"
                       aria-describedby="basic-addon1">
            </div>
            <div class="col-lg-6">
                <button id="btn_search" type="button" class="btn"
                        style="background-color: #27AE60;color: #fff;width: 90px;margin-right: 10px;">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
                <button id="btn_refresh" type="button" class="btn" style="background-color: #ff754e;color: #fff;">
                    <i class="fa fa-refresh" aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <div id="category_toolbar" class="btn-group">
            <button id="btn_add_category_click" type="button" class="btn"
                    style="background-color: #3c8dbc;color: #fff;">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
        </div>
        <table id="div_table_categorys">

        </table>
        <div>
            <#include "CategorysCreateOrModify.ftl"/>
        </div>
    </div>
</div>
<script>
    toastr.options.positionClass = 'toast-top-center';
    (function ($, win) {

        function categorysInit() {
            searchArticles();
        }

        function searchArticles() {
            $('#div_table_categorys').bootstrapTable({
                toolbar: '#categorys_toolbar',
                pagination: true,
                detailView: true,//父子表
                onExpandRow: function (index, row, $detail) {
                    InitSubTable(index, row, $detail);
                },
                queryParams: function (params) {
                    var filters = '';
                    var categoryName = $("#categoryName").val();
                    if (!isEmpty(categoryName)) {
                        filters = 'LIKE_categoryName=' + categoryName;
                    }
                    var temp = {
                        pageSize: params.limit,                         //页面大小
                        pageNumber: (params.offset / params.limit) + 1,   //页码
                        sorts: '-createDate',      //排序列名
                        filters: filters + ';EQ_level=1' //排位命令（desc，asc）
                    };
                    return temp;
                },
                sidePagination: 'server',//指定服务器端分页
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 15,                       //每页的记录行数（*）
                pageList: [15, 30, 50, 100],        //可供选择的每页的行数（*）
                method: 'get',
                url: "../categorys",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'categoryName',
                    title: '分类名称'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 260,
                    events: cOperateEvents,
                    formatter: cOperateFormatter
                }]
            });
        }

        //初始化子表格(无线循环)
        function InitSubTable(index, row, $detail) {
            var pId = row.id;
            var cur_table = $detail.html('<table></table>').find('table');
            $(cur_table).bootstrapTable({
                sidePagination: 'server',//指定服务器端分页
                contentType: "application/x-www-form-urlencoded",
                method: 'get',
                url: "../categorys",//要请求数据的文件路径
                queryParams: function (params) {
                    var filters = '';
                    var categoryName = $("#categoryName").val();
                    if (!isEmpty(categoryName)) {
                        filters = 'LIKE_categoryName=' + categoryName;
                    }
                    var temp = {
                        pageSize: params.limit,
                        pageNumber: (params.offset / params.limit) + 1,
                        sorts: '-createDate',
                        filters: filters + ';EQ_pId=' + pId
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
                    field: 'categoryName',
                    title: '分类名称'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: 260,
                    events: cOperateEvents,
                    formatter: cOperateFormatter
                }],
                //无线循环取子表，直到子表里面没有记录
                onExpandRow: function (index, row, $Subdetail) {
                    InitSubTable(index, row, $Subdetail);
                }
            });
        };

        $('#btn_search').click(function () {
            $('#div_table_categorys').bootstrapTable('refresh');
        });

        $('#btn_refresh').click(function () {
            $("#categoryName").val(null);
        });

        $("#btn_add_category_click").click(function () {
            var categoryName = $("#categoryName").val();
            if (isEmpty(categoryName)) {
                return;
            }
            $.ajax({
                type: 'post',
                url: '../categorys',
                data: {
                    category: JSON.stringify({
                        categoryName: categoryName,
                        level: 1
                    })
                },
                datatype: 'json',
                success: function (data) {
                    if (!data.success) {
                        toastr.error(data.message);
                    } else {
                        toastr.success(data.message);
                    }
                    $('#div_table_categorys').bootstrapTable('refresh');
                },
                error: function (data) {
                    toastr.success(data.message);
                }
            });
        });

        win.cOperateEvents = {
            'click .category-delete': function (e, value, row, index) {
                deleteBy(row);
            },
            'click .category-modify': function (e, value, row, index) {
                $("#new_category_title").text("修改分类");
                $('#category_modal').modal();
                commitCategory({row: row});
            },
            'click .category-add-child': function (e, value, row, index) {
                $("#new_category_title").text('添加' + row.categoryName + '子类');
                $('#category_modal').modal();
                commitCategory({row: row, child: 'child'});
            }
        };

        function cOperateFormatter(value, row, index) {
            var editBtns = ['<button type="button" class="category-delete btn btn-delete btn-sm" style="margin-right:15px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button>',
                '<button type="button" class="category-modify btn btn-default btn-sm" style="margin-right:15px;"><i class="fa fa-pencil" aria-hidden="true"></i></button>',
                '<button type="button" class="category-add-child btn btn-delete btn-sm" style="margin-right:15px;background-color: #3c8dbc;color: #fff;"><i class="fa fa-plus" aria-hidden="true"></i></button>',
            ];
            return editBtns.join('');
        }

        function deleteBy(row) {
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + row.categoryName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: 'delete',
                    url: '../categorys/' + row.id,
                    datatype: 'json',
                    success: function (data) {
                        toastr.success('删除成功');
                        $('#div_table_categorys').bootstrapTable('refresh');
                    },
                    error: function (data) {
                        console.log(data)
                    }
                });
            });
        }

        categorysInit();

    })(jQuery, window)
</script>