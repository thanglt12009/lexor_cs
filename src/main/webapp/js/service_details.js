function registerCaseTransactionDialog(dialogElement, title, path, defaultSaleOrder = {}) {

    var element = $('#' + dialogElement);
    element.dialog({
        title: title,
        width: 400,
        height: 320,
        closed: false,
        cache: false,
        modal: true,
        inline: true,
        href: 'case_service.html',
        onLoad: function () {
            registerAddSaleOrder(defaultSaleOrder);
            registerCaseSeviceAutoComplete();
            registerSaveCaseService(path, dialogElement, title);
        }
    });
    $(element).dialog("move", {top: 100});
}

function registerCaseSeviceAutoComplete() {
    var options = {
        url: function(orderId) {
            return "/lexor_cs/api/apiSO/find/" + orderId;
	},
        getValue: "code",
        ajaxSettings: {
            dataType: "json",
            method: "GET"
        },
        theme: "square"
    };
    $("#saleOrderSearch").easyAutocomplete(options);
}

function registerSaveCaseService(path, dialogElement, title) {
    $("#saveCaseService").on("click", function () {
        if ($.urlParam('rma_id')) {
            createServiceOrReturn($.urlParam('rma_id'), dialogElement);
        }
    });
}

function createServiceOrReturn(caseServiceId, dialog) {
    const promises = [];
    const productList = getProductsBySaleOrder(saleOrderToSave).then(function(soList) {
        for (const key in saleOrderToSave) {
            if (soList[key]) {
                promises.push(createRMASaleOrder(caseServiceId, key, soList[key]));
            }

            Promise.all(promises).then(function() {
                $('#' + dialog).window('close');
            });
        };
    });
}