if(!sessionStorage.login)
		window.location="login.html"

function build(){
	var src = document.getElementById("src");
	var nome = document.getElementById("nome");
	var contato = document.getElementById("contato");
	var img = document.getElementById("pic");
	
	src.value = sessionStorage.src;
	nome.value = sessionStorage.name;
	contato.value = sessionStorage.email;
	img.setAttribute("src", "perfil/" + src.value);
}

function updatePic(){
	var pic = document.getElementById("pic");
	var src = document.getElementById("src");
	pic.setAttribute("src", "perfil/" + src.value);
}

function updateUser(){
	var nome = document.getElementById("nome").value;
	var src = document.getElementById("src").value;
	var email = document.getElementById("contato").value;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', "perfilUpdateServlet?dbcode=UPDATE Guardians SET name = '" + nome + "', email = '" + email + "', foto = '" + src + "' WHERE login ='" + sessionStorage.login +  "'");
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4) {
	        var data = xhr.responseText;
	        if (data=="-1"){
	        	alert("Houve um erro na atualizacao do usuario.");
	        }
	        else if (data=="1"){
	        	alert("Dados do usuario atualizados com sucesso!");
	        	sessionStorage.name = nome;
	        	sessionStorage.email = email;
	        	sessionStorage.src = src;
	        	window.location="index.html";
	        }
	    }
	}
	xhr.send(null);
}

function cadastrar(){
	var nome = document.getElementById("nome").value;
	var contato = document.getElementById("contato").value;
}

function back(){
	window.location = "index.html";
}