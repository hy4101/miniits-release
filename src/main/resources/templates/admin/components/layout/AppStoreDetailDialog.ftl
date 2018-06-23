<div class="modal fade" id="app_select_dialog_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <form>
            <div class="modal-content" style="padding: 10px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" style="font-size: xx-large;" id="app_name">查看图片信息</h4>
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

        function appDetailInit(o) {
        }

        win.commitApp = function (params) {
            var param = {
                method: 'get', url: params.imageId, sessionId: 'set-image-info'
            };
            httpClient(param);
        };
    })(jQuery, window)
</script>