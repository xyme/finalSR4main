/*
** for hiding the delete button. Only superadmin can see the delete button
*/


                                    
var approveButton = '';
approveButton += '\
                <div class="col-md-6">\
                    <div class="btn-group">\
                        <a href="transfer-request-addnew.html">\
                        <button id="approve_item_request" class="btn green" \
                        onclick="return confirm(\'Approve Request and Create Transfer Request?\')">Approve\
                        </button> </a>\
                    </div>\
                </div>\
                ';

var rejectButton = '';
rejectButton += '\
                <div class="col-md-6">\
                    <div class="btn-group">\
                        <a href="item-request-viewall.html">\
                        <button id="reject_item_request" class="btn red" onclick="return confirm(\'Reject Request?\')"> Reject\
                        </button> </a>\
                    </div>\
                </div>\
                ';

                        

var buttons = approveButton + rejectButton;
document.getElementById('approve_reject_buttons').innerHTML = buttons;