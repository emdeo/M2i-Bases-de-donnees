package m2i.formation.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principale {

	public static void main(String[] args) {
		/*
		 * url = "protocole//nom_de_hote:port_de_communication/nom_de_ma_BD"
		 * 
		 * (Nécessite de télécharger mysql-connector-java-8.0.16 et de l'importer :
		 * "Project Properties" -> "Java Build Path" -> "Libraries" -> "ClassPath" ->
		 * "Add External JARs")
		 * 
		 * Vérifier également que MySQL tourne bien sur XAMPP
		 */
		String url = "jdbc:mysql://localhost:3306/m2i-formation?serverTimezone=UTC";

		String user = "root";
		String password = "";

		String chSql = "select * from personne"; // mes instructions (requêtes) SQL
		String chSql_insert = "insert into Personne (ID, Nom, Prénom, Téléphone, Email) values (?, ?, ?, ?, ?)";

		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // stockera le résultat de ma requête SQL

		/* 
		 * Exécuter cette instruction si tout va bien (nécessaire car sinon lance une
		 * exception)
		 */
		try {

			Connection cnn = DriverManager.getConnection(url, user, password); // établir connection à ma base de
																				// données

			// On crée une requête SQL
			// st = cnn.createStatement();

			/* 
			 * Récupérer un jeu d'enregistrements (= result set). Prend une requête SQL en
			 * paramètre
			 */
//			rs = st.executeQuery(chSql);
//
//			while (rs.next()) {
//				AfficherDB(rs); // sysout() de tous les élément de la BD passée en prm
//			}

			ps = cnn.prepareStatement(chSql_insert);
			ps.setInt(1, 103); // insérer l'index d'un paramètre et sa valeur (ici = ID) -> LE DECOMPTE
								// COMMENCE A 1 !!!!
			ps.setString(2, "Dupont");
			ps.setString(3, "Didier");
			ps.setString(4, "0678915423");
			ps.setString(5, "didudipont@lol.org");
			
			System.out.println(ps.executeUpdate());
			System.out.println(ps.toString());

		} // Sinon, exécuter cette instruction
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void AfficherDB(ResultSet rs) {
		try {
			// prend un nom de colonne en prm
			int id = rs.getInt("ID");
			String nom = rs.getString("Nom");
			String prenom = rs.getString("Prénom");
			String tel = rs.getString("Téléphone");
			String email = rs.getString("Email");
			int nbVictoires = rs.getInt("NbVictoires");

			// On vérifie que la BD a bien été chargée
			System.out.println(id + "\t" + nom + "\t" + prenom + "\t" + tel + "\t" + email);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
