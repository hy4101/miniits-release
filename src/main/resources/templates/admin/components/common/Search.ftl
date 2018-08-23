<#macro form options>


    <#--<#assign toupper = 'com.miniits.base.controller.FreemarkerController'?new() />-->
<#--<td>${toupper(1)}</td>-->


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
                   <select name="${item.name!''}"
                           class="form-control input-form-group-value-item" ${item.placeholder!''}
                           name="pageStatus">
                       <option value=""></option>
                       <option value="100000001">启用</option>
                       <option value="100000002">禁用</option>
                   </select>
               </div>
           </div>
        </#if>
    </#list>
</div>
</#macro>
