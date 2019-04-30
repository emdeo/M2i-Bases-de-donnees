package m2i.java.projet1;

import java.util.ArrayList;

public class Eleve extends Personne implements ICalcul {
	private int ID_Eleve;
	private ArrayList<Note> lstNotes = new ArrayList<Note>();

	public Eleve(int ID_Eleve, String nom, String prenom, ArrayList<Note> lstNotes) {
		super(nom, prenom);
		this.ID_Eleve = ID_Eleve;
		this.lstNotes = lstNotes;
	}

	public Eleve(int ID_Eleve, String nom, String prenom) {
		super(nom, prenom);
		this.ID_Eleve = ID_Eleve;
	}

	@Override
	public String toString() {
		String lstNotes = "";
		for (Note e : this.lstNotes) {
			lstNotes += e.toString();
		}
		return nom + " " + prenom + " (id " + this.ID_Eleve + ")" + lstNotes;
	}

	public void AjoutNote(Note n) {
		this.lstNotes.add(n);
		n.setID_Eleve(this.ID_Eleve);
	}

	@Override
	public float Moyenne() {
		if (lstNotes.size() == 0)
			return -99;

		float somme = 0f;
		float sommeCoef = 0;

		for (Note elem : lstNotes) {
			somme += elem.getValeur();
			sommeCoef += elem.getCoef();
		}

		return somme / sommeCoef;
	}

	@Override
	public float Moyenne(Matiere mat) {
		if (lstNotes.size() == 0)
			return -99;

		float somme = 0f;
		float sommeCoef = 0;

		for (Note elem : lstNotes) {
			if (elem.getMatiere() == mat) {
				somme += elem.getValeur();
				sommeCoef += elem.getCoef();
			}
		}

		return somme / sommeCoef;
	}

	public void Afficher() {
		System.out.println(this.prenom + " " + this.nom);

		for (Matiere elem : Matiere.values()) {
			System.out.println(elem + "\t" + Moyenne(elem));
		}
	}

	public int getID_Eleve() {
		return ID_Eleve;
	}

	public void setID_Eleve(int iD_Eleve) {
		ID_Eleve = iD_Eleve;
	}

	public ArrayList<Note> getLstNotes() {
		return lstNotes;
	}

	public void setLstNotes(ArrayList<Note> lstNotes) {
		this.lstNotes = lstNotes;
	}

}
