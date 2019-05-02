package m2i.java.projet1;

public enum Matiere {
	FRANC, MATHS, HIST, SPORT;

	// Renvoie le coefficient correspondant à la matière
	public int getCoef() {
		switch (this.name()) {
		case "FRANC":
			return 3;
		case "MATHS":
			return 1;
		case "HIST":
			return 2;
		case "SPORT":
			return 2;
		default:
			return -1;
		}
	}
}
