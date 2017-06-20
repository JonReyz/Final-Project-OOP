/*var id = document.getElementById('id');
var pass = document.getElementById('pass');
var btCad = document.getElementById('cadastrar');
var btVer = document.getElementById('verificar');
var verificado = document.getElementById('cadastro');
*/
/*
var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if (xhr.readyState == 4) {
        var data = xhr.responseText;
       
    }
}*/

function checkUser(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4) {
	    	var data = xhr.responseText;
	        if (data=="-1")
	        	alert("Houve um erro na verificacao do usuario.");
	        else{
	             var tst = JSON.parse(data);
	              //alert(tst[0]);
	             sessionStorage.name = tst[0][1];
	             sessionStorage.email = tst[0][2];
	             sessionStorage.login = tst[0][3];
	             sessionStorage.src = tst[0][4];
	             window.location = "index.html"; 
	        	}
	        }
	    }
	xhr.open('GET', 'loginServlet?usu=' + id.value + '&pass=' + pass.value, true);
	xhr.send(null);
}

function newUser(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4) {
	    	var data = xhr.responseText;
	        if (data=="-1")
	        	alert("Houve um erro na criacao do usuario.");
	        else 
	        	alert("Usuario criado com sucesso!");
	    }
	}
	xhr.open('GET', 'newUserServlet?usu=' + id.value + '&pass=' + pass.value, true);
	xhr.send(null);
}

