function updatePic(){
	var pic = document.getElementById("pic");
	var src = document.getElementById("src");
	pic.setAttribute("src", "fotos/" + src.value);
}

function cadastrar(){
	var nome = document.getElementById("nome").value;
	var tam = document.getElementById("tam").value;
	var peso = document.getElementById("peso").value;
}

function back(){
	window.location = "index.html";
}