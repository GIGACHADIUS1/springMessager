function friendButOnClick(buttonText) {
   if (subscribe !== 0) {
      subscribeOnChat.unsubscribe();
   }
   subscribe = 1;
   var parts = buttonText.split(" | ");
   chatId = parts[1];
   fetch("/openchat/" + chatId)
       .then(response => response.json())
       .then(data => {
          openChat(data);
       })
       .catch(error => {
          console.error("Ошибка при выполнении запроса:", error);
       });
   messageUl.innerHTML = '';
   writeMessageInput.value = '';
   subscribeOnChat = stompClient.subscribe("/topic/chat/" + chatId, function (messageDTO) {
      showMessage(JSON.parse(messageDTO.body));
   });
}


function openChat(chatDTO) {
   if (chatDTO.messages.length !== 0) {
      chatDTO.messages.forEach(function (messageDTO) {
         showMessage(messageDTO);
      });
   } else {
      let messageLi = document.createElement('li');
      let messageButton = document.createElement('button');
      messageLi.appendChild(messageButton);
      messageUl.appendChild(messageLi);
      messageButton.classList.add('messageButton');
      messageLi.classList.add('messageLi');
      messageButton.classList.add('noMessage');
      messageButton.setAttribute('id', 'noMessage')
      messageButton.innerText = 'Send first message';
      haveMessageOrNot = false;
   }
   document.getElementById('writeMessageForm').style.display = 'block';
}


function showMessage(messageDTO) {
   if (haveMessageOrNot === false) {
      document.getElementById('noMessage').remove();
      haveMessageOrNot = true;
   }
   let messageLi = document.createElement('li');
   let messageButton = document.createElement('button');
   messageLi.appendChild(messageButton);
   messageUl.appendChild(messageLi);
   messageButton.innerText = messageDTO.text;
   if (messageDTO.sender.id !== getId()) {
      messageButton.classList.add('messageButton2');
      messageLi.classList.add('messageLi2`');
   } else {
      messageButton.classList.add('messageButton');
      messageLi.classList.add('messageLi');
   }
}


function sendMessage(event) {
   event.preventDefault();
   if (writeMessageInput.value) {
      if (writeMessageInput.value.trim().length > 0) {
         stompClient.send("/app/sendMessage/" + chatId, {}, "Text:" + writeMessageInput.value + "SenderId:" + getId() + "ChatId:" + chatId);
         writeMessageInput.value = '';
      }
   }
}


function getChatList(id) {
   fetch("/showChatList/" + id)
       .then(response => response.json())
       .then(data => {
          data.forEach(function (chatDTO) {
             let friendLi = document.createElement('li');
             let friendBut = document.createElement('button')
             friendUl.appendChild(friendLi);
             friendLi.appendChild(friendBut)
             friendBut.classList.add('friendButton');
             friendLi.classList.add('friendLi');
             friendBut.setAttribute('id', 'friendButton');
             if (chatDTO.user1DTO.id === id) {
                friendBut.innerText = chatDTO.user2DTO.name + " | " + chatDTO.id;
             } else {
                friendBut.innerText = chatDTO.user1DTO.name + " | " + chatDTO.id;
             }
             friendBut.addEventListener('click', function () {
                friendButOnClick(this.innerText);
             })
          })
       })
       .catch(error => {
          console.error("Ошибка при выполнении запроса:", error);
       });


}