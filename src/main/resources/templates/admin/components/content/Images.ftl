<div style="padding: 10px 10px 10px 10px">
    <div class="row clearfix" style="margin-bottom: 10px;">
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">图片名称
                    :</label>
                <input type="text" class="form-control" id="titleName" name="titleName"
                       style="flex: 4;"
                       aria-describedby="basic-addon3" placeholder="请输入图片名称">
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">图片URL
                    :</label>
                <input type="text" class="form-control" id="contentTitle" name="contentTitle"
                       style="flex: 4;"
                       aria-describedby="basic-addon3" placeholder="输入图片URL">
            </div>
        </div>
        <div class="col-md-4 column">
            <button id="btn_articles_search" type="button" class="btn"
                    style="background-color: #27AE60;color: #fff;">
                <i class="fa fa-search" aria-hidden="true"></i>
            </button>
            <button id="btn_articles_refresh" type="button" class="btn"
                    style="background-color: #ff754e;color: #fff;">
                <i class="fa fa-refresh" aria-hidden="true"></i>
            </button>
            <button id="btn_image_upload" type="button" class="btn">
                <i class="fa fa-upload" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <div>
    <#include "ImageUploadDialog.ftl"/>
    </div>
</div>
<script>
    (function ($, win) {

        $("#btn_image_upload").click(function () {
            $('#image_upload_dialog_modal').modal();
        });

        function imageInit() {

        }

        imageInit();
    })(jQuery, window)
</script>