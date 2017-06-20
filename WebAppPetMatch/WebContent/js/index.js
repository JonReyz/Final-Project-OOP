function menuClick() {
    var x = document.getElementById('menu');
    if (x.className === 'menuHidden') {
        x.className = 'menuShown';
    } else {
        x.className = 'menuHidden';
    };
}

function popUp(name, size, weight, src) {
    var pop = document.getElementById('pop');
    var nome = document.getElementById('nome');
    var tam = document.getElementById('tam');
    var peso = document.getElementById('peso');
    var img = document.getElementById('img');
    if (name === "-1"){
    	pop.className = "popHide";
        setTimeout(function(){ this.style.display="none"; }.bind(pop), 300);
    } else if(pop.className !== "popShow"){
    	img.src = src;
        pop.style.display="block";
        setTimeout(function(){ this.className="popShow"; }.bind(pop), 10);
        nome.innerHTML = "Nome: " + name;
        tam.innerHTML = "Tamanho: " + size;
        peso.innerHTML = "Peso: " + weight;
    }
}

function createMenuItem(txt, onclick){
	var menuItem = document.createElement("div");
	menuItem.setAttribute("class", "menuItem");
	menuItem.setAttribute("onclick", onclick);
	
	var menuItemIcon = document.createElement("div");
	menuItemIcon.setAttribute("class", "menuItemIcon");
	menuItem.appendChild(menuItemIcon);
	
	var menuItemText = document.createElement("div");
	menuItemText.setAttribute("class", "menuItemText");
	menuItemText.innerHTML = txt;
	menuItem.appendChild(menuItemText);
	
	return menuItem;
}


function createCard(src, onclickFunction){
	var card = document.createElement("div");
	card.setAttribute("class", "card");
	
	var img = document.createElement("img");
	img.setAttribute("class", "img-circle");
	img.setAttribute("src", src);
	img.setAttribute("onclick", onclickFunction);
	
	card.appendChild(img);
	
	return card;
}

function buildPopUp(display){
	var popUp = document.createElement("div");
	popUp.setAttribute("class", "popHide");
	popUp.setAttribute("id", "pop");
	popUp.setAttribute("style", "display: none");
	
	var popUpBar = document.createElement("div");
	popUpBar.setAttribute("class", "popUpBar");
	
	var closeButton = document.createElement("input");
	closeButton.setAttribute("class", "closeButton");
	closeButton.setAttribute("type", "submit");
	closeButton.setAttribute("value", "");
	closeButton.setAttribute("onclick", "popUp('-1')");
	popUpBar.appendChild(closeButton);
	
	popUp.appendChild(popUpBar);

	var popUpCard = document.createElement("div");
	popUpCard.setAttribute("class", "card");
	
	var popUpCardImg = document.createElement("img");
	popUpCardImg.setAttribute("id", "img");
	popUpCardImg.setAttribute("class", "img-circle");
	popUpCard.appendChild(popUpCardImg);
	
	var popUpCardNome = document.createElement("div");
	popUpCardNome.setAttribute("id", "nome");
	popUpCardNome.setAttribute("style", "padding-bottom: 10px; font-size: 50px;");
	popUpCard.appendChild(popUpCardNome);
	
	var popUpCardTam = document.createElement("div");
	popUpCardTam.setAttribute("id", "tam");
	popUpCardTam.setAttribute("style", "padding-bottom: 10px; font-size: 30px;");
	popUpCard.appendChild(popUpCardTam);
	
	var popUpCardPeso = document.createElement("div");
	popUpCardPeso.setAttribute("id", "peso");
	popUpCardPeso.setAttribute("style", "padding-bottom: 10px; font-size: 30px;");
	popUpCard.appendChild(popUpCardPeso);

	popUp.appendChild(popUpCard);
	display.appendChild(popUp);
}


function buildMenu(){
	var menu = document.getElementById("menu");
	menu.setAttribute("class", "menuHidden");
	
	var menuClickable = document.createElement("div");
	menuClickable.setAttribute("id", "menuClickable");
	menuClickable.setAttribute("onclick", "menuClick()");
	
	var menuButton = document.createElement("input");
	menuButton.setAttribute("class", "menuButton");
	menuButton.setAttribute("type", "submit");
	menuButton.setAttribute("value", "");
	menuClickable.appendChild(menuButton);
	
	menu.appendChild(menuClickable);
	
	var profilePicDiv = document.createElement("div");
	profilePicDiv.setAttribute("class", "picDiv");

	var profilePicDivImg = document.createElement("img");
	profilePicDivImg.setAttribute("class", "pic");
	profilePicDivImg.setAttribute("src", "perfil/" + sessionStorage.src);
	profilePicDiv.appendChild(profilePicDivImg);
	
	menu.appendChild(profilePicDiv);
	
	menu.appendChild(createMenuItem("Perfil", 'window.location = "perfil.html";'));
	menu.appendChild(createMenuItem("Cadastrar", 'window.location = "cadastro.html";'));
	menu.appendChild(createMenuItem("Filtros", null));
	menu.appendChild(createMenuItem("Log out", 'logOut()'));

}

function buildDisplay(){
	var display = document.getElementById("display");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4) {
	        var data = xhr.responseText;
	        if (data!="-1"){
	        	var tst = JSON.parse(data);
	        	for (var i=0; i<tst.length; i++){
	        		var card;
	        		var nome = tst[i][1];
	        		var tam = tst[i][5];
	        		var peso = tst[i][6];
	        		var src = tst[i][13];
	        		card = createCard("fotos/" + src, "popUp('"+nome+"', '"+tam+"', '"+peso+"', 'fotos/" + src + "')");
	        		display.appendChild(card);
	        	}
	        }
	    }
	}
	xhr.open('GET', 'animalsDbServlet?dbcode=SELECT * FROM Animals', true);
	xhr.send(null);

	buildPopUp(display);
}

function build(){
	if(!sessionStorage.login)
		window.location="login.html"
	else{
		buildDisplay();
		buildMenu();
	}
}

function logOut(){
	sessionStorage.clear();
	window.location = "login.html";
}