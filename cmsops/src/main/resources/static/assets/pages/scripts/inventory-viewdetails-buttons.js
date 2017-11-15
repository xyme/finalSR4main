/*
** for hiding the delete button. Only superadmin can see the delete button
*/


//if user is superadmin:
var editButton = '';
editButton += '<div class="col-md-6">\
                <div class="btn-group">\
                    <a href="inventory-editdetails.html">\
                    <button id="inventory_edit_button" class="btn blue"> Edit\
                    </button> </a>\
                </div>\
            </div>';

var deleteButton = '';
deleteButton += '<div class="col-md-6">\
				<div class="btn-group">\
	                <a href="inventory-viewall.html">\
	                <button id="inventory_delete_button" class="btn red"\
	                 onclick="return confirm(\'Delete this item?\')"> Delete\
	                </button></a>\
	          	</div>\
            </div>';
            

var buttons = editButton + deleteButton;
document.getElementById('unhide_buttons').innerHTML = buttons;