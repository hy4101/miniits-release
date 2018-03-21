<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta name="description" content="${description!}">
    <meta name="keywords" content="${keywords!}">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title>${title!}</title>
</head>
<style>
    #div_index {
        width: 100%;
        font-weight: bold;
    }

    .test-border {
        border: 1px solid red
    }

    .div-fixed-title {
        /* background-color: #f9fff6; */
        text-align: center;
        line-height: 25px;
        font-size: 13px;
        font-weight: bold;
        z-index: 99;
        width: 100%;
        /* height: 50px; */
        /* left: 50%; */
        /* top: 50%; */
        /* margin-left: -150px!important; */
        /* margin-top: -60px!important; */
        /* margin-top: 0px; */
        position: fixed !important;
        position: absolute;
        _top: expression(eval(document.compatMode && document.compatMode=='CSS1Compat') ? documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/ document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);
    }

    .col-md-12 {
        padding-left: 0;
        padding-right: 0;
    }

    .carousel-control.right {
        background-image: linear-gradient(to right, rgba(0, 0, 0, .0001) 0, rgba(243, 243, 243, 0) 100%);
    }

    .carousel-control.left {
        background-image: linear-gradient(to right, rgba(0, 0, 0, .0001) 0, rgba(243, 243, 243, 0) 100%);
    }
</style>
<body>
<div id="div_index">
    <#assign num='hehe'/>
${num}
    <div class="container-fluid">
        <div class="row div-fixed-title">
            <div class="col-md-12" style="line-height: 60px;">
                <#include "components/Header-Title.ftl"/>
            </div>
        </div>
        <div class="row" style="height: 500px;margin-top: 60px;">
            <div class="col-md-12">
                <#include "components/Header-Banner.ftl"/>
            </div>
        </div>
        <div class="row"
             style="width: 80%;margin: auto;margin-top: 20px;margin-bottom: 20px;">
            <#include "components/Body.ftl"/>
        </div>
        <div class="row" style="height: 300px">
            <div class="col-md-12">
                <#include "components/Footer.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>