<script src="/static/bootstrap-3.3.7-dist/table/bootstrapValidator.min.js"></script>
<style>
    /*.div-form-group {*/
    /*display: flex;*/
    /*}*/

    /*.label-form-group-title-item {*/
    /*flex: 2;*/
    /*line-height: 34px;*/
    /*}*/

    /*.input-form-group-value-item {*/
    /*flex: 10;*/
    /*}*/
</style>
<script type="text/javascript" src="/static/js/md5.js"></script>
<div class="modal fade" id="user_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <form id="user_form" name="user_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="new_user_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">用户名 : </label>
                        <input type="text" name="userName" class="form-control input-form-group-value-item"
                               id="inp_userName"
                               placeholder="请输入用户名">
                    </div>
                    <div class="form-group div-form-group">
                        <label for="txt_parentdepartment" class="label-form-group-title-item">密码 : </label>
                        <input type="password" name="password" class="form-control input-form-group-value-item"
                               id="password"
                               placeholder="密码输入密码">
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
            var uf = $("#user_form");
            // uf.bootstrapValidator('validate');//提交验证
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
                debugger;
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

        $("#user_form").bootstrapValidator({
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
                // ,text: {
                //     validators: {
                //         notEmpty: {//检测非空,radio也可用
                //             message: '文本框必须输入'
                //         },
                //         stringLength: {//检测长度
                //             min: 6,
                //             max: 30,
                //             message: '长度必须在6-30之间'
                //         },
                //         regexp: {//正则验证
                //             regexp: /^[a-zA-Z0-9_\.]+$/,
                //             message: '所输入的字符不符要求'
                //         },
                //         remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                //             url: '指定页面',
                //             message: 'The username is not available'
                //         },
                //         different: {//与指定文本框比较内容相同
                //             field: '指定文本框name',
                //             message: '不能与指定文本框内容相同'
                //         },
                //         emailAddress: {//验证email地址
                //             message: '不是正确的email地址'
                //         },
                //         identical: {//与指定控件内容比较是否相同，比如两次密码不一致
                //             field: 'confirmPassword',//指定控件name
                //             message: '输入的内容不一致'
                //         },
                //         date: {//验证指定的日期格式
                //             format: 'YYYY/MM/DD',
                //             message: '日期格式不正确'
                //         },
                //         choice: {//check控件选择的数量
                //             min: 2,
                //             max: 4,
                //             message: '必须选择2-4个选项'
                //         }
                //     }
                // }
            }
        });


        userDialogInit();
    })(jQuery, window);

    function selectOnchang(obj) {
    }
</script>