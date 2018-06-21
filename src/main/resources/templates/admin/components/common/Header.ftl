<div>
    <header class="main-header">
        <a href="/admin/dashboard/init" class="logo">
            <span class="logo-mini"><b>M+</b></span>
            <span class="logo-lg"><b>MiniIts</b></span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
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