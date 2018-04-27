/**
 * @author: wq
 * @Date: 2018/4/26
 * @Time: 17:40
 * <p>
 * Description:
 *          ***
 */
(function ($) {
    window.httpClient = function (params) {
        return $.ajax({
            type: params.method,
            url: params.url,
            data: params.data || {},
            async: params.async || true,
            dataType: params.dataType || 'json',
            success: function (data) {
                httpClientSuccess({sessionId: params.sessionId, data: data});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    };
})(jQuery);