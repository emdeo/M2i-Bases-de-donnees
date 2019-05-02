package m2i.java.projet1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Utilitaire {

	/**
	 * Génère des requêtes CRUD SQL à partir des attributs d'une classe passée en
	 * paramètre (modèles ci-dessous)
	 * 
	 * INSERT INTO nomClasse VALUES (?,?,?) SELECT * FROM nomClasse SELECT * FROM
	 * nomClasse WHERE ID_nomClasse = ? DELETE FROM nomClasse WHERE ID_nomClasse = ?
	 * UPDATE nomClasse SET Champs1 = ?, Champs2 = ? WHERE ID_nomClasse = ?
	 * 
	 * @param nomClasse
	 * @return Map<String, String>
	 * @throws ClassNotFoundException
	 */
	public static Map<String, String> defRequetes(String nomClasse) throws ClassNotFoundException {

		Map<String, String> listeRequetes = new HashMap<String, String>(); // Créer une map vide
		Class<?> maClasse = Class.forName(nomClasse); // Détecter le type de ma classe (Eleve, Note...), nécessaire pour
														// détecter les attributs et les méthodes

		// Débuts de requête SQL
		String insert = "INSERT INTO";
		String readAll = "SELECT * FROM";
		String delete = "DELETE FROM";
		String update = "UPDATE";

		// Générer les morceaux de requête manquants
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];
		String where = "WHERE ID_" + nomTable + " = ?";
		String champsInsert = ""; // ?,?,?,?...
		String champsUpdate = ""; // champs1 = ?, champs2 = ?, champs3 = ?...

		// Générer les champs vides
		try {
			Field[] mesAttributs = Utilitaire.ListeDesChamps(nomClasse);
			for (Field f : mesAttributs) {
//				System.out.println(f.getName() + " (" + f.getType().getSimpleName() + ")");
				champsInsert += "?,";
				champsUpdate += f.getName() + " = ?, ";
			}
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}

		champsInsert = "(" + champsInsert.substring(0, champsInsert.length() - 1) + ")";

		String[] champsUpdate2 = champsUpdate.split(",");
		champsUpdate2 = Arrays.copyOfRange(champsUpdate2, 1, champsUpdate2.length);
		champsUpdate = String.join(",", champsUpdate2);;

		champsUpdate = champsUpdate.substring(0, champsUpdate.length() - 2);

//		System.out.println(insert);
//		System.out.println(select);
//		System.out.println(delete);
//		System.out.println(update);
//		System.out.println(nomTable);
//		System.out.println(where);
//		System.out.println(champsInsert);
//		System.out.println(champsUpdate);

		// Compléter les requêtes
		insert += " " + nomTable + " VALUES " + champsInsert;
		readAll += " " + nomTable;
		String read = readAll + " " + where;
		delete += " " + nomTable + " " + where;
		update += " " + nomTable + champsUpdate + " " + where;

		// Stocker les requêtes dans la Map()
		listeRequetes.put("Insert", insert);
		listeRequetes.put("Read", read);
		listeRequetes.put("ReadAll", readAll);
		listeRequetes.put("Delete", delete);
		listeRequetes.put("Update", update);

		return listeRequetes;
	}

	public static Field[] ListeDesChamps(String nomComplet) throws ClassNotFoundException {

		/*
		 * Opérateur diamant <?> : on demande au système de deviner le type réel de
		 * l'objet passé en paramètre de Class ci-dessous
		 */
		Class<?> maclasse = Class.forName(nomComplet); // prend en paramètre le nom du package avec celui de la classe

		return maclasse.getDeclaredFields();

	}

}
