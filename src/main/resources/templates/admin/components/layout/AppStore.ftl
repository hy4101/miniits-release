<div style="padding: 10px 10px 10px 10px">
    <div class="row clearfix" style="margin-bottom: 10px;">
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">应用名称
                    :</label>
                <input type="text" class="form-control" id="imageName" name="imageName"
                       style="flex: 4;"
                       aria-describedby="basic-addon3" placeholder="请输入图片名称">
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">应用URL
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
        </div>
    </div>
    <div>
        <div id="div_apps" style="display:flex;flex-wrap:wrap">
               <#list apps as app>
                   <div style="margin: 5px;border: 1px solid #c8c8c8;">
                       <a class="select-app-info" valueId="${app.number}" appName="${app.appTypeName}">
                           <img src="${app.appImageUrl}" style="width: 400px;height: 300px;cursor: pointer">
                       </a>
                       <div class="row">
                           <div class="col-md-8" style="line-height: 54px;"><label
                                   style="margin-left: 10px;">${app.appTypeName}</label></div>
                           <div class="col-md-4">
                           <#--下载量：  ${app.downloadNumber}-->
                               <button type="button" valueId="${app.number}" valueName="${app.appTypeName}"
                                       class="btn btn-link" style="float: right;margin: 10px;">
                                   ${app.downloadNumber}
                                   <i class="fa fa-star" aria-hidden="true"></i>
                               </button>
                           </div>
                       </div>
                   </div>
               </#list>
        </div>
        <nav aria-label="Page navigation" style="display: flex; justify-content: flex-end;">
             <#assign baseUrl="/admin/app-store/init?pageSize=16&">
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
    <#include "AppStoreDetailDialog.ftl"/>
    </div>
</div>
<script>
    (function ($, win) {
        $("#btn_images_refresh").click(function () {
            $("#imageName").val(null);
            $("#imageUrl").val(null);
            localStorage.removeItem('filter_name');
            localStorage.removeItem('filter_url');
            var params = {method: 'post', url: 'reset/filters-cache'};
            httpClient(params);
        });

        $("#btn_images_search").click(function () {
            var name = $("#imageName").val();
            var url = $("#imageUrl").val();
            var filters = '';
            if (!isEmpty(name)) {
                localStorage.setItem('filter_name', name);
                filters = 'LIKE_name=' + name
            }
            if (!isEmpty(url)) {
                localStorage.setItem('filter_url', url);
                filters += ';LIKE_url=' + url
            }
            win.location.href = "init?pageSize=16&pageNumber=1&sorts=-createDate&filters=" + filters;
        });

        $("#div_apps").on('click', ".select-app-info", function () {
            var appId = $(this).attr('valueId');
            var appName = $(this).attr('appName');
            $('#app_select_dialog_modal').modal();
            $('#app_name').text(appName);
            commitApp({appId: appId});
        });

        function imageInit() {
            $("#imageName").val(null);
            $("#imageUrl").val(null);
            var name = localStorage.getItem('filter_name');
            var url = localStorage.getItem('filter_url');
            if (!isEmpty(name)) {
                $("#imageName").val(name);
            }
            if (!isEmpty(url)) {
                $("#imageUrl").val(url);
            }
        }

        imageInit();
    })(jQuery, window)
</script>