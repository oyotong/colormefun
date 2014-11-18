if (!ui) {
    var ui = {};
}
var ltie9 = $('html').is('.lt-ie9'),
    ltie8 = $('html').is('.lt-ie8'),
    ltie7 = $('html').is('.lt-ie7');

/*over loading setTimeout*/
var window_setTimeout = window.setTimeout;
window.setTimeout = function (fun, delay) {
    if (typeof fun === "function")
    {
        var args = Array.prototype.slice.call(arguments, 2);
        var funPushP = function () {
            return fun.apply(null, args);
        };
        return window_setTimeout(funPushP, delay);
    }

    return window_setTimeout(fun, delay);
}

/*over loading setInterval*/
var window_setInterval = window.setInterval;
window.setInterval = function (fun, delay) {
    if (typeof fun === "function")
    {
        var args = Array.prototype.slice.call(arguments, 2);
        var funPushP = function () {
            return fun.apply(null, args);
        };
        return window_setInterval(funPushP, delay);
    }

    return window_setInterval(fun, delay);
}

ui.init = function () {
    //do some this as document loaded.

    ui.iefix();
    ui.Html5FormExtention.Input.PlaceHolder.Initialization();
//    ui.NoMediaQueriesFix.Initialization();
};
ui.iefix = function () {
    //#1 fix ie6/ie7/ie8 ":nth-child(an+b)" for datagrid
    if (ltie9)
    {
        $("table tbody:not(:has(tr.even))").each(function () {
            $(this).children("tr:odd").addClass("even");
            $(this).children("tr:even").addClass("odd");
        });
        $(".fieldset:not(:has(.section.even))").each(function () {
            $(this).children(".section:odd").addClass("even");
            $(this).children(".section:even").addClass("odd");
        });
    }
};
ui.Html5FormExtention = {
    Input: {
        PlaceHolder: {
            Initialization: function () {
                if (!Modernizr.input.placeholder)
                {
                    $('input[type!="password"][placeholder]').each(function () {
                        var input = $(this);
                        ui.Html5FormExtention.Input.PlaceHolder.Add(input);
                        if (input.is(".placeholder"))
                        {
                            input.blur();
                        }
                        input.focus(function () {
                            ui.Html5FormExtention.Input.PlaceHolder.Remove(input);
                        });
                        input.blur(function () {
                            ui.Html5FormExtention.Input.PlaceHolder.Add(input);
                        });
                    });
                    $('input[type="password"][placeholder]').each(function () {
                        var input = $(this);
                        var placeholder = $('<div class="placeholder" style="position: absolute; height: ' + $(this).outerHeight(true) + 'px; line-height: ' + $(this).outerHeight(true) + 'px; padding-left: ' + $(this).css("padding-left") + '; margin-left: ' + $(this).css("margin-left") + '; border-left: ' + $(this).css("border-left-width") + ' solid transparent;">' + input.attr("placeholder") + '</div>');
                        input.before(placeholder);
                        placeholder.click(function () {
                            input.focus();
                        });
                        input.focus(function () {
                            ui.Html5FormExtention.Input.PlaceHolder.RemoveOnPassword(input, placeholder);
                        });
                        input.blur(function () {
                            ui.Html5FormExtention.Input.PlaceHolder.AddOnPassword(input, placeholder);
                        });
                        input[0].onpropertychange = function (event) {
                            event = event || window.event;
                            var input = $(event.srcElement),
                                placeholder = input.prev(".placeholder");
                            ui.Html5FormExtention.Input.PlaceHolder.RemoveOnPassword(input, placeholder);
                            ui.Html5FormExtention.Input.PlaceHolder.AddOnPassword(input, placeholder);
                        };
                    });
                }
            },
            Add: function (input) {
                if (input.attr("placeholder") !== null && input.attr("placeholder") !== "")
                {
                    if (input.val() === "")
                    {
                        input.val(input.attr("placeholder"));
                    }
                    if (input.val() === input.attr("placeholder"))
                    {
                        input.addClass("placeholder");
                    }
                }
            },
            Remove: function (input) {
                if (input.val() === input.attr("placeholder"))
                {
                    input.val("");
                }
                input.removeClass("placeholder");
            },
            AddOnPassword: function (input, placeholder) {
                if (input.val() === "")
                {
                    placeholder.show();
                }
            },
            RemoveOnPassword: function (input, placeholder) {
                if (placeholder.is(":visible"))
                {
                    placeholder.hide();
                }
            }
        }
    }
}
ui.NoMediaQueriesFix = {
    PAGE_WIDTH_LT_1200_OBJS:
        'body > header > div,' +
        'body .page-wrapper > header > div,' +
        'body > .main-content,' +
        'body .page-wrapper > .main-content,' +
        'body > footer > address,' +
        'body > footer > div',
    PAGE_WIDTH_LT_1200_CLASS: 'abstract-page-width-lt1200',
    Initialization: function () {
        if (!Modernizr.mediaqueries) {
            ui.NoMediaQueriesFix.CheckPageWidthLt1200();

            $(window).resize(function () {
                ui.NoMediaQueriesFix.CheckPageWidthLt1200();
            });
        }
    },
    CheckPageWidthLt1200: function () {
        if ($(window).width() <= 1200) {
            $(ui.NoMediaQueriesFix.PAGE_WIDTH_LT_1200_OBJS).addClass(ui.NoMediaQueriesFix.PAGE_WIDTH_LT_1200_CLASS);
        }
        else {
            $('.' + ui.NoMediaQueriesFix.PAGE_WIDTH_LT_1200_CLASS).removeClass(ui.NoMediaQueriesFix.PAGE_WIDTH_LT_1200_CLASS);
        }
    }
};
ui.Datepicker = function (datepickerObj) {
    var self = function () { };

    self.prototype = {
        Initialization: function () {
            var today = Date.parse(new Date());
            if (!datepickerObj.is('[beginningDate]') && !datepickerObj.is('[endingDate]')) {
                datepickerObj.datepicker();
            }
            else if (datepickerObj.is('[endingDate]')) {
                var endingDateObj = $('#' + datepickerObj.attr('endingDate'));

                var maxDate,
                    endingDate = Date.parse(endingDateObj.val());

                if(isNaN(endingDate)){
                    maxDate = "+0d";
                }else{
                    maxDate = today - endingDate > 0 ? endingDateObj.val() : "+0d";
                }
                datepickerObj.datepicker({
                    maxDate: maxDate
                });

                datepickerObj.datepicker({
                    maxDate: endingDateObj.val()
                });
                datepickerObj.change(function () {
                    try
                    {
                        if ($.datepicker.parseDate($(this).data('datepicker').dateFormat || $.datepicker._defaults.dateFormat, $(this).val()) !== null)
                        {
                            var option = 'minDate',
                                instance = $(this).data('datepicker'),
                                date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, $(this).val(), instance.settings);

                            endingDateObj.datepicker('option', option, date);
                        }
                    }
                    catch (e)
                    {
                        $(this).val('');
                        endingDateObj.datepicker('option', 'minDate', null);
                    }
                });
                datepickerObj.keyup(function () {
                    if ($(this).val() === '')
                    {
                        endingDateObj.datepicker('option', 'minDate', null);
                    }
                });

                datepickerObj.datepicker('setDate','-14d');
            }
            else if (datepickerObj.is('[beginningDate]')) {
                var beginningDate = $('#' + datepickerObj.attr('beginningDate'));

                datepickerObj.datepicker({
                    minDate: beginningDate.val()
                });
                datepickerObj.change(function () {
                    try
                    {
                        if ($.datepicker.parseDate($(this).data('datepicker').dateFormat || $.datepicker._defaults.dateFormat, $(this).val()) !== null)
                        {
                            var option = 'maxDate',
                                instance = $(this).data('datepicker'),
                                date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, $(this).val(), instance.settings);

                            beginningDate.datepicker('option', option, today - Date.parse(date) > 0 ? date : "+0d");
                        }
                    }
                    catch (e)
                    {
                        $(this).val('');
                        beginningDate.datepicker('option', 'maxDate', "+0d");
                    }
                });
                datepickerObj.keyup(function () {
                    if ($(this).val() === '')
                    {
                        beginningDate.datepicker('option', 'maxDate', "+0d");
                    }
                });
                datepickerObj.datepicker('setDate','+0d');
            }
        }
    };

    self.prototype.Initialization();

    return {
        Initialization: function () {
            return self.prototype.Initialization();
        },
        DatepickerObj: datepickerObj
    };
};
ui.Popup = function (handleObj, popupObj, options) {
    var settings = {
            //need click to show the popup
            clickToShow: false,
            //auto hide when mouse out
            autoHide: true,
            //only show one popup
            single: false,
            //add class to handleObj when poped
            activeClass: false,
            activeClassName: 'active',
            //interactive timeout
            timeout: 300,
            showAnim: 'show',
            showSpeed: null,
            hideAnim: 'hide',
            hideSpeed: null
        },
        isMouseOver = false,
        showTimeout = null,
        hideTimeout = null;

    $.extend(settings, options || {});
    if (settings.showSpeed) {
        switch (settings.showSpeed) {
            case 'normal':
                if (settings.timeout <= $.fx.speeds._default) {
                    settings.timeout = $.fx.speeds._default + 10;
                }
                break;
            case 'fast':
                if (settings.timeout <= $.fx.speeds.fast) {
                    settings.timeout = $.fx.speeds.fast + 10;
                }
                break;
            case 'slow':
                if (settings.timeout <= $.fx.speeds.slow) {
                    settings.timeout = $.fx.speeds.slow + 10;
                }
                break;
            default:
                if (settings.timeout <= settings.showSpeed) {
                    settings.timeout = settings.showSpeed + 10;
                }
                break;
        }
    }
    if (settings.hideSpeed) {
        switch (settings.hideSpeed) {
            case 'normal':
                if (settings.timeout <= $.fx.speeds._default) {
                    settings.timeout = $.fx.speeds._default + 10;
                }
                break;
            case 'fast':
                if (settings.timeout <= $.fx.speeds.fast) {
                    settings.timeout = $.fx.speeds.fast + 10;
                }
                break;
            case 'slow':
                if (settings.timeout <= $.fx.speeds.slow) {
                    settings.timeout = $.fx.speeds.slow + 10;
                }
                break;
            default:
                if (settings.timeout <= settings.showSpeed) {
                    settings.timeout = settings.showSpeed + 10;
                }
                break;
        }
    }

    var self = function () { };

    ui.Popup.lastShowedHandleObj = null;
    ui.Popup.lastShowedPopupObj = null;

    self.prototype = {
        Initialization: function () {
            if (handleObj) {
                handleObj.click(function (event) {
                    self.prototype.Show();
                    event.stopPropagation();
                });
                handleObj.hover(
                    function () {
                        isMouseOver = true;
                        clearTimeout(hideTimeout);
                        if (!settings.clickToShow) {
                            showTimeout = setTimeout(function () {
                                if (isMouseOver && popupObj.is(':hidden')) {
                                    self.prototype.Show();
                                }
                            }, settings.timeout);
                        }
                    },
                    function () {
                        isMouseOver = false;
                        clearTimeout(showTimeout);
                        if (settings.autoHide) {
                            hideTimeout = setTimeout(function () {
                                if (!isMouseOver && popupObj.is(':visible')) {
                                    self.prototype.Hide();
                                }
                            }, settings.timeout);
                        }
                    }
                );
                handleObj.blur(function () {
                    isMouseOver = false;
                    clearTimeout(showTimeout);
                    if (settings.autoHide) {
                        hideTimeout = setTimeout(function () {
                            if (!isMouseOver && popupObj.is(':visible')) {
                                self.prototype.Hide();
                            }
                        }, settings.timeout);
                    }
                });
            }
            popupObj.hover(
                function () {
                    isMouseOver = true;
                    clearTimeout(hideTimeout);
                },
                function () {
                    isMouseOver = false;
                    if (settings.autoHide) {
                        hideTimeout = setTimeout(function () {
                            if (!isMouseOver && popupObj.is(':visible')) {
                                self.prototype.Hide();
                            }
                        }, settings.timeout);
                    }
                }
            );
            popupObj.click(function (event) {
                isMouseOver = true;
                event.stopPropagation();
            });
            if (!settings.autoHide) {
                $(document).on('click.popupFocusOut', function () {
                    if (!isMouseOver) {
                        self.prototype.Hide();
                    }
                });
            }
        },
        Show: function () {
            if (settings.single) {
                if (ui.Popup.lastShowedPopupObj) {
                    ui.Popup.lastShowedPopupObj[settings.hideAnim].apply(ui.Popup.lastShowedPopupObj, [settings.hideSpeed]);
                    if (settings.activeClass) {
                        ui.Popup.lastShowedHandleObj.removeClass(settings.activeClassName);
                    }
                }
            }
            popupObj[settings.showAnim].apply(popupObj, [settings.showSpeed, function (){
                popupObj.stop(true);
            }]);
            if (settings.activeClass) {
                handleObj.addClass(settings.activeClassName);
            }
            if (settings.position) {
                var positionSettings = {
                    of: handleObj,
                    my: 'left top',
                    at: 'left bottom',
                    collision: 'fit fit'
                };
                $.extend(positionSettings, settings.position);
                popupObj.position(positionSettings);
            }
            if (settings.single) {
                ui.Popup.lastShowedHandleObj = handleObj;
                ui.Popup.lastShowedPopupObj = popupObj;
            }
        },
        Hide: function () {
            popupObj[settings.hideAnim].apply(popupObj, [settings.hideSpeed]);
            if (settings.activeClass) {
                handleObj.removeClass(settings.activeClassName);
            }
        }
    };

    self.prototype.Initialization();

    return {
        Initialization: function () {
            return self.prototype.Initialization();
        },
        HandleObj: handleObj,
        PopupObj: popupObj,
        Show: function () {
            return self.prototype.Show();
        },
        Hide: function () {
            return self.prototype.Hide();
        }
    };
};
ui.Dialog = function (openButtonObj, dialogObj, options) {
    var settings = {
        autoOpen: false,
        modal: true,
        resizable: false
    };
    $.extend(settings, options || {});

    var dialog = dialogObj.dialog(settings);

    var self = function () { };

    self.prototype = {
        Initialization: function () {
            if (openButtonObj)
            {
                openButtonObj.click(function () {
                    self.prototype.Open();
                });
            }
            dialogObj.find('.dialog-ok').click(function(){
                if(options.beforeSubmit){
                    options.beforeSubmit();
                }
                if(options.delayClose){
                    setTimeout(self.prototype.Close,1000 * options.delayClose);
                }else{
                    self.prototype.Close();
                }
            });

            dialogObj.find('.dialog-cancel').click(function () {
                self.prototype.Close();
            });
        },
        Open: function () {
            dialog.dialog("open");
        },
        Close: function () {
            dialog.dialog("close");
        }
    };

    self.prototype.Initialization();

    return {
        Dialog: dialog,
        OkButton: dialog.find(".dialog-ok"),
        Initialization: function () {
            return self.prototype.Initialization();
        },
        Open: function () {
            return self.prototype.Open();
        },
        Close: function () {
            return self.prototype.Close();
        }
    }
};
ui.HtmlTag = function (options) {
    var settings = {
            tagName: 's'
        },
        formerHtml = '',
        afterHtml = '';

    $.extend(settings, options || {});

    var self = function () { };

    self.prototype = {
        Initialization: function () {
            formerHtml += '<' + settings.tagName;
            if (settings.attrs) {
                $.each(settings.attrs, function (name, value) {
                    formerHtml += ' ' + name + '="' + value + '"';
                });
            }
            formerHtml += '>';
            afterHtml += '</' + settings.tagName + '>';
        }
    };

    self.prototype.Initialization();

    return {
        FormerHtml: formerHtml,
        AfterHtml: afterHtml,
        Initialization: function () {
            return self.prototype.Initialization();
        }
    }
};

jQuery.Event.prototype.cancel = function () {
    this.stopPropagation(); // to prevent event from bubbling up
    this.preventDefault(); // then cancel the event (if it's cancelable)
};

$.datepicker.setDefaults({
    changeMonth: true,
    changeYear: true,
    numberOfMonths: 2,
    showButtonPanel: true,
    closeText:'Close'
});

$.datepicker._gotoToday = function (id) {
    var target = $(id);
    var inst = this._getInst(target[0]);
    if (this._get(inst, 'gotoCurrent') && inst.currentDay) {
        inst.selectedDay = inst.currentDay;
        inst.drawMonth = inst.selectedMonth = inst.currentMonth;
        inst.drawYear = inst.selectedYear = inst.currentYear;
    }
    else {
        var date = new Date();
        inst.selectedDay = date.getDate();
        inst.drawMonth = inst.selectedMonth = date.getMonth();
        inst.drawYear = inst.selectedYear = date.getFullYear();
        this._setDateDatepicker(target, date);
        this._selectDate(id, this._getDateDatepicker(target));
    }
    this._notifyChange(inst);
    this._adjustDate(target);
}