function genererOptionsCouleurs(lstCouleurs) {

	$("#selectCouleur").empty()

	// alert("lstCouleurs : " + lstCouleurs)

	for (i = 0; i < lstCouleurs.length; i++) {
		var ligne = ("<option>" + lstCouleurs[i] + "</option>")
		$("#selectCouleur").append(ligne)
	}

}

function changerCouleur() {

	var choix = $("#radioChoix:selected").val()
	var couleur = $("#selectCouleur").val()

	alert("value : " + choix + "\ncouleur : " + couleur)
	
}