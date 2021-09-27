var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/secured/room');
    stompClient = Stomp.over(socket);
    var sessionId = "";
    var id = $("#name").val();
    var headers = {
           uuid: id
           };
    stompClient.connect(headers, function (frame) {
        var url = stompClient.ws._transport.url;
        console.log("frame " + frame.headers['user-name']);
        url = url.replace("ws://localhost:8081/secured/room/",  "");
        url = url.replace("/websocket", "");
        url = url.replace(/^[0-9]+\//, "");
        console.log("Your current session is: " + url);
        sessionId = url;
        stompClient.subscribe('/user/queue/response/completed'
        , function (msgOut) {
             //handle messages
             console.log("Received message: " +  msgOut);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var id = $("#name").val();
    var headers = {
           uuid: id
           };

    stompClient.send("/app/quota/response",
    headers, JSON.stringify({ 'from': 'none','to': $("#name").val(), 'text': 'teste osn'}));

}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});