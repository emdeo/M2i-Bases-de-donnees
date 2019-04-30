package m2i.BDD;

import java.sql.Connection;
import java.util.ArrayList;

public class Principale {

	public static void main(String[] args) {

		/*
		 * "Java build path" -> "Library" -> "Add external JAR"
		 * (mysql-connector-java-8.0.16) Vérifier que Apache et MySQL soient bien
		 * démarrés (et MySQL en Admin)
		 * 
		 * PAS NECESSAIRE : la classe DAO_Personne permet déjà de se connecter à la BD
		 */
//		Connection cnn1 = Connexion.get_instance();
//		System.out.println(cnn1);

		/*
		 * PRESENTATION DU CODE CI-DESSOUS :
		 * 
		 * ETAPE 1 - Créer des objets de type DAO_Personne et DAO_Societe (nécessaire pour utiliser les méthodes CRUD)
		 * 
		 * ETAPE 2 - Ajouter une peronne à la table "Personne"
		 * ETAPE 3 - Afficher l'objet Personne dont l'ID est passé en paramètre
		 * ETAPE 4 - Afficher tous les objets Personne de la table
		 * ETAPE 5 - Mettre à jour une ligne de la table "Personne" (je l'identifie à son ID : 3)
		 * ETAPE 6 - Supprimer une ligne de la table "Personne"
		 * 
		 * ETAPE 7 - Créer un objet Societe qu'on ajoute à la table "Société"
		 * ETAPE 8 - Afficher une société (ID en paramètre)
		 * ETAPE 9 - Afficher la liste d'employés d'une société en particulier (ID en paramètre)
		 * ETAPE 10 - Afficher la liste complète des Sociétés
		 * ETAPE 11 - Modifier les informations d'une Société
		 * ETAPE 12 - Supprimer une société de la table
		 * ETAPE 13 - Ajouter un employé à une Societe
		 */

		// ETAPE 1
		DAO_Personne daop = new DAO_Personne();
		DAO_Societe daos = new DAO_Societe();

		// ETAPE 2
//		daop.Create(new Personne(5, "Echo", "Elise", 34, 1.37f, Genre.FEMININ, 2));

		// ETAPE 3
//		System.out.println(daop.Read(1));

		// ETAPE 4
//		System.out.println(daop.ReadAll());

		// ETAPE 5
//		System.out.println(daop.Update(new Personne(3, "Carroll", "Cecile", 69f, 1.87f, Genre.FEMININ, 1)));

		// ETAPE 6
//		daop.Delete(3);
		
		// ETAPE 7
//		daos.Create(new Societe(3, "NAZA", 12.9f, Activites.SCIENCES));
		
		// ETAPE 8
//		System.out.println(daos.Read(2));

		// ETAPE 9
//		Afficher_Personne(daop.ListeEmployesSociete(2));
		
		// ETAPE 10
//		Afficher_Societe(daos.ReadAll());
		
		// ETAPE 11
//		daos.Update(new Societe(2, "Blablabus", 5.4f, Activites.TRANSPORTS));
		
		// ETAPE 12
//		daos.Delete(3);
		
		// ETAPE 13
//		Societe s1 = new Societe(3, "Picsou", 101f, Activites.FINANCE);
//		s1.AjoutEmploye(new Personne(6, "Foxtrot", "Fifi", 34f, 1.12f, Genre.MASCULIN, s1.get_ID_Societe()));
//		Afficher_Personne(s1.get_lstEmployes());
		
	}
	
	public static void Afficher_Personne(ArrayList<Personne> liste) {
		System.out.println("LISTE DE PERSONNES :");
		for (Personne element : liste)
			System.out.println(element + "\n");
	}

	public static void Afficher_Societe(ArrayList<Societe> liste) {
		System.out.println("LISTE DE SOCIETES :");
		for (Societe element : liste)
			System.out.println(element.toString());
	}
}













