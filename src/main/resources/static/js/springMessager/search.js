function sendFindUserRequest(event) {
   event.preventDefault();
   var request = searchUserInput.value.trim();
   if (request) {
      fetch("/find/" + request, {
         method: 'POST'
      })
          .then(response => response.json())
          .then(result => showFindUser(result))
          .catch(error => console.error('Ошибка:', error));
   }
}


function showFindUser(userDTO) {
   if (userDTO) {
      resultsSearchButton.style.display = 'block';
      resultsSearchButton.style.pointerEvents = 'auto';
      document.addEventListener('click', deletePopUp);
      resultsSearchButton.textContent = "Username:" + userDTO.name + " |  ID:" + userDTO.id + " | ADD TO FRIENDS";
   } else {
      resultsSearchButton.textContent = 'SEARCH ERROR';
   }
}


function deletePopUp(event) {
   let isClickInsideInput = searchUserInput.contains(event.target);
   let isClickInsideButton = resultsSearchButton.contains(event.target);
   let isClickInsideAreaBetween = leftContainer.contains(event.target);
   if (!isClickInsideInput && !isClickInsideButton && !isClickInsideAreaBetween) {
      buttonOnHidden();
   }
}


function buttonOnHidden() {
   resultsSearchButton.style.display = 'none';
   resultsSearchButton.style.pointerEvents = 'none';
   document.removeEventListener('click', deletePopUp);
}