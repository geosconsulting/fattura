package it.geos.fattura;

import it.geos.fattura.calcoli.CalcoloIVA;
import it.geos.fattura.calcoli.CalcoloImponibile;
import it.geos.fattura.calcoli.CalcoloInarcassa;
import it.geos.fattura.calcoli.CalcoloRitenuta;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class InputDati {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Sezione INPUT
		Scanner input = new Scanner(System.in);				
		
		System.out.println("Numero di giorni?");
		int giorni=0;
		try {
			giorni = input.nextInt();
			if (giorni<1 || giorni>31) {
				System.out.println("Deve essere un numero tra 1 e 31");
				System.exit(0);
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
				System.out.println("Deve essere un numero");
				System.exit(0);
			}			
			
		
		CalcoloImponibile.setGiorni(giorni);		
		input.close();
		System.out.println("INARCASSA(0)/INPS(1)?");		
		try {
			int trattamentoPensionistico = input.nextInt();			
				if (trattamentoPensionistico == 0){
					int riduzione=4;			
					CalcoloImponibile.setRateRiduzione(riduzione);			
				} else if (trattamentoPensionistico == 1) {
					int riduzione=10;			
					CalcoloImponibile.setRateRiduzione(riduzione);
				}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Deve essere 0 o 1");
		}		
			
		System.out.println("Rate giorno?");
		double rateGiorno=0;
		try {
			rateGiorno = input.nextDouble();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, Double> datiStampa = new HashMap<String, Double>();		
		CalcoloImponibile.setRateLordo(rateGiorno);	
		CalcoloImponibile.setDailyRateNetto(rateGiorno);		
		
		datiStampa.put("Mensile Lordo ", CalcoloImponibile.getMensileLordo());
		datiStampa.put("Rate Netto ", CalcoloImponibile.getDailyRateNetto());
		datiStampa.put("Netto ", CalcoloImponibile.getMensileNetto());		
		datiStampa.put("Inarcassa ", CalcoloInarcassa.getInarcassa());				
		datiStampa.put("Imponibile " , CalcoloImponibile.getImponibile());		
		datiStampa.put("Iva " , CalcoloIVA.getIva());		
		datiStampa.put("Ritenuta " , CalcoloRitenuta.getRitenuta());	
		datiStampa.put("Totale " , CalcoloImponibile.getTotale());	
		
		//Sezione OUTPUT
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ITALY);
		
		Set<String> tuttiFrutti= datiStampa.keySet();
		Iterator<String> iteratore= tuttiFrutti.iterator();
		while (iteratore.hasNext()) {
			String titoloDelDato= iteratore.next();
			//String valoreDelDato = myFormatter.format(datiStampa.get(titoloDelDato));
			double valoreDelDato = datiStampa.get(titoloDelDato);
			System.out.println(titoloDelDato + format.format(valoreDelDato));			
		}
											
	}

}
