function getMarques(){
	
	tab = new Array();
	
	for(i=1;i<= localStorage.length ;i++){
		
		if(localStorage.getItem(i)){
			
			var marque = JSON.parse(localStorage.getItem(i));
			marque = marque.lien+";;"+marque.chemin+";;"+marque.position+";;"+marque.longueur;
			tab.push(marque);
			
			//suppression de la donnee
			localStorage.removeItem(i);
		}
	}
	
	if(localStorage.getItem('compteur'))
		localStorage.removeItem('compteur');
	
	tab = tab.join(",,");
	return tab;
}

function getUrl(){
	return document.location.toString();
}

function getElt(chemin){
	
	tab = chemin.split("::");
	elt_node = (document.getElementsByTagName("BODY"))[0];
	
	//alert(elt_node.nodeName);
	for(i =0; i < tab.length;i++){
		
		indice = parseInt(tab[i]);
		elt_node = elt_node.childNodes[indice];
	}	
	return elt_node;
}


function recharger(ch,pos,lon){
	
		
			var current_node = getElt(ch);
			//alert(current_node.nodeName);
			var texte = current_node.childNodes[0].nodeValue;
			//alert(texte);
			var pre_texte = texte.slice(0,pos);
			var post_texte = texte.slice(pos+lon,texte.length);
			var select = texte.slice(pos,pos+lon);
			
			//alert("pos="+pos+"\nlon="+lon+'\n1='+(pos+lon)+"\n2="+texte.length);

			//alert('avant='+pre_texte+"\nselect="+select+"\nafter="+post_texte);
		
			//noeud parent
			var parent_node = current_node.parentNode;
			
			//creation d'un span
			select_node = document.createTextNode(select);
			span_node = document.createElement("span");
			span_node.setAttribute('style','background-color:yellow');
			span_node.appendChild(select_node);
				
			//creation des elements avant,apres et select
			if(pre_texte !="" && post_texte !=""){
			
				pre_node = document.createTextNode(pre_texte);
				post_node = document.createTextNode(post_texte);	
				
				//insertion au noeud parentNode
				parent_node.insertBefore(post_node,current_node);
				parent_node.insertBefore(span_node,post_node);
				parent_node.insertBefore(pre_node,span_node);
				
				
			}else if(pre_texte !=""){
			
				pre_node = document.createTextNode(pre_texte);
				
				parent_node.insertBefore(span_node,current_node);
				parent_node.insertBefore(pre_node,span_node);

				

			}
			else if(post_texte !=""){
			
				post_node = document.createTextNode(post_texte);	
			
				parent_node.insertBefore(post_node,current_node);
				parent_node.insertBefore(span_node,post_node);
		
			}
			else{
				
				parent_node.insertBefore(span_node,current_node);
			}
			
			//suppression de l'ancien texte
			parent_node.removeChild(current_node);
				
}

function appeleRech(reps)
{
	var reps1 = reps.split(',,');
		alert(reps1.length);
		for (i=0;i<reps1.length;i++)
		{
			alert(i);
			var reps2 = reps1[i].split(';;');
			var ch = reps2[0];
			var pos = parseInt(reps2[1]);
			var lon = parseInt(reps2[2]);
			recharger(ch,pos,lon);		
		}
}


function sendValue(value,cb) 
{
  var req = new XMLHttpRequest();
  req.addEventListener('readystatechange', function (evt) {
    if (req.readyState === 4) {
      if (req.status === 200) 
	  {
		var reps = req.responseText;
		cb(reps);
		//alert(reps);
		
      } else {
        alert("ERROR: status " + req.status);
      }
    }
  });
  
  req.open('POST', 'http://localhost:9090/JEE3/sauv.jsp', true);
  req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  req.send('URL=' + value);

}


var urlPage = getUrl();
sendValue(urlPage,appeleRech);