package it.geos.fattura.calcoli;
/** 
 * @author  Fabio
 */
public class CalcoloImponibile {
	
	private static int giorni;
	private static double dailyRateLordo;
	private static double dailyRateNetto;	
	static int rateRiduzione;
	static double mensileLordo;
	static double mensileNetto;
	private static double imponibile;
	private static double totale;
	
	public CalcoloImponibile() {
		CalcoloImponibile.giorni=0;
		CalcoloImponibile.rateRiduzione=4;		
	}
	
	//SETTERS
	public static void setGiorni(int giorni) {
		CalcoloImponibile.giorni = giorni;
	}
	
	public static void setRateRiduzione(int riduzione) {
		CalcoloImponibile.rateRiduzione = riduzione;
	}
	
	public static void setRateLordo(double lordo) {
		CalcoloImponibile.dailyRateLordo = lordo;		
	}	
	
	public static double setDailyRateNetto(double lordo) {
		double divisore = 100.0 / (100.0 + rateRiduzione);
		double netto=CalcoloImponibile.dailyRateNetto = lordo * divisore;	
		return netto;
	}	
	
	//GETTERS
	public static double getDailyRateNetto() {
		return dailyRateNetto;
	}
	
	public static double getImponibile() {
		imponibile = mensileNetto + CalcoloInarcassa.inarcassa;
		return imponibile;
	}
		
	public static double getMensileLordo() {
		mensileLordo = giorni * dailyRateLordo;
		return mensileLordo;
	}	
	
	public static double getMensileNetto() {
		mensileNetto = giorni * dailyRateNetto;
		return mensileNetto;
	}

	public static double getTotale() {
		totale = mensileLordo + CalcoloIVA.iva - CalcoloRitenuta.ritenuta;
		return totale;
	}
	
	
}
