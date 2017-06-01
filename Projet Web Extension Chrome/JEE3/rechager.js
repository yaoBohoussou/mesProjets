function recharger(ch,pos,lon){
	
		alert(ch);
		alert(pos);
		alert(lon);
		
			/*var current_node = getElt(ch);
			alert(ch);
			var texte = current_node.nodeValue;
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
			parent_node.removeChild(current_node);*/
				
}