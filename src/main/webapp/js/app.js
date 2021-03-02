(function($){
    function pagerFilter(data){
        if ($.isArray(data)){    // is array
            data = {
                total: data.length,
                rows: data
            }
        }
        var target = this;
        var dg = $(target);
        var state = dg.data('datagrid');
        var opts = dg.datagrid('options');
        if (!state.allRows){
            state.allRows = (data.rows);
        }
        if (!opts.remoteSort && opts.sortName){
            var names = opts.sortName.split(',');
            var orders = opts.sortOrder.split(',');
            state.allRows.sort(function(r1,r2){
                var r = 0;
                for(var i=0; i<names.length; i++){
                    var sn = names[i];
                    var so = orders[i];
                    var col = $(target).datagrid('getColumnOption', sn);
                    var sortFunc = col.sorter || function(a,b){
                        return a==b ? 0 : (a>b?1:-1);
                    };
                    r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
                    if (r != 0){
                        return r;
                    }
                }
                return r;
            });
        }
        var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
        var end = start + parseInt(opts.pageSize);
        data.rows = state.allRows.slice(start, end);
        return data;
    }

    var loadDataMethod = $.fn.datagrid.methods.loadData;
    var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
    $.extend($.fn.datagrid.methods, {
        clientPaging: function(jq){
            return jq.each(function(){
                var dg = $(this);
                var state = dg.data('datagrid');
                var opts = state.options;
                opts.loadFilter = pagerFilter;
                var onBeforeLoad = opts.onBeforeLoad;
                opts.onBeforeLoad = function(param){
                    state.allRows = null;
                    return onBeforeLoad.call(this, param);
                }
                var pager = dg.datagrid('getPager');
                pager.pagination({
                    onSelectPage:function(pageNum, pageSize){
                        opts.pageNumber = pageNum;
                        opts.pageSize = pageSize;
                        pager.pagination('refresh',{
                            pageNumber:pageNum,
                            pageSize:pageSize
                        });
                        dg.datagrid('loadData',state.allRows);
                    }
                });
                $(this).datagrid('loadData', state.data);
                if (opts.url){
                    $(this).datagrid('reload');
                }
            });
        },
        loadData: function(jq, data){
            jq.each(function(){
                $(this).data('datagrid').allRows = null;
            });
            return loadDataMethod.call($.fn.datagrid.methods, jq, data);
        },
        deleteRow: function(jq, index){
            return jq.each(function(){
                var row = $(this).datagrid('getRows')[index];
                deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                var state = $(this).data('datagrid');
                if (state.options.loadFilter == pagerFilter){
                    for(var i=0; i<state.allRows.length; i++){
                        if (state.allRows[i] == row){
                            state.allRows.splice(i,1);
                            break;
                        }
                    }
                    $(this).datagrid('loadData', state.allRows);
                }
            });
        },
        getAllRows: function(jq){
            return jq.data('datagrid').allRows;
        }
    })
})(jQuery);

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results === null) {
       return null;
    }
    return decodeURI(results[1]) || 0;
};

isWarrantyOptions = {1 : "Y", 0: "N"};
function getProductsBySaleOrder(listOfSO) {
    const promise = [];

    for (let key in listOfSO) {
        const options = {
            type: "GET",
            url: '/lexor_cs/api/apiSODetail/' + key,
            success: function (data) {
               return data;
            },
            contentType: 'application/json'
        };
        
        promise.push(new Promise(function (resolve, reject) {
            return $.ajax(options).done(resolve).fail(reject);
        }));
    }
    
    const result = {};
    return Promise.all(promise).then(function(results) {
        for (let i = 0; i < results.length; i++) {
            result[results[i].SOID] = results[i].products.map(function(product){
                return {
                    RMAID: false,
                    price: product['price'],
                    serialNumber: product['name'],
                    quantity: product['quantity'],
                    productID: Math.floor(Math.random() * 10000) + 1,
                    soldPrice: "$" + product['price'],
                    amount: "$" + (product['price'] * product['quantity']),
                    image: "<img width='60px' height='60px' src='../../../images/product02.jpg' />",
                    product_name: product['name'],
                    under_warranty: "Y",
                    warranty_issue: "25/01/2021",
                    warranty_expire: "25/01/2021",
                    isWarranty: isWarrantyOptions[1],
                    originalSo: results[i].SOID,
                    serviceMasterID: false,
                    totalWeight: 1000,
                    action: '<a href="javascript:void(0)" onClick="addProduct('+results[i].SOID+', '+i+')" class="easyui-linkbutton">Add</a>'
                }
            });
        }
        return new Promise(function(resolve) {
            resolve(result);
        });
    });
}