package m2i.BDD;

public class Personne {

	private int _ID_Personne;
	private String _Nom;
	private String _Prenom;
	private float _Poids;
	private float _Taille;
	private Genre _Sexe;

	public Personne(int _ID_Personne, String _Nom, String _Prenom, float _Poids, float _Taille, Genre _Sexe) {
		super();
		this._ID_Personne = _ID_Personne;
		this._Nom = _Nom;
		this._Prenom = _Prenom;
		this._Poids = _Poids;
		this._Taille = _Taille;
		this._Sexe = _Sexe;
	}
	
	

	public int get_ID_Personne() {
		return _ID_Personne;
	}



	public void set_ID_Personne(int _ID_Personne) {
		this._ID_Personne = _ID_Personne;
	}



	public String get_Nom() {
		return _Nom;
	}



	public void set_Nom(String _Nom) {
		this._Nom = _Nom;
	}



	public String get_Prenom() {
		return _Prenom;
	}



	public void set_Prenom(String _Prenom) {
		this._Prenom = _Prenom;
	}



	public float get_Poids() {
		return _Poids;
	}



	public void set_Poids(float _Poids) {
		this._Poids = _Poids;
	}



	public float get_Taille() {
		return _Taille;
	}



	public void set_Taille(float _Taille) {
		this._Taille = _Taille;
	}



	public Genre get_Sexe() {
		return _Sexe;
	}



	public void set_Sexe(Genre _Sexe) {
		this._Sexe = _Sexe;
	}



	public float IMC() {
		return Math.round(this._Poids/(this._Taille * this._Taille)*100f)/100f;
	}
	
	public float PoidsMin() {
		return  Math.round(19*(this._Taille * this._Taille)*100f)/100f;
	}
	
	public float PoidsMax() {
		return  Math.round(25*(this._Taille * this._Taille)*100f)/100f;
	}
	
	public float PoidsIdeal() {
		if(this._Sexe == Genre.MASCULIN)
			return  Math.round(22*(this._Taille * this._Taille)*100f)/100f;
		return  Math.round(21*(this._Taille * this._Taille)*100f)/100f;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personne [_ID_Personne=");
		builder.append(_ID_Personne);
		builder.append(", _Nom=");
		builder.append(_Nom);
		builder.append(", _Prenom=");
		builder.append(_Prenom);
		builder.append(", _Poids=");
		builder.append(_Poids);
		builder.append(", _Taille=");
		builder.append(_Taille);
		builder.append(", _Sexe=");
		builder.append(_Sexe);
		builder.append(", IMC()=");
		builder.append(IMC());
		builder.append(", PoidsMin()=");
		builder.append(PoidsMin());
		builder.append(", PoidsMax()=");
		builder.append(PoidsMax());
		builder.append(", PoidsIdeal()=");
		builder.append(PoidsIdeal());
		builder.append("]");
		return builder.toString();
	}

}
