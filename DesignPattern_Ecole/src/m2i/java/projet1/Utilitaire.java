package m2i.java.projet1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Utilitaire {

	/**
	 * G�n�re des requ�tes CRUD SQL � partir des attributs d'une classe pass�e en
	 * param�tre (mod�les ci-dessous)
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

		Map<String, String> listeRequetes = new HashMap<String, String>(); // Cr�er une map vide
		Class<?> maClasse = Class.forName(nomClasse); // D�tecter le type de ma classe (Eleve, Note...), n�cessaire pour
														// d�tecter les attributs et les m�thodes

		// D�buts de requ�te SQL
		String insert = "INSERT INTO";
		String readAll = "SELECT * FROM";
		String delete = "DELETE FROM";
		String update = "UPDATE";

		// G�n�rer les morceaux de requ�te manquants
		String[] nomCompletTable = nomClasse.split("\\.");
		String nomTable = nomCompletTable[nomCompletTable.length - 1];
		String where = "WHERE ID_" + nomTable + " = ?";
		String champsInsert = ""; // ?,?,?,?...
		String champsUpdate = ""; // champs1 = ?, champs2 = ?, champs3 = ?...

		// G�n�rer les champs vides
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

		// Compl�ter les requ�tes
		insert += " " + nomTable + " VALUES " + champsInsert;
		readAll += " " + nomTable;
		String read = readAll + " " + where;
		delete += " " + nomTable + " " + where;
		update += " " + nomTable + champsUpdate + " " + where;

		// Stocker les requ�tes dans la Map()
		listeRequetes.put("Insert", insert);
		listeRequetes.put("Read", read);
		listeRequetes.put("ReadAll", readAll);
		listeRequetes.put("Delete", delete);
		listeRequetes.put("Update", update);

		return listeRequetes;
	}

	public static Field[] ListeDesChamps(String nomComplet) throws ClassNotFoundException {

		/*
		 * Op�rateur diamant <?> : on demande au syst�me de deviner le type r�el de
		 * l'objet pass� en param�tre de Class ci-dessous
		 */
		Class<?> maclasse = Class.forName(nomComplet); // prend en param�tre le nom du package avec celui de la classe

		return maclasse.getDeclaredFields();

	}

}
