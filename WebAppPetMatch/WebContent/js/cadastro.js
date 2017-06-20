function updatePic(){
	var pic = document.getElementById("pic");
	var src = document.getElementById("src");
	pic.setAttribute("src", "fotos/" + src.value);
}

function cadastrar(){
	var nome = document.getElementById("nome").value;
	var porte = document.getElementById("port").value;
	var peso = document.getElementById("peso").value;
	var src = document.getElementById("src").value;
	
	/*
	 * 
	<div class = "container"><input type="text" id = "src" style= "font-size: 24px;"></div>
	<div class = "container"><input type="text" id = "nome" style= "font-size: 24px;"></div>
	<div class = "container"><input type="text" id = "tipo" style= "font-size: 24px;"></div>
	<div class = "container"><input type="text" id = "sex" style= "font-size: 24px;"></div>
	<div class = "container"><input type="text" id = "port" style= "font-size: 24px;"></div>
	<div class = "container"><input type="text" id = "idade" style= "font-size: 24px;"></div>
	 * 
	 */
	//insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description,foto) values('Obitão','Cão','M','0','Grande','Grande','Brabo','asd@asd',3,'0',(select login from Users where login='ong'),'Obitão é brabo','foto1')
	var xhr = new XMLHttpRequest();
/*	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4) {
	        var data = xhr.responseText;
	        if (data!="-1"){
	        	var tst = JSON.parse(data);
	            alert(tst[0]);
	        	sessionStorage.name = tst[0][1];
	        	sessionStorage.email = tst[0][2];
	        	sessionStorage.login = tst[0][3];
	        	window.location = "index.html";
	        }
	    }
	}*/
	xhr.open('GET', "addAnimalServlet?dbcode=insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description,foto) values('"+nome+"','Cão','M','0','"+tam+"','"+peso+"','Brabo','asd@asd',3,'0',(select login from Users where login='ong'),'Obitão é brabo','"+src+"')");
	xhr.send(null);
}

function back(){
	window.location = "index.html";
}