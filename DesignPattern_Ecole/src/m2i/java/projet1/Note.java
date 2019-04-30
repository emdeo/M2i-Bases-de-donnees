package m2i.java.projet1;

import java.time.LocalDate;

public class Note {
	private int ID_Note;
	private float valeur;
	private int coef;
	private LocalDate date;
	private Matiere matiere;
	private int ID_Eleve;

	public Note(int ID_Note, float valeur, int coef, LocalDate date, Matiere matiere, int ID_Eleve) {
		super();
		this.ID_Note = ID_Note;
		this.valeur = valeur;
		this.coef = coef;
		this.date = date;
		this.matiere = matiere;
		this.ID_Eleve = ID_Eleve;
	}

	public Note(int ID_Note, float valeur, int coef, LocalDate date, Matiere matiere) {
		super();
		this.ID_Note = ID_Note;
		this.valeur = valeur;
		this.coef = coef;
		this.date = date;
		this.matiere = matiere;
	}

	public Note() {
		super();
	}

	@Override
	public String toString() {
		return "\n" + matiere + "\t" + valeur + "/20 (coef " + coef + "), le " + date + " (id_note " + ID_Note
				+ ", id_élève " + ID_Eleve + ")";
	}

	public int getID_Note() {
		return ID_Note;
	}

	public void setID_Note(int iD_Note) {
		ID_Note = iD_Note;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public int getID_Eleve() {
		return ID_Eleve;
	}

	public void setID_Eleve(int iD_Eleve) {
		ID_Eleve = iD_Eleve;
	}

}
