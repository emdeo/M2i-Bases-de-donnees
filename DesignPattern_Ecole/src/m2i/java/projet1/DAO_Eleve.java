package m2i.java.projet1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Eleve implements IDAO<Eleve> {

	final static String url = "jdbc:mysql://localhost:3306/projet_ecole?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);
	
	@Override
	public int Instanciate() {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'Eleve' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;
		String request = "INSERT INTO Eleve VALUES (?,?,?)";

		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		listeEleves.add(new Eleve(1, "Stark", "Alice"));
		listeEleves.add(new Eleve(2, "Baratheon", "Bernard"));
		listeEleves.add(new Eleve(3, "Lannister", "Carole"));
		listeEleves.add(new Eleve(4, "Targaryen", "Didier"));
		
		for (Eleve eleve : listeEleves) {
			try {

				PreparedStatement ps = _Cnn.prepareStatement(request);

				ps.setInt(1, eleve.getID_Eleve());
				ps.setString(2, eleve.getNom());
				ps.setString(3, eleve.getPrenom());

				output = ps.executeUpdate();

				/*
				 * Si l'élève ne possède pas déjà les notes de la table "Note", on ajoute
				 * celles-ci à sa collection de notes
				 */
				DAO_Note daon = new DAO_Note();
				for (Note n : eleve.getLstNotes()) {
//					if (!eleve.getLstNotes().contains(n))
						daon.Create(n);
				}

			} catch (SQLException error) {
				System.out.println("DAO_Eleve Instanciate() error: " + error.getMessage() + "\n");
			}
		}

		return output;
	}
	
	@Override
	public int Create(Eleve eleve) {
		int output = 1;
		String request = "INSERT INTO Eleve VALUES (?,?,?)";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);

			ps.setInt(1, eleve.getID_Eleve());
			ps.setString(2, eleve.getNom());
			ps.setString(3, eleve.getPrenom());

			output = ps.executeUpdate();

			/*
			 * Si l'élève ne possède pas déjà les notes de la table "Note", on ajoute
			 * celles-ci à sa collection de notes
			 */
			DAO_Note daon = new DAO_Note();
			for (Note n : eleve.getLstNotes()) {
//				if (!eleve.getLstNotes().contains(n))
					daon.Create(n);
			}

		} catch (SQLException error) {
			System.out.println("DAO_Eleve Create() error: " + error.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public int CreateSeveral(ArrayList<Eleve> listeEleves) {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'Eleve' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;
		String request = "INSERT INTO Eleve VALUES (?,?,?)";

		for (Eleve eleve : listeEleves) {
			try {

				PreparedStatement ps = _Cnn.prepareStatement(request);

				ps.setInt(1, eleve.getID_Eleve());
				ps.setString(2, eleve.getNom());
				ps.setString(3, eleve.getPrenom());

				output = ps.executeUpdate();

				/*
				 * Si l'élève ne possède pas déjà les notes de la table "Note", on ajoute
				 * celles-ci à sa collection de notes
				 */
				DAO_Note daon = new DAO_Note();
				for (Note n : eleve.getLstNotes()) {
					if (!eleve.getLstNotes().contains(n))
						daon.Create(n);
				}

			} catch (SQLException error) {
				System.out.println("DAO_Eleve Instanciate() error: " + error.getMessage() + "\n");
			}
		}

		return output;
	}
	
	@Override
	public Eleve Read(int id) {

		Eleve output = null;
		String request = "SELECT * FROM Eleve WHERE ID_Eleve = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ID_Eleve = rs.getInt("ID_Eleve");
				String Nom = rs.getString("Nom");
				String Prenom = rs.getString("Prenom");

				output = new Eleve(ID_Eleve, Nom, Prenom);
				output.toString();
			}

		} catch (SQLException error) {
			System.out.println("DAO_Eleve Read() error: " + error.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public ArrayList<Eleve> ReadAll() {

		ArrayList<Eleve> output = new ArrayList<Eleve>();
		String request = "SELECT * FROM Eleve";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int ID_Eleve = rs.getInt("ID_Eleve");
				String Nom = rs.getString("Nom");
				String Prenom = rs.getString("Prenom");

				output.add(new Eleve(ID_Eleve, Nom, Prenom));

			}

		} catch (SQLException error) {
			System.out.println("DAO_Eleve ReadAll() error: " + error.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public int Update(Eleve eleve) {

		int output = -1;
		String request = "UPDATE Eleve SET Nom = ?, Prenom = ? WHERE ID_Eleve = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);

			ps.setString(1, eleve.getNom());
			ps.setString(2, eleve.getPrenom());
			ps.setInt(3, eleve.getID_Eleve());

			output = ps.executeUpdate(); // mettre à jour le nom/prénom de l'élève
			
			DAO_Note daon = new DAO_Note();
			daon.DeleteStudent(eleve.getID_Eleve()); // supprimer toutes les notes de l'ancien élève
			daon.CreateSeveral(eleve.getLstNotes()); // insérer toutes les notes du nouvel élève

		} catch (SQLException error) {

		}
		return output;
	}

	@Override
	public int Delete(int id) {

		int output = -1;
		String delete_student = "DELETE FROM Eleve WHERE ID_Eleve = ?";

		try {

			// Supprimer l'élève dans la table "Eleve"
			PreparedStatement ps = _Cnn.prepareStatement(delete_student);
			ps.setInt(1, id);
			output = ps.executeUpdate();
			
			// Supprimer les notes de l'élève dans la table "Note"
			DAO_Note daon = new DAO_Note();
			daon.DeleteStudent(id);

		} catch (SQLException error) {
			System.out.println("DAO_Eleve Delete() error: " + error.getMessage() + "\n");
		}
		return output;
	}
	
	// Supprimer tous les élèves de la table
	public int DeleteAll() {

		int output = -1;
		String delete_student = "DELETE FROM Eleve";

		try {

			// Supprimer l'élève dans la table "Eleve"
			PreparedStatement ps = _Cnn.prepareStatement(delete_student);
			output = ps.executeUpdate();
			

		} catch (SQLException error) {
			System.out.println("DAO_Eleve DeleteAll() error: " + error.getMessage() + "\n");
		}
		return output;
	}

}
