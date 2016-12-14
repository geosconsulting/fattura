package it.geos.fattura.calcoli;

public class CalcoloRitenuta extends CalcoloImponibile {
	static double ritenuta;
	private static double RITENUTA_PERCENTAGE=0.20;

	public static double getRitenuta() {
		ritenuta = CalcoloImponibile.mensileNetto * RITENUTA_PERCENTAGE;
		return ritenuta;
	}
}
