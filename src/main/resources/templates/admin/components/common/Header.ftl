<div>
    <header class="main-header">
        <a href="/admin/dashboard/init" class="logo">
            <span class="logo-mini"><b>M+</b></span>
            <span class="logo-lg"><b>MiniIts</b></span>
        </a>
        <nav class="navbar navbar-static-top f-theme-c" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu" style="line-height: 46px">
                        <button id="example" type="button" style="background-color: #f4f4f400;" class="btn btn-default"
                                data-container="body"
                                data-toggle="popover" data-placement="bottom" data-html="true"
                                data-content='<div id="cp1" data-color="#27AE60">
  <input type="text" class="form-control" style="width:auto"/> <br>
</div>'>
                            <i class="fa fa-chevron-down" aria-hidden="true"></i>
                        </button>

                    </li>
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="http://www.miniits.com" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/static/images/default_user.png" class="user-image" alt="User Image">
                            <span class="hidden-xs">M+</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="/static/images/default_user.png" class="img-circle" alt="User Image">
                                <p>
                                <#--${user.userName}-->
                                    <small>
                                    <@shiro.authenticated>
                                        欢迎：<@shiro.principal property="userName"/>
                                    </@shiro.authenticated>
                                    </small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                <#--<a href="#" class="btn btn-default btn-flat">Profile</a>-->
                                </div>
                                <div class="pull-right">
                                    <form action="/admin/login/logout" method="post">
                                        <button type="submit" class="btn btn-link">注 销</button>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
</div>
<script>
    (function ($, win) {
        // $('body').on('click', function (event) {
        //     var target = $(event.target);
        //     //弹窗内部点击不关闭
        //     if (!target.hasClass('popover')
        //             && target.parent('.popover-content').length === 0
        //             && target.parent('.popover-title').length === 0
        //             && target.parent('.popover').length === 0
        //             && target.data("toggle") !== "popover") {
        //         //弹窗触发列不关闭，否则显示后隐藏
        //         $('[data-toggle="popover"]').popover('hide');
        //     }
        // });
        var themeColor = localStorage.getItem("f-theme-c");
        //
        console.log(themeColor)
        if (isEmpty(themeColor)) {
            themeColor = '#27AE60';
        }
        $(".f-theme-c").css("background-color", themeColor);
        $('#example').popover();
        $('#example').on('shown.bs.popover', function () {
            $('#cp1').data("color", themeColor);
            $('#cp1').colorpicker({
                inline: true,
                container: true
            }).on('colorpickerChange colorpickerCreate', function (event) {
                console.log(event.color.toString());
                localStorage.setItem("f-theme-c", event.color.toString());
                $(".f-theme-c").css("background-color", event.color.toString());
            });
        })
    })(jQuery, window)
</script>