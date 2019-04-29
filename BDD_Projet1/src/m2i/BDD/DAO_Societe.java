package m2i.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Societe implements IDAO<Personne> {
	
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// On se connecte à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Create(Personne obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Personne Read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Personne> ReadAll() {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de données
			while (rs.next()) {
				int id_Personne = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id_Personne, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("Read() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public int Update(Personne obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
