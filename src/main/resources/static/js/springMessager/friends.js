function addChat() {
   let content = resultsSearchButton.textContent;
   let chatId = getIdByChatButton(content);
   fetch("/addchat/" + id + "/" + chatId, {
      method: 'POST'
   })
       .then(response => response.json())
       .then(result => showNewChat(result))
       .catch(error => console.error('Ошибка:', error));
   buttonOnHidden();
}


function showNewChat(chatDTO) {
   if (chatDTO) {
     let friendUl = document.getElementById('friendUl');
     let friendLi = document.createElement('li');
     let chatButton = document.createElement('button');
      friendLi.appendChild(chatButton);
      friendUl.appendChild(friendLi);
      chatButton.classList.add('friendButton');
      friendLi.classList.add('friendLi');
      chatButton.setAttribute('id', 'friendButton');
      let content;
      let pathId = getId();
      if (Number(chatDTO.user1DTO.id) === pathId) {
         content = chatDTO.user2DTO.name + " | " + chatDTO.id;
      } else {
         content = chatDTO.user1DTO.name + " | " + chatDTO.id;
      }
      console.log(content)
      chatButton.innerText = content;
   } else {
   }
}


function getIdByChatButton(text) {
   let parts = text.split('|');
   let idPart = parts.find(part => part.trim().startsWith('ID:'));
   if (idPart) {
      return idPart.split(':')[1].trim();
   }
   return null;
}
