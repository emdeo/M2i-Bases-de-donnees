package m2i.java.projet1;

import java.time.LocalDate;

public class Principale {

	public static void main(String[] args) {
		
		/*
		 * MANIPULER DES OBJETS ELEVE ET NOTE :
		 * 
		 * ETAPE 1 - Cr�er un objet de classe Eleve()
		 * ETAPE 2 - Ajouter des notes
		 * ETAPE 3 - Afficher les notes de l'�l�ve
		 * 
		 * AGIR SUR LA TABLE "ELEVES" :
		 * 
		 * ETAPE 4 - Cr�er le Data Access Object (pour pouvoir utiliser les m�thodes CRUD sur la table "Eleves")
		 * ETAPE 5 - Ins�rer une ligne dans la table "El�ves"
		 * ETAPE 6 - Afficher un �l�ve de la table (id en param�tre)
		 * ETAPE 7 - Lire toutes les ligne de la table "El�ves"
		 * ETAPE 8 - Modifier les informations d'une ligne de la table "Eleves"
		 * ETAPE 9 - Supprimer un ligne de la table "El�ves" (ID_Eleve en param�tre)
		 * 
		 * AGIR SUR LA TABLE "NOTES" :
		 * 
		 * ETAPE 10 - Cr�er le Data Access Object (pour pouvoir utiliser les m�thodes CRUD sur la table "Notes")
		 * ETAPE 11 - Ins�rer une ligne dans la table "Notes"
		 * ETAPE 12 - Afficher une note de la table (id en param�tre)
		 * ETAPE 13 - Afficher toutes les notes de la table
		 * ETAPE 14 - Modifier une note de la table
		 * ETAPE 15 - Supprimer une note de la table
		 */
		
		// ETAPE 1
		Eleve e = new Eleve(1, "Alpha", "Alice");
		
		// ETAPE 2
//		e.AjoutNote(new Note(10, 15f, 2, LocalDate.now(), Matiere.FRANC));
//		e.AjoutNote(new Note(11, 11.5f, 1,LocalDate.of(2012, 12, 12), Matiere.MATHS));
		
		// ETAPE 3
		System.out.println(e);
		
		// ETAPE 4
		DAO_Eleve daoe = new DAO_Eleve();
		
		// ETAPE 5
//		daoe.Create(new Eleve(2,"Alpha","Alice"));
		
		// ETAPE 6
//		System.out.println(daoe.Read(1));
		
		// ETAPE 7
//		System.out.println(daoe.ReadAll());
		
		// ETAPE 8
//		daoe.Update(new Eleve(2,"Bibi","Bouboule"));
		
		// ETAPE 9
//		daoe.Delete(1);
		
		// ETAPE 10
		DAO_Note daon = new DAO_Note();
		
		// ETAPE 11
//		daon.Create(new Note(1, 12.5f, 3, LocalDate.now(), Matiere.HIST, 2));
		
		// ETAPE 12
		System.out.println(daon.Read(3));
		
		// ETAPE 13
//		System.out.println(daon.ReadAll());
		
		// ETAPE 14
//		daon.Update(new Note(3, 10.5f, 2, LocalDate.now(), Matiere.HIST, 3));
		
		// ETAPE 15
//		daon.Delete(1);
		
	}

}
