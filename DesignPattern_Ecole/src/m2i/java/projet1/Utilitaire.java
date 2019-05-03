package m2i.java.projet1;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Utilitaire {

	/**
	 * Génère des requêtes CRUD SQL à partir des attributs d'une classe passée en
	 * paramètre (modèles ci-dessous)
	 * 
	 * INSERT INTO nomClasse VALUES (?,?,?)
	 * 
	 * SELECT * FROM nomClasse SELECT * FROM nomClasse WHERE ID_nomClasse = ?
	 * 
	 * DELETE FROM nomClasse WHERE ID_nomClasse = ?
	 * 
	 * UPDATE nomClasse SET Champs1 = ?, Champs2 = ? WHERE ID_nomClasse = ?
	 * 
	 * @param nomClasse
	 * @return Map<String, String>
	 * @throws ClassNotFoundException
	 */
	public static Map<String, String> defRequetes(String nomClasse) throws ClassNotFoundException {

		// Créer une map vide
		Map<String, String> listeRequetes = new HashMap<String, String>();

		// Stocker les requêtes dans la Map()
		listeRequetes.put("Create", generateCreateSql(nomClasse));
		listeRequetes.put("Read", generateReadSql(nomClasse, false));
		listeRequetes.put("ReadAll", generateReadSql(nomClasse, true));
		listeRequetes.put("Delete", generateDeleteSql(nomClasse));
		listeRequetes.put("Update", generateUpdateSql(nomClasse));

		return listeRequetes;
	}

	/**
	 * 
	 * Afficher la requête SQL pour appliquer un CREATE à partir d'une classe passée
	 * en paramètre. Les champs sont générés automatiquement.
	 * 
	 * @param nomClasse
	 * @return String (requête SQL)
	 */
	public static String generateCreateSql(String nomClasse) {

		String create = "INSERT INTO";

		// Générer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		String champsInsert = "";

		// Générer les champs vides
		try {
			Field[] mesAttributs = ListeDesChamps(nomClasse);
			for (Field f : mesAttributs) {
				champsInsert += "?,";
			}
		} catch (ClassNotFoundException exc) {
//			exc.printStackTrace();
			return null;
		}

		champsInsert = "(" + champsInsert.substring(0, champsInsert.length() - 1) + ")";

		return String.join(" ", Arrays.asList(create, nomTable, "VALUES", champsInsert));
	}

	/**
	 * 
	 * Afficher la requête SQL pour appliquer un READ à partir d'une classe passée
	 * en paramètre. Les champs sont générés automatiquement.
	 * 
	 * Affiche TOUTE la table si all == "true", n'affiche qu'une seule entrée sinon.
	 * 
	 * @param nomClasse
	 * @param all
	 * @return String (requête SQL)
	 */
	public static String generateReadSql(String nomClasse, boolean all) {

		String read = "SELECT * FROM";

		// Générer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		// Si all == "true", on affiche toutes les entrées
		if (all)
			return String.join(" ", Arrays.asList(read, nomTable));

		// Si all == "false", on n'affiche qu'une seule entrée
		String where = "WHERE ID_" + nomTable + " = ?";

		return String.join(" ", Arrays.asList(read, nomTable, where));
	}

	/**
	 * 
	 * Afficher la requête SQL pour appliquer un UPDATE à partir d'une classe passée
	 * en paramètre. Les champs sont générés automatiquement.
	 * 
	 * @param nomClasse
	 * @return String (requête SQL pour un update)
	 */
	public static String generateUpdateSql(String nomClasse) {

		String update = "UPDATE";

		// Générer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		String champsUpdate = ""; // champs1 = ?, champs2 = ?, champs3 = ?...
		try {
			Field[] mesAttributs = ListeDesChamps(nomClasse);
			for (Field f : mesAttributs) {
				if (!f.getName().contentEquals("ID_" + nomTable)) // Ajouter tous les champs sauf ID_nomClasse
					champsUpdate += f.getName() + " = ?, ";
			}
		} catch (ClassNotFoundException exc) {
//			exc.printStackTrace();
			return null;
		}

		String where = "WHERE ID_" + nomTable + " = ?";

		return String.join(" ", Arrays.asList(update, nomTable, "SET", champsUpdate, where));
	}

	/**
	 * 
	 * Afficher la requête SQL pour appliquer un DELETE à partir d'une classe passée
	 * en paramètre. Les champs sont générés automatiquement.
	 * 
	 * @param nomClasse
	 * @return String (requête SQL)
	 */
	public static String generateDeleteSql(String nomClasse) {

		String delete = "DELETE FROM";

		// Générer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		String where = "WHERE ID_" + nomTable + " = ?";

		return String.join(" ", Arrays.asList(delete, nomTable, where));
	}

	/**
	 * Afficher toutes les requetes renvoyées par la méthode defRequetes()
	 * 
	 * @param nomClasse
	 */
	public static void AfficherRequetes(String nomClasse) {

		System.out.println("Requêtes SQL générées à partir de la classe " + nomClasse + " :");

		try {

			Map<String, String> map = Utilitaire.defRequetes(nomClasse);

			for (Entry<String, String> element : map.entrySet())
				System.out.println("    " + element.getKey() + "\t" + element.getValue());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(); // saut de ligne
	}

	/**
	 * Renvoie la liste des attributs et des méthodes d'une classe passée en
	 * paramètre
	 * 
	 * @param nomComplet (package.nomClasse)
	 * @return liste des champs d'une classe (attributs)
	 * @throws ClassNotFoundException
	 */
	public static Field[] ListeDesChamps(String nomComplet) throws ClassNotFoundException {

		/*
		 * Opérateur diamant <?> : on demande au système de deviner le type réel de
		 * l'objet passé en paramètre de Class ci-dessous
		 */
		Class<?> maclasse = Class.forName(nomComplet); // prend en paramètre le nom du package suivi de la classe

		return maclasse.getDeclaredFields();

	}

	/**
	 * 
	 * Dit si une chaine de caractère débute par le préfixe passé en paramètre
	 * 
	 * @param test
	 * @param prefixe
	 * @return boolean (true si le préfixe équivaut au début du test)
	 */
	public static boolean isKey(String test, String prefixe) {
		if (prefixe.length() > test.length() || prefixe == "") {
			System.out.println("Le préfixe n'est pas valide");
			return false;
		}

		String output = test.substring(0, prefixe.length()); // Extraire le début de la chaine test

		if (output.contentEquals(prefixe))
			System.out.println(output + " = " + prefixe);
		else
			System.out.println(output + " != " + prefixe);

		return output.contentEquals(prefixe); // Comparer le début de test avec le préfixe

	}

}
