$(document).ready(function () {
    loadCase();
});

function loadCase(data = 'all') {
    var services = [];

    $.get({
        url: "/lexor_cs/api/case/find/" + data,
        success: function(data) {
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    
                    data[i].customerServiceRep = customerServiceRep[data[i].customerServiceRep];
                    data[i].action = '<a href="javascript:void(0)" onClick="openCase( '+ data[i].caseID +', '+ data[i].customerID +')" class="easyui-linkbutton">Select</a>';
                    services.push(data[i]); 
                }

                $("#caseGrid").datagrid({
                    pagination:true,
                    pageSize: 20,
                    showFooter: true,
                    data: services,
                    onLoadSuccess: function() {
                        $(".easyui-linkbutton").linkbutton();
                    }
                }).datagrid('clientPaging');
            }
        },
        contentType: 'application/json'
    });
}

function openCase(caseId ,customerId) {
    window.location.href = '/lexor_cs/pages/cs/purchaseorder/case.html?case_id=' + caseId + '&user_id=' + customerId;
}
