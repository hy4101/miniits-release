<div class="modal fade" id="category_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <form id="category_form" name="category_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="new_category_title" style="font-size: xx-large;">新增</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group div-form-group">
                        <label for="txt_departmentname" class="label-form-group-title-item">类别名称 :</label>
                        <input type="text" name="inp_categoryName" class="form-control input-form-group-value-item"
                               id="inp_categoryName"
                               placeholder="请输入类别名称">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <i class="fa fa-times" aria-hidden="true"></i>
                        关闭
                    </button>
                    <button type="submit" id="btn_save_category" class="btn btn-primary" data-dismiss="modal">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                        保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    (function ($, win) {

        var categoryData = null;
        var row = null;
        var isChild = false;

        function categoryCreateOrModifyInit() {

        }

        $("#btn_save_category").click(function () {
            var categoryName = $("#inp_categoryName").val();
            if (isChild) {
                var child = {
                    pId: row.id,
                    level: ++row.level,
                    categoryName: categoryName
                };
                row = child;
            } else {
                row.categoryName = categoryName;
            }

            var param = {
                method: 'POST', url: '${request.contextPath}/admin/categorys', data: {
                    category: JSON.stringify(row)
                }, sessionId: 'category-refresh'
            };
            httpClient(param);
            categoryData = null;
            row = null;
            isChild = false;
        });

        win.commitCategory = function (data) {
            categoryData = data;
            row = data.row;
            if (isEmpty(data.child)) {
                $("#inp_categoryName").val(row.categoryName);
            } else {
                isChild = true;
            }
        };

        categoryCreateOrModifyInit();
    })(jQuery, window)
</script>