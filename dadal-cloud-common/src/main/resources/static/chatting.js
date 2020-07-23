var stompClient = null;
function connect(){
	var socket = new SockJS('/speaking');
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame){
		stompClient.subscribe('/user/quene/chatting', function(chat){
			showChatting(JSON.parse(chat.body));
		});
	});
}

function sendMsg(){
	stompClient.send('/speak/chatting',{},
	JSON.stringify(
		{'content':$('#content').val(),
		'to':$('#to').val()}
	));
}

function showChatting(message){
	$('#chattingContent').append('<div>' + message.from + ':' + message.content + '</div>');
}

$(function(){
	connect();
	$('#send').click(function(){
		sendMsg();
	});

})


