package m2i.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAO_Personne implements IDAO<Personne> {

	// Constantes statiques qui n'existent qu'en un seul exemplaire
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	// INSERER UNE LIGNE DANS LA TABLE (= nouvelle personne)
	@Override
	public int Create(Personne p) {
		
		int output = -1;
		String ma_requete = "INSERT INTO Personne (ID_Personne, Nom, Prenom, Poids, Taille, Sexe) VALUE (?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, p.get_ID_Personne());
			ps.setString(2, p.get_Nom());
			ps.setString(3, p.get_Prenom());
			ps.setFloat(4, p.get_Poids());
			ps.setFloat(5, p.get_Taille());
			ps.setString(6, p.get_Sexe().name());

			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return output;
	}

	// RENVOIE L'OBJET DONT L'ID EST EN PARAMETRE
	@Override
	public Personne Read(int id) {

		Personne output = null;
		String ma_requete = "SELECT * FROM Personne WHERE ID_Personne ="+id;
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return output;
	}

	@Override
	public List<Personne> ReadAll() {
		// TODO Auto-generated method stub
		return null;
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
