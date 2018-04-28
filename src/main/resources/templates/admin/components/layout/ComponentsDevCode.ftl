<style>
    .bootstrap-select.form-control:not([class*=col-]) {
        width: 81% !important;
    }

    #codes textarea {
        display: none;
    }
</style>
<div style="padding: 10px 10px 10px 10px;display: flex">
    <link href="/static/toastr/toastr.css" rel="stylesheet"/>
    <script src="/static/toastr/toastr.min.js"></script>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css"/>
    <#--<link rel="stylesheet" href="/static/editor.md/examples/css/style.css"/>-->
    <script src="/static/editor.md/editormd.js"></script>
    <div style="width: 100%;">
        <div class="modal-content" style="height: 100%;">
            <div class="modal-header">
                <h4 class="modal-title" style="font-size: xx-large;">
                    <label style="font-weight: normal;float: left;">组件开发-编程式</label>
                    <button id="save_article_btn" type="submit" class="btn btn-primary"
                            style="float: right;background-color: #3c8dbc;">
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
                        <#if development?exists>
                           <input type="text" class="form-control" id="componentName" name="componentName"
                                  style="flex: 4;" value="${development.componentName}"
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
                        <#if development?exists>
                           <select class="form-control input-form-group-value-item" id="componentBodyApi"
                                   style="flex: 4;"
                                   name="componentBodyApi">
                               <option value="">请选择组件API（静态组件无需选择 API）</option>
                               <option value="100007001=article/search" ${(development?exists&&development.filters=='100007001=article/search')?string('selected', '')}>查文章-多条</option>
                               <option value="100007001=article/search-one" ${(development?exists&&development.filters=='100007001=article/search-one')?string('selected', '')}>查文章-单条</option>
                           </select>
                        <#else>
                               <select class="form-control input-form-group-value-item" id="componentBodyApi"
                                       style="flex: 4;"
                                       name="componentBodyApi">
                                   <option value="">请选择组件API（静态组件无需选择 API）</option>
                                   <option value="100007001=article/search">查文章-多条</option>
                                   <option value="100007001=article/search-one">查文章-单条</option>
                                   <option value="100007001=image/search">查图片-多条</option>
                                   <option value="100007001=image/search-one">查图片-单条</option>
                                   <option value="100007001=category/search">查分类-多条</option>
                                   <option value="100007001=category/search-one">查分类-单条</option>
                                   <option value="100007001=links/search">查友链-多条</option>
                                   <option value="100007001=links/search-one">查友链-单条</option>
                                   <option value="100007001=tag/search">查标签-多条</option>
                                   <option value="100007001=tag/search-one">查标签-单条</option>
                               </select>
                        </#if>
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div class="input-group" style="display: flex">
                            <label for="txt_parentdepartment" class="label-form-group-title-item"
                                   style="flex: 1;line-height: 29px;">查询条件
                                :</label>
                             <#if development?exists>
                               <textarea placeholder="这里可以编辑你查询数据的条件" style="flex: 4;" id="dataFilters">${development.dataFilters}</textarea>
                             <#else>
                                 <textarea placeholder="这里可以编辑你查询数据的条件" style="flex: 4;" id="dataFilters"></textarea>
                             </#if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="editormd" id="div-editormd">
                <#if development?exists>
                    <textarea style="display:none;">${development.componentBody}</textarea>
                <#else>
                    <textarea style="display:none;"></textarea>
                </#if>
            </div>
        </div>
    </div>
</div>
<script>
    (function ($, win) {
        var categorys = null;
        var seTags = null;
        var editor = null;

        $("#save_article_btn").click(function () {
            if (isEmpty($("#componentName").val())) {
                return toastr.error('组件名称不能为空');
            }
            var html = editor.getValue();
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
            var id = null;
               <#if development??&&development.id??>
                   id = '${development.id}';
               </#if>

            var component = {
                id: id,
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
                width: "90%",
                height: 1000,
                watch: false,
                toolbar: false,
                codeFold: true,
                searchReplace: true,
                placeholder: "Enjoy coding!",
                path: "/static/editor.md/lib/",
                value: (localStorage.mode) ? $("#" + localStorage.mode.replace("text/", "") + "-code").val() : $("#html-code").val(),
                theme: (localStorage.theme) ? localStorage.theme : "default",
                mode: (localStorage.mode) ? localStorage.mode : "text/html"
            });
            // debugger;
        <#--<#if development??>-->
        <#--debugger;-->
        <#--editor.setValue(${development.componentBody});-->
        <#--</#if>-->
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