/*
** for hiding the delete button. Only superadmin can see the delete button
*/


var editButton = '';
editButton += '\
                <div class="col-md-6">\
                    <div class="btn-group">\
                        <a href="transfer-request-editdetails.html">\
                        <button id="edit_dispatch_status" class="btn green">Edit Dispatch Status\
                        </button> </a>\
                    </div>\
                </div>\
                ';
/*
var rejectButton = '';
rejectButton += '\
                <div class="col-md-6">\
                    <div class="btn-group">\
                        <a href="transfer-request-viewall.html">\
                        <button id="reject_item_request" class="btn red" onclick="return confirm(\'Reject Request?\')"> Reject\
                        </button> </a>\
                    </div>\
                </div>\
                ';
*/
//var buttons = approveButton + rejectButton;


var buttons = editButton;
document.getElementById('edit_dispatch_buttons').innerHTML = buttons;