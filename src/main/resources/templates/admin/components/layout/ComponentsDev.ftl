<div style="padding: 10px 10px 10px 10px;">
    <link rel="stylesheet" href="/static/editor.md/css/editormd.css">
    <script src="/static/editor.md/editormd.min.js"></script>
    <iframe src="/static/layoutit/index.html" style="width: 100%;height: 1200px;border: 0px;"></iframe>
<#--<div id="my-editormd" >-->
<#--<textarea id="my-editormd-markdown-doc" name="my-editormd-markdown-doc" style="display:none;"></textarea>-->
<#--<!-- 注意：name属性的值&ndash;&gt;-->
<#--<textarea id="my-editormd-html-code" name="my-editormd-html-code" style="display:none;"></textarea>-->
<#--</div>-->
</div>
<script>
    var testEditor;

    (function ($, win) {

        function initMarkDown() {
            editormd("my-editormd", {//注意1：这里的就是上面的DIV的id属性值
                width: "90%",
                height: 640,
                syncScrolling: "single",
                path: "/static/editor.md/lib/",
                saveHTMLToTextarea: true,//注意3：这个配置，方便post提交表单

                /**上传图片相关配置如下*/
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "/smart-api/upload/editormdPic/",//注意你后端的上传图片服务地址
            });
            //
            // testEditor = editormd("my-editormd", {
            //     saveHTMLToTextarea : true//注意3：这个配置，方便post提交表单
            //     width: '500px',
            //     height: '300px',
            //     syncScrolling: "single",
            //     path    : "/static/editor.md/lib/",//注意2：你的路径
            //     //启动本地图片上传功能
            //     imageUpload: true,
            //     imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            //     imageUploadURL: "{{url_for('main.upload')}}"
            // });
        }

        initMarkDown();
    })(jQuery, window)
</script>