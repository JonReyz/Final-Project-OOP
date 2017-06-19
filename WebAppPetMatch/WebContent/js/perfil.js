function build(){
	var src = document.getElementById("src");
	var nome = document.getElementById("nome");
	var contato = document.getElementById("contato");
	
	nome.value = sessionStorage.name;
	contato.value = sessionStorage.email;
}

function updatePic(){
	var pic = document.getElementById("pic");
	var src = document.getElementById("src");
	pic.setAttribute("src", "perfil/" + src.value);
}

function cadastrar(){
	var nome = document.getElementById("nome").value;
	var contato = document.getElementById("contato").value;
}

function back(){
	window.location = "index.html";
}