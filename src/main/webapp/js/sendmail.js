$(document).ready(function () {
    $("#sendMail").form();
    
    $("#send").on("click", function() {
        $('#sendMail').submit();
        
        if ($('#sendMail').form('validate')) {
            var formData = $('#sendMail').serializeObject();
            $.post({
                type: "POST",
                url: '/lexor_cs/api/case' + "/" + formData.email + "/" + formData.subject +"/" + formData.message,
                data: JSON.stringify(),
                contentType: 'application/json'
            });
            $('#sendMail').form('clear');
        }
    });
});

jQuery.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};