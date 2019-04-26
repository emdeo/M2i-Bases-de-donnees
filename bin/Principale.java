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
		 * (N�cessite de t�l�charger mysql-connector-java-8.0.16 et de l'importer :
		 * "Project Properties" -> "Java Build Path" -> "Libraries" -> "ClassPath" ->
		 * "Add External JARs")
		 * 
		 * V�rifier �galement que MySQL tourne bien sur XAMPP
		 */
		String url_films = "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC";
		String url_m2i = "jdbc:mysql://localhost:3306/m2i-formation?serverTimezone=UTC";

		String user = "root";
		String password = "";

		String chSql = "select * from film"; // mes instructions (requ�tes) SQL
//		String chSql_insert = "insert into Personne (ID, Nom, Pr�nom, T�l�phone, Email) values (?, ?, ?, ?, ?)";
		String chSql_update = "update Personne set Nom = ?, Pr�nom = ?, T�l�phone = ?, Email = ? where ID = ?";

		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps_upd = null;
		ResultSet rs = null; // stockera le r�sultat de ma requ�te SQL

		/*
		 * Ex�cuter cette instruction si tout va bien (n�cessaire car sinon lance une
		 * exception)
		 */
		try {

			Connection cnn = DriverManager.getConnection(url_m2i, user, password); // �tablir connection � ma base de
																				// donn�es

			// On cr�e une requ�te SQL
			st = cnn.createStatement();

			/*
			 * R�cup�rer un jeu d'enregistrements (= result set). Prend une requ�te SQL en
			 * param�tre
			 */
//			rs = st.executeQuery(chSql);

//			while (rs.next()) {
//				AfficherSakilaFilms(rs);
//			}

//			ps = cnn.prepareStatement(chSql_insert);
//			ps.setInt(1, 103); // ins�rer l'index d'un param�tre et sa valeur (ici = ID) -> LE DECOMPTE
//								// COMMENCE A 1 !!!!
//			ps.setString(2, "Dupont");
//			ps.setString(3, "Didier");
//			ps.setString(4, "0678915423");
//			ps.setString(5, "didudipont@lol.org");
//			
//			System.out.println(ps.executeUpdate());
//			System.out.println(ps.toString());
			
			ps_upd = cnn.prepareStatement(chSql_update);
			ps_upd.setString(1, "Etans");
			ps_upd.setString(2, "Elise");
			ps_upd.setString(3, "0687945132");
			ps_upd.setString(4, "emm.etans@lol.fr");
			ps_upd.setInt(5, 125);
			
			System.out.println(ps_upd.executeUpdate());
			System.out.println(ps_upd.toString()); // afficher la requ�te SQL

		} // Sinon, ex�cuter cette instruction
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Afficher les donn�es de Personnes (stock�e dans BD m2i-formation)
	public static void AfficherPersonne(ResultSet rs) {
		try {
			// prend un nom de colonne en prm
			int id = rs.getInt("ID");
			String nom = rs.getString("Nom");
			String prenom = rs.getString("Pr�nom");
			String tel = rs.getString("T�l�phone");
			String email = rs.getString("Email");
			int nbVictoires = rs.getInt("NbVictoires");

			// On v�rifie que la BD a bien �t� charg�e
			System.out.println(id + "\t" + nom + "\t" + prenom + "\t" + tel + "\t" + email);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Afficher les de donn�es de Film (stock�e dans BD sakila)
	public static void AfficherSakilaFilms(ResultSet rs) {
		try {
			// prend un nom de colonne en prm
			int id = rs.getInt("film_id");
			String title = rs.getString("title");
			String descr = rs.getString("description");
			int release_year = rs.getInt("release_year");
			int rental_duration = rs.getInt("rental_duration");
			float rental_rate = rs.getFloat("rental_rate");

			// On v�rifie que la BD a bien �t� charg�e
			System.out.println(id + "\t" + title + "\t" + release_year + "\t" + rental_duration + "\t" + rental_rate);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
