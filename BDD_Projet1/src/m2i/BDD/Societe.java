package m2i.BDD;

import java.util.ArrayList;

// DAO_Societe

public class Societe {
	private String _Nom;
	private float _CA;
	private Activites _Activite;
	private ArrayList<Personne> _lstEmployes = new ArrayList<Personne>();

	public Societe(String _Nom, float _CA, Activites _Activite, ArrayList<Personne> _lstEmployes) {
		super();
		this._Nom = _Nom;
		this._CA = _CA;
		this._Activite = _Activite;
		this._lstEmployes = _lstEmployes;
	}
	
	public Societe(String _Nom, float _CA, Activites _Activite) {
		super();
		this._Nom = _Nom;
		this._CA = _CA;
		this._Activite = _Activite;
	}
	
	public void CreerListe(ArrayList<Personne> liste) {
		if (this._lstEmployes.size() == 0)
			this._lstEmployes = liste;
		else
			System.out.println("La liste existe déjà\n");
	}
	
	
	
	
	
	
	
	

	@Override
	public String toString() {
		return "Societe [_Nom=" + _Nom + ", _CA=" + _CA + ", _Activite=" + _Activite + "]";
	}

	public String get_Nom() {
		return _Nom;
	}

	public void set_Nom(String _Nom) {
		this._Nom = _Nom;
	}

	public float get_CA() {
		return _CA;
	}

	public void set_CA(float _CA) {
		this._CA = _CA;
	}

	public Activites get_Activite() {
		return _Activite;
	}

	public void set_Activite(Activites _Activite) {
		this._Activite = _Activite;
	}

	public ArrayList<Personne> get_lstEmployes() {
		return _lstEmployes;
	}

	public void set_lstEmployes(ArrayList<Personne> _lstEmployes) {
		this._lstEmployes = _lstEmployes;
	}

}
