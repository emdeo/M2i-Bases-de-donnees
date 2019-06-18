$(document).ready(function() {

	$.ajax({
		url : "Servlet_Couleurs", // chemin relatif (pas de '/')
		type : "get",
		dataType : "json",
		success : function(code, status) {
			// alert(code)
			genererOptionsCouleurs(code)
		},
		error : function(xhr) {
			alert("Erreur " + xhr.status)
		}
	})

	// Modifier la couleur en cliquant sur une nouvelle option
	$("#selectCouleur").on("change", function() {

		changerCouleur()

	})

	// Modifier la couleur en cliquant sur un bouton radio
	$("#radioChoix").on("change", function() {

		changerCouleur()

	})

})