/**
 * @author: wq
 * @Date: 2018/4/26
 * @Time: 17:40
 * <p>
 * Description:
 *          ***
 */
(function ($, win) {
    window.get = function (params) {
        $.ajax({
            type: params.method,
            url: params.url,
            data: params.data,
            async: isEmpty(params.async) ? true : params.async,
            dataType: isEmpty(params.dataType) ? 'json' : params.dataType,
            success: function (data) {
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    };
})(jQuery, window);