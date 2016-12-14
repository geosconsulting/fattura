package it.geos.fattura.calcoli;

public class CalcoloInarcassa extends CalcoloImponibile {
	static double inarcassa;
	
	public static double getInarcassa() {
		inarcassa = CalcoloImponibile.mensileNetto * (CalcoloImponibile.rateRiduzione / 100.0);
		return inarcassa;
	}
}
