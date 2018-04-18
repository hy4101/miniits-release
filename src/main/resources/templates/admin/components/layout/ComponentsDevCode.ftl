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
        <div class="modal-content" style="height: 100%;">
            <div class="modal-header">
                <h4 class="modal-title" style="font-size: xx-large;">
                    组件开发
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
                                   style="flex: 1;line-height: 29px;">组件名称
                                :</label>
                        <#if article?exists>
                           <input type="text" class="form-control" id="componentName" name="componentName"
                                  style="flex: 4;" value="${article.titleName}"
                                  aria-describedby="basic-addon3" placeholder="请输入标题名称">
                        <#else>
                         <input type="text" class="form-control" id="componentName" name="componentName"
                                style="flex: 4;"
                                aria-describedby="basic-addon3" placeholder="请输入标题名称">
                        </#if>
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">组件API
                                :</label>
                        <#if article?exists>
                           <select class="form-control input-form-group-value-item" id="componentBodyApi"
                                   style="flex: 4;"
                                   name="pageStatus">
                               <option value="">请选择组件API（静态组件无需选择 API）</option>
                               <option value="100007001=article/search">查文章-多条</option>
                               <option value="100007001=article/search-one">查文章-单条</option>
                           </select>
                        <#else>
                               <select class="form-control input-form-group-value-item" id="componentBodyApi"
                                       style="flex: 4;"
                                       name="pageStatus">
                                   <option value="">请选择组件API（静态组件无需选择 API）</option>
                                   <option value="100007001=article/search">查文章-多条</option>
                                   <option value="100007001=article/search-one">查文章-单条</option>
                               </select>
                        </#if>
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">查询条件
                                :</label>
                             <#if article?exists>
                               <textarea placeholder="这里可以编辑你查询数据的条件" style="flex: 4;" id="dataFilters"></textarea>
                             <#else>
                                 <textarea placeholder="这里可以编辑你查询数据的条件" style="flex: 4;" id="dataFilters"></textarea>
                             </#if>
                        </div>
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
            if (isEmpty($("#componentName").val())) {
                return toastr.error('组件名称不能为空');
            }
            var html = editor.getHTML();
            if (isEmpty(html)) {
                return toastr.error('组件内容不能为空');
            }
            saveComponent(html);
        });

        function saveComponent(element) {
            var componentBodyApi = $("#componentBodyApi").val();
            var componentName = $("#componentName").val();
            var dataFilters = $("#dataFilters").val();
            if ((!isEmpty(componentBodyApi)) && (isEmpty(dataFilters))) {
                return toastr.error('选择了api，必须填写过滤条件');
            }
            var component = {
                componentBodyApi: componentBodyApi.split('=')[1],
                apiDataStructureType: componentBodyApi.split('=')[0],
                dataFilters: dataFilters,
                componentName: componentName,
                componentBody: element
            };
            $.ajax({
                type: 'post',
                url: '/admin/components/save',
                datatype: 'json',
                data: component,
                success: function (data) {
                    toastr.success('组件添加成功');
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

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