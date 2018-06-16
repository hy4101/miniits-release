<html>
<head>
    <meta charset="UTF-8">
    <meta mplushtmlmapping="2018">
    <title>登入-M-Plus</title>
    <script src="/static/js/jQuery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/css/bootstrap.css">
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <link href="/static/css/font-awesome/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/Miniits_Common.css" rel="stylesheet">
    <link href="/static/toastr/toastr.css" rel="stylesheet"/>
    <script src="/static/toastr/toastr.min.js"></script>
</head>
<body class="f-bc">
<style>
    .f-w80 {
        width: 80%;
    }
</style>
<div style="width:100%;height:100%;display: flex;justify-content: center;align-items: center">
    <div class="row" style="display: flex;justify-content: center;align-items: center;width: 100%;">
        <div class="col-md-6" style="padding-left: 10%;">
            <img src="/static/images/login-img.png">
        </div>
        <div class="col-md-6" style="display: flex; justify-content: center;">
            <div class="f-bc-fff"
                 style="width: 50%; height: 300px;display: flex;justify-content: center;align-items: center">
                <form id="f_login" method="post" class="f-w80" style="display: flex;flex-direction: column;"
                      action="/admin/login">
                    <div class="input-group f-mt5 f-mb10 txt-align-center">
                        <h2>M+</h2>
                    </div>
                    <div class="input-group f-mt5 f-mb10">
                        <input type="text" name="userName" class="form-control" placeholder="用户名"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group f-mt5 f-mb10">
                        <input type="text" name="password" class="form-control" placeholder="密 码"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group f-mt5 f-mb10">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" id="inp_code" name="verificationCode" class="form-control" placeholder="验证码" aria-describedby="basic-addon1">
                            </div>
                            <div class="col-md-4">
                                <input type="text" id="code" class="form-control" placeholder="验证码" disabled aria-describedby="basic-addon1">
                            </div>
                            <div class="col-md-2">
                                <i style="line-height: 34px;cursor: pointer;" class="fa fa-refresh" id="i_refresh_code" aria-hidden="true"></i>
                            </div>
                        </div>
                    </div>
                    <div class="input-group f-mt5 f-mb10">
                        <a id="a_login" class="btn btn-link" style="float: right" type="submit">登 入
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    (function ($, win) {

        var code = '';
        var baseStr = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R'
            , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
            , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];

        $("#a_login").click(function () {
            var iCode = $("#inp_code").val();
            if (iCode !== code) {
                toastr.error('请输入正确的验证码,区分大小写');
                // return;
            }
            $("#f_login").submit();
        });

        $("#i_refresh_code").click(function () {
            code = "";
            getCode();
        });
        function getCode(n, m) {
            for (var i = 0; i < 4; i++) {
                var ds = rnd();
                var b = baseStr[ds];
                code = code + b;
            }
            $("#code").val(code);
        }

        function rnd() {
            return Math.floor(Math.random() * (61 - 1 + 1) + 1);
        }

        getCode();
    })(jQuery, window)
</script>
</body>
</html>