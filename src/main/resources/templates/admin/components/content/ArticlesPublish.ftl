<style>
    .bootstrap-select.form-control:not([class*=col-]){
        width: 81% !important;
    }
</style>
<div style="padding: 10px 10px 10px 10px;display: flex">
    <link href="/static/toastr/toastr.css" rel="stylesheet"/>
    <script src="/static/toastr/toastr.min.js"></script>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css"/>
    <script src="/static/editor.md/src/editormd.js"></script>
    <link rel="stylesheet" href="/static/tagsinput/jquery.tagsinput.css"/>
    <script src="/static/tagsinput/jquery.tagsinput.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/css/bootstrap-select.min.css"/>
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap-select.min.js"></script>
    <div style="width: 100%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="font-size: xx-large;">
                    发布文章
                    <button id="save_article_btn" type="submit" class="btn btn-primary"
                            style="float: right">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        保存
                    </button>
                </h4>
            </div>
            <div class="modal-body">
                <div class="row clearfix" style="margin-bottom: 10px;">
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">标题名称
                                :</label>
                            <input type="text" class="form-control" id="titleName" name="titleName" style="flex: 4;"
                                   aria-describedby="basic-addon3" placeholder="请输入标题名称">
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">标题图片
                                :</label>
                            <input type="text" class="form-control" id="titleImage" name="titleImage"
                                   style="flex: 4;"
                                   aria-describedby="basic-addon3" placeholder="选择标题图片">
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">标题内容
                                :</label>
                            <input type="text" class="form-control" id="contentTitle" name="contentTitle"
                                   style="flex: 4;"
                                   aria-describedby="basic-addon3" placeholder="输入标题内容">
                        </div>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">类别
                            : </label>
                        <select class="form-control selectpicker" id="types" title="请选择省份" multiple >
                            <option data-content="<span class='label label-success'>广东省</span>">100000001</option>
                            <option data-content="<span class='label label-info'>广西省</span>">100000002</option>
                            <option data-content="<span class='label label-warning'>福建省</span>">100000003</option>
                            <option data-content="<span class='label label-danger'>山东省</span>">100000004</option>
                        </select>
                    <#--<select class="form-control input-form-group-value-item" id="types" style="flex: 4"-->
                    <#--name="types">-->
                    <#--<option value="100000001">启用</option>-->
                    <#--<option value="100000002">禁用</option>-->
                    <#--</select>-->
                    </div>
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">标签
                            : </label>
                        <input name="tags" id="tags" style="flex: 4" type="text" class="form-control"
                               placeholder="请填写文章标签"/>
                    </div>
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">是否允许评论
                            : </label>
                        <select class="form-control input-form-group-value-item" id="allowComment" style="flex: 4"
                                name="allowComment">
                            <option value="100000001">允许</option>
                            <option value="100000002">禁言</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="editormd" id="test-editormd">
                <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc"></textarea>
                <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
                <textarea class="editormd-html-textarea" name="text"></textarea>
            </div>
        </div>
    </div>
</div>
<script>
    toastr.options.positionClass = 'toast-top-center';
    (function ($, win) {
        $('#tags').tagsInput({
            width: '81%',
            height: '35px',
            defaultText: '输入标签'
        });


        $("#save_article_btn").click(function () {
            var titleName = $("#titleName").val();
            var titleImage = $("#titleImage").val();
            var contentTitle = $("#contentTitle").val();
            var types = $("#types").val();
            var tags = $("#tags").val();
            var allowComment = $("#allowComment").val();
            var data = {
                titleName: titleName,
                titleImage: titleImage,
                contentTitle: contentTitle,
                types: types,
                tags: tags,
                allowComment: allowComment
            };
            $.ajax({
                type: 'post',
                url: '/admin/contents/article/publish',
                data: {article: JSON.stringify(data)},
                datatype: 'json',
                success: function (data) {
                    toastr.success('发布成功');
                },
                error: function (data) {
                    console.log(data);
                }
            });
        });

        function initArticlesPublish() {
            editormd("test-editormd", {
                width: "95%",
                height: 740,
                syncScrolling: "single",
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tex: true,  // 默认不解析
                flowChart: true,  // 默认不解析
                sequenceDiagram: true,  // 默认不解析
                //你的lib目录的路径，我这边用JSP做测试的
                path: "/static/editor.md/lib/",
                //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
                saveHTMLToTextarea: true
            });
        }

        initArticlesPublish();
    })(jQuery, window)
</script>