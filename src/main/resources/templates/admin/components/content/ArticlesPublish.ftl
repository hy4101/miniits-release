<style>
    .bootstrap-select.form-control:not([class*=col-]) {
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

    <script type="text/javascript" src="/static/select-tree-search/bootstrap-select.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/select-tree-search/bootstrap-select.css">

    <div style="width: 100%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="font-size: xx-large;">
                    发布文章
                    <script async id="chevereto-pup-src" src="https://imgchr.com/sdk/pup.js"
                            data-url="https://imgchr.com/upload" data-auto-insert="bbcode-embed-medium"></script>
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
                        <#if article?exists>
                           <input type="text" class="form-control" id="titleName" name="titleName"
                                  style="flex: 4;" value="${article.titleName}"
                                  aria-describedby="basic-addon3" placeholder="请输入标题名称">
                        <#else>
                         <input type="text" class="form-control" id="titleName" name="titleName"
                                style="flex: 4;"
                                aria-describedby="basic-addon3" placeholder="请输入标题名称">
                        </#if>
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">标题图片
                                :</label>
                        <#if article?exists>
                            <input type="text" class="form-control" id="titleImage" name="titleImage"
                                   style="flex: 4;" value="${article.titleImage}"
                                   aria-describedby="basic-addon3" placeholder="请使用图片外链">
                        <#else>
                            <input type="text" class="form-control" id="titleImage" name="titleImage"
                                   style="flex: 4;"
                                   aria-describedby="basic-addon3" placeholder="请使用图片外链">
                        </#if>
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">标题内容
                                :</label>
                             <#if article?exists>
                            <input type="text" class="form-control" id="contentTitle" name="contentTitle"
                                   style="flex: 4;" value="${article.contentTitle}"
                                   aria-describedby="basic-addon3" placeholder="输入标题内容">
                             <#else>
                             <input type="text" class="form-control" id="contentTitle" name="contentTitle"
                                    style="flex: 4;"
                                    aria-describedby="basic-addon3" placeholder="输入标题内容">
                             </#if>
                        </div>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">类别
                            : </label>
                        <select id="sel_category_select" class="selectpicker form-control bla bla bli" multiple
                                data-live-search="true">
                        </select>
                    </div>
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">标签
                            : </label>
                    <#--<input name="tags" id="tags" style="flex: 4" type="text" class="form-control"-->
                    <#--placeholder="请填写文章标签"/>-->
                        <select id="sel_tags" class="sel_tags form-control bla bla bli" multiple
                                data-live-search="true">
                        </select>
                    </div>
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">是否允许评论
                            : </label>
                        <select class="form-control input-form-group-value-item" id="allowComment" style="flex: 4"
                                name="allowComment">
                            <option value="100000001" ${(article?exists&&article.status==100002001)?string('selected', '')}>
                                允许
                            </option>
                            <option value="100000002" ${(article?exists&&article.status==100002002)?string('selected', '')}>
                                禁言
                            </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="editormd" id="div-editormd">
                <#if article?exists>
                           <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc"
                                     id="textarea-text">${article.content}</textarea>
                <#else>
                        <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc"
                                  id="textarea-text"></textarea>
                </#if>
                <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
                <textarea class="editormd-html-textarea" name="text"></textarea>
            </div>
            <textarea id="copy_text"></textarea>
        </div>
    </div>
    <div>
        <script async id="chevereto-pup-src" src="https://imgchr.com/sdk/pup.js" data-url="https://imgchr.com/upload"
                data-auto-insert="bbcode-embed-medium"></script>
    </div>
</div>
<script>
    (function ($, win) {
        var categorys = null;
        var seTags = null;
        var editor = null;
        $('#tags').tagsInput({
            width: '81%',
            height: '35px',
            defaultText: '输入标签'
        });

        $("#save_article_btn").click(function () {
            var titleName = $("#titleName").val();
            var id = null;
               <#if article??&&article.id??>
                   id = '${article.id}';
               </#if>
            if (isEmpty(titleName)) {
                return toastr.error('文章名称不能为空');
            }
            var content = editor.getMarkdown();
            if (isEmpty(content)) {
                return toastr.error('文章内容不能为空');
            }
            var titleImage = $("#titleImage").val();
            var contentTitle = $("#contentTitle").val();
            var types = $("#types").val();
            var typeNames = categorys.val().join();
            var tags = seTags.val().join();
            var allowComment = $("#allowComment").val();
            var data = {
                id: id,
                titleName: titleName,
                titleImage: titleImage,
                contentTitle: contentTitle,
                content: content,
                typeNames: typeNames,
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
            editor = editormd("div-editormd", {
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
                // imageUpload : true,
                // imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                // imageUploadURL : "/smart-api/upload/editormdPic/",
            });
              <#if article??&&article.content??>
              <#--var text = '${article.content!}';-->
                  // $("#textarea-text").val(text);
              </#if>
            searchCategorys();
        }

        function searchCategorys() {
            $.ajax({
                type: 'get',
                url: '/admin/categorys/category-and-tag',
                data: {
                    pageSize: 1000,
                    pageNumber: 1,
                    sorts: '-createDate',
                    filters: ''
                },
                success: function (data) {
                    var o = data.object;
                    for (var i = 0; i < o.categories.length; i++) {
                        var category = o.categories[i];
                        $("#sel_category_select").append('<option value="' + category.categoryName + '">' + category.categoryName + '</option>');
                    }

                    for (var i = 0; i < o.tags.length; i++) {
                        var tag = o.tags[i];
                        $("#sel_tags").append('<option value="' + tag.name + '">' + tag.name + '</option>');
                    }
                    categorys = $('#sel_category_select').selectpicker({});
                    seTags = $('.sel_tags').selectpicker({});
                <#if article??&&article.typeNames??>
                    var tnvs = '${article.typeNames}';
                    $('#sel_category_select').selectpicker('val', tnvs.split(','));
                </#if>
                <#if article??&&article.tags??>
                    var tgvs = '${article.tags}';
                    $('.sel_tags').selectpicker('val', tgvs.split(','));
                </#if>
                },
                error: function (data) {
                    console.log(data);
                }
            });
        }

        initArticlesPublish();
    })(jQuery, window)
</script>