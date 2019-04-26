package m2i.formation.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principale {

	public static void main(String[] args) {
		// url = "protocole//nom_de_hote:port_de_communication/nom_de_ma_BD"
		// (N�cessite de t�l�charger mysql-connector-java-8.0.16 et de l'importer :
		// "Project Properties" -> "Java Build Path" -> "Libraries" -> "ClassPath" ->
		// "Add External JARs")
		String url = "jdbc:mysql://localhost:3306/m2i-formation?serverTimezone=UTC";

		String user = "root";
		String password = "";

		String chSql = "select * from personne"; // mes instructions (requ�tes) SQL

		Statement st = null;
		ResultSet rs = null; // stockera le r�sultat de ma requ�te SQL

		// ex�cuter cette instruction si tout va bien (n�cessaire car sinon lance une
		// exception)
		try {

			Connection cnn = DriverManager.getConnection(url, user, password); // �tablir connection � ma base de
																				// donn�es

			// On cr�e une requ�te SQL
			st = cnn.createStatement();

			// R�cup�rer un jeu d'enregistrements (= result set). Prend une requ�te SQL en
			// param�tre
			rs = st.executeQuery(chSql);

			while (rs.next()) {
				AfficherDB(rs); // sysout() de tous les �l�ment de la BD pass�e en prm
			}

		} // sinon, ex�cuter cette instruction
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void AfficherDB(ResultSet rs) {
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

}
