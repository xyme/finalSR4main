//=====================================================================
//*************************** DYNAMIC *********************************
//*************************** MESSAGES ********************************
//*************************** PREVIEW *********************************
//=====================================================================

/** 
For displaying unread msges preview for a user
**/


var newMsgCount = 5; //hardcoded. to change during integration


var allMsges = new Array(); //2D array of to contain all msg objects



//do for-loop for all the unread msgs
for (i=0; i<newMsgCount; i++) {
	
	//$$==BEGIN HARDCODED PART==$$
	if (i==0) {
		//hardcoded msg 1
		var msg = ["Handsome boy", getMsgTime(), "blah blah", "underconstruction.html", "../assets/layouts/layout/img/dora.png"];
	}
	if (i==1) {
		//hardcoded msg 2
		var msg = ["Big boss", getMsgTime(), "blah blah blah", "underconstruction.html", "../assets/layouts/layout/img/dora.png"];
	}
	if (i==2) {
		//hardcoded msg 3
		var msg = ["Old man", getMsgTime(), "some words", "underconstruction.html", "../assets/layouts/layout/img/dora.png"];
	}
	if (i==3) {
		//hardcoded msg 3
		var msg = ["Weird man", getMsgTime(), "shut up", "underconstruction.html", "../assets/layouts/layout/img/dora.png"];
	}
	if (i==4) {
		//hardcoded msg 3
		var msg = ["Who-is-this", getMsgTime(), "blah", "underconstruction.html", "../assets/layouts/layout/img/dora.png"];
	}
	//##==END HARDCODED PART==##


	var eachMsg = {
		name: msg[0],
		time: msg[1],
		content: msg[2],
		link: msg[3],
		img: msg[4]
	};

	//add new msg object into array
	allMsges.push(eachMsg);
}


/*
GET TIMESTAMP OF MSG
(hardcoded for current system time now)
*/
function getMsgTime() {
	var msgTime = new Date(); //create new date object. When integrating use actual msg time
	//return msgTime.toDateString;
	//var time = msgTime.getTime();
	//return time.format("HH:MM");
	return msgTime.getHours() + ':' + msgTime.getMinutes(); //make it readable
}


/*
TO DYNAMICALLY GENERATE NEW MSGES PREVIEWS
*/
var newMsgPreview = '';
var numOfMsges = allMsges.length;
var current;

for (i=0; i<numOfMsges; i++) {

	current = allMsges[i];

	newMsgPreview += '<li>\
						<a href=' + current.link + ' >\
							<span class="photo">\
								<img src=' + current.img + ' class="img-circle" alt=""></span>\
							<span class="subject">\
								<span class="from">' + current.name + '</span>\
								<span class="time">' + current.time + '</span>\
							</span>\
							<span class="message">' + current.content + '</span>\
						</a>\
					</li>';
}

/*
document.getElementById('myInbox').innerHTML = newMsgCount;
document.getElementById('newMsgesCountBadge').innerHTML = newMsgCount; //modifies the badge count
document.getElementById('msgCount').innerHTML = newMsgCount; //modifies "You have xxx new messages"
document.getElementById('msgesPreviews').innerHTML = newMsgPreview; //dynamically generates the scrollable new msges preview
*/






//=====================================================================
//*************************** DYNAMIC *********************************
//*************************** NOTIFICATIONS ***************************
//*************************** PREVIEW *********************************
//=====================================================================

/*
For dynamically generating notifications
*/

//!! need fetch from backend the actual num of notifications !! 
var newNotiCount = 3; //hardcoded. total count of notifications


var allNotifications = new Array(); //2D array for storing all notification arrays


//!! should change to how many days / hours ago !!
function getMsgTime() {
	var msgTime = new Date(); //create new date object. When integrating use actual msg time
	return msgTime.getHours() + ':' + msgTime.getMinutes(); //make it readable
}


//do for-loop for all new notifications
for (i=0; i<newNotiCount; i++) {
	
	//$$==BEGIN HARDCODED PART==$$
	if (i==0) {
		//hardcoded msg 1
		var noti = ["New user registered", getMsgTime(), "underconstruction.html"];
	}
	if (i==1) {
		//hardcoded msg 2
		var noti = ["New maintenance report pending approval", getMsgTime(), "underconstruction.html"];
	}
	if (i==2) {
		//hardcoded msg 3
		var noti = ["New item to procure", getMsgTime(), "underconstruction.html"];
	}
	//##==END HARDCODED PART==##


	var eachNoti = {
		subject: noti[0],
		time: noti[1],
		link: noti[2]
	};

	//add new msg object into array
	allNotifications.push(eachNoti);
}


/*
TO DYNAMICALLY GENERATE NEW NOTIFICATIONS
*/
var newNotifications = '';
var numOfNoti = allNotifications.length;
var current;

for (i=0; i<numOfNoti; i++) {

	current = allNotifications[i];

	newNotifications += '<li>\
						<a href=' + current.link + ' >\
							<span class="time">' + current.time + '</span>\
							<span class="details">\
								<span class="label label-sm label-icon label-warning">\
                                	<i class="fa fa-bell-o"></i>\
							</span>' + current.subject + '</span>\
						</a>\
					</li>';
}


/*
document.getElementById('notificationsBadge').innerHTML = newNotiCount;
document.getElementById('pendingNotifications').innerHTML = newNotiCount + ' pending';
document.getElementById('allNotifications').innerHTML = newNotifications;
*/






//=====================================================================
//*************************** GENERATE ********************************
//*************************** HTML ************************************
//*************************** CODES ***********************************
//=====================================================================

var logo = '';
logo += '\
		<!-- BEGIN LOGO -->\
		<div class="page-logo">\
	        <a href="index.html">\
	            <img src="../assets/layouts/layout/img/logo.png" alt="logo" class="logo-default" /> </a>\
	        <div class="menu-toggler sidebar-toggler">\
	            <span></span>\
	        </div>\
	    </div>\
	    <!-- END LOGO -->\
	    \
	    <!-- BEGIN RESPONSIVE MENU TOGGLER -->\
        <a href="javascript:;" class="menu-toggler responsive-toggler" \
        			data-toggle="collapse" data-target=".navbar-collapse">\
            <span></span>\
        </a>\
        <!-- END RESPONSIVE MENU TOGGLER -->\
		';


var naviMenuStart = '';
naviMenuStart += '\
					<!-- BEGIN TOP NAVIGATION MENU -->\
                    <div class="top-menu">\
                        <ul class="nav navbar-nav pull-right">\
                    ';

var naviMenuEnd = '';
naviMenuEnd += '\
				<!-- BEGIN TOP NAVIGATION MENU -->\
					</ul>\
				</div>\
				';


var notidropdown = '';
notidropdown += '\
				<!-- BEGIN NOTIFICATION DROPDOWN -->\
                <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">\
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" \
                    				data-hover="dropdown" data-close-others="true">\
                        <i class="icon-bell"></i>\
                        <span class="badge badge-default" id="notificationsBadge">'
                        	+ newNotiCount +
                        '</span>\
                    </a>\
                    <ul class="dropdown-menu">\
                        <li class="external">\
                            <h3>\
                                <span class="bold" id="pendingNotifications">' + newNotiCount + ' pending</span> notifications</h3>\
                            <a href="underconstruction.html">view all</a>\
                        </li>\
                        <li>\
                            <ul class="dropdown-menu-list scroller" style="height: 250px;" \
                            				data-handle-color="#637283" id="allNotifications">'
                            				+ newNotifications + 
                            '</ul>\
                        </li>\
                    </ul>\
                </li>\
                <!-- END NOTIFICATION DROPDOWN -->\
                ';


var msgdropdown = '';
msgdropdown += '\
				<!-- BEGIN INBOX DROPDOWN -->\
                <li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">\
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" \
                    			data-hover="dropdown" data-close-others="true">\
                        <i class="icon-envelope-open"></i>\
                        <span class="badge badge-default" id="newMsgesCountBadge"> ' 
                        	+ newMsgCount + 
                        '</span>\
                    </a>\
                    <ul class="dropdown-menu">\
                        <li class="external">\
                            <h3>You have\
                                <span id="msgCount" class="bold" >' + newMsgCount + ' New</span> Messages\
                            </h3>\
                            <a href="underconstruction.html">view all</a>\
                        </li>\
                        <li>\
                            <ul class="dropdown-menu-list scroller" style="height: 275px;" \
                            			data-handle-color="#637283" id="msgesPreviews">' 
                            			+ newMsgPreview +
                            '</ul>\
                        </li>\
                    </ul>\
                </li>\
                <!-- END INBOX DROPDOWN -->\
                ';



var userdropdown = '';
userdropdown += '\
				<!-- BEGIN USER LOGIN DROPDOWN -->\
                <li class="dropdown dropdown-user">\
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" \
                    			data-hover="dropdown" data-close-others="true">\
                    			<img id="profilePic" alt="" class="img-circle" src="../assets/layouts/layout/img/dora.png" />\
                    			<span id="username" class="username username-hide-on-mobile"> White Knight </span>\
                    			<i class="fa fa-angle-down"></i></a>\
					<ul class="dropdown-menu dropdown-menu-default">\
						<li>\
                            <a href="staff-viewdetails.html">\
                                <i class="icon-user"></i> My Profile </a>\
                        </li>\
                        <li>\
                            <a href="app_calendar.html">\
                                <i class="icon-calendar"></i> My Calendar </a>\
                        </li>\
                        <li>\
                            <a href="app_inbox.html">\
                                <i class="icon-envelope-open"></i> My Inbox\
                                <span class="badge badge-danger" id="myInbox"> ? </span>\
                            </a>\
                        </li>\
                        <li class="divider"> </li>\
                        <li>\
                            <a href="login.html">\
                                <i class="icon-key"></i> Log Out </a>\
                        </li>\
                    </ul>\
                </li>\
                <!-- END USER LOGIN DROPDOWN -->\
                ';





var result = '';
//result += logo;
//result = logo + naviMenuStart + naviMenuEnd;
//result = logo + naviMenuStart + notidropdown + naviMenuEnd;
//result = logo + naviMenuStart + notidropdown + msgdropdown + naviMenuEnd;
result = logo + naviMenuStart + notidropdown + msgdropdown + userdropdown  + naviMenuEnd;

document.getElementById('headerbar').innerHTML = result;