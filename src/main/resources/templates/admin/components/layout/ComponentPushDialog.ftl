<script src="/static/bootstrap-3.3.7-dist/table/bootstrapValidator.min.js"></script>
<div class="modal fade" id="push_component_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="push_component_form_title" style="font-size: xx-large;">新增</h4>
            </div>
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label class="label-form-group-title-item">作者 :</label>
                    <input type="text" name="authorName" id="authorName"
                           class="form-control input-form-group-value-item"
                           placeholder="作者">
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label class="label-form-group-title-item">预览图片 :</label>
                    <input type="text" name="appImageUrl" id="appImageUrl"
                           class="form-control input-form-group-value-item"
                           placeholder="应用市场可以更直观的展示您的应用，请输入图片url">
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label class="label-form-group-title-item">标签 :</label>
                    <input type="text" name="tags" id="tags" class="form-control input-form-group-value-item"
                           placeholder="应用市场可以快速通过标签查找您的应用，请输入标签（多个逗号隔开）">
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label class="label-form-group-title-item">备注 :</label>
                    <textarea type="text" name="remark" id="remark" class="form-control input-form-group-value-item"
                              id="remark"
                              placeholder="请输入备注"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-link">
                    如何发布
                </button>
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
    </div>
</div>
<script>
    (function ($, win) {
        var userName = '';
        var component = '';
<@shiro.authenticated>
    userName = '<@shiro.principal property="userName"/>';
</@shiro.authenticated>
        $("#authorName").val(userName);
        $("#btn_push_component").click(function () {
            var number = component.componentId;
            var authorName = $("#authorName").val();
            var appImageUrl = $("#appImageUrl").val();
            var tags = $("#tags").val();
            var remark = $("#remark").val();
            var pushData = {
                tags: tags,
                appImageUrl: appImageUrl,
                authorName: authorName,
                remark: remark,
                appType: '101001001',
                appTypeName: '组件',
                number: number
            };
            var url = 'http://localhost:8000/miniits/app/push';
            var param = {
                method: 'post',
                url: url,
                data: {app: JSON.stringify(pushData)},
                sessionId: 'push-component',
                message: '推送成功，等待审核'
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