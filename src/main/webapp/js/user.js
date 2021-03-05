window.defaultUser = {};
$(document).ready(function () {
    reInitUser();
    $('#userSearch').textbox({
        inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
            keyup: function(event){
                if(event.keyCode === 13){
                    event.preventDefault();

                    searchUser($(event.data.target).textbox('getText'));
                }
            }
        })
    });
});

function editUserInformation() {
    var users = JSON.parse(localStorage.getItem("users"));
    var user = users.find(x => x.doc_number == $.urlParam("user_id"));
    var index = users.indexOf(user);
    user.contactInfo.cellPhone = $("#editMobilePhone").val();
    user.contactInfo.busPhone = $("#editBusinessPhone").val();         
    user.contactInfo.lastName = $("#editLastName").val();
    user.contactInfo.firstName = $("#editFirstName").val();   
    user.contactInfo.email = $("#editEmail").val();   
    users.fill(user, index, index++); 

    localStorage.setItem("users", JSON.stringify(users));
    getUserInfo();
    $('#editContactDialog').window('close');
}

function getUserInfo() {
   var user = findUser($.urlParam('user_id')).shift();
   if (user) {
       window.defaultUser = user;
        $("#contactId").html(user.contactInfo.id);
        $("#salonInformation").html("<li>Salon Name : "+ user.salonInfo.company +"</li><li>Salon Address : "+ user.salonInfo.address +" </li><li>Suite Number : "+ user.salonInfo.suite +" </li>");
        $("#userInformation").html("<li>First Name: " + user.contactInfo.firstName + " </li><li>Last Name : " + user.contactInfo.lastName + " </li><li>Mobile Phone : " + user.contactInfo.cellPhone + "</li><li>Business Phone : " + user.contactInfo.busPhone + "</li><li>Email : " + user.contactInfo.email + "</li> ");
   }
}

function searchUser(userId) {
    if ( user = findUser(userId) ) {
        $('#userGrid').datagrid({
            data: user,
            onLoadSuccess: function() {
                $(".easyui-linkbutton").linkbutton();
            }
        });
    }
}

function findUser(userId) {
  var users = JSON.parse(localStorage.getItem("users"));
  
  return userId.trim() === '' ? users : [users.find(x => x.contactInfo.id == userId)];
}

function reInitUser() {
    var users = [{
        "contactInfo": {
            "firstName": "Sharon",
            "lastName": "Dau",
            "id": 87503,
            "cellPhone": "9097936610",
            "busPhone": "9097936610",
            "email": "uoc.lexor@gmail.com"
        },
        "salonInfo": {
            "id": 110387,
            "address": "138 N Brand Blvd, Glendale, CA 91203, US",
            "street": "138 N Brand Blvd",
            "company": "Marci’s Hair Nail & Spa",
            "busPhone": "",
            "cellPhone": "",
            "suite": "14",
            "firstName": "",
            "lastName": "",
            "idUSZip": 46922,
            "qa": "",
            "ra": "",
            "postalCode": "91203"
        },
    }];

    if (localStorage.getItem("users") === null) {
        localStorage.setItem("users", JSON.stringify(users));
    }
}