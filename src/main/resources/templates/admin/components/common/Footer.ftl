<div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2018-2020 <a href="http://www.miniits.com">MiniIts</a>.</strong> All rights
        reserved.
    </footer>
</div>
<script>
    (function ($, win) {
        var themeColor = localStorage.getItem("f-theme-c");

        if (isEmpty(themeColor)) {
            themeColor = '#27AE60';
        }
        $(".f-theme-c").css("background-color", themeColor);
    })(jQuery, window)
</script>