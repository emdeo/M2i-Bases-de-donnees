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

//	La couleur change en sélectionnant une radio différente (background / texte)
	$("#radioChoix").on("change", function() {

		changerCouleur()

	})

})