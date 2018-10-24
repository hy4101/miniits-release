<script src="${request.contextPath}/static/bootstrap-3.3.7-dist/table/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/md5.js"></script>
<div class="modal fade" id="page_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <form id="page_form" name="page_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="new_page_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">页面类型 :</label>
                    <#--<input type="checkbox" id="index_btn" class="minimal">-->
                        <select class="form-control input-form-group-value-item" id="index_btn" style="flex: 4"
                                name="allowComment">
                            <option value="diy" ${(article?exists&&article.allowComment==100000001)?string('selected', '')}>
                                自定义
                            </option>
                            <option value="index" ${(article?exists&&article.allowComment==100000001)?string('selected', '')}>
                                index
                            </option>
                            <option value="404" ${(article?exists&&article.allowComment==100000002)?string('selected', '')}>
                                404
                            </option>
                            <#--<option value="500" ${(article?exists&&article.allowComment==100000002)?string('selected', '')}>-->
                                <#--500-->
                            <#--</option>-->
                        </select>
                    </div>

                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">页面(文件)名称 :
                            <br>
                            (提示：该属性为创建静态HTML文件的名称和文件的访问路径，
                            <label style="color: red">建议使用英文</label>
                            )</label>
                        <input type="text" name="pageName" class="form-control input-form-group-value-item"
                               id="pageName"
                               placeholder="请输入页面名称">
                    </div>
                    <div class="form-group div-form-group">
                        <label for="txt_parentdepartment" class="label-form-group-title-item">页面访问路径 : (
                            默认后缀 .html )</label>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon3">https://www.URI.com/${rootPath}/</span>
                            <input type="text" class="form-control" id="pagePath" name="pagePath" disabled
                                   aria-describedby="basic-addon3" placeholder="页面访问路径">
                            <span class="input-group-addon">.html</span>
                        </div>
                    </div>
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">页面别名 :</label>
                        <input type="text" name="pageAliasName" class="form-control input-form-group-value-item"
                               id="pageAliasName"
                               placeholder="请输入页面别名">
                    </div>
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
                    <button id="btn_save_page" class="btn btn-primary">
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

        $("#index_btn").change(function () {
            var sv = this.value;
            if (sv === 'diy') {
                $("#pageName").val(null);
                $("#pagePath").val(null);
                $("#pageName").removeAttr("disabled");
                return;
            }
            if (sv) {
                $("#pageName").attr("disabled", "disabled");
                $("#pageName").val(sv);
                $("#pagePath").val(sv);
                // $("#pagePath").attr("disabled", "disabled");
            } else {
                $("#pageName").val(null);
                $("#pagePath").val(null);
                // $("#pagePath").removeAttr("disabled");
                $("#pageName").removeAttr("disabled");
            }
        });

        function userDialogInit() {

        }

        $("#pageName").blur(function () {
            var pageName = $("#pageName").val();
            if (pageName) {
                $("#pagePath").val(pageName);
            }
        });
        // $("#index_btn").click(function () {
        //     var check = $("#index_btn")[0].checked;
        //     if (check) {
        //         $("#pageName").val('index');
        //         $("#pageName").attr("disabled", "disabled");
        //         // $("#pagePath").attr("disabled", "disabled");
        //     } else {
        //         $("#pageName").val(null);
        //         $("#pagePath").val(null);
        //         // $("#pagePath").removeAttr("disabled");
        //         $("#pageName").removeAttr("disabled");
        //     }
        // });

        //Modal验证销毁重构
        $('#page_modal').on('hidden.bs.modal', function () {
            $("#page_form").data('bootstrapValidator').destroy();
            $('#page_form').data('bootstrapValidator', null);
            extracted();
        });

        $("#btn_save_page").click(function () {
            var uf = $("#page_form");
            uf.bootstrapValidator('validate');
            if (uf.data('bootstrapValidator').isValid()) {
                //获取验证结果，如果成功，执行下面代码
                var pageName = $("#pageName").val();
                var pagePath = $("#pagePath").val();
                var pageStatus = $("#pageStatus").val();
                var pageAliasName = $("#pageAliasName").val();
                if (isEmpty(pageStatus)) {
                    pageStatus = '100000002';
                }
                var pageData = null;
                if (isEmpty(page)) {
                    pageData = {
                        createStaticFile: 100000001,
                        pageName: pageName,
                        pagePath: pagePath,
                        pageStatus: pageStatus,
                        pageAliasName: pageAliasName,
                        pageStatusName: pageStatus === '100000001' ? '启用' : '禁用'
                    };
                } else {
                    page.pageName = pageName;
                    page.pageStatus = pageStatus;
                    page.pagePath = pagePath;
                    page.pageAliasName = pageAliasName;
                    page.pageStatusName = pageStatus === '100000001' ? '启用' : '禁用';
                    pageData = page;
                }
                pageData.createDate = isEmpty(pageData.createDate) ? new Date() : new Date(pageData.createDate);

                var param = {
                    method: 'post', url: '${request.contextPath}/admin/pages/save',
                    data: pageData, sessionId: 'page-save', message: '保存成功'
                };
                httpClient(param);
            }
        });

        win.commitPage = function (data) {
            if (isEmpty(data)) {
                $("#pageName").val(null);
                $("#pagePath").val(null);
                $("#pageStatus").val(null);
                $("#pageAliasName").val(null);
                page = null;
            } else {
                $("#pageName").val(data.pageName);
                $("#pagePath").val(data.pagePath);
                $("#pageStatus").val(data.pageStatus);
                $("#pageAliasName").val(data.pageAliasName);
                page = data;
            }
        };

        function extracted() {
            $("#page_form").bootstrapValidator({
                live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
                excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
                submitButtons: '#btn_save_user',
                message: '输入错误信息，请重新输入',
                feedbackIcons: {//根据验证结果显示的各种图标
                    valid: 'fa fa-check',
                    invalid: 'fa fa-times',
                    validating: 'fa fa-magic'
                },
                fields: {
                    pageName: {
                        validators: {
                            notEmpty: {//检测非空,radio也可用
                                message: '文本框必须输入'
                            },
                            stringLength: {//检测长度
                                min: 1,
                                max: 20,
                                message: '长度必须在6-20之间'
                            }
                        }
                    },
                    pagePath: {
                        validators: {
                            notEmpty: {//检测非空,radio也可用
                                message: '文本框必须输入'
                            },
                            stringLength: {//检测长度
                                min: 1,
                                max: 30,
                                message: '长度必须在8-30之间'
                            }
                        }
                    }
                }
            });
        }

        extracted();

        userDialogInit();

    })(jQuery, window);

</script>