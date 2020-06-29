/*!
 * commons.js 1.0.20190516
 *
 * https://github.com/oxidyc
 *
 * Copyright (c) 2003-2015 Oxidyc.
 * Licensed under MIT (https://github.com/oxidyc/smart/blob/master/LICENSE)
 */

/* ========================================================================
 * 全选按钮(checkAllFunction)
 *
 * @param rootId
 * @param subName
 * ======================================================================== */
function checkAllFunction(rootId,subName){
    $(rootId).click(function() {
        $("input[name='"+subName+"']").prop("checked", this.checked);
    });

    $("input[name='"+subName+"']").click(function() {
        var $subs = $("input[name='"+subName+"']");
        $(rootId).prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
    });
}