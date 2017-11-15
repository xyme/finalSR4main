/*
** for hiding the delete button. Only superadmin can see the delete button
*/


//if user is superadmin:
var buttons = '';
buttons += '<div class="col-md-6">\
                <div class="btn-group">\
                    <a href="acct-int-editdetails.html">\
                    <button id="acct_int_edit_button" class="btn blue"> Edit\
                    </button> </a>\
                </div>\
            </div>';
/*
buttons += '<div class="col-md-6">\
				<div class="btn-group">\
	                <a href="underconstruction.html">\
	                <button id="acct_int_delete_button" class="btn red"\
	                 onclick="return confirm(\'Delete this account?\')"> Delete\
	                </button></a>\
	          	</div>\
            </div>\
            <div class="col-md-6">\
                <div class="btn-group">\
                    <a href="acct-int-editdetails.html">\
                    <button id="acct_int_edit_button" class="btn blue"> Edit\
                    </button> </a>\
                </div>\
            </div>';
            */


document.getElementById('buttons_for_superadmin').innerHTML = buttons;