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