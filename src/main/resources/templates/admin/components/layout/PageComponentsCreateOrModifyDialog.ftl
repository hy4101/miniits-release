<div class="modal fade" id="page_components_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document" style="width: 50%;">
        <form id="page_form" name="page_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="new_page_components_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="row" style="margin-bottom: 10px">
                        <div class="col-lg-3">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入名称" id="inp_componentName"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入ID" id="componentId"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入API" id="componentBodyApi"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <button id="btn_page_components_search" type="button" class="btn"
                                    style="background-color: #27AE60;color: #fff;">
                                <i class="fa fa-search" aria-hidden="true"></i>
                            </button>
                            <button id="btn_add_page_components_refresh" type="button" class="btn"
                                    style="background-color: #ff754e;color: #fff;">
                                <i class="fa fa-refresh" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                    <table id="table_components" style="background-color: #FFFFFF"></table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                    <button type="submit" id="btn_save_page_component" class="btn btn-primary" data-dismiss="modal">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        添加
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {
        var PData = null;

        function componentDialogInit() {
            getComponents();
        }

        $('#btn_add_page_components_refresh').click(function () {
            $("#inp_componentName").val(null);
            $("#componentId").val(null);
            $("#componentBodyApi").val(null)
        });

        $('#btn_page_components_search').click(function () {
            $('#table_components').bootstrapTable('refresh');
        });

        function getComponents() {
            $('#table_components').bootstrapTable({
                pagination: true,
                queryParams: function (params) {
                    var filters = '';
                    var componentName = $("#inp_componentName").val();
                    var componentId = $("#componentId").val();
                    var componentBodyApi = $("#componentBodyApi").val();
                    if (!isEmpty(componentName)) {
                        filters = 'LIKE_componentName=' + componentName;
                    }
                    if (!isEmpty(componentId)) {
                        filters += ';LIKE_componentId=' + componentId;
                    }
                    if (!isEmpty(componentBodyApi)) {
                        filters += ';LIKE_componentBodyApi=' + componentBodyApi;
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
                url: "../components",//要请求数据的文件路径
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                columns: [{
                    field: 'id',
                    visible: false
                }, {
                    field: 'checked',
                    checkbox: true
                }, {
                    field: 'componentName',
                    title: '组件名称'
                }, {
                    field: 'componentId',
                    title: '组件ID'
                }, {
                    field: 'componentBodyApi',
                    title: '数据API'
                }]
            });
        }

        $("#btn_save_page_component").click(function () {
            var rows = $('#table_components').bootstrapTable('getSelections');
            $.ajax({
                type: 'post',
                url: '../page-component-associate/save',
                datatype: 'json',
                data: {
                    jsonPage: JSON.stringify(PData.page),
                    jsonComponentImagePId: JSON.stringify(PData.componentImagePId.componentImageVO),
                    jsonComponentImages: JSON.stringify(rows)
                },
                success: function (data) {
                    win.refreshPageComponent({type: 'success', message: '组件添加成功'});
                },
                error: function (data) {
                    console.log(data)
                }
            });
        });

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

        win.commitAddComponents = function (data) {
            if (isEmpty(data)) {
                return toastr.error('打开失败，你可以尝试刷新页面后重试');
            }
            PData = data;
        };

        componentDialogInit();
    })(jQuery, window);

</script>