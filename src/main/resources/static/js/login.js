let loginNameInput;
let loginPasswordInput;
let regNameInput;
let regPasswordInput;
let login1Container;
let reg1Container
let login2Container;
let reg2Container;


function connect() {
   reg1Container.classList.add('hidden');
   reg2Container.classList.add('hidden');
}


function toLogin(event) {
   event.preventDefault();
   login1Container.classList.remove('hidden');
   login2Container.classList.remove('hidden');
   reg1Container.classList.add('hidden');
   reg2Container.classList.add('hidden');
}


function toReg(event) {
   event.preventDefault();
   login1Container.classList.add('hidden');
   login2Container.classList.add('hidden');
   reg1Container.classList.remove('hidden');
   reg2Container.classList.remove('hidden');
}


function loginSubmit(event) {
   event.preventDefault();
   var loginData = loginNameInput.value + ' | ' + loginPasswordInput.value;
   console.log(loginData)
   fetch("/login/" + loginData)
       .then(response => response.json())
       .then(data => {
          if (data === 0) {
             regNameInput.value = '';
             regPasswordInput.value = '';
             regNameInput.setAttribute('placeholder', 'Uncorrect name or password');
             regPasswordInput.setAttribute('placeholder', 'Uncorrect name or password');
          } else {
             window.location.href = '/springmessager/' + data;
          }
       }).catch(error => console.error("Ошибка при отправке запроса:", error));
}


function reqSubmit(event) {
   event.preventDefault();
   var regData = regNameInput.value + ' | ' + regPasswordInput.value;
   fetch("/registration/" + regData)
       .then(response => response.json())
       .then(data => {
          if (data === 0) {
             regNameInput.value = '';
             regPasswordInput.value = '';
             regNameInput.setAttribute('placeholder', 'Uncorrect name or password');
             regPasswordInput.setAttribute('placeholder', 'Uncorrect name or password');
          } else {
             window.location.href = '/springmessager/' + data;
          }
       }).catch(error => console.error("Ошибка при отправке запроса:", error));
}


document.addEventListener('DOMContentLoaded', function () {


   loginNameInput = document.getElementById('loginNameInput');
   loginPasswordInput = document.getElementById('loginPasswordInput');
   regNameInput = document.getElementById('regNameInput');
   regPasswordInput = document.getElementById('regPasswordInput');
   login1Container = document.getElementById('login1Container')
   reg1Container = document.getElementById('reg1Container')
   login2Container = document.getElementById('login2Container')
   reg2Container = document.getElementById('reg2Container')


   connect();


   document.getElementById('toLoginButton').addEventListener('click', toLogin);
   document.getElementById('toRegButton').addEventListener('click', toReg);
   document.getElementById('regSubmit').addEventListener('click', reqSubmit);
   document.getElementById('loginSubmit').addEventListener('click', loginSubmit);


});