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

function build(){
	var display = document.getElementById("display");
	
	buildPopUp(display);
	
	display.appendChild(createCard("fotos/1.jpg", "popUp('Jey', '30cm', '7 kg', 'fotos/1.jpg')"));
	display.appendChild(createCard("fotos/2.jpg", "popUp('Alon', '30cm', '2 kg', 'fotos/2.jpg')"));
	display.appendChild(createCard("fotos/3.jpg", "popUp('Ownti', '17cm', '1 kg', 'fotos/3.jpg')"));
	display.appendChild(createCard("fotos/4.jpg", "popUp('Ourinho', '1 m', '7 kg', 'fotos/4.jpg')"));
	display.appendChild(createCard("fotos/5.jpg", "popUp('Fofo', '1,3 m', '17 kg', 'fotos/5.jpg')"));
	display.appendChild(createCard("fotos/6.jpeg", "popUp('Pede', '1 m', '10 kg', 'fotos/6.jpeg')"));
	display.appendChild(createCard("fotos/7.jpg", "popUp('Tristonho', '30cm', '7 kg', 'fotos/7.jpg')"));
	display.appendChild(createCard("fotos/8.jpg", "popUp('Folhinha', '20cm', '300 g', 'fotos/8.jpg')"));
}