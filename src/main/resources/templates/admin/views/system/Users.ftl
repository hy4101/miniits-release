<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/css/all-skins.min.css">
    <link rel="stylesheet" href="/static/css/blue.css">
    <link rel="stylesheet" href="/static/css/morris.css">
    <link rel="stylesheet" href="/static/css/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="/static/css/datepicker3.css">
    <link rel="stylesheet" href="/static/css/daterangepicker-bs3.css">
    <link rel="stylesheet" href="/static/css/bootstrap3-wysihtml5.min.css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <!-- jQuery 2.1.4 -->
    <script src="/static/css/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.5 -->
    <script src="/static/css/bootstrap.min.js"></script>
    <!-- Morris.js charts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="/static/css/morris.min.js"></script>
    <!-- Sparkline -->
    <script src="/static/css/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="/static/css/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/static/css/jquery-jvectormap-world-mill-en.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="/static/css/jquery.knob.js"></script>
    <!-- daterangepicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
    <script src="/static/css/daterangepicker.js"></script>
    <!-- datepicker -->
    <script src="/static/css/bootstrap-datepicker.js"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="/static/css/bootstrap3-wysihtml5.all.min.js"></script>
    <!-- Slimscroll -->
    <script src="/static/css/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="/static/css/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="/static/css/app.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="/static/css/dashboard.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="/static/css/demo.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../../components/Header.ftl"/>
    <#include "Users-Body.ftl"/>
    <#include "../../components/Footer.ftl"/>
</div>
</body>
</html>
