# lexor_cs
# Test man hinh 
## Cases 
@Staging/QA Environment: http://freetekdemo.com/lexor_cs/pages/cs/purchaseorder/cases.html
1. URL Development http://localhost:8080/lexor_cs/pages/cs/purchaseorder/cases.html
2. + Add new contact : tao moi va di vao man hinh case
3. Click : Select user va di vao man hinh case

## Cases
@Staging/QA Environment: http://freetekdemo.com/lexor_cs/pages/cs/purchaseorder/case.html?case_id=xx&user_id=1
1. URL Development : http://localhost:8080/lexor_cs/pages/cs/purchaseorder/case.html?case_id=xx&user_id=1
1. Tao Service , nhap Sale Order : SO001 de search.
2. Tao RMA , nhap Sale Order : SO001 de search.
3. Edit User information
4. View Tab Case Information, Transaction, Log History sau khu tao Service, RMA.

## Services
@Staging/QA Environment:  http://freetekdemo.com/lexor_cs/pages/cs/purchaseorder/services.html
1. URL Development : http://localhost:8080/lexor_cs/pages/cs/purchaseorder/services.html
2. Click : Select user va di vao man hinh service

## Service
@Staging/QA Environment:  http://freetekdemo.com/lexor_cs/pages/cs/purchaseorder/service.html?user_id=1&service_id=xx
1. URL Development : http://localhost:8080/lexor_cs/pages/cs/purchaseorder/service.html?user_id=1&service_id=xx
2. Co the change Service status
3. Co the add, remove product.
4. Tab shipping : co the xem thong tin shipping sau khi add, edit product.

## RMAs
@Staging/QA Environment: http://freetekdemo.com/lexor_cs/pages/cs/purchaseorder/rmas.html
1. URL Development : http://localhost:8080/lexor_cs/pages/cs/purchaseorder/rmas.html
2. Click : Select user va di vao man hinh service

## RMA
@Staging/QA Environment:  http://freetekdemo.com/lexor_cs/pages/cs/purchaseorder/rma.html?user_id=1&rma_id=xx
1. URL Development : http://localhost:8080/lexor_cs/pages/cs/purchaseorder/rma.html?user_id=1&rma_id=xx
2. Co the change RMA status
3. Co the add, remove product nhung chua save duoc vi dang cho thong tin product de tinh toan
4. Tab activity : co the xem activity sau khi save products

# Task se update sau
### Tat ca man hinh : chuc nang send mail
## Man hinh Case : integrate voi SO, User API.
## Man hinh Service : integrate API SO & Product, tinh toan shipping, update payment, activity
## Man hinh RMA : integrate API SO & Product, update payment

## Update service status
http://localhost:8080/lexor_cs/api/caseService/updateServiceStatus/43
## Update RMA status 
http://localhost:8080/lexor_cs/api/caseRMA/updateRMAStatus/43