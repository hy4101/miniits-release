<script src="/static/bootstrap-3.3.7-dist/table/bootstrapValidator.min.js"></script>
<div class="modal fade" id="push_component_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <form id="push_component_form" name="page_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="push_component_form_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label class="label-form-group-title-item">备注 :</label>
                        <input type="text" name="remark" class="form-control input-form-group-value-item"
                               id="remark"
                               placeholder="请输入页面别名">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                    <button id="btn_push_component" class="btn btn-primary">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        发布
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {

        var component = '';
        $("#btn_push_component").click(function () {
            //获取验证结果，如果成功，执行下面代码
            var remark = $("#remark").val();
            var pushData = {remark: remark};
            var param = {
                method: 'post',
                url: '../push/' + component.id,
                data: {remark: remark},
                sessionId: 'push-component',
                message: '发布成功'
            };
            httpClient(param);
        });

        win.commitComponent = function (data) {
            component = data.row;
        };

        win.httpClientSuccess = function (data) {
            toastr.success(data.message);
        }

    })(jQuery, window);

</script>