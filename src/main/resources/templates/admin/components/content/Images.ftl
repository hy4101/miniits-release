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
        <div id="div_images" style="display:flex;flex-wrap:wrap">
               <#list images as image>
                   <div style="margin: 5px;border: 1px solid #c8c8c8;">
                       <img src="${image.url}" style="width: 400px;height: 300px"><br>
                       <i class="fa fa-trash-o" style="font-size: 30px; color: red;float: right;margin: 10px;" aria-hidden="true"></i>
                   </div>
               </#list>
        </div>
        <nav aria-label="Page navigation">
             <#assign baseUrl="/admin/images/init?pageSize=16&">
            <ul class="pagination">
                <#if (thisPageNumber>1)>
                <li>
                    <a href="${baseUrl}pageNumber=${thisPageNumber-1}&sorts=-createDate&filters=" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                </#if>
            <#list pageNumbers as pn>
                <li class="${(pn == thisPageNumber)?string('active','')}">
                    <a href="${baseUrl}pageNumber=${pn}&sorts=-createDate&filters=">${pn}</a>
                </li>
            </#list>
            <#if (thisPageNumber<totalPageNumber)>
                <li>
                    <a href="${baseUrl}pageNumber=${thisPageNumber+1}&sorts=-createDate&filters=" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </#if>
            </ul>
        </nav>
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
            // searchImages();
        }

        function searchImages(e) {
            $.ajax({
                type: 'get',
                url: '../images',
                datatype: 'json',
                data: {
                    pageSize: 1,                         //页面大小
                    pageNumber: 1,   //页码
                    sorts: '-createDate',      //排序列名
                    filters: "" //排位命令（desc，asc）
                },
                success: function (data) {
                    debugger;
                },
                error: function (data) {
                    console.log(data)
                }
            });
        }

        imageInit();
    })(jQuery, window)
</script>