package m2i.java.bdd;

public class Principale {

	public static void main(String[] args) {
		// Le code ci-dessous crée une seule instance de la classe Singleton_Personne
		// On ne peut créer d'autre instance (cf. p2 = adresse et attributs inchangés)
		
		Personne p1 = Singleton_Personne.getInstance("Alpha", "Alice");
		System.out.println(p1);
		p1.Afficher();

		Personne p2 = Singleton_Personne.getInstance("Beta", "Bernard");
		System.out.println(p2);
		p2.Afficher();
	}

}
