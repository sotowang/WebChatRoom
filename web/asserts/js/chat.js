var messageTo=null;
var histories=[];
function History(htmls,id){
	this.id=id;
	this.htmls=htmls;
	return this;
}
$(document).ready(function(){
	initConnection();
	getOnlineUser();
	loadChatHistory();
	$('#switch-list').click(function(){
		$('#online-list').css('display','block');
	})
	$('#opt-close').click(function(){
		$('#online-list').css('display','none');
	})
	$('#send-message').click(function(){
		sendMessage();
	})
	$('#reset-message').click(function(){
		$('#message-content').val('');
	})
	$('#logout').click(function(){
		closeConnection();
	})
})
function findHistory(history){		 //找到对应的聊天历史
	for (var i = 0; i < histories.length; i++) {
		var cur = histories[i];
		if (cur.id==history.id && cur.from==history.from){
			return cur;
		}
	}
} 
var websocket=null;
var url = 'ws://localhost:8080/ChatRoom/chat/' + loginedUsername;
function initConnection(){
	if ("WebSocket" in window){
		websocket=new WebSocket(url);
	}else{
		alert('您当前的浏览器不支持Websocket!');
	}
	websocket.onerror=function(){
		alert('websocke通信协议发生错误');
	}
	websocket.onopen=function(){
		console.log('websocket协议已经打开');
	}
	websocket.onclose=function(){
		logout();
		alert('您当前已经被注销！请重新登录');
		window.location.href="Login";
	}
	window.onbeforeunload=function(){
		backupCurHistory();
		websocket.close();
	}
	websocket.onmessage=function(event){
		var msg=JSON.parse(event.data);
		if (msg.messageType.trim()=='ChatMessage'){
			addRecievedMessage(msg);
		}else{
			if (msg.messageType=='SystemNotify'){
				getOnlineUser();
			}
		}
	}
}
function closeConnection(){
	websocket.close();
}
function sendMessage(){
	var content=$('#message-content').val();
	if (content==null || content.trim().length<=0){
		$('#message-content')[0].focus();
		console.log('内容不符合要求！');
		return false;
	}
	var date=new Date();
	console.log(loginedUsernick);
	console.log(loginedUsername);
	var msg={
		time: date.getHours()+':'+date.getMinutes()+':'+date.getSeconds(),
		messageContent:content,
		messageType:'ChatMessage',
		from:loginedUsername,
	}
	addSentMessage(msg);
	$('#message-content').val('');
	if (websocket==null){
		initConnection();
	}
	websocket.send(JSON.stringify(msg));
	return false;
}
function addSentMessage(msg) {
    var sentMsgTemplate = '<li class="message-cell message-sent">\
                    <span class="message-info">\
                        <a class="message-time">{{time}}</a>\
                        <a class="send-user">{{user}}说:<br/></a>\
                        <a class="message-content">{{content}}</a>\
    				</span>\
                </li>';
    var msgText = sentMsgTemplate.replace(/{{time}}/, msg.time).replace(/{{user}}/, loginedUsernick).replace(/{{content}}/, msg.messageContent);
    $('.messages-list').append(msgText);
    scrollToBottom();
}
function addRecievedMessage(msg) {
    var recievedMsgTemplate = '<li class="message-cell message-recieved">\
        			<span class="message-info">\
    					<a class="message-time">{{time}}</a>\
    					<a class="send-user">{{user}}说:<br/></a>\
        				<a class="message-content">{{content}}</a>\
    				</span>\
    			</li>';
    if (msg.time == null) {
        var now = new Date();
        msg.time = now.getHours()+':'+now.getMinutes()+':'+now.getSeconds();
    }
    var msgText = recievedMsgTemplate.replace(/{{time}}/, msg.time).replace(/{{user}}/, msg.fromNick).replace(/{{content}}/, msg.messageContent);
    $('.messages-list').append(msgText);
    scrollToBottom();
}
function scrollToBottom() {
    var destView = $('.bottom-anchors')[0];
    destView.scrollIntoView(false);
}
function logout(){
	$.ajax({
		url:'logout',
		type:'POST',
		data:JSON.stringify({}),
		dataType: "JSON",
		contentType: "application/json;charset=utf-8",
		success:function(){
		},
		error:function(){
		}
	})
}
function getOnlineUser(){
	$.ajax({
		url:'userlist',
		type:'GET',
		success: function(data){
            var onlineUserTemplate = '<li class="user-cell">\
                           <span class="user-info">用户名:<a class="user-name">{{username}}</a> 昵称:<a class="user-nick">{{nickname}}</a></span>\
                       </li>';
           var htmls = [];
           var allUsers = data;
           console.log('所有信息'+data);
           if (allUsers instanceof Array) {
               for (var i = 0; i < allUsers.length; i++) {
                   var user = allUsers[i];
                   var tempHtml = onlineUserTemplate.replace(/{{username}}/, user.username).replace(/{{nickname}}/, user.nickname);
                   htmls.push(tempHtml);
                   console.log('单个用户：'+user);
               }
           }
           $('.users-list').html('');
           $('.users-list').append(htmls.join(''));
       },
       error: function(){
       }
   })
}
function backupCurHistory(){
    // 备份当前历史纪录
	// 从localStorage读取历史纪录
    histories = getHistories();
    var curHis = new History($('.messages-list').html(), messageTo);
    curHis.from = loginedUsername;
    var storedHis = findHistory(curHis);
    if (null == storedHis) {
        histories.push(curHis);
        
    }else {
        histories.splice(histories.indexOf(storedHis), 1);
        histories.push(curHis);
    }
    saveHistories(histories);
}
function getHistories() {
    histories = window.localStorage.getItem('histories');
    if (null == histories) {
        histories = new Array();
    }else {
        histories = JSON.parse(histories);
    }
    return histories;
}
function saveHistories(his) {
    // 将历史纪录存储到storage
    window.localStorage.setItem('histories', JSON.stringify(his));
}
function loadChatHistory() {
    histories = getHistories();
    var destHis = new History(null, messageTo);
    destHis.from = loginedUsername;
    var storedHis = findHistory(destHis);
    if (null == storedHis) {
        $('.messages-list').html('');
    }else {
        $('.messages-list').html(storedHis.htmls);
    }
}
