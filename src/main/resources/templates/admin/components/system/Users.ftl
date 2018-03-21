<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/locale/bootstrap-table-zh-CN.min.js"></script>
<div style="margin-left: 250px">
    <div>
        <div class="alert alert-success" role="alert">...</div>
        <div class="alert alert-info" role="alert">...</div>
        <div class="alert alert-warning" role="alert">...</div>
        <div class="alert alert-danger" role="alert">...</div>
        <table id="table_users"></table>
    </div>
</div>
<script>


(function ($,win) {
    function userInit(){
        searchUsers("filters")
    }

    function searchUsers(filters){
        $('#table_users').bootstrapTable({
            columns: [{
                field: 'id',
                title: 'Item ID'
            }, {
                field: 'name',
                title: 'Item Name'
            }, {
                field: 'price',
                title: 'Item Price'
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                width : 100,
                events: operateEvents,
                formatter: operateFormatter
                }],
            data: [{
                id: 1,
                name: 'Item 1',
                price: '$1'
            }, {
                id: 2,
                name: 'Item 2',
                price: '$2'
            }],
            onClickRow:function(row, $element){
            }
        });
    }

  win.operateEvents = {
            'click .RoleOfdelete': function (e, value, row, index) {
                alert(row.id);
         },
            'click .RoleOfedit': function (e, value, row, index) {
                $("#editModal").modal('show');

            }
    };
    function operateFormatter(value, row, index) {
      return [
      '<button type="button" class="RoleOfdelete btn btn-primary  btn-sm" style="margin-right:15px;">删除</button>',

      '<button type="button" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;">修改</button>'
      ].join('');
      }

    userInit();



})(jQuery,window);



</script>

