package m2i.java.projet1;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Utilitaire {

	/**
	 * G�n�re des requ�tes CRUD SQL � partir des attributs d'une classe pass�e en
	 * param�tre (mod�les ci-dessous)
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

		// Cr�er une map vide
		Map<String, String> listeRequetes = new HashMap<String, String>();

		// Stocker les requ�tes dans la Map()
		listeRequetes.put("Create", generateCreateSql(nomClasse));
		listeRequetes.put("Read", generateReadSql(nomClasse, false));
		listeRequetes.put("ReadAll", generateReadSql(nomClasse, true));
		listeRequetes.put("Delete", generateDeleteSql(nomClasse));
		listeRequetes.put("Update", generateUpdateSql(nomClasse));

		return listeRequetes;
	}

	/**
	 * 
	 * Afficher la requ�te SQL pour appliquer un CREATE � partir d'une classe pass�e
	 * en param�tre. Les champs sont g�n�r�s automatiquement.
	 * 
	 * @param nomClasse
	 * @return String (requ�te SQL)
	 */
	public static String generateCreateSql(String nomClasse) {

		String create = "INSERT INTO";

		// G�n�rer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		String champsInsert = "";

		// G�n�rer les champs vides
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
	 * Afficher la requ�te SQL pour appliquer un READ � partir d'une classe pass�e
	 * en param�tre. Les champs sont g�n�r�s automatiquement.
	 * 
	 * Affiche TOUTE la table si all == "true", n'affiche qu'une seule entr�e sinon.
	 * 
	 * @param nomClasse
	 * @param all
	 * @return String (requ�te SQL)
	 */
	public static String generateReadSql(String nomClasse, boolean all) {

		String read = "SELECT * FROM";

		// G�n�rer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		// Si all == "true", on affiche toutes les entr�es
		if (all)
			return String.join(" ", Arrays.asList(read, nomTable));

		// Si all == "false", on n'affiche qu'une seule entr�e
		String where = "WHERE ID_" + nomTable + " = ?";

		return String.join(" ", Arrays.asList(read, nomTable, where));
	}

	/**
	 * 
	 * Afficher la requ�te SQL pour appliquer un UPDATE � partir d'une classe pass�e
	 * en param�tre. Les champs sont g�n�r�s automatiquement.
	 * 
	 * @param nomClasse
	 * @return String (requ�te SQL pour un update)
	 */
	public static String generateUpdateSql(String nomClasse) {

		String update = "UPDATE";

		// G�n�rer le nom de la table
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
	 * Afficher la requ�te SQL pour appliquer un DELETE � partir d'une classe pass�e
	 * en param�tre. Les champs sont g�n�r�s automatiquement.
	 * 
	 * @param nomClasse
	 * @return String (requ�te SQL)
	 */
	public static String generateDeleteSql(String nomClasse) {

		String delete = "DELETE FROM";

		// G�n�rer le nom de la table
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];

		// La 1ere lettre du nom de table est une majuscule (utile pour mettre en forme
		// les noms de champs)
		nomTable = nomTable.substring(0, 1).toUpperCase() + nomTable.substring(1);

		String where = "WHERE ID_" + nomTable + " = ?";

		return String.join(" ", Arrays.asList(delete, nomTable, where));
	}

	/**
	 * Afficher toutes les requetes renvoy�es par la m�thode defRequetes()
	 * 
	 * @param nomClasse
	 */
	public static void AfficherRequetes(String nomClasse) {

		System.out.println("Requ�tes SQL g�n�r�es � partir de la classe " + nomClasse + " :");

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
	 * Renvoie la liste des attributs et des m�thodes d'une classe pass�e en
	 * param�tre
	 * 
	 * @param nomComplet (package.nomClasse)
	 * @return liste des champs d'une classe (attributs)
	 * @throws ClassNotFoundException
	 */
	public static Field[] ListeDesChamps(String nomComplet) throws ClassNotFoundException {

		/*
		 * Op�rateur diamant <?> : on demande au syst�me de deviner le type r�el de
		 * l'objet pass� en param�tre de Class ci-dessous
		 */
		Class<?> maclasse = Class.forName(nomComplet); // prend en param�tre le nom du package suivi de la classe

		return maclasse.getDeclaredFields();

	}

	/**
	 * 
	 * Dit si une chaine de caract�re d�bute par le pr�fixe pass� en param�tre
	 * 
	 * @param test
	 * @param prefixe
	 * @return boolean (true si le pr�fixe �quivaut au d�but du test)
	 */
	public static boolean isKey(String test, String prefixe) {
		if (prefixe.length() > test.length() || prefixe == "") {
			System.out.println("Le pr�fixe n'est pas valide");
			return false;
		}

		String output = test.substring(0, prefixe.length()); // Extraire le d�but de la chaine test

		if (output.contentEquals(prefixe))
			System.out.println(output + " = " + prefixe);
		else
			System.out.println(output + " != " + prefixe);

		return output.contentEquals(prefixe); // Comparer le d�but de test avec le pr�fixe

	}

}
