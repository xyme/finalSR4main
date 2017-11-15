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


document.getElementById('myInbox').innerHTML = newMsgCount;
document.getElementById('newMsgesCountBadge').innerHTML = newMsgCount; //modifies the badge count
document.getElementById('msgCount').innerHTML = newMsgCount; //modifies "You have xxx new messages"
document.getElementById('msgesPreviews').innerHTML = newMsgPreview; //dynamically generates the scrollable new msges preview

