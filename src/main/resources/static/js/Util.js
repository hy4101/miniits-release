(function ($) {

    /**
     * 判断属性是否空
     *  isEmpty 支持的类型如下：
     *    1、字符串：内容为空，返回 true，否则 false
     *    2、对象：对象为空，返回 true，否则 false
     *    3、数组：数组为空，返回 true，否则 false
     * @param str
     * @returns {boolean}
     */
    window.isEmpty = function (str) {
        if (str === '' || str == null || str === undefined) {
            return true;
        }
        if (str instanceof Array) {
            if (str == null || str.length <= 0) {
                return true;
            }
        }
        return false;
    };

    $.fn.getFilters = function () {
        var sa = this.serializeArray();
        var filters = '';
        $.each(sa, function () {
            if (!isEmpty(this.value)) {
                filters += this.name.replace('||', '=' + this.value + '_OR_') + '=' + this.value + ';';
            }
        });
        console.log(filters);
    };

    $.fn.serializeObject = function () {
        var o = {};
        var sa = this.serializeArray();
        $.each(sa, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    window.Mini = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm" style="margin-top: 150px;width: 35%;">' +
            '<div class="modal-content" style="height: 363px;border-radius: 10px;">' +
            '<div class="modal-header" style="border-bottom: 0">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '</div>' +
            '<div class="modal-body" style="text-align: center; color: red;font-size: 94px;"><i class="[Font]" aria-hidden="true"></i></div>' +
            '<h4 class="modal-title" style="text-align: center;" id="modalLabel">[Title]</h4>' +
            '<div class="modal-body" style="text-align: center;"><p>[Message]</p></div>' +
            '<div class="modal-footer" style="text-align: center;">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Font: options.font || 'fa fa-trash-o fa-6',
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {
                                callback(true);
                            });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {
                                callback(true);
                            });
                            modal.find('.cancel').click(function () {
                                callback(false);
                            });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () {
                    },
                    onShown: function (e) {
                    }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            },
        }
    }();
})(jQuery);
