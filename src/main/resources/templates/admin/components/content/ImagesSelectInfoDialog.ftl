<style>
    .label-image-item {
        margin-left: 10px;
    }

    .div-row-item {
        line-height: 54px;
    }
</style>
<div class="modal fade" id="image_select_dialog_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <form>
            <div class="modal-content" style="padding: 10px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" style="font-size: xx-large;">查看图片信息</h4>
                </div>
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">图片名称 :</label>
                    <input type="text" class="form-control input-form-group-value-item" disabled
                           id="lab_name">
                </div>
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">图片URL :</label>
                    <input type="text" class="form-control input-form-group-value-item" disabled
                           id="lab_url">
                </div>
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">HTML格式 :</label>
                    <input type="text" class="form-control input-form-group-value-item" disabled
                           id="lab_html">
                </div>
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">BBCode格式 :</label>
                    <input type="text" class="form-control input-form-group-value-item" disabled
                           id="lab_BBCode">
                </div>
                <div class="form-group div-form-group">
                    <label for="txt_departmentname" class="label-form-group-title-item">Markdown格式 :</label>
                    <input type="text" class="form-control input-form-group-value-item" disabled
                           id="lab_markdown">
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
        function timestampToTime(timestamp) {
            var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            Y = date.getFullYear() + '-';
            M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds();
            return Y + M + D + h + m + s;
        }

        win.commitImage = function (params) {
            $.ajax({
                type: 'get',
                url: params.imageId,
                datatype: 'json',
                success: function (data) {
                    var o = data.object;
                    var createDate = o.createDate;
                    $("#lab_name").val(o.name);
                    // $("#lab_createDate").html(createDate.getFullYear() + '-' + (createDate.getMonth() + 1) + '-' + createDate.getDate());
                    $("#lab_url").val(o.url);
                    $("#lab_html").val(o.html);
                    $("#lab_BBCode").val(o.bbcode);
                    $("#lab_markdown").val(o.markdown);
                },
                error: function (data) {
                    console.log(data)
                }
            });
        };
    })(jQuery, window)
</script>