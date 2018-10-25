<style>
    .bootstrap-select.form-control:not([class*=col-]) {
        width: 81% !important;
    }
</style>
<div style="padding: 10px 10px 10px 10px;display: flex">
    <link href="${request.contextPath}/static/toastr/toastr.css" rel="stylesheet"/>
    <script src="${request.contextPath}/static/toastr/toastr.min.js"></script>
    <link href="${request.contextPath}/static/editor.md/css/editormd.css" rel="stylesheet"/>
    <script src="${request.contextPath}/static/editor.md/src/editormd.js"></script>
    <link href="${request.contextPath}/static/tagsinput/jquery.tagsinput.css" rel="stylesheet"/>
    <script src="${request.contextPath}/static/tagsinput/jquery.tagsinput.min.js"></script>
    <script src="${request.contextPath}/static/select-tree-search/bootstrap-select.js" type="text/javascript"></script>
    <link href="${request.contextPath}/static/select-tree-search/bootstrap-select.css" rel="stylesheet" type="text/css">

    <div style="width: 100%;">
        <div class="modal-content" style="height: 848px;">
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
                <div class="row clearfix f-mb10">
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
                                   style="flex: 1;line-height: 29px;">概述
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
                <div class="row clearfix f-mb10">
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
                            <option value="100000001" ${(article?exists&&article.allowComment==100000001)?string('selected', '')}>
                                允许
                            </option>
                            <option value="100000002" ${(article?exists&&article.allowComment==100000002)?string('selected', '')}>
                                禁言
                            </option>
                        </select>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 column" style="display: flex">
                        <label for="txt_departmentlevel" class="label-form-group-title-item"
                               style="flex: 1;line-height: 29px;">关键字
                            : </label>
                            <#if article?exists&&article.keys?exists>
                            <input type="text" class="form-control" id="keys" name="keys"
                                   style="flex: 4;" value="${article.keys}"
                                   aria-describedby="basic-addon3" placeholder="请输入关键字（多个逗号隔开）">
                            <#else>
                         <input type="text" class="form-control" id="keys" name="keys"
                                style="flex: 4;"
                                aria-describedby="basic-addon3" placeholder="请输入关键字（多个逗号隔开）">
                            </#if>
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
        </div>
    </div>
<#include "ImagesDialog.ftl"/>
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
            var keys = $("#keys").val();
            var data = {
                id: id,
                titleName: titleName,
                titleImage: titleImage,
                contentTitle: contentTitle,
                content: content,
                typeNames: typeNames,
                tags: tags,
                keys: keys,
                allowComment: allowComment
            };
            var param = {
                method: 'post', url: '${request.contextPath}/admin/contents/article/publish', data: {article: JSON.stringify(data)},
                sessionId: 'articles-refresh'
            };
            httpClient(param);
            toastr.success('发布成功');
        });

        function initArticlesPublish() {
            editor = editormd("div-editormd", {
                width: "95%",
                toolbarIconsClass: {
                    imageUpload: "fa-cloud-upload"  // 指定一个FontAawsome的图标类
                },
                // 自定义工具栏按钮的事件处理
                toolbarHandlers: {
                    /**
                     * @param {Object}      cm         CodeMirror对象
                     * @param {Object}      icon       图标按钮jQuery元素对象
                     * @param {Object}      cursor     CodeMirror的光标对象，可获取光标所在行和位置
                     * @param {String}      selection  编辑器选中的文本
                     */
                    imageUpload: function (cm, icon, cursor, selection) {
                        $('#div_select_image_modal').modal();
                    },

                    testIcon2: function (cm, icon, cursor, selection) {
                        cm.replaceSelection("[" + selection + ":testIcon2](" + icon.html() + ")");
                        console.log("testIcon2 =>", this, icon.html());
                    }
                },
                height: 610,
                syncScrolling: "single",
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tex: true,  // 默认不解析
                flowChart: true,  // 默认不解析
                sequenceDiagram: true,  // 默认不解析
                path: "/static/editor.md/lib/",
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
                url: '${request.contextPath}/admin/categorys/category-and-tag',
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