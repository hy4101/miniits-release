<div class="modal fade" id="page_setting_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="border-radius: 5px;">
    <div class="modal-dialog" role="document">
        <form>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="page_setting_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">【SEO】显示名称 :</label>
                        <input type="text" class="form-control input-form-group-value-item"
                               placeholder="提示：浏览器显示的页面名称">
                    </div>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">【SEO】页面关键字 :</label>
                        <input type="text" class="form-control input-form-group-value-item"
                               placeholder="提示：页面关键字，多个逗号隔开">
                    </div>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">【SEO】页面描述 :</label>
                    <#--<input type="textarea" class="form-control input-form-group-value-item"-->
                    <#--placeholder="提示：页面描述">-->
                        <textarea placeholder="提示：页面描述" style="width: 100%;"></textarea>
                    </div>
                </div>
                <div class="modal-body">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        禁用
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        启用
                    </button>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {
        var page = null;

        function pageSettingDialogInit() {

        }

        win.commitPageSetting = function (data) {

        };


        pageSettingDialogInit();

    })(jQuery, window);

</script>