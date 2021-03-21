productDatas = {
    "total" : 2,
    "footer":[
	{"shipingDay":'<strong>Subtotal</strong>', "originalSo":"$0.00"},
	{"shipingDay":'<strong>Shipping Fee</strong>',"originalSo":"$0.00"},
        {"shipingDay":'<strong>Manager Discount</strong>',"originalSo":'<strong>-$0.00</strong>'},
        {"shipingDay":'<strong>Total</strong>',"originalSo":'<strong> $0.00</strong>'}
    ],
    "rows":[]
};

servicesData = [];
discountShiping = 20;
shippingFee = 0;
shippingDatas = {
    "total" : 0,
    "rows":[
    ],
    "footer":[
	{"shipping_type":'<strong>Total Shipping Cost</strong>',"shipping_cost":"$0.00"},
	{"shipping_type":'<strong>Shipping Discount</strong>',"shipping_cost":"-$<input style='width:70px' value='"+discountShiping+".00' />"},
        {"shipping_type":'<strong>Shipping Fee</strong>',"shipping_cost":'<strong>$0.00</strong>'}
    ]
};

serviceMaster = {};
serviceStatus = { 1: 'Closed', 0: "Processing", 2: "Submitted To Production" };

isWarrantyOptions = {1 : "Y", 0: "N"};
paymentMethod = 1;
comboBoxedProduct = {};
dateBoxProduct = {};
removeTagProduct = {};
shippingDetail = {};
removeTagProduct[1] = 1;
removeTagProduct[2] = 2;
discount = 0;
total = 0;
totalAmount = 0;
shippingAmount = 0;
totalShippingAmount = 0;
oldProduct = {};

// Shipping warehouse
discountShiping = 20;  // This is shipping price for default when user has only one warehouse
variableOptions = {1 :'CA', 2: 'CB' };
wareHouseExchange = {'CA' : 1, 'CB': 2 };
// This is shipping price for each ware house
shippingPrice = {
    1 : 20,
    2 : 40
}
