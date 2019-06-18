function genererOptionsCouleurs(lstCouleurs) {

	$("#selectCouleur").empty()

	// alert("lstCouleurs : " + lstCouleurs)

	for (i = 0; i < lstCouleurs.length; i++) {
		var ligne = ("<option>" + lstCouleurs[i] + "</option>")
		$("#selectCouleur").append(ligne)
	}

}

function changerCouleur() {

	// Nom de la couleur choisie
	var couleur = $("#selectCouleur").val()

	// Option sélectionnée (background | texte)
	if ($("#optTexte").is(":checked")) {

//		alert("Texte " + couleur)
		$("#zoneTexte").css("background-color", "white")
		$("#zoneTexte").css("color", couleur)
		
	} else {

//		alert("Background " + couleur)
		$("#zoneTexte").css("background-color", couleur)
		$("#zoneTexte").css("color", "black")
		
	}

}