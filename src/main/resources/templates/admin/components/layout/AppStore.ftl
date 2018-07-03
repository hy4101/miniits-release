<div style="padding: 10px 10px 10px 10px">
    <link href="/static/toastr/toastr.css" rel="stylesheet"/>
    <script src="/static/toastr/toastr.min.js"></script>
    <div class="row clearfix" style="margin-bottom: 10px;">
        <div class="col-md-4 column">
            <div class="input-group" style="display: flex">
                <label for="txt_parentdepartment" class="label-form-group-title-item"
                       style="flex: 1;line-height: 29px;">应用名称
                    :</label>
                <input type="text" class="form-control" id="appName" name="appName"
                       style="flex: 4;"
                       aria-describedby="basic-addon3" placeholder="请输入图片名称">
            </div>
        </div>
        <div class="col-md-4 column">
            <button id="btn_apps_search" type="button" class="btn"
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
                       <a class="select-app-info" valueId="${app.am}" appName="${app.appTypeName}">
                           <img src="${app.appImageUrl}"
                                style="width: 400px;height: 300px;cursor: pointer;padding: 5px;">
                       </a>
                       <div class="row">
                           <div class="col-md-8" style="line-height: 54px;">
                               <label style="margin-left: 10px;">${app.appTypeName}</label>
                               <label style="margin-left: 10px;font-size: 10px;font-weight: 100;">下载：${app.downloadNumber}</label>
                           </div>
                           <div class="col-md-4">
                               <button type="button" am="${app.am}" sm="${app.sm}" valueName="${app.appTypeName}"
                                       data-loading-text="正在获取。。。"
                                       class="btn btn-link btn-get" style="float: right;margin: 10px;">
                                   获取
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
        var getApp = null;
        $("#btn_images_refresh").click(function () {
            $("#appName").val(null);
            localStorage.removeItem('filter_name');
            localStorage.removeItem('filter_url');
            var params = {method: 'post', url: 'reset/filters-cache'};
            httpClient(params);
        });

        $("#btn_apps_search").click(function () {
            var name = $("#appName").val();
            var filters = '';
            if (!isEmpty(name)) {
                localStorage.setItem('filter_name', name);
                filters = name
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
        $('#div_apps').on("click", ".btn-get", function () {
            getApp = $(this).button('loading');
            var am = $(this).attr('am');
            var sm = $(this).attr('sm');
            if (isEmpty(am) || isEmpty(sm)) {
                getApp.button('reset');
                return toastr.error('获取异常，您可以刷新后重试');
            }
            var param = {
                method: 'get', url: 'get-app', data: {am: am, sm: sm}, sessionId: 'get-app'
            };
            httpClient(param);
        });

        function appInit() {
            $("#appName").val(null);
            var name = localStorage.getItem('filter_name');
            if (!isEmpty(name)) {
                $("#appName").val(name);
            }
        }

        win.httpClientSuccess = function (data) {
            if (data.sessionId === 'get-app') {
                getApp.button('reset');
                var d = data.data;
                if (d.success) {
                    toastr.success(d.message);
                } else {
                    toastr.error(d.message);
                }
            }
        };
        appInit();
    })(jQuery, window)
</script>