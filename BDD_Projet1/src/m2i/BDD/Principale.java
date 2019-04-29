package m2i.BDD;

import java.sql.Connection;

public class Principale {

	public static void main(String[] args) {

		// "Java build path" -> "Library" -> "Add external JAR"
		// (mysql-connector-java-8.0.16)
		// V�rifier que Apache et MySQL soient bien d�marr�s (MySQL en Admin)
//		Connection cnn1 = Connexion.get_instance();
//		System.out.println(cnn1);

		Personne p1 = new Personne(1, "Alpha", "Alice", 64f, 1.67f, Genre.FEMININ);
		Personne p2 = new Personne(2, "Bravo", "Bernard", 92f, 1.75f, Genre.MASCULIN);

		// Je cr�e un objet de type DAO_Personne et j'utilise la m�thode .Create() pour
		// ajouter p1 � la table "dp_formation"
		DAO_Personne dao_p = new DAO_Personne();
//		dao_p.Create(p1);
		dao_p.Create(p2);

	}

}
