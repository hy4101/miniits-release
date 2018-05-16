<style>
    .menu-item {
        line-height: 40px;
    }

    .menu-title-text {
        font-size: 15px;
    }
</style>
<div class="div-main-menu">
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/static/images/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Alexander Pierce</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- search form -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i
                        class="fa fa-search"></i></button>
              </span>
                </div>
            </form>
            <ul class="sidebar-menu">
                <li class="header">MAIN NAVIGATION</li>
                <li class="${(active == 'Dashboard')?string('active', '')} treeview">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i
                            class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                        <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                    </ul>
                </li>
                <li class="${(active == 'users')?string('active', '')} treeview">
                    <a href="#">
                        <i class="fa fa-files-o"></i>
                        <span>系统管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li class="menu-item"><a href="../users/init"><i class="fa fa-user" aria-hidden="true"></i>用户管理</a>
                        </li>
                    </ul>
                </li>
                <li class="${(active == 'layout')?string('active', '')} treeview">
                    <a href="#">
                        <i class="fa fa-files-o"></i>
                        <span>布局管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                        <span class="label label-primary pull-right">4</span>
                    </a>
                    <ul class="treeview-menu">
                        <li class="menu-item"><a href="/admin/pages/init"><i class="fa fa-paperclip"
                                                                             aria-hidden="true"></i>页面管理</a></li>
                        <li class="menu-item"><a href="/admin/components/init"><i class="fa fa-puzzle-piece"
                                                                                  aria-hidden="true"></i>组件管理</a></li>
                        <li class="menu-item"><a href="/admin/components/init-development-code"><i
                                class="fa fa-puzzle-piece" aria-hidden="true"></i>组件开发-编程式</a></li>
                        <li class="menu-item"><a href="/admin/components/init-development"><i class="fa fa-puzzle-piece"
                                                                                              aria-hidden="true"></i>组件开发-拖拽式</a>
                        </li>
                        <li class="menu-item"><a href="/admin/components/init"><i class="fa fa-cubes"
                                                                                  aria-hidden="true"></i>系统页面</a></li>
                        <li class="menu-item">
                            <a href="/admin/components/init">
                                <i class="fa fa-file-code-o" aria-hidden="true"></i>
                                系统组件
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/admin/components/init">
                                <i class="fa fa-shopping-bag" aria-hidden="true"></i>
                                DIY市场
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="${(active == 'content')?string('active', '')} treeview">
                    <a href="pages/widgets.html">
                        <i class="fa fa-th"></i> <span>内容管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li class="menu-item"><a href="/admin/contents/article/publish/init?id="><i
                                class="fa fa-paperclip" aria-hidden="true"></i>发布文章</a></li>
                        <li class="menu-item"><a href="/admin/contents/article/init"><i class="fa fa-paperclip"
                                                                                        aria-hidden="true"></i>采集文章</a>
                        </li>
                        <li class="menu-item"><a href="/admin/contents/article/init"><i class="fa fa-paperclip"
                                                                                        aria-hidden="true"></i>文章管理</a>
                        </li>
                        <li class="menu-item"><a href="/admin/tags/init"><i class="fa fa-puzzle-piece"
                                                                            aria-hidden="true"></i>云标签管理</a></li>
                        <li class="menu-item"><a href="/admin/categorys/init"><i class="fa fa-puzzle-piece"
                                                                                 aria-hidden="true"></i>分类管理</a></li>
                        <li class="menu-item"><a
                                href="/admin/images/init?pageSize=16&pageNumber=1&sorts=-createDate&filters="><i
                                class="fa fa-puzzle-piece" aria-hidden="true"></i>图片管理</a></li>
                        <li class="menu-item"><a href="/admin/links/init"><i class="fa fa-puzzle-piece"
                                                                             aria-hidden="true"></i>友情链接</a></li>
                    </ul>
                </li>
                <li><a href="documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
</div>