<div style="padding: 10px 10px 10px 10px">
    <link href="/static/toastr/toastr.css" rel="stylesheet"/>
    <script src="/static/toastr/toastr.min.js"></script>
    <div class="row" style="margin-bottom: 10px">
        <div class="col-lg-2">
            <input type="text" class="form-control" placeholder="请输入标签名称" id="tagName"
                   aria-describedby="basic-addon1">
        </div>
        <div class="col-lg-6">
            <button id="add_tag_btn" type="button" class="btn"
                    style="background-color: #27AE60;color: #fff;margin-right: 10px;">
                <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <div id="div_tags">
    </div>
</div>
<script>
    toastr.options.positionClass = 'toast-top-center';
    (function ($, win) {

        function initTage() {
            searchTages();
        }

        function searchTages() {
            $("#div_tags").html('');
            $.ajax({
                type: 'get',
                url: '../tags',
                data: {
                    pageNumber: 1,
                    pageSize: 1000
                },
                dataType: 'json',
                success: function (data) {
                    var os = data.rows;
                    for (var i = 0; i < os.length; ++i) {
                        $("#div_tags").append(getHtml(os[i]))
                    }

                },
                error: function (data) {
                    toastr.error(data.responseText);
                }
            });
        }

        $("#add_tag_btn").click(function () {
            var tagName = $("#tagName").val();
            if (isEmpty(tagName)) {
                return;
            }
            if (tagName.length > 20) {
                return toastr.error("字符长度请控制在20以内，当前长度为：" + tagName.length);
            }
            $.ajax({
                type: 'POST',
                url: '../tags',
                data: {
                    name: tagName,
                    number: 0
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        toastr.success(data.message);
                        searchTages();
                    } else {
                        toastr.error(data.message);
                    }
                },
                error: function (data) {
                    toastr.error(data.responseText);
                }
            });
        })

        function getHtml(data) {
            var RGBA = '#' + Math.floor(Math.random() * 16777215).toString(16);
            return ' <div class="btn-group" style="margin: 5px 5px;">' +
                    '            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="background-color:' + RGBA + '" aria-haspopup="true"\n' +
                    '                    aria-expanded="false">\n' +
                    '                ' + data.name + ' <span class="badge" style="background-color: #13945d;    margin-left: 5px;\n' +
                    '    margin-right: 5px;">' + data.number + '</span><span class="caret"></span>\n' +
                    '            </button>\n' +
                    '            <ul class="dropdown-menu">\n' +
                    '                <li><button type="button" class="tag-delete btn btn-delete btn-sm" style="margin-right:15px;    width: 100%;    margin-bottom: 10px;"><i class="fa fa-trash-o" aria-hidden="true"></i></button></li>\n' +
                    '                <li><button type="button" class="tag-modify btn btn-default btn-sm" style="margin-right:15px;    width: 100%;"><i class="fa fa-pencil" aria-hidden="true"></i></button></li>\n' +
                    '            </ul>\n' +
                    '        </div>'
        }

        initTage()
    })(jQuery, window)
</script>