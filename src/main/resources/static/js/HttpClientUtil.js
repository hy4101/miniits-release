/**
 * @author: wq
 * @Date: 2018/4/26
 * @Time: 17:40
 * <p>
 * Description:
 *          http 工具
 */
(function ($, win) {
    window.httpClient = function (params) {
        var doc = win.document;
        var metas = doc.head.getElementsByTagName("meta");
        var htmlMapping = '';
        for (var i = 0; i < metas.length; i++) {
            var arrt = metas[i].getAttribute("mPlusHtmlMapping");
            if (!isEmpty(arrt)) {
                htmlMapping = arrt;
                break;
            }
        }
        $.ajax({
            type: params.method,
            url: params.url,
            data: params.data || {},
            async: params.async || true,
            dataType: params.dataType || 'json',
            success: function (data) {
                if (!isEmpty(params.callback)){
                    params.callback(data);
                    // return;
                }
                data.message = params.message || data.message;
                if (!isEmpty(params.sessionId)) {
                    httpClientSuccess({sessionId: params.sessionId, data: data});
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    };
})(jQuery, window);