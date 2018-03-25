<script src="/static/bootstrap-3.3.7-dist/table/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="/static/js/md5.js"></script>
<div class="modal fade" id="page_components_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <form id="page_form" name="page_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="new_page_components_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentlevel" class="label-form-group-title-item">状态 : </label>
                        <select class="form-control input-form-group-value-item" id="pageStatus"
                                name="pageStatus">
                            <option value="100000001">启用</option>
                            <option value="100000002">禁用</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                    <button type="submit" id="btn_save_page" class="btn btn-primary" data-dismiss="modal">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {
        var page = null;

        function userDialogInit() {

        }
        $("#btn_save_page").click(function () {
            var uf = $("#page_form");
            if (uf.data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
                var pageName = $("#pageName").val();
                var pagePath = $("#pagePath").val();
                var pageStatus = $("#pageStatus").val();
                var pageData = null;
                if (isEmpty(page)) {
                    pageData = {
                        pageName: pageName,
                        pagePath: pagePath,
                        pageStatus: pageStatus,
                        pageStatusName: pageStatus === '100000001' ? '启用' : '禁用'
                    };
                } else {
                    page.pageName = pageName;
                    page.pageStatus = pageStatus;
                    page.pagePath = pagePath;
                    page.pageStatusName = pageStatus === '100000001' ? '启用' : '禁用';
                    pageData = page;
                }
                pageData.createDate = isEmpty(pageData.createDate) ? new Date() : new Date(pageData.createDate);
                $.ajax({
                    type: 'post',
                    url: 'save',
                    data: pageData,
                    datatype: 'json',
                    success: function (data) {
                        debugger;
                        var ud = null;
                        if (data.success) {
                            ud = {type: 'success', message: '成功'};
                        } else {
                            ud = {type: 'error', message: data.message};
                        }
                        win.refreshPage(ud);
                    },
                    error: function (data) {
                        console.log(data);
                    }
                });
            }
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

        win.commitPage = function (data) {
            if (isEmpty(data)) {
                $("#pageName").val(null);
                $("#pagePath").val(null);
                $("#pageStatus").val(null);
                page = null;
            } else {
                $("#pageName").val(data.pageName);
                $("#pagePath").val(data.pagePath);
                $("#pageStatus").val(data.pageStatus);
                page = data;
            }
        };

        $("#page_form").bootstrapValidator({
            live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
            excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
            submitButtons: '#btn_save_user',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
            message: '输入错误信息，请重新输入',//好像从来没出现过
            feedbackIcons: {//根据验证结果显示的各种图标
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-magic'
            },
            fields: {
                userName: {
                    validators: {
                        notEmpty: {//检测非空,radio也可用
                            message: '文本框必须输入'
                        },
                        stringLength: {//检测长度
                            min: 2,
                            max: 20,
                            message: '长度必须在6-20之间'
                        },
                    }
                },
                password: {
                    validators: {
                        notEmpty: {//检测非空,radio也可用
                            message: '文本框必须输入'
                        },
                        stringLength: {//检测长度
                            min: 8,
                            max: 30,
                            message: '长度必须在8-30之间'
                        },
                    }
                }
            }
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
        userDialogInit();
    })(jQuery, window);

</script>