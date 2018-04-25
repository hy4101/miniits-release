<div style="padding: 10px 10px 10px 10px">
    <div class="row clearfix" style="margin-bottom: 10px;">
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">图片名称
                    :</label>
                <input type="text" class="form-control" id="imageName" name="imageName"
                       style="flex: 4;"
                       aria-describedby="basic-addon3" placeholder="请输入图片名称">
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">图片URL
                    :</label>
                <input type="text" class="form-control" id="imageUrl" name="imageUrl"
                       style="flex: 4;"
                       aria-describedby="basic-addon3" placeholder="输入图片URL">
            </div>
        </div>
        <div class="col-md-4 column">
            <button id="btn_images_search" type="button" class="btn"
                    style="background-color: #27AE60;color: #fff;">
                <i class="fa fa-search" aria-hidden="true"></i>
            </button>
            <button id="btn_images_refresh" type="button" class="btn"
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
                       <a class="select-image-info" valueId="${image.id}">
                           <img src="${image.url}" style="width: 400px;height: 300px">
                       </a>
                       <div class="row">
                           <div class="col-md-8" style="line-height: 54px;"><label
                                   style="margin-left: 10px;">${image.name}</label></div>
                           <div class="col-md-4">
                               <button type="button" valueId="${image.id}" valueName="${image.name}"
                                       class="btn btn-danger btn-delete"
                                       style="float: right;margin: 10px;">
                                   <i class="fa fa-trash-o" style="" aria-hidden="true"></i>
                               </button>
                           <#--<button type="button" valueUrl="${image.url}"-->
                           <#--class="btn btn-danger cope-btn"-->
                           <#--style="float: right;margin: 10px;">-->
                           <#--<i class="fa fa-trash-o" style="" aria-hidden="true"></i>-->
                           <#--</button>-->
                           </div>
                       </div>
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
    <#include "ImagesSelectDialog.ftl"/>
    </div>
</div>
<script>
    (function ($, win) {
        $("#btn_images_refresh").click(function () {
            $("#imageName").val(null);
            $("#imageUrl").val(null);
        });

        $("#btn_images_search").click(function () {

        });

        $("#div_images").on('click', ".select-image-info", function () {
            var imageId = $(this).attr('valueId');
            $('#image_select_dialog_modal').modal();
            commitImage({imageId: imageId});
        })
        $('#div_images').on("click", ".btn-delete", function () {
            var imageId = $(this).attr('valueId');
            var imageName = $(this).attr('valueName');
            Mini.confirm({
                message: "您确认要删除 <b style='color: red'>" + imageName + "</b> ？",
                btnok: '是的！确认删除'
            }).on(function (e) {
                if (!e) {
                    return;
                }
                $.ajax({
                    type: 'delete',
                    url: imageId,
                    datatype: 'json',
                    success: function (data) {
                        location.reload();
                    },
                    error: function (data) {
                        console.log(data)
                    }
                });
            });
        });

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