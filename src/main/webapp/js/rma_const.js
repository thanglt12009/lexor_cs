productDatas = {
    "total" : 2,
    "footer":[
	{"price":'<strong>Subtotal</strong>', "wareHouse":"$3000.00"},
	{"price":'<strong>Restocking Fee</strong>',"wareHouse":"$0.00"},
        {"price":'<strong>Total</strong>',"wareHouse":'<strong> $0.00</strong>'}
    ],
    "rows":[]
    
};

servicesData = [];

shippingDatas = {
    "total" : 0,
    "rows": [],
    "footer":[
	{"shipping_type":'<strong>Total Shipping Cost</strong>',"shipping_cost":"$10.00"},
	{"shipping_type":'<strong>Shipping Discount</strong>',"shipping_cost":"-$<input style='width:100px' value='5.00' />"},
        {"shipping_type":'<strong>Shipping Fee</strong>',"shipping_cost":'<strong>$5.00</strong>'}
    ]
};

isSumFee = {};
productList = {};
paymentMethod = 1;
comboBoxedProduct = {};
dateBoxProduct = {};
removeTagProduct = {};
shippingDetail = {};
comboBoxedProduct[1] = "Y";
removeTagProduct[1] = 1;
comboBoxedProduct[2] = "N";
removeTagProduct[2] = 2;
rmaSO = {};

total = 0;
totalAmount = 0;
shippingAmount = 0;
rmaStatus = 1;
rmaTempStatus = 1;
isWarrantyOptions = {1 : "Y", 0: "N"};

paymentMethod = 1;
rmaPayment = {};
discount = 0;
discountShipping = 20; // This is shipping price for default when user has only one warehouse
totalShippingAmount = 0;

variableOptions = {1 :'CA', 2: 'CB' };
wareHouseExchange = {'CA' : 1, 'CB': 2 };
// This is shipping price for each ware house
shippingPrice = {
    1 : 20,
    2 : 40
}