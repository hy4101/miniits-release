<div class="modal fade" id="page_setting_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="page_setting_title" style="font-size: xx-large;">新增</h4>
            </div>
            <input type="hidden" id="id">
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">显示名称 :</label>
                    <input type="text" class="form-control input-form-group-value-item" id="title"
                           placeholder="提示：浏览器显示的页面名称">
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">页面关键字 :</label>
                    <input type="text" class="form-control input-form-group-value-item" id="keywords"
                           placeholder="提示：页面关键字，多个逗号隔开">
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">页面描述 :</label>
                <#--<input type="textarea" class="form-control input-form-group-value-item"-->
                <#--placeholder="提示：页面描述">-->
                    <textarea placeholder="提示：页面描述" style="width: 100%;" id="description"></textarea>
                </div>
            </div>
            <div class="modal-header">
                <h4 class="modal-title" id="page_setting_title" style="font-size: xx-large;">其他</h4>
            </div>
            <div class="modal-footer">
            <#--<button type="button" id="access_page_btn" class="btn btn-default" data-dismiss="modal">-->
            <#--</button>-->
                <button id="btn_setting_page_caching" class="btn btn-info">
                    <i class="fa fa-level-down" aria-hidden="true"></i>
                    设置模板缓存
                </button>
                <button id="btn_setting_page_delete_caching" class="btn btn-warning">
                    <i class="fa fa-eraser" aria-hidden="true"></i>
                    清理模板缓存
                </button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <i class="fa fa-times" aria-hidden="true"></i>
                    关闭
                </button>
                <button id="btn_setting_page_seo" class="btn btn-primary">
                    <i class="fa fa-floppy-o" aria-hidden="true"></i>
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    (function ($, win) {
        var pageData = null;

        function pageSettingDialogInit() {
            $("#btn_setting_page_seo").click(function () {
                var seoData = {
                    id: pageData.id,
                    keywords: $("#keywords").val(),
                    description: $("#description").val(),
                    title: $("#title").val()
                };
                var param = {
                    url: 'setting/seo', method: 'post', data: seoData, sessionId: 'caching',
                    message: 'SEO设置成功'
                };
                httpClient(param)
            });

            $("#btn_setting_page_caching").click(function () {
                var caching = {
                    id: pageData.id,
                    caching: 100000001,
                    pageName: pageData.pageName
                };
                var param = {
                    url: 'setting/templates-caching', method: 'post', data: caching, sessionId: 'caching',
                    message: '缓存设置成功'
                };
                httpClient(param)
            });

            $("#btn_setting_page_delete_caching").click(function () {
                var caching = {
                    id: pageData.id,
                    caching: 100000002,
                    pageName: pageData.pageName
                };
                var param = {
                    url: 'setting/templates-caching',
                    method: 'post',
                    data: caching,
                    sessionId: 'caching',
                    message: '缓存清除成功'
                };
                httpClient(param)
            });
        }

        win.commitPageSetting = function (data) {
            pageData = data;
            $("#id").val(data.id);
            $("#title").val(data.title);
            $("#keywords").val(data.keywords);
            $("#description").val(data.description);
        };

        win.httpClientSuccess = function (data) {
            toastr.error(data.message);
        };

        pageSettingDialogInit();

    })(jQuery, window);

</script>