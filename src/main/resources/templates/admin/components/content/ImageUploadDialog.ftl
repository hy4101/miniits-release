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
                    <textarea id="tex-image-url" style="width: 100%;height: 300px;" placeholder="上传后会在框内赋值视为上传成功，上传成功后无需修改。"></textarea>
                    <script async id="chevereto-pup-src" src="https://imgchr.com/sdk/pup.js"
                            data-url="https://imgchr.com/upload" data-auto-insert="bbcode-embed-medium"></script>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                    <button type="submit" id="btn_save_category" class="btn btn-primary" data-dismiss="modal">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        保存
                    </button>
                </div>
                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                    <h3 style="color: red;">异常处理：图片在上传后没有在框内赋值视为上传失败</h3>
                </div>
                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                    <h5 style="color: red;">处理方法如下：(共两步)</h5>
                </div>
                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                    <h5 style="color: red;">1、选择【查看链接】，之后复制下边的链接：如下图</h5>
                    <img src="https://s1.ax1x.com/2018/04/21/CKOyJf.png" width="100%">
                </div>
                <div class="row clearfix" style="margin-left: 5%;width: 90%;">
                    <h5 style="color: red;">2、复制成功后，鼠标移动在到框内，右键选择粘贴：如下图</h5>
                    <img src="https://s1.ax1x.com/2018/04/21/CKOdLd.png" width="100%">
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {

        function imageUploadInit() {

        }

        imageUploadInit();
    })(jQuery, window)
</script>