package m2i.java.projet1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAO_Note implements IDAO<Note> {

	final static String url = "jdbc:mysql://localhost:3306/projet_ecole?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	public int Instanciate() {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'Note' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;
		String request = "INSERT INTO Note VALUES (?,?,?,?,?,?)";

		ArrayList<Note> listeNotes = new ArrayList<Note>();
		listeNotes.add(new Note(1, 5.5f, LocalDate.now(), Matiere.FRANC, 1));
		listeNotes.add(new Note(2, 13.5f, LocalDate.now(), Matiere.HIST, 2));
		listeNotes.add(new Note(3, 19.5f, LocalDate.now(), Matiere.MATHS, 1));
		listeNotes.add(new Note(4, 11f, LocalDate.now(), Matiere.SPORT, 3));
		
		for (Note n : listeNotes) {
			try {

				PreparedStatement ps = _Cnn.prepareStatement(request);

				ps.setInt(1, n.getID_Note());
				ps.setFloat(2, n.getValeur());
				ps.setInt(3, n.getCoef());
				ps.setDate(4, Date.valueOf(n.getDate())); // Convertir un LOCALDATE en SQL.DATE
				ps.setString(5, n.getMatiere().name());
				ps.setInt(6, n.getID_Eleve());

				// Exécute la requête et enregistre le nombre de modifs
				output = ps.executeUpdate();

			} catch (SQLException error) {
				System.out.println("DAO_Note Instanciate() error: " + error.getMessage() + "\n");
			}
		}

		return output;
	}

	@Override
	public int Create(Note note) {
		int output = -1;
		String request = "INSERT INTO Note VALUES (?,?,?,?,?,?)";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);

			ps.setInt(1, note.getID_Note());
			ps.setFloat(2, note.getValeur());
			ps.setInt(3, note.getCoef());
			ps.setDate(4, Date.valueOf(note.getDate())); // Convertir un LOCALDATE en SQL.DATE
			ps.setString(5, note.getMatiere().name());
			ps.setInt(6, note.getID_Eleve());

			// Exécute la requête et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException error) {
			System.out.println("DAO_Note Create() error: " + error.getMessage() + "\n");
		}

		return output;
	}
	
	@Override
	public int CreateSeveral(ArrayList<Note> listeNotes) {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'Note' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;
		String request = "INSERT INTO Note VALUES (?,?,?,?,?,?)";

		for (Note n : listeNotes) {
			try {

				PreparedStatement ps = _Cnn.prepareStatement(request);

				ps.setInt(1, n.getID_Note());
				ps.setFloat(2, n.getValeur());
				ps.setInt(3, n.getCoef());
				ps.setDate(4, Date.valueOf(n.getDate())); // Convertir un LOCALDATE en SQL.DATE
				ps.setString(5, n.getMatiere().name());
				ps.setInt(6, n.getID_Eleve());

				// Exécute la requête et enregistre le nombre de modifs
				output = ps.executeUpdate();

			} catch (SQLException error) {
				System.out.println("DAO_Note Instanciate() error: " + error.getMessage() + "\n");
			}
		}

		return output;
	}

	@Override
	public Note Read(int id) {
		Note output = null;
		String request = "SELECT * FROM Note WHERE ID_Note = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				float valeur = rs.getFloat("Valeur");
				int coef = rs.getInt("Coef");
				LocalDate date = rs.getObject("Date", LocalDate.class); // Convertir un SQL.DATE en LOCALDATE
				Matiere mat = Matiere.valueOf(rs.getString("Matiere"));
				int id_eleve = rs.getInt("ID_Eleve");

				output = new Note(id, valeur, date, mat, id_eleve);
				output.toString();
			}

		} catch (SQLException error) {
			System.out.println("DAO_Note Read() error: " + error.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public ArrayList<Note> ReadAll() {
		ArrayList<Note> output = new ArrayList<Note>();
		String request = "SELECT * FROM Note";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id_note = rs.getInt("ID_Note");
				float valeur = rs.getFloat("Valeur");
				int coef = rs.getInt("Coef");
				LocalDate date = rs.getDate("Date").toLocalDate(); // Convertir un SQL.DATE en LOCALDATE
				Matiere mat = Matiere.valueOf(rs.getString("Matiere"));
				int id_eleve = rs.getInt("ID_Eleve");

				output.add(new Note(id_note, valeur, date, mat, id_eleve));

			}

		} catch (SQLException error) {
			System.out.println("DAO_Note ReadAll() error: " + error.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public int Update(Note note) {
		int output = -1;
		String request = "UPDATE Note SET Valeur = ?, Coef = ?, Date = ?, Matiere = ?, ID_Eleve = ? WHERE ID_Note = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);

			ps.setFloat(1, note.getValeur());
			ps.setInt(2, note.getCoef());
			ps.setDate(3, Date.valueOf(note.getDate())); // Convertir un LOCALDATE en SQL.DATE
			ps.setString(4, note.getMatiere().name());
			ps.setInt(5, note.getID_Eleve());
			ps.setInt(6, note.getID_Note());

			output = ps.executeUpdate();

		} catch (SQLException error) {
			System.out.println("DAO_Note Update() error: " + error.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public int Delete(int id) {
		int output = -1;
		String request = "DELETE FROM Note WHERE ID_Note = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException error) {
			System.out.println("DAO_Note Delete() error: " + error.getMessage() + "\n");
		}

		return output;
	}
	
	// Vider la table (supprimer toutes les notes)
	public int DeleteAll() {
		int output = -1;
		String request = "DELETE FROM Note";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			output = ps.executeUpdate();

		} catch (SQLException error) {
			System.out.println("DAO_Note DeleteAll() error: " + error.getMessage() + "\n");
		}

		return output;
	}
	
	// Supprimer toutes les notes dont l'ID_Eleve est passé en paramètre
	public int DeleteStudent(int id) {
		int output = -1;
		String request = "DELETE FROM Note WHERE ID_Eleve = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException error) {
			System.out.println("DAO_Note DeleteStudent() error: " + error.getMessage() + "\n");
		}

		return output;
	}

}
