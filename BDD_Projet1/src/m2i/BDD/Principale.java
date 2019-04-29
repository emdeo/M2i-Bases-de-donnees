package m2i.BDD;

import java.sql.Connection;

public class Principale {

	public static void main(String[] args) {

		// "Java build path" -> "Library" -> "Add external JAR"
		// (mysql-connector-java-8.0.16)
		// Vérifier que Apache et MySQL soient bien démarrés (MySQL en Admin)
//		Connection cnn1 = Connexion.get_instance();
//		System.out.println(cnn1);

		Personne p1 = new Personne(1, "Alpha", "Alice", 64f, 1.67f, Genre.FEMININ);
		Personne p2 = new Personne(2, "Bravo", "Bernard", 92f, 1.75f, Genre.MASCULIN);

		// Je crée un objet de type DAO_Personne et j'utilise la méthode .Create() pour
		// ajouter p1 à la table "dp_formation"
		DAO_Personne dao_p = new DAO_Personne();
//		dao_p.Create(p1);
		dao_p.Create(p2);

	}

}
