window.defaultUser = {};
$(document).ready(function () {
   $("#editUser").on("click", function() {
      var users = JSON.parse(localStorage.getItem("users"));
      var user = users.find(x => x.doc_number == $.urlParam("user_id"));
      var index = users.indexOf(user);
      user.mobile_phone = $("#editMobilePhone").val();
      user.business_phone = $("#editBusinessPhone").val();         
      users.fill(user, index, index++); 

      localStorage.setItem("users", JSON.stringify(users));
      getUserInfo();
      $('#editContactDialog').window('close');
  });

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

function getUserInfo() {
   var user = findUser($.urlParam('user_id')).shift();
   if (user) {
       window.defaultUser = user;
       $("#userInformation").html("<li>First Name: " + user.name + " </li><li>Last Name : " + user.name + " </li><li>Mobile Phone : " + user.mobile_phone + "</li><li>Business Phone : " + user.business_phone + "</li><li>Email : </li> ");
   }
}

function searchUser(userId) {
   $('#userGrid').datagrid({
       data: findUser(userId),
       onLoadSuccess: function() {
           $(".easyui-linkbutton").linkbutton();
       }
   });
}

function findUser(userId) {
  var users = JSON.parse(localStorage.getItem("users"));
  
  return userId.trim() === '' ? users : [users.find(x => x.doc_number == userId)];
}
