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



document.getElementById('notificationsBadge').innerHTML = newNotiCount;
document.getElementById('pendingNotifications').innerHTML = newNotiCount + ' pending';
document.getElementById('allNotifications').innerHTML = newNotifications;
