<#macro form options formId>
<link rel="stylesheet" href="/static/css/selectpage.css" type="text/css">
<script type="text/javascript" src="/static/js/selectpage.js"></script>

<script>
    console.log("初始化查询表单。。。");
    /**
     * 查询表单的通用组件
     * 使用说明：
     *      在需要查询的页面映入如下两行标签
    <#-- <#import "../common/Search.ftl" as search>-->
    <#--<@search.form options=[{"type":"input","name":"fd","label":"名称","placeholder":"请输入名称"},-->
    <#--{"type":"select","name":"sss","label":"用户状态",value:[{name:1,value:2},{name:1-1,value:2-2}]}]-->
    <#--formId="search_user_form" />-->
     * 标签的使用说明:
     *      第一行直接复制使用，
     *      第二行为配置你的查询form表单的参数配置
     *          formId:查询表单的id，必填
     *          options：数组对象格式，每个对象为查询元素类型，[{
     *              type:元素类型，必填，可选值：input , select。
     *              name：元素的 name 属性，必填。
     *              label：显示名称。
     *              placeholder：元素内的占位符（提示语）
     *              value：数组对象格式，只对type=select的元素有用.
     *              target: 获取远程数据，只对type=select的元素有用,可选值：DICT , USER。
     *              filters: 获取远程数据的查询条件，只对type=select的元素并且是远程数据时有用,
     *              pageSize: 获取远程数据的分页条件（显示条数），只对type=select的元素并且是远程数据分页时有用,
     *              pageNumber: 获取远程数据的分页条件（显示页码），只对type=select的元素并且是远程数据分页时有用,
     *              sorts: 获取远程数据的排序条件，只对type=select的元素并且是远程数据时有用,
     *          }]
     *
     */
</script>
        <form id="${formId!''}">
            <div class="row">
    <#list options as item>
        <#if item.type == 'input'>
              <div class="col-lg-4">
                  <div class="input-group">
                      <span class="input-group-addon">${item.label!''}：</span>
                      <input type="text" name="${item.name!''}" class="form-control"
                             placeholder="${item.placeholder!''}"
                             aria-describedby="basic-addon1">
                  </div>
              </div>
        <#elseif item.type == 'select'>
           <div class="col-lg-4">
               <div class="input-group">
                   <span class="input-group-addon">${item.label!''}：</span>
                       <#if item.value?exists>
                           <select name="${item.name!''}"
                                   class="form-control input-form-group-value-item" ${item.placeholder!''}
                                   name="pageStatus">
                               <#list item.value as o>
                                   <option value="${o.name!''}">${o.value!''}</option>
                               </#list>
                           </select>
                       <#elseif item.target?exists>
                       <input type="text" id="${item.key}">
                       <script>
                           (function ($, win) {
                               searchSelect("${item.key!''}", "${item.target!''}", "${item.filters!''}", "${item.filtersConnection!''}", "${item.pageSize!''}", "${item.pageNumber!''}", "${item.sorts!''}", "${item.showField!''}");
                               function searchSelect(key, target, filters, filtersConnection, pageSize, pageNumber, sorts, showField) {
                                   showField = showField.split(",");
                                   $('#' + key + '').selectPage({
                                       showField: isEmpty(showField) ? 'name' : showField[0],
                                       keyField: isEmpty(showField) ? 'id' : showField[1],
                                       pageSize: pageSize || 15,
                                       target: target,
                                       data: '/admin/search-form',
                                       params: function () {
                                           return {field: filtersConnection, sorts: sorts, filters: filters};
                                       },
                                       eAjaxSuccess: function (d) {
                                           var result;
                                           if (d) {
                                               result = d
                                           } else {
                                               result = undefined
                                           }
                                           return result;
                                       }
                                   });
                               }

                           })(jQuery, window)
                       </script>
                       <#--<#assign toupper = 'com.miniits.base.controller.FreemarkerController'?new() />-->
                       <#--<option value="123"> ${toupper(item.filters!'',"12")}</option>-->
                       </#if>
               </div>
           </div>
        </#if>
    </#list>
            </div>
        </form>
<#--<script>-->
<#--(function ($, win) {-->
<#--function searchSelect(key, target, filters, filtersConnection, pageSize, pageNumber, sorts, showField) {-->
<#--$('#' + key + '').selectPage({-->
<#--showField: isEmpty(showField) ? 'name' : showField.split(",")[0],-->
<#--keyField: isEmpty(showField) ? 'id' : showField.split(",")[1],-->
<#--pageSize: pageSize || 15,-->
<#--target: target,-->
<#--data: '/admin/search-form',-->
<#--params: function () {-->
<#--return {field: filtersConnection, sorts: sorts, filters: filters};-->
<#--},-->
<#--eAjaxSuccess: function (d) {-->
<#--var result;-->
<#--if (d) {-->
<#--result = d-->
<#--} else {-->
<#--result = undefined-->
<#--}-->
<#--return result;-->
<#--}-->
<#--});-->
<#--}-->

<#--})(jQuery, window)-->
<#--</script>-->
</#macro>
