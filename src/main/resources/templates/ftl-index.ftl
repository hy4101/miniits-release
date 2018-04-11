<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div componentid="1523434683706" componentname="index_title" style="width:100%;height:auto">
    <#list uXppmcPtoMxFeweSdZnJKList as uXppmcPtoMxFeweSdZnJK>;
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="jumbotron well">
                        <h1> ${uXppmcPtoMxFeweSdZnJK.titleName} </h1>
                        <p> ${uXppmcPtoMxFeweSdZnJK.contentTitle} </p>
                        <p><a class="btn btn-primary btn-large" href="#">Learn more</a></p>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
<div componentid="1523434683707" componentname="index_body" style="width:100%;height:auto"></div>
<div componentid="1523434683708" componentname="index_footer" style="width:100%;height:auto"></div>
</body>
</html>