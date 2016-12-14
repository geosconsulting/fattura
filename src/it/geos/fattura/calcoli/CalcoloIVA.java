package it.geos.fattura.calcoli;

public class CalcoloIVA {
	static double iva;	
	private static double IVA_PERCENTAGE=0.21;
	
	public static double getIva() {
		iva = CalcoloImponibile.mensileLordo * IVA_PERCENTAGE;
		return iva;
	}


}
