<script src="/static/bootstrap-3.3.7-dist/table/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="/static/js/md5.js"></script>
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
                        <label for="txt_departmentname" class="label-form-group-title-item">页面名称 : </label>
                        <input type="text" name="userName" class="form-control input-form-group-value-item"
                               id="inp_userName"
                               placeholder="请输入页面名称">
                    </div>
                    <div class="form-group div-form-group">
                        <label for="txt_parentdepartment" class="label-form-group-title-item">页面访问路径 : </label>
                        <input type="password" name="password" class="form-control input-form-group-value-item"
                               id="password"
                               placeholder="密码输入页面访问路径">
                    </div>
                    <div class="form-group div-form-group">
                        <label for="txt_departmentlevel" class="label-form-group-title-item">状态 : </label>
                        <select class="form-control input-form-group-value-item" id="userStatusCode"
                                name="userStatusCode"
                                onchange="selectOnchang(this)">
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
                    <button type="submit" id="btn_save_user" class="btn btn-primary" data-dismiss="modal">
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
        var user = null;

        function userDialogInit() {

        }

        $("#btn_save_user").click(function () {
            var uf = $("#page_form");
            if (uf.data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
                var userName = $("#inp_userName").val();
                var password = $("#password").val();
                var userStatusCode = $("#userStatusCode").val();
                var userData = null;
                if (isEmpty(user)) {
                    userData = {
                        userName: userName,
                        password: $.md5(password),
                        userStatusCode: userStatusCode,
                        userStatusName: userStatusCode === '100000001' ? '启用' : '禁用'
                    };
                } else {
                    user.userName = userName;
                    if (user.password !== password) {
                        user.password = $.md5(password);
                    }
                    user.userStatusCode = userStatusCode;
                    user.userStatusName = userStatusCode === '100000001' ? '启用' : '禁用';
                    userData = user;
                }
                userData.createDate = isEmpty(userData.createDate) ? new Date() : new Date(userData.createDate);
                $.ajax({
                    type: 'post',
                    url: 'save',
                    data: userData,
                    datatype: 'json',
                    success: function (data) {
                        var ud = null;
                        if (data.success) {
                            ud = {type: 'success', message: '用户新增成功'};
                        } else {
                            ud = {type: 'error', message: data.message};
                        }
                        win.refreshUser(ud);
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

        win.commitUser = function (data) {
            if (isEmpty(data)) {
                $("#inp_userName").val(null);
                $("#password").val(null);
                $("#userStatusCode").val(null);
                user = null;
            } else {
                $("#inp_userName").val(data.userName);
                $("#password").val(data.password);
                $("#userStatusCode").val(data.userStatusCode);
                user = data;
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

        userDialogInit();
    })(jQuery, window);

    function selectOnchang(obj) {
    }
</script>