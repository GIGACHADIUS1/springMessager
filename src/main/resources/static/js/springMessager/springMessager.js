let searchUserForm;
let searchUserInput;
let searchUserButton;
let friendUl;
let friendButton;
let resultsSearchButton;
let resultsSearchContainer;
let leftContainer;
let messageUl;
let writeMessageInput;
let writeMessageForm;


let socket;
let stompClient;
let subscribeOnChat;


let haveMessageOrNot = true;
let subscribe = 0;
let urlPath = window.location.pathname;
let id = urlPath.substring(urlPath.lastIndexOf('/') + 1);
let chatId;


function connect() {
   socket = new SockJS('/ws');
   stompClient = Stomp.over(socket);
   stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
   });
   getChatList(getId())
}


function getId() {
   const currentUrl = window.location.href;
   const idRegex = /\/springmessager\/(\d+)/;
   const match = currentUrl.match(idRegex);
   if (match) {
      let stringId = match[1];
      return Number(stringId);
   }
}


document.addEventListener("DOMContentLoaded", function () {


   resultsSearchContainer = document.getElementById('resultsSearchContainer');
   searchUserForm = document.getElementById('searchUserForm');
   searchUserInput = document.getElementById('searchUserInput');
   searchUserButton = document.getElementById('searchUserButton');
   friendUl = document.getElementById('friendUl');
   friendButton = document.getElementById('friendButton');
   resultsSearchButton = document.getElementById('resultsSearchButton');
   leftContainer = document.getElementById('leftContainer');
   messageUl = document.getElementById('messageUl');
   writeMessageInput = document.getElementById('writeMessageInput');
   writeMessageForm = document.getElementById('writeMessageForm');


   resultsSearchButton.addEventListener("click", addChat);
   searchUserForm.addEventListener("submit", sendFindUserRequest);
   searchUserButton.addEventListener("click", sendFindUserRequest);
   writeMessageForm.addEventListener("submit", sendMessage);


   const friendButtons = [...document.getElementsByClassName('friendButton')];
   friendButtons.forEach(function (friendButton) {
      friendButton.addEventListener('click', function () {
         friendButOnClick(this.innerText);
      });
   });


   connect();


});

