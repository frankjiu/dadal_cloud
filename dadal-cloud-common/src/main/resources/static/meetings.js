var stompClient = null;
function setConnected(connected) {
	$('#connect').prop('disabled', connected);
	$('#disconnect').prop('disabled', !connected);
	if (connected) {
		$('#conversation').show();
		$('#meetingsContent').show();
	}else{
		$('#conversation').hide();
		$('#meetingsContent').hide();
	}
	$('#meeting').html('');
}

function connect(){
	if (!$('#name').val()) {
		return;
	}
	var socket = new SockJS('/speaking');
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame){
		setConnected(true);
		stompClient.subscribe('/topic/meetings', function(meeting){
			showMeeting(JSON.parse(meeting.body));
		});
	});
}

function disconnect(){
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
}

function sendName(){
	stompClient.send('/speak/meetings',{},JSON.stringify({'name': $('#name').val(),'content':$('#content').val()}));
}

function showMeeting(message){
	$('#meetings').append('<div>' + message.name + ':' + message.content + '</div>');
}

$(function(){
	$('#connect').click(function(){
		connect();
	});

	$('#disconnect').click(function(){
		disconnect();
	});
	
	$('#send').click(function(){
		sendName();
	});

})


