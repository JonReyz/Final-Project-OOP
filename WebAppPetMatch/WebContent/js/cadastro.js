if(!sessionStorage.login)
		window.location="login.html"

function updatePic(){
	var pic = document.getElementById("pic");
	var src = document.getElementById("src");
	pic.setAttribute("src", "fotos/" + src.value);
}

function cadastrar(){
	var nome = document.getElementById("nome").value;
	var porte = document.getElementById("port").value;
	var idade = document.getElementById("idade").value;
	var src = document.getElementById("src").value;
	var tipo = document.getElementById("tipo").value;
	var sex = document.getElementById("sex").value;
	
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
	xhr.open('GET', "addAnimalServlet?dbcode=insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description,foto) values('"+nome+"','" + tipo + "','" + sex + "','0','"+porte+"','com pelo','Brabo','" + sessionStorage.email + "'," + idade + ",'0','" + sessionStorage.login + "','Obitão é brabo','"+src+"')");
	xhr.send(null);
}

function back(){
	window.location = "index.html";
}