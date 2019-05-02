package m2i.java.projet1;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Principale {

	public static void main(String[] args) {

		/**
		 * MANIPULER DES OBJETS ELEVE ET NOTE :
		 * 
		 * ETAPE 1 - Créer un objet de classe Eleve() ETAPE 2 - Ajouter des notes ETAPE
		 * 3 - Afficher les notes de l'élève
		 * 
		 * AGIR SUR LES TABLES :
		 * 
		 * ETAPE 4 - Créer le Data Access Object (pour pouvoir utiliser les méthodes
		 * CRUD sur la table "Eleves") ETAPE 5 - Instancier les tables (insérer des
		 * valeurs prédéfinies) ETAPE 6 - Insérer une ligne dans une table ETAPE 7 -
		 * Afficher une ligne d'une table (id en paramètre) ETAPE 8 - Lire toutes les
		 * ligne d'une table ETAPE 9 - Modifier les informations de la ligne d'une table
		 * ETAPE 10 - Supprimer une ligne d'une table (id en paramètre) ETAPE 11 -
		 * Supprimer un élève de la table "Eleve" et toutes les notes correspondantes
		 * dans la table "Note" ETAPE 12 - Supprimer toutes les lignes d'une table
		 */

		// ETAPE 1
		Eleve eleve = new Eleve(10, "Alpha", "Alice");

		// ETAPE 2
		eleve.AjoutNote(new Note(20, 15f, LocalDate.now(), Matiere.FRANC));
		eleve.AjoutNote(new Note(21, 11.5f, LocalDate.of(2012, 12, 12), Matiere.MATHS));

		// ETAPE 3
//		System.out.println(eleve);

		// ETAPE 4 - NE PAS COMMENTER
		DAO_Eleve daoe = new DAO_Eleve();
		DAO_Note daon = new DAO_Note();

		// ETAPE 5
//		daoe.Instanciate();
//		daon.Instanciate();

		// ETAPE 6
//		daoe.Create(eleve);
//		daon.Create(new Note(18, 12.5f, LocalDate.now(), Matiere.HIST, 2));

		// ETAPE 7
//		System.out.println(daoe.Read(1));
//		System.out.println(daon.Read(3));

		// ETAPE 8
//		System.out.println(daoe.ReadAll());
//		System.out.println(daon.ReadAll());

		// ETAPE 9
//		daoe.Update(new Eleve(2,"Bibi","Bouboule"));
//		daon.Update(new Note(3, 10.5f, 2, LocalDate.now(), Matiere.HIST, 3));

		// ETAPE 10
//		daoe.Delete(1);
//		daon.Delete(1);

		// ETAPE 11
//		daoe.Delete(1);

		// ETAPE 12
//		daon.DeleteAll();
//		daoe.DeleteAll();

		/*
		 * EXERCICE SUR LA REFLEXION DE CLASSE
		 */

		try {
			Map<String, String> defR = Utilitaire.defRequetes("m2i.java.projet1.Eleve");
			for (String key : defR.keySet()) {
			    System.out.println(key + "\t\t" + defR.get(key));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
