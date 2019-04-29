package m2i.BDD;

import java.sql.Connection;
import java.util.ArrayList;

public class Principale {

	public static void main(String[] args) {

		/* 
		 * "Java build path" -> "Library" -> "Add external JAR"
		 * (mysql-connector-java-8.0.16)
		 * V�rifier que Apache et MySQL soient bien d�marr�s (et MySQL en Admin)
		 * 
		 * PAS NECESSAIRE : la classe DAO_Personne permet d�j� de se connecter � la BD
		 */
//		Connection cnn1 = Connexion.get_instance();
//		System.out.println(cnn1);

		/*
		 * Je cr�e un objet de type DAO_Personne
		 */
		DAO_Personne dao_p = new DAO_Personne();

		/*
		 * J'utilise la m�thode .Create() pour ajouter p1 � la table "dp_formation"
		 */
		dao_p.Create(new Personne(4, "Dupont", "Didier", 79, 1.68f, Genre.MASCULIN));

		/* 
		 * J'affiche l'objet Personne dont l'ID est 1
		 */
		Personne p = dao_p.Read(1);
		System.out.println(p);

		/* 
		 * J'affiche tous les objets Personne de la table
		 */
		ArrayList<Personne> lstPers = dao_p.ReadAll();
		System.out.println(lstPers);
		
		/*
		 * Je mets � jour une ligne de la table (je l'identifie � son ID : 3)
		 */
		int update = dao_p.Update(new Personne(3, "Carroll", "Cecile", 69f, 1.87f, Genre.FEMININ));
		System.out.println(update);
		
		/*
		 * Je supprime une ligne de ma table
		 */
		dao_p.Delete(3);
	}

}
