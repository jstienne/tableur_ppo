package noyau;

public class Case {
	String colonne;
	int ligne;
	double valeur;
	OperationBinaire gauche, droite;

	public Case(String colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
	}

	//TODO : un autre constructeur prenant la veleur en plus ? 
	
	double valeur() {
		//TODO
		return 0;
	}
	
	void fixerValeur(double x) {
		//TODO		
	}
	
	void setFormule(Formule f) {
		//TODO
	}
	
}
