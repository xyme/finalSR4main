/* 
** Counting seconds clock timer on index page
*/


function startTime() {
	var m_names = new Array("Jan", "Feb", "Mar", 
			"Apr", "May", "Jun", "Jul", "Aug", "Sep", 
			"Oct", "Nov", "Dec");
    var today = new Date();
    var curr_date = today.getDate();
	var curr_month = today.getMonth();
	var curr_year = today.getFullYear();
	document.getElementById('date').innerHTML = (curr_date + " " + m_names[curr_month] + " " + curr_year);
	
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('time').innerHTML =
    h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}

function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}