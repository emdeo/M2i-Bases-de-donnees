package m2i.java.bdd;

public class Singleton_Personne {
	private static Personne _instance = null;
	
	// Constructeur
	private Singleton_Personne() {}
	
	// Getter
	public static Personne getInstance(String nom, String prenom) {
		if (_instance == null) {
			_instance = new Personne(nom, prenom); // on instancie notre objet de classe Personne (new)
		}
		return _instance;
	}

}
