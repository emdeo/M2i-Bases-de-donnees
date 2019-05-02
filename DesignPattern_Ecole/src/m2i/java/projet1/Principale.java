package m2i.java.projet1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Principale {

	public static void main(String[] args) {
		
		/**
		 * MANIPULER DES OBJETS ELEVE ET NOTE :
		 * 
		 * ETAPE 1 - Cr�er un objet de classe Eleve()
		 * ETAPE 2 - Ajouter des notes
		 * ETAPE 3 - Afficher les notes de l'�l�ve
		 * 
		 * AGIR SUR LES TABLES :
		 * 
		 * ETAPE 4 - Cr�er le Data Access Object (pour pouvoir utiliser les m�thodes CRUD sur la table "Eleves")
		 * ETAPE 5 - Instancier les tables (ins�rer des valeurs pr�d�finies)
		 * ETAPE 6 - Ins�rer une ligne dans une table
		 * ETAPE 7 - Afficher une ligne d'une table (id en param�tre)
		 * ETAPE 8 - Lire toutes les ligne d'une table
		 * ETAPE 9 - Modifier les informations de la ligne d'une table
		 * ETAPE 10 - Supprimer une ligne d'une table (id en param�tre)
		 * ETAPE 11 - Supprimer un �l�ve de la table "Eleve" et toutes les notes correspondantes dans la table "Note"
		 * ETAPE 12 - Supprimer toutes les lignes d'une table
		 */
		
		// ETAPE 1
		Eleve e = new Eleve(10, "Alpha", "Alice");
		
		// ETAPE 2
		e.AjoutNote(new Note(20, 15f, LocalDate.now(), Matiere.FRANC));
		e.AjoutNote(new Note(21, 11.5f,LocalDate.of(2012, 12, 12), Matiere.MATHS));
		
		// ETAPE 3
		System.out.println(e);
		
		// ETAPE 4 - NE PAS COMMENTER
		DAO_Eleve daoe = new DAO_Eleve();
		DAO_Note daon = new DAO_Note();
		
		// ETAPE 5
		daoe.Instanciate();
		daon.Instanciate();
		
		// ETAPE 6
		daoe.Create(e);
		daon.Create(new Note(18, 12.5f, LocalDate.now(), Matiere.HIST, 2));
		
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
		
	}

}
