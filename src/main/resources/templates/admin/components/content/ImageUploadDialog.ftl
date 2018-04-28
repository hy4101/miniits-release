<style>
    .modal-dialog {
        width: 750px
    }
</style>
<div class="modal fade" id="image_upload_dialog_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="">
    <div class="modal-dialog" role="document">
        <form id="category_form" name="category_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="image_upload_dialog_title" style="font-size: xx-large;">图片上传</h4>
                </div>
                <div style="margin-left: 5%;width: 90%;">
                    <h5>上传须知：你可以选择【单张】或者【多张】图片进行上传。上传（下框赋值成功）后请直接点击保存，</h5>
                    <textarea id="tex-image-url" style="width: 100%;height: 300px;"
                              placeholder="上传后会在框内赋值视为上传成功，上传成功后无需修改。"></textarea>
                    <script async id="chevereto-pup-src" src="https://imgchr.com/sdk/pup.js"
                            data-url="https://imgchr.com/upload" data-auto-insert="bbcode-embed-medium"></script>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                    <button type="submit" id="btn_save_image" class="btn btn-primary" data-dismiss="modal">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        保存
                    </button>
                </div>
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    <div style="margin-left: 5%;width: 90%;">
                                        <h4 style="color: red;">查看异常处理
                                            <i class="fa fa-angle-double-down" style="margin-left: 10px"
                                               aria-hidden="true"></i></h4>
                                    </div>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                                    <h4 style="color: red;">图片在上传后没有在框内赋值视为上传失败</h4>
                                </div>
                                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                                    <h5>处理方法如下：(共两步)</h5>
                                    <h5>在你上传完毕后，可以看到如下界面</h5>
                                </div>
                                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                                    <h5>1、选择【查看链接】，之后复制下边的链接：如下图</h5>
                                    <img src="https://s1.ax1x.com/2018/04/21/CKOyJf.png" width="100%">
                                </div>
                                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                                    <h5>2、复制成功后，鼠标移动在到框内，右键选择粘贴：如下图</h5>
                                    <img src="https://s1.ax1x.com/2018/04/21/CKOdLd.png" width="100%">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {

        function imageUploadInit() {
        }

        $("#btn_save_image").click(function () {
            var fileUrl = $("#tex-image-url").val();
            var params = {sessionId: 'image-upload', method: 'post', url: 'upload', data: {fileUrl: fileUrl}};
            httpClient(params);
            win.httpClientSuccess = function (data) {
                if (data.sessionId == 'image-upload') {
                    location.reload();
                }
            }
        });
        imageUploadInit();
    })(jQuery, window)
</script>